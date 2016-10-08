import java.util.Random;


public class MutateAddGaussian implements Mutator {
	
	Random rand = new Random();
	double scale;
	
	public MutateAddGaussian(double scale){
		this.scale = scale;
	}

	public Tuple mutate(Tuple tomutate) {
		double[] oldvector = tomutate.vector;
		double[] newvector = new double[oldvector.length];
		for(int i = 0; i < oldvector.length; i++){
			newvector[i] = oldvector[i] + scale*rand.nextGaussian();
		}
		return new Tuple(newvector);
	}

	public void scale(double s) {
		this.scale *= s;		
	}
	

}
