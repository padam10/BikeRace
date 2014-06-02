
public class Lookup
{
	
	//cant do calculations at class scope, so you can write a functions that does your calculation and
	//call the function at class scope.
	
	//cos of an Angle
	//sin of an Angle
    final static double[] cos   = generateCos();
	final static double[] sin   = generateSin();
	
	
	public static double[] generateCos()
	{
		double[] cos = new double[360];
		for(int degrees = 0; degrees < 360; degrees++)
			cos[degrees] = Math.cos(degrees * Math.PI / 180);
			
		return cos;
		
		
	}
	
	public static double[] generateSin()
	{
		double[] sin = new double[360];
		for(int degrees = 0; degrees < 360; degrees++)
			sin[degrees] = Math.sin(degrees * Math.PI / 180);
		
		return sin;
		
	}
	
	//generate the table
	/*public static void generate()
	{
		for(int degrees = 0; degrees < 360; degrees++)
		{
			//arrays will store cos, sin for all degrees
			cos[degrees] = Math.cos(degrees * Math.PI / 180);
			sin[degrees] = Math.sin(degrees * Math.PI / 180);
		}
		
		//to find cos,sin for 90 degree angle: sin[90];
		
	}*/
}