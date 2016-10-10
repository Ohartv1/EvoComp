import java.util.ArrayList;
import java.util.Random;

public class CombineLocal_distance {
	
	public ArrayList<Tuple> combinelist(ArrayList<Tuple> l, Combinator combinator) {
		
		ArrayList<Tuple> returnList = new ArrayList<Tuple>();
		
		//bedenk hier iets leuks, euh, ... a neutrino enters a bar...
		int scalor = 10;
		ArrayList<Tuple> subsection = new ArrayList<Tuple>();
		//Tuple[] subsection = new Tuple[10];
				
		//selectie van 10 tuples / individuals die gaan recombineren:
			
		for (int index0 = 0 ; index0 < l.size()/10 ; index0++){
					
			//herhaal dit in a for loop voor total_population_size / 10 ==> deze for loop nog te schrijven 
			for (int i=0;i<scalor;i++){
				subsection.add(l.get(i+(10*index0)));
				}
			
			//this will be the tuple that has to be added to the returned list
			Tuple empty_child1_ = new Tuple();
				
			
			for (int index1 = 0; index1 < 10; index1++){
				Tuple selected_tuple1 = subsection.get(index1);
				double[] total_distances_one_comparison = new double[10];
				//select second tuple to combing first with, compare to make sure we don't compare to each other:
				for (int index2 = 0; index2 < 10; index2++){
					Tuple selected_tuple2 = subsection.get(index2);
					if (selected_tuple2 != subsection.get(index1)) {
						//calculate difference score of the single elements of the vectors:
						double[] list_distances_squared = new double[10]; //empty list to hold the distances
						for (int index3 = 0; index3<10; index3++){
							double A = Math.pow((selected_tuple1.vector[index3] - selected_tuple2.vector[index3]), 2);
							list_distances_squared[index3] = A;					
						}
						//calculate the total distance for single pair:
						double summ = 0.0;
						for (int index4 = 0; index4<10;index4++){
							summ = summ + list_distances_squared[index4];
						//	return summ;?? needed?					
						}
									
					//put all the summs in a big list for a single vector + select the smallest and recombine
					total_distances_one_comparison[index1] = (summ);
					}//end of if statement
					
					
					
				//calculate the minimal distance from the 'total_distances_one_comparison' list:
				double minimum = 0;
				int index = 0;
				for (int index5 = 0 ; index5 <10 ;index5++){
					double comparison_value = total_distances_one_comparison[index5];
					if (comparison_value < minimum);
						minimum = comparison_value;
						index = index5;							
				}	
				//these are the selected vectors to recombine::
				//subsection[index] && subsection[index1];
				Tuple parent1 = subsection.get(index); 
				Tuple parent2 = subsection.get(index1);
							
				//recombine vector 1 with minimal vector into 1 child:
				for (int index6 = 0; index6<0; index6++){
					Random nr = new Random();
					int A = nr.nextInt(2);
					if (A==1){
						empty_child1_.vector[index6] = parent1.vector[index];
						}
					if (A==0) {
						empty_child1_.vector[index6] = parent2.vector[index1];
						}
					
					}//for loop index 6 (just above here^^)
				}//for loop index2, right at the top of the screen 	
									
				}//for loop index1
		
			returnList.add(empty_child1_); //where to put this statement? note the brackets
		
		}//for loop index0 = loops over different subsections of size 10
			 
	return returnList;
	
	} //close the "method"===>>>>  	 public ArrayList<Tuple> combinelist(ArrayList<Tuple> l, Combinator combinator)	
	

}//close class


//
// .. and he asks how much for a drink, bartender says, "for you, no charge" 


