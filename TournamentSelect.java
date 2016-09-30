import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Onno
 *
 * Tournament Selection: Increasing k increases the selection pressure. 
 * The larger k the more likely a Tuple is selected with above-average fitness. 
 *
 * Returns a ArrayList of n Tuples
 */


public class TournamentSelect {
	
	public ArrayList<Tuple> select(ArrayList<Tuple> selectfrom, int n){
		
		// Setting;
		int k = 5;
		
		int RandIndex;
		double mu = selectfrom.size();
		ArrayList<Tuple> ReturnPopulation = new ArrayList<Tuple>();
		ArrayList<Tuple> SelectionPool = new ArrayList<Tuple>();
		int count = 0;
		
		while(count < n){
			for (int i = 0; i < k; i++){
				RandIndex = (int) Math.floor(Math.random() * (mu + 1));
				SelectionPool.add(selectfrom.get(RandIndex));
			}
			Collections.sort(SelectionPool, Collections.reverseOrder());
			ReturnPopulation.add(selectfrom.get(0));
			count++;
			SelectionPool.clear();
			
		}
		return ReturnPopulation;
	}
}
