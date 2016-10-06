import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Statistics 
{
    static double getMean(double[] data)
    {
        double sum = 0.0;
        for(double a : data)
            sum += a;
        return sum/data.length;
    }
    
    static double getMean(ArrayList<Double> data){
    	Double sum = 0.0;
    	for(Double d : data){
    		sum += d;
    	}
    	return sum / data.size();
    }
    
    static double getMean(List<Double> data){
    	return getMean(new ArrayList<Double>(data));
    }

    static double getVariance(double[] data)
    {
        double mean = getMean(data);
        double temp = 0;
        for(double a :data)
            temp += (a-mean)*(a-mean);
        return temp/data.length;
    }

    static double getStdDev(double[] data)
    {
        return Math.sqrt(getVariance(data));
    }

    static public double median(double[] data) 
    {
       Arrays.sort(data);

       if (data.length % 2 == 0) 
       {
          return (data[(data.length / 2) - 1] + data[data.length / 2]) / 2.0;
       } 
       else 
       {
          return data[data.length / 2];
       }
    }
}

