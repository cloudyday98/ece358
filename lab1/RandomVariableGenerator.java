package lab1;

import java.lang.Math;
import java.util.ArrayList;
import java.util.List;

public class RandomVariableGenerator {

/* the following main function was for debugging purpose */
/*
	public static void main(String[] args) {
		
	    ArrayList<Double> a = new ArrayList<>();
	    
	    double lambda = 75.0;
	    double rand_num = 0.0;
	  
	    for(int i = 0; i < 2000; i++) a.add( GenerateRandomVariable(lambda));
	    
	    double average = GetAverage(a, 2000);
	    double variance = GetVariance(a, average, 2000);
	    
	    System.out.println("Average is: " + average + " Variance is: " + variance);

	}
*/
	
	public static double GenerateRandomVariable(double lambda) {
		return -(1.0/lambda)*Math.log(1-Math.random());
	}
	
	static double GetAverage( List<Double> a, int size){
		
	    double sum = 0;
	    
	    for (double d : a) sum += d;

	    return sum/size;
	}
	
	static double GetVariance(List<Double> a, double average, int size){
		
	    double sum = 0;
	    
	    for (double d : a) sum = sum + Math.pow((d - average), 2);

	    return sum/size;
	}
}
