import java.util.Random;

public class CombineRandPointCrossover implements Combinator{

	Random rand = new Random();
	
	@Override
	public Tuple combine(Tuple a, Tuple b) {
		int N = rand.nextInt(a.vector.length);
		double[] crossover = new double[a.vector.length];
		for(int i = 0; i < a.vector.length; i++){
			if(i < N){
				crossover[i] = a.vector[i];
			} 
			else{
				crossover[i] = b.vector[i];
			}
		}
		return new Tuple(crossover);
	}

}
