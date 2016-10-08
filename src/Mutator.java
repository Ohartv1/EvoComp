
public interface Mutator {
	
	// Mutate a given individual
	public Tuple mutate(Tuple tomutate);
	
	// Change the scaling parameter, i.e. multiply it by s
	public void scale(double s);

}
