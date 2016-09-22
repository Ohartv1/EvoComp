import java.util.Properties;
import org.vu.contest.ContestEvaluation;

// Cross-in-Tray function from
// https://en.wikipedia.org/wiki/Test_functions_for_optimization
public class CrossInTrayEvaluation implements ContestEvaluation 
{
	// Evaluations budget
	private final static int EVALS_LIMIT_ = 100000;
	// The base performance. It is derived by doing random search on the sphere function (see function method) with the same
	//  amount of evaluations
	private final static double BASE_ = 1;
	// The minimum of the sphere function
	private final static double ftarget_=0;
	
	// Best fitness so far
	private double best_;
	// Evaluations used so far
	private int evaluations_;
	
	// Properties of the evaluation
	private String multimodal_ = "false";
	private String regular_ = "true";
	private String separable_ = "true";
	private String evals_ = Integer.toString(EVALS_LIMIT_);

	public CrossInTrayEvaluation()
	{
		best_ = 0;
		evaluations_ = 0;		
	}

	// The standard sphere function. It has one minimum at 0.
	private double function(double[] x)
	{	
		double x_ = 0;
		double y_ = 0;
		for(int i = 0; i < 5; i++){
			x_ += x[2*i]/2.5;
			y_ += x[2*(i)+1]/2.5;
		}
		
		return 10 - 2.06261 + 0.0001*Math.pow( (Math.abs( Math.sin(x_)*Math.sin(y_)*Math.exp(Math.abs(100 - (Math.sqrt( (x_)*(x_) + (y_)*(y_)) / Math.PI )  ) +1 ))  ), 0.1);
	}
	
	public Object evaluate(Object result) 
	{
		// Check argument
		if(!(result instanceof double[])) throw new IllegalArgumentException();
		double ind[] = (double[]) result;
		if(ind.length!=10) throw new IllegalArgumentException();
		
		if(evaluations_>EVALS_LIMIT_) return null;
		
		// Transform function value (sphere is minimization).
		// Normalize using the base performance
		double f = function(ind) ;
		if(f>best_) best_ = f;
		evaluations_++;
		
		return new Double(f);
	}

	public Object getData(Object arg0) 
	{
		return null;
	}

	public double getFinalResult() 
	{
		return best_;
	}

	public Properties getProperties() 
	{
		Properties props = new Properties();
		props.put("Multimodal", multimodal_);
		props.put("Regular", regular_);
		props.put("Separable", separable_);
		props.put("Evaluations", evals_);
		return props;
	}
}
