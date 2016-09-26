import java.util.ArrayList;
import java.util.Collections;


/**
 * @author jim
 * This Selector simply sorts the list of Tuples and
 * returns only the best n Tuples.
 */
public class SelectTopN implements Selector {

	public ArrayList<Tuple> select(ArrayList<Tuple> selectfrom, int n) {
		Collections.sort(selectfrom, Collections.reverseOrder());
		return new ArrayList<Tuple>(selectfrom.subList(0, n));
	}
	
	

}
