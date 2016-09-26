import java.util.ArrayList;


public interface Selector {

	
	/**
	 * @param selectFrom The list from which to select
	 * @param n Number of Tuple to return
	 * @return
	 */
	public ArrayList<Tuple> select(ArrayList<Tuple> selectFrom, int n);
	
}
