//import com.sun.org.apache.xpath.internal.compiler.Keywords;
//
//public class Mutator_2 extends Tuple {
//	
//	//variables
//	static int 	numberOfGenerations = 0;
//	static int 	numberOfMutations = 0;
//	public static int A;
//	public static double mutation_amount;
//	public static int size_of_mutated_population;
//	
//	public static Tuple mutator(Tuple a, String mutationtype) {
//		double amount = 0.05;
//		numberOfMutations++; 
//		numberOfGenerations= numberOfMutations/size_of_mutated_population;
//		
//		String[] keyword_strings = {"standard", "decreasing", "other"};
//		
//		//mutationtype 1
//		if (mutationtype == "standard") {
//			
//			for(int i = 0; i < vector.length; i++){
//				vector[i] += amount*(2*rand.nextDouble() - 1);
//			
//			}
//					
//		}
//		
//		//mutation type 2
//		if (mutationtype == "decreasing") {
//			double scalor;
//			scalor = numberOfGenerations;
//			for (int i = 0; i < vector.length; i++){
//				vector[i] +=  (amount*(2*rand.nextDouble() - 1))/scalor;
//			}
//			
//			
//		}
//	
//		//mutation type 3
//		if (mutationtype == "other") {
//			for (int i = 0; i < vector.length; i++){
//				double random_number = Math.random(); 
//				double mu = vector[i];//not sure why this is useful, ... 
//				double sigma = 20.0; // now set to some relatively random amount, should be made dynamic on basis of total individual / population variation, etc
//				
//				//highest chance small mutation
//				double A = sigma*(rand.nextDouble()-1); 			// chance for one standard deviation left or right of mean
//							
//				//smaller chance middle mutation
//				double B = (sigma)*(rand.nextDouble()-1) - 1.5* sigma ;			//two sd's left of mean  
//				double BB = (sigma)*(rand.nextDouble()-1) + 1.5* sigma ;		//two sd's right of mean
//				//smallest chance large mutation
//				double C = (sigma) * (rand.nextDouble()-1) - 2.5*sigma;		// three sd's
//				double CC = (sigma) * (rand.nextDouble()-1) + 2.5*sigma;	// three sd's
//				
//				// 68.2%
//				if (random_number < .682){
//					mutation_amount = A;
//				}
//				// 27.2%
//				if (random_number >= .682 && random_number < .954){
//					if (rand.nextBoolean()){
//						mutation_amount = B;
//					}
//					else{
//						mutation_amount = BB;
//					}
//				}
//				// 4.6%
//				if (random_number >= .954){
//					if (rand.nextBoolean()){
//						mutation_amount = C;
//					}
//					else{
//						mutation_amount = CC;
//					}
//												
//				}
//				
//				vector[i] +=  vector[i] + mutation_amount;
//			
//			}
//			
//			//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//			//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//			//define some error output for wrong keyword specification::
//			//define some error output for wrong keyword specification::
//			for (int i=0; i<keyword_strings.length;i = i+1){
//				if (keyword_strings[i]== mutationtype){
//					A = 1;
//					}
//				else{
//					A = 0;
//				}
//			}
//			
//			if (A==0){
//				System.out.println("specify mutation type with keywords:" + keyword_strings);
//			
//			}
//			
//		return new Tuple(vector);
//	
//		
////-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------		
//	}//closing bracket for mutator method
//
//	}//?
//	}//closing bracket class
//	
//	
