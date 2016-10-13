import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;
import java.util.Random;
import org.vu.contest.ContestEvaluation;
import org.vu.contest.ContestSubmission;

public class player200 implements ContestSubmission{

	// declaration of variables
	static ContestEvaluation eval;
	static Random rand = new Random();
	static NumberFormat formatter  = new DecimalFormat("+#0.0;-#0.0");
	static NumberFormat formatter2 = new DecimalFormat("+#0.0000;-#0.0000");
	static NumberFormat formatter3 = new DecimalFormat("#00000");
	static int max_evals;
	static int evals = 0;
	ArrayList<Tuple> tuples;
	static boolean isMultimodal;
    static boolean hasStructure;
    static boolean isSeparable;
    
    Combinator combinator = new CombineRandomWeightedCrossover();
    Selector   selector   = new SelectTopN();
    Mutator    mutator    = new MutateAddGaussian(0.1);
    ListCombinator lcombinator = new locationCombine();
    ListMutator lmutator = new mutateList();
    
    //// settings
    static int initial; // initial population
    static int recombine; // # of individuals to recombine
	
    // This method is called when you press Run in Eclipse
	// It creates an instance of player20, gives it a
	// ContestEvaluation object and calls testrun()
	public static void main(String[] args) {	
		player200 sub = new player200();
		sub.setEvaluation(new RastriginEvaluation());
		sub.run();		
	}

	// Work in progress, a simple evolutionary algorithm
	// is implemented, but it's probably not perfect.
	
	
	public void run() {
		
		// Initialize the population
		tuples = new ArrayList<Tuple>();
		
//		for(int i = 0; i < initial; i++){
//			Tuple t = new Tuple();
//			Evaluate(t)			
//			tuples.add(t);
//		}
				
		// Needed selection
		ArrayList<Tuple> parentSelection = new ArrayList<Tuple>();
		ArrayList<Tuple> survivorSelection = new ArrayList<Tuple>();
		ArrayList<Tuple> happyChildren = new ArrayList<Tuple>();
		ArrayList<Tuple> sadChildren = new ArrayList<Tuple>();
		
		// Run the algorithm while we are allowed to		
		while(evals < max_evals){
			
			// select parents
			parentSelection.addAll(selector.select(tuples, initial));
			// have some children
			happyChildren.addAll(lcombinator.combinelist(parentSelection, combinator));
			// mutate the offspring
			sadChildren.addAll(lmutator.mutatelist(happyChildren, mutator));
			// check fitness
//			for(Tuple t : sadChildren){
//				Evaluate(t);
			}
			// select survivors
			survivorSelection.addAll(selector.select(happyChildren, initial));
			// to count number of evaluations
			evals++;			
			System.out.println("hoi");
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
        boolean isSeparable  = Boolean.parseBoolean(props.getProperty("Separable"));

		// Relevant settings to adapt to different function properties.
        if(isMultimodal){
            //
            combinator = new CombineRandomWeightedCrossover();
            selector   = new SelectTopN();
            mutator    = new MutateAddGaussian(0.1);
            
            //ect.....
        	
        }else{
            selector   = new RankSelect();
        	
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
		
		
}
