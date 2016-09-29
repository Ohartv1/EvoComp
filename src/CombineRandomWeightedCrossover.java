import java.util.Random;


public class CombineRandomWeightedCrossover implements Combinator{

	Random rand = new Random();
	
	@Override
	public Tuple combine(Tuple a, Tuple b) {
		double[] v = new double[a.vector.length];
		for(int i = 0; i < a.vector.length; i++){
			double x = rand.nextDouble();
			v[i] = x*a.vector[i] + (1-x)*b.vector[i];
		}
		return new Tuple(v);
	}

}
