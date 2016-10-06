import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Onno
 *
 * P_lineair-rank(i) = (2 - s) / mu  + (2*i(s-1)) / (mu*(mu-1)) 
 * 		mu = number of individuals in population
 *		(1 < s <= 2): s = 1 equal changes and s = 2 dubbles the changes of the best
 *		i = ranking #
 *
 *
 * Returns a ArrayList of n Tuples
 */

public class RankSelect implements Selector {
	
	
	public ArrayList<Tuple> select(ArrayList<Tuple> selectfrom, int n){
		
		double mu = selectfrom.size();
		double s = 1.5;
		int Chance = 0;
		ArrayList<Tuple> ReturnPopulation = new ArrayList<Tuple>();
		ArrayList<Integer> BigPopulationIndex = new ArrayList<Integer>();
		int RandIndex;
		
		Collections.sort(selectfrom);
		
		for (int i = 0; i < mu; i++) {
			Chance = (int) Math.round(((2 - s) / mu  + (2 * i * (s - 1)) / (mu * ( mu - 1))) * mu);
			for (int j = 0; j < Chance; j++){
				BigPopulationIndex.add(i);
			}
		}
		
		int big = BigPopulationIndex.size();
		
		for (int l = 0; l < n; l++){
			RandIndex = (int) Math.floor(Math.random() * (big + 1));
			ReturnPopulation.add(selectfrom.get(BigPopulationIndex.get(RandIndex)));
		}
		
		return ReturnPopulation;
		//return new ArrayList<Tuple>(selectfrom.subList(0, n));
	}
	
}