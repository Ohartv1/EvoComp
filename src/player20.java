import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;
import java.util.Random;
import org.vu.contest.ContestEvaluation;
import org.vu.contest.ContestSubmission;

public class player20 implements ContestSubmission{

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
    ArrayList<Tuple> tuples;
    
    Combinator combinator = new CombineRandomWeightedCrossover();
    Selector   selector   = new SelectTopN();
    Mutator    mutator    = new MutateAddGaussian(0.1);
    
    //// settings
    static int initial; // initial population
    static int recombine; // # of individuals to recombine
	
    // This method is called when you press Run in Eclipse
	// It creates an instance of player20, gives it a
	// ContestEvaluation object and calls testrun()
	public static void main(String[] args) {	
		player20 sub = new player20();
		sub.setEvaluation(new RastriginEvaluation());
		sub.run();		
	}

	// Work in progress, a simple evolutionary algorithm
	// is implemented, but it's probably not perfect.
	public void run() {
		
		// Initialize the population
		tuples = new ArrayList<Tuple>();
		for(int i = 0; i < initial; i++){
			Tuple t = new Tuple();
			t.evaluate(eval);			
			evals++;
			tuples.add(t);
		}
		Collections.sort(tuples, Collections.reverseOrder());
		System.out.println(initial + " vectors initialized.");
		System.out.println("Top 10:");
		for(int i = 0; i < 10; i++){
			System.out.println(tuples.get(i).toString());
		}
				
		// Run the algorithm while we are allowed to
		while(evals < max_evals){
			System.out.println();
			System.out.println("Evals: " + formatter3.format(evals) 
					+ ". Recombining the best " + recombine +"...");
			for(int i = 0; i < recombine; i++){
				for(int j = 0; j < recombine; j++){		
					if(evals < max_evals){
						Tuple combination = combinator.combine(
								tuples.get(i), tuples.get(j));	
						mutator.mutate(combination);
						combination.evaluate(eval);
						evals++;
						tuples.add(combination) ;
					}
				}
			}
			
			Collections.sort(tuples, Collections.reverseOrder());
			System.out.println("Top 10:");
			for(int i = 0; i < 10; i++){
				System.out.println(tuples.get(i).toString());
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
        
        initial = Math.round((float)0.01*max_evals);
        recombine = Math.round((float)0.001*max_evals);
        
		// Property keys depend on specific evaluation
		// E.g. double param = Double.parseDouble(props.getProperty("property_name"));
        boolean isMultimodal = Boolean.parseBoolean(props.getProperty("Multimodal"));
        boolean hasStructure = Boolean.parseBoolean(props.getProperty("Regular"));
        boolean isSeparable = Boolean.parseBoolean(props.getProperty("Separable"));

		// Do sth with property values, e.g. specify relevant settings of your algorithm
        if(isMultimodal){
            // Do sth
        }else{
            // Do sth else
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
			eval.evaluate(t);
			int percentage = 100*Math.round(evals/max_evals);
			if(percentage % 5 == 0){
				double[] sds = gen_sd();
				System.out.println(percentage + "of evals used, standard deviations:");
				System.out.println( sds.toString() );
				System.out.println("Average standard deviation: " + Statistics.getMean(sds));				
			}
			
			
			
			
			
		}
	}
	
	public double[] gen_sd(){
		double[] sds = new double[10];
		for(int i = 0; i < 10; i++){
			double[] temp = new double[tuples.size()];
			for(int j = 0; j < tuples.size(); j++){
				temp[j] = tuples.get(j).vector[i];
			}
			sds[i] = Statistics.getStdDev(temp);
		}
		return sds;
	}
		
}
