import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import org.vu.contest.ContestEvaluation;

public class tests {

	static ContestEvaluation eval = new SphereEvaluation();
	static Random rand = new Random();
	static int n = 100;
	static NumberFormat formatter  = new DecimalFormat("+#0.0;-#0.0");
	static NumberFormat formatter2 = new DecimalFormat("+#0.0;-#0.0");
	
	public static class Tuple implements Comparable<Tuple>{ 
		public double[] vector;
		public double fitness;
		
		 public Tuple(double[] vector) { 
		    this.vector = vector;
		    this.fitness = Double.MIN_VALUE;
		  }
		 
		 public Tuple(){
			this.vector = new double[10];
			this.fitness = Double.MIN_VALUE;
		 }
		 
		 public void evaluate(){
			 this.fitness = (Double) eval.evaluate(vector);
		 }
		 
		 public String toString(){
			 String vectorstring = "";
			 for(int i = 0; i < vector.length; i++){
				 vectorstring = vectorstring + formatter.format(vector[i]) + ",";
			 }
			 vectorstring = vectorstring.substring(0,vectorstring.length() - 1);			 
			 return "{" + vectorstring + "} : " + formatter2.format(fitness) +".";				     
		 }

		public int compareTo(Tuple other) {
			if(this.fitness == other.fitness){
				return 0;
			}
			if(this.fitness > other.fitness){
				return 1;
			}
			return -1;
		}
		
		// add a random amount from (-amount , amount)
		public void mutate(double amount){
			for(int i = 0; i < vector.length; i++){
				vector[i] += amount*(2*rand.nextDouble() - 1);
			}
		}
		
		public Tuple combineWith(Tuple other){
			Tuple combination = new Tuple();
			for(int i = 0; i < vector.length; i++){
				if(rand.nextBoolean()){
					combination.vector[i] = this.vector[i];
				} 
				else{
					combination.vector[i] = other.vector[i];
				}				
			}
			return combination;
		}
	} 
	
	public static void main(String[] args) {		
				
		Tuple[] tuples = new Tuple[n];
		for(int i = 0; i < n; i++){
			tuples[i] = new Tuple();
			for(int j = 0; j < 10; j++){
				tuples[i].vector[j] = 10*rand.nextDouble() - 5;						
			}
			//System.out.println(i);
			tuples[i].evaluate();
			//System.out.println(tuples[i].toString());
		}
		Arrays.sort(tuples, Collections.reverseOrder());
		System.out.println(n + " vectors initialized.");
		System.out.println("Top 10:");
		for(int i = 0; i < 10; i++){
			System.out.println(tuples[i]);
		}
		
		System.out.println();
		System.out.println("Recombining the best 10");
		int k = tuples.length;
		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 10; j++){
				tuples[k] = tuples[i].combineWith(tuples[j]);
			}
		}
		
		Arrays.sort(tuples, Collections.reverseOrder());
		System.out.println("Top 10:");
		for(int i = 0; i < 10; i++){
			System.out.println(tuples[i]);
		}
		
	}
		
		
}
