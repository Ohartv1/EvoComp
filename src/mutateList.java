import java.util.ArrayList;

/**
 * @author Onno
 *
 */

public class mutateList implements ListMutator{
	
	public ArrayList<Tuple> mutatelist(ArrayList<Tuple> k, Mutator mutator) {
		
		ArrayList<Tuple> returnList = new ArrayList<Tuple>();
		
		int len = k.size();
		for(int i = 0; i < len; i++){
			returnList.add(mutator.mutate(k.get(i)));
		}
		
		return returnList;
		
	}

}
