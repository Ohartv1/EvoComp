import java.util.ArrayList;

public class locationCombinator implements ListCombinator{
	
	public ArrayList<Tuple> combinelist(ArrayList<Tuple> l, Combinator combinator) {
		
		//  based on location in the array the tuples are picked to combine.
		ArrayList<Tuple> returnList = new ArrayList<Tuple>();
		
		int len = (int) Math.floor(l.size() / 2.0);
		int len2 = (int) Math.floor(l.size());
		for(int i = 0; i < len; i++){
			returnList.add(combinator.combine(l.get(i), l.get(len2 - 1 - i)));
			// very lame solution to keep up a constant population size
			returnList.add(combinator.combine(l.get(i), l.get(len2 - 1 - i)));
		}
		
		return returnList;
	}
	
}
