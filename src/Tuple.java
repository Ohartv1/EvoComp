import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;

import org.vu.contest.ContestEvaluation;

// Tuple class
// This nested class represents an individual
// Every individual has a genotype (vector) and 
// a fitness (fitness).
public class Tuple implements Comparable<Tuple>{ 
	public double[] vector;
	public double fitness;
	static NumberFormat formatter  = new DecimalFormat("+#0.0;-#0.0");
	static NumberFormat formatter2 = new DecimalFormat("+#0.0000;-#0.0000");
	static Random rand = new Random();
	
	// Constructor of the class
	 public Tuple(double[] vector) { 
	    this.vector = vector;
	    this.fitness = Double.MIN_VALUE;
	  }
	 
	 // Constructor of the class
	 public Tuple(){
		this.vector = new double[10];
		this.fitness = Double.MIN_VALUE;
		this.randomise();
	 }
	 	 
	 public String toString(){
		 String vectorstring = "";
		 for(int i = 0; i < vector.length; i++){
			 vectorstring = vectorstring + formatter.format(vector[i]) + ",";
		 }
		 vectorstring = vectorstring.substring(0,vectorstring.length() - 1);			 
		 return "{" + vectorstring + "} : " + formatter2.format(fitness);				     
	 }

	// needed to be able to sort a list of Tuples
	public int compareTo(Tuple other) {
		if(this.fitness == other.fitness){
			return 0;
		}
		if(this.fitness > other.fitness){
			return 1;
		}
		return -1;
	}
	
//	public void setFitness(Double f){
//		if(f != null){
//			this.fitness = f;
//		}
//	}
	
	public void evaluate(ContestEvaluation eval){
		Object temp = eval.evaluate(vector);
		if(temp != null){
			fitness = (double) temp;
		}
	}
	
	// REMOVE because we now use the Mutator classes instead
//	// add a random amount from (-amount , amount) to every element in vector
//	public void mutate(double amount){
//		for(int i = 0; i < vector.length; i++){
//			vector[i] += amount*(2*rand.nextDouble() - 1);
//		}
//	}
	
		
	// Fill the vector with random numbers from (-5, 5)
	public void randomise(){
		for(int i = 0; i < vector.length; i++){
			vector[i] = 10*rand.nextDouble() - 5;						
		}
	}
} 
