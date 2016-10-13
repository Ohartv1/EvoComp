import java.util.Random;

public class CombineTwoPointCrossover implements Combinator{

	Random rand = new Random();
	
	@Override
	public Tuple combine(Tuple a, Tuple b) {
		int N = rand.nextInt(a.vector.length);
		int N2 = rand.nextInt(a.vector.length);
		double[] crossover = new double[a.vector.length];
		
		//add if statement to check size of n1 and 2
		
		if (N < N2){
			for(int i = 0; i < a.vector.length; i++) {
				if(i < N){
					crossover[i] = a.vector[i];
				}
				if (i >= N && i <= N2){
					crossover[i]=b.vector[i];				
				}
				else{
					crossover[i] = a.vector[i];
					}
				}
			}
		
		
		else{
			for(int i = 0; i < a.vector.length; i++) {
				if(i < N2){
					crossover[i] = a.vector[i];
				}
				if (i >= N2 && i <= N){
					crossover[i]=b.vector[i];				
				}
				else{
					crossover[i] = a.vector[i];
					}
				}
			}
			
		
		return new Tuple(crossover);
	}

}
