



import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;



 public  class  CreateRamps {
	
	
	//first ramp
	static int x1 = 420;//470
	static int y1 = 480;//
	
	static int x2 = 670;//720
	static int y2 = 350;//
	
	//second ramp part 1
	static int x3 = x1 + 900;//+650
	static int y3 = y1;
	
	static int x4 = x2 + 770;//770
	static int y4 = y2 ;
	
	// part 2
	
	static int x5 = x4 + 50;
	static int y5 = y4 - 101;
	
	// part 3
	
	static int x6 = x5 + 85;//85
	static int y6 = y5 ;
	
	//part 4
	
	static int x7 = x6 + 95;//x7 = x6 +95
	static int y7 = y3 ;
	
	// third Ramp
	
	static int x8 = x3 + 780;
	static int y8 = y3;
	
	static int x9 = x4 + 810;
	static int y9 = y4 + 40; 
	
	// fourth Ramp
	
	static int x10 = x9 + 100;
	static int y10 = y9 - 40;
	
	static int x11 = x10 + 90;
	static int y11 = y10 + 20;
	
	static int x12 = x11 + 50;
	static int y12 = y8;
	
	// fifth Ramp
	
	//part 1
	static int x13 = x10 + 900;
	static int y13 = y12;
	
	static int x14 = x13 + 100;
	static int y14 = y13 - 100;
	
	//part 2
	static int x15 = x14 + 70;
	static int y15 = y14 - 120;
	
	//part 3
	static int x16 = x15 + 160;
	static int y16 = y13;
	
	static int x17 = x16 - 50;//-50
	static int y17 = y15;
	
	static int x18 = x17 + 500;
	static int y18 = y17;
	
	static Image ramp1;
	static Image ramp2;
	static Image ramp3;
	static Image ramp4;
	static Image ramp5;
	
	static boolean create1 = true;
	static boolean create2 = true;
	static boolean create3 = true;
	static boolean create4 = true;
	static boolean create5 = true;
	static boolean create6 = true;
	static boolean create7 = true;
	static boolean create8 = true;
	static boolean create9 = true;
	static boolean create10 = true;
	static boolean create11 = true;
	
	static CurrentRamp ramp;	
	
	static boolean projectile = false;
 
	
	
	public  static void BigRamp(MotorCycle motor)
	{
		ramp1 = Toolkit.getDefaultToolkit().getImage("r1.png");
		ramp2 = Toolkit.getDefaultToolkit().getImage("r2.png");
		ramp3 = Toolkit.getDefaultToolkit().getImage("r3.png");
		ramp4 = Toolkit.getDefaultToolkit().getImage("r4.png");
		ramp5 = Toolkit.getDefaultToolkit().getImage("r6.png");
		
				
		if(((motor.frontWheel.x + motor.frontWheel.r )<= (x1 - 5))				
				&& create1 == true ) 
		{
			projectile = true;			
			ramp = new CurrentRamp(x1, y1, x2, y2);			
			create1 = false;
			motor.theta1 =50;
		}
		
		else if((((motor.frontWheel.x + motor.frontWheel.r ) > (x3 - 10)) 
				&& ((motor.frontWheel.x + motor.frontWheel.r) <= (x4 - 10)))
					&& create2 == true)
		{
			projectile = false;
			ramp = new CurrentRamp(x3, y3, x4, y4);
			create2 = false;
		
		}
		
		else if((motor.frontWheel.x + motor.frontWheel.r) > (x4 - 10) 
					&& (motor.frontWheel.x + motor.frontWheel.r) <= (x5 + 5) 
						&& create3 == true )
		{
			projectile = true;
			ramp = new CurrentRamp(x4, y4, x5, y5);
			create3 = false;
			motor.theta1 = 50;
		}

		else if ((motor.frontWheel.x + motor.frontWheel.r) > (x5 + 5) 
					&& (motor.frontWheel.x + motor.frontWheel.r) < (x6 - 5) 
							&& create4 == true)
		{
			projectile = true;
			ramp = new CurrentRamp(x5, y5, x6, y6);			
			create4 = false;
		}

		else if ((motor.frontWheel.x + motor.frontWheel.r) >= (x6 - 5) 
					&& (motor.frontWheel.x + motor.frontWheel.r) <=  (x7 - 5) 
						&& create5 == true)
		{
			projectile = true;
			ramp = new CurrentRamp(x6, y6, x7, y7);
			create5 = false;
		}
		else if ((motor.frontWheel.x + motor.frontWheel.r) > (x8- 10) 
				&& (motor.frontWheel.x + motor.frontWheel.r) <=  (x9 - 5) 
					&& create6 == true)
		{
			projectile = false;
			ramp = new CurrentRamp(x8, y8, x9, y9);
			create6 = false;
		}
		else if ((motor.frontWheel.x + motor.frontWheel.r) > (x9- 10) 
				&& (motor.frontWheel.x + motor.frontWheel.r) <=  (x10 + 5) 
					&& create7 == true)
		{
			projectile = true;
			ramp = new CurrentRamp(x9, y9, x10, y10);
			create7 = false;
			motor.theta1 = 50;
		}
		
		else if ((motor.frontWheel.x + motor.frontWheel.r) > (x10 + 5) 
				&& (motor.frontWheel.x + motor.frontWheel.r) <=  (x12 + 5)
					&& create8 == true)
		{
			projectile = true;
			ramp = new CurrentRamp(x11, y11, x12, y12);
			create8 = false;
			
		}
		
		else if ((motor.frontWheel.x + motor.frontWheel.r) > (x13 - 10) 
				&& (motor.frontWheel.x + motor.frontWheel.r) <=  (x14 + 5)
					&& create9 == true)
		{
			projectile = false;
			ramp = new CurrentRamp(x13, y13, x14, y14);
			create9 = false;
			
		}
		else if ((motor.frontWheel.x + motor.frontWheel.r) > (x14 + 5 ) 
				&& (motor.frontWheel.x + motor.frontWheel.r) <=  (x15 - 5)
					&& create10 == true)
		{
			projectile = true;
			ramp = new CurrentRamp(x14, y14, x15, y15);
			create10 = false;
			motor.theta1 = 60;
		}
		
		else if ((motor.frontWheel.x + motor.frontWheel.r) > (x15 - 5 ) 
				&& (motor.frontWheel.x + motor.frontWheel.r) <=  (x17 +120)
					&& create10 == true)
		{
			projectile = true;
			ramp = new CurrentRamp(x15, y15, x16, y16);
			create10 = false;
			
		}
	
		
		else if ((motor.frontWheel.x + motor.frontWheel.r) > (x17 + 120 ) 
				&& (motor.frontWheel.x + motor.frontWheel.r) <=  (x18 + 5 )
					&& create11 == true)
		{		
				projectile = false;
				ramp = new CurrentRamp(x17, y17, x18, y18);
				create11 = false;			
		}		
		
	}
}
