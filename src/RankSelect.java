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
		
		// parameter s // see above
		double s = 1.5;
		
		double mu = selectfrom.size();
		double Chance;
		ArrayList<Tuple> ReturnPopulation = new ArrayList<Tuple>();
		int i = 0;
		double x = (2 - s) / mu;
		double y = 2.0 * (s - 1);
		double z = mu * ( mu - 1);
		
		Collections.sort(selectfrom, Collections.reverseOrder());
		
		while (n > ReturnPopulation.size()) {
			if(i == n){
				i = i % n;
			}

			Chance = x + (i * y) / z;
			if (Math.random() <= Chance){
				ReturnPopulation.add(selectfrom.get(i));
			}
			i++;
		}
		
		return ReturnPopulation;
		//return new ArrayList<Tuple>(selectfrom.subList(0, n));
	}
	
}