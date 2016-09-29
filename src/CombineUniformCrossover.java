import java.util.Random;


public class CombineUniformCrossover implements Combinator {

	Random rand = new Random();

	@Override
	public Tuple combine(Tuple a, Tuple b) {
		double[] v = new double[a.vector.length];
		for(int i = 0; i < a.vector.length; i++){
			if(rand.nextBoolean()){
				v[i] = a.vector[i];
			}
			else{
				v[i] = b.vector[i];
			}
		}
		return new Tuple(v);
	}
	
}
