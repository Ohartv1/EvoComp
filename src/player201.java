import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import org.vu.contest.ContestEvaluation;
import org.vu.contest.ContestSubmission;
import java.io.FileWriter;
import java.io.IOException;

public class player201 implements ContestSubmission{

	// declaration of variables
	static ContestEvaluation eval;
	static Random rand = new Random();
	static NumberFormat formatter  = new DecimalFormat("+#0.0;-#0.0");
	static NumberFormat formatter2 = new DecimalFormat("+#0.0000;-#0.0000");
	static NumberFormat formatter3 = new DecimalFormat("#00000");
	static int max_evals;
	static int evals = 0;
	static boolean isMultimodal;
    static boolean hasStructure;
    static boolean isSeparable;
    ArrayList<Tuple>  tuples;
    static boolean testing = true;
     
    Combinator combinator = new CombineRandomWeightedCrossover();
    Selector   selector   = new RankSelect();
    Mutator    mutator    = new MutateAddGaussian(0.5);
    ListCombinator lcombinator = new allCombine();
    ListMutator lmutator = new mutateList();

    
    // needed for evaluation 
    int count = 0;
    int mutation_increased = 0;
    int added_individuals  = 0;
    ArrayList<Double> progress = new ArrayList<Double>();
    ArrayList<Double> speed    = new ArrayList<Double>();
    ArrayList<Double> mean_sd  = new ArrayList<Double>();
    ArrayList<Integer> mut_inc = new ArrayList<Integer>();
    ArrayList<Integer> add_ind = new ArrayList<Integer>();
    ArrayList<Double> maximum = new ArrayList<Double>();
        
    //// settings
    static int initial; // initial population
    static int recombine; // # of individuals to recombine
	
    // This method is called when you press Run in Eclipse
	// It creates an instance of player20, gives it a
	// ContestEvaluation object and calls testrun()
	public static void main(String[] args) {	
		testing = true;
		player201 sub = new player201();
		sub.setEvaluation(new RastriginEvaluation());
		sub.run();		
	}

	// Work in progress, a simple evolutionary algorithm
	// is implemented, but it's probably not perfect.
	public void run() {
		ArrayList<Tuple> parentSelection = new ArrayList<Tuple>();
		ArrayList<Tuple> survivorSelection = new ArrayList<Tuple>();
		ArrayList<Tuple> happyChildren = new ArrayList<Tuple>();
		ArrayList<Tuple> sadChildren = new ArrayList<Tuple>();
		tuples = new ArrayList<Tuple>();
		
		int numberOfParents = 50;
		int numberOfChildren = 100;
		
		
		// Initialize the population
		for(int i = 0; i < initial; i++){
			Tuple t = new Tuple();
			Evaluate(t);	
			survivorSelection.add(t);
		}

		
		System.out.println("Start");
		System.out.println("initial population: " + initial);
		System.out.println("number of parents: " + numberOfParents);
		System.out.println("number of selected children: " + numberOfChildren);
		
		// Run the algorithm while we are allowed to		
		while(evals < max_evals){
			
			// select parents
			parentSelection.addAll(selector.select(survivorSelection, numberOfParents));
			sadChildren.clear();
			survivorSelection.clear();	
			
			// have some children
			happyChildren.addAll(lcombinator.combinelist(parentSelection, combinator));
			parentSelection.clear();			
			
			// mutate the offspring
			sadChildren.addAll(lmutator.mutatelist(happyChildren, mutator));
			happyChildren.clear();			
			
			// check fitness
			for(Tuple t : sadChildren){
				Evaluate(t);
			}
			
			// select survivors
			survivorSelection.addAll(selector.select(sadChildren, numberOfChildren));
			
			tuples.addAll(survivorSelection);
			
		}

		System.out.println("number of combine population: " + sadChildren.size());
		System.out.println("Evals: " + formatter3.format(evals));
		System.out.println();
		Collections.sort(tuples, Collections.reverseOrder());
		System.out.println("Top 3:");
		for(int i = 0; i < 3; i++){
			System.out.println(tuples.get(i).toString());
		}
		
		
		
		// if we're testing (i.e. running locally)
		// generate result files and plot them with Python
		if(testing){
			writeToFile(progress, "progress.txt" );	
			writeToFile(speed,    "speed.txt"    );
			writeToFile(mean_sd,  "mean_sd.txt"  );
			writeToFile(add_ind,  "add_ind.txt"  );
			writeToFile(mut_inc,  "mut_inc.txt"  );
			writeToFile(maximum,  "maximum.txt"  );

			try {
				Runtime.getRuntime().exec("python plot_progress.py");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
		
	// This method is called by the test system of the VU
	// We will receive a ContestEvaluation object through this
	// method, that will gives us the opportunity to request
	// some basic properties of the contest function.
	public void setEvaluation(ContestEvaluation evaluation) {
		eval = evaluation;
		
		Properties props = evaluation.getProperties();
        
        max_evals = Integer.parseInt(props.getProperty("Evaluations"));
        
        initial = Math.round((float)0.1*max_evals);
        recombine = Math.round((float)0.001*max_evals);
        
		// Property keys depend on specific evaluation
		// E.g. double param = Double.parseDouble(props.getProperty("property_name"));
        boolean isMultimodal = Boolean.parseBoolean(props.getProperty("Multimodal"));
        boolean hasStructure = Boolean.parseBoolean(props.getProperty("Regular"));
        boolean isSeparable = Boolean.parseBoolean(props.getProperty("Separable"));

		// Relevant settings to adapt to different function properties.
        if(isMultimodal){
            //
            combinator = new CombineRandomWeightedCrossover();
            selector   = new SelectTopN();
            mutator    = new MutateAddGaussian(0.1);
            
            //ect.....
        	
        }else{
            //
        	
        }
        
        if(hasStructure){
            //
        	
        }else{
            //
        }
        
        if(isSeparable){
            // 

        }else{
            // 
        }	
	}

	// We use a random number generator in our code a few times
	// If you set the seed of this RNG to the same value, we will
	// get the same result. Maybe they use this to detect cheating
	// or something.
	public void setSeed(long seed) {
		rand.setSeed(seed);		
	}
	
	// Evaluate a Tuple. This is also where we keep track of our
	// progress and diversity.
	public void Evaluate(Tuple t){
		if(evals < max_evals){
			t.evaluate(eval);
			evals++;
			progress.add(t.fitness);

			double best = best();
			maximum.add(best);
			
			if(evals > initial + recombine){
				double curr_mean_sd = genMeanSd();
				mean_sd.add(new Double(curr_mean_sd));					
				List<Double> current  = 
						progress.subList(progress.size()-100, progress.size());
				List<Double> previous = 
						progress.subList( progress.size() - 300, progress.size() - 200);
				Collections.sort(current);
				Collections.sort(previous);
				List<Double> currenttop  = current.subList(0, 50);
				List<Double> previoustop = previous.subList(0,50);
				double current_avg_progress = Statistics.getMean(currenttop);
				double previous_avg_progress = Statistics.getMean(previoustop);
				double currentspeed = current_avg_progress - previous_avg_progress;				
				speed.add(currentspeed);
				
				// if were progressing too slowly				
//				if(currentspeed < 1 && (count++ % 1000 == 0 )){
//					
//					// if diversity is too small, add individuals (max. 1 time)
//					if(curr_mean_sd < 0.1 && added_individuals < 1){
//						added_individuals++;
//						System.out.println("Standard deviation: " + formatter2.format(curr_mean_sd));
//						addIndividuals();
//					}
//					
//					
//					// if our best is pretty bad, increase mutation (max. 5 times)
//					if(best < 7 && mutation_increased < 5){
//						System.out.println("Speed: " + formatter2.format(currentspeed)
//								+ ", best score: "+ formatter2.format(best) + " increasing mutation");						
//						increaseMutation();
//					}
//					// if our best is still pretty bad, add some individuals
//					if(best < 7 && mutation_increased >= 5){
//						addIndividuals();
//					}
//					// if we used 50% of iterations and we're still not
//					// close to 10, create some new individuals
//					if(best < 9.5 && (evals/max_evals > 1/2 && added_individuals < 5)){
//						System.out.println("Going pretty well. " + formatter2.format(evals/max_evals)
//								+ "% of evals done, score: " + formatter.format(best));						
//						addIndividuals();
//					}					
//					// if we're almost out of iterations and have a good score
//					// reduce mutation
//					if(evals/max_evals > 5/10 && best > 9.3){
//						scaleMutation(0.2);
//					}
//
//				}	
				
			} else{
				mean_sd.add(Double.NaN);
				speed.add(Double.NaN);
			}
//			
//			double percentage = 100* (evals / max_evals);
//			if(evals % 500 == 0){
//				double[] sds = gen_sd();
//				String percentagestring = Integer.toString((int)Math.round(percentage));
//				System.out.println(percentagestring + "% of evals used, standard deviations:");
//				System.out.println( Arrays.toString(sds));
//				System.out.println("Average standard deviation: " + 
//									Double.toString(Statistics.getMean(sds)));	
//				System.out.println();
//			}		
		}
	}
			
	
	public double genMeanSd(){
		return Statistics.getMean(genSD());
	}
	
	public void increaseMutation(){
		scaleMutation(1.5);
	}
	
	public void scaleMutation(double s){
		mut_inc.add(evals);
		System.out.println("Increasing mutation by factor " + s);
		mutator.scale(s);
		mutation_increased++;
	}
	
	public void addIndividuals(){
		addIndividuals(Math.round(initial));
	}

	public void addIndividuals(int n){
		add_ind.add(evals);
		ArrayList<Tuple> new_tuples = new ArrayList<Tuple>();
		System.out.println("Creating " + n +" new individuals.");
		for(int i = 0; i < Math.round(n); i++){
			Tuple t_new = new Tuple();
			t_new.evaluate(eval);
			evals++;
			new_tuples.add(t_new);			
		}
		Collections.sort(new_tuples,Collections.reverseOrder());
		System.out.println("Recombining the best " + recombine/2 +" new individuals");
		for(int i = 0; i < recombine/2; i++){
			for(int j = 0; j < i; j++){	
				if(i != j && evals < max_evals){
					Tuple combination = combinator.combine(
							new_tuples.get(i), new_tuples.get(j));
					combination.evaluate(eval);
					evals++;
					tuples.add(combination) ;						
				}
			}
		}
		Collections.sort(tuples, Collections.reverseOrder());
	}
	
	public double best(){
		if(tuples.size() > 0){
			ArrayList<Tuple> templist = tuples;
			Collections.sort(templist, Collections.reverseOrder());
			return templist.get(0).fitness;
		} else{
			return Double.NaN;
		}		
	}
	
	public double[] genSD(){
		double[] sds = new double[10];
		for(int i = 0; i < 10; i++){
			double[] temp = new double[tuples.size()/10];
			for(int j = 0; j < 0.01*tuples.size(); j += 1){
				temp[j] = tuples.get(j).vector[i];
			}
			sds[i] = Statistics.getStdDev(temp);
		}
		return sds;
	}
	

	public void writeToFile(ArrayList<?> list, String filename){
		try{
			FileWriter writer = new FileWriter(filename); 
			for(Object d : list){
				writer.write(d.toString());
				writer.write("\n");
			}	
			writer.close();
		} 
		catch(IOException e){
			System.out.println(e.getMessage());
		}
	}
		
}
