import java.util.ArrayList;

public class allCombine implements ListCombinator {

	@Override
	public ArrayList<Tuple> combinelist(ArrayList<Tuple> l, Combinator combinator) {
		
		ArrayList<Tuple> returnList = new ArrayList<Tuple>();
		
		int len = l.size();
		
		for(int i = 0; i < len; i++){
			for(int j = 0; j < i; j++){	
				if(i != j){
					returnList.add(combinator.combine(l.get(i), l.get(j)));						
				}
			}
		}
		
		return returnList;
	}

}
