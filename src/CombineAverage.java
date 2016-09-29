
public class CombineAverage implements Combinator{

	@Override
	public Tuple combine(Tuple a, Tuple b) {
		double[] v = new double[10];
		for(int i = 0; i < a.vector.length; i++){
			v[i] = 0.5*(a.vector[i] + b.vector[i]);
		}
		return new Tuple(v);
	}

}
