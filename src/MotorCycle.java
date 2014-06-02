


import java.awt.*;

public class MotorCycle {

	Circle frontWheel;
	Circle backWheel;

	Image image;
	
	int mx= 0;
	int my = 380;
	int w = 99;
	int h = 106;
	
	boolean moving = false;

	int dvx;
	int dvy;
	
	int dir = RT;
	
	final static int RT = 0;
	final static int UP = 1;
	final static int DN = 2;
	   
	static int v0 = 10;	  
	
	int theta1 = 60;	

	int ay = 10;

	public MotorCycle(String filename,int mx,int my) {
		this.mx =mx;
		this.my = my;
		this.image = Toolkit.getDefaultToolkit().getImage(filename);

		frontWheel = new Circle(78 , 470, 10, 0);
		backWheel = new Circle(20, 470, 10, 0);

	}

	public void moveUpBy()
	{   
	   dir = UP;
	   moving = true;
	}

	public void moveDownBy()
	{	      
		dir = DN;
		moving = true;
	}
	
	public void moveForwardBy(int dx) {
		
		if (isFlying == false) {
			frontWheel.x += dx;
			backWheel.x += dx;
			mx+=dx;
			
			dir = RT;
			
			moving = true;
		}

	}

	public void moveBackwardBy(int dx) {

		if (isFlying == false)
		{
			if (backWheel.x > CurrentRamp.x2 
					&& backWheel.x < CurrentRamp.x2 + 5) {
				mx -= 0;
				my -= 0;
				frontWheel.x -= 0;
				backWheel.y -= 0;
				
				dir = RT;
				moving = true;
			} else {
				mx -= dx;
				
				frontWheel.x -= dx;
				backWheel.x -= dx;
				
				dir = RT;
				moving = true;
			}

		}
	}
	
	boolean isFallingDown = false;
	boolean isFlying = false;

	double v0x;
	double v0y;

	public void projectileMotion() {
		if ((frontWheel.y + frontWheel.r) < 480) {
			
			if (theta1 > 359)
				theta1 = 0;

			double v0x = 10 * Math.abs(Lookup.cos[theta1]);
			double v0y = 10 * Math.abs(Lookup.sin[theta1]);
			if (theta1 >= 5 && isFallingDown == false)
			{				
				moveUpBy();				

				isFlying = true;
				mx = (int) (mx + Math.ceil(v0x));
				my = (int) (my - Math.ceil(v0y));
				
				frontWheel.x = (int) (frontWheel.x + (Math.ceil(v0x)));
				frontWheel.y = (int) (frontWheel.y - (Math.ceil(v0y)));

				backWheel.x = (int) (backWheel.x + (Math.ceil(v0x)));
				backWheel.y = (int) (backWheel.y - (Math.ceil(v0y)));			
				
				theta1 -= 3;
				if (theta1 < 0)
					theta1 *= (-1);	
				
			} else {			
				
				moveDownBy();
				
				isFallingDown = true;
					
				frontWheel.x = (int)(frontWheel.x + ((Math.ceil(v0x))) );					
				
				frontWheel.y = (int)(frontWheel.y + (Math.ceil(v0y) )) + ay ;
				backWheel.x = (int)(backWheel.x + ((Math.ceil(v0x)) ));					
				backWheel.y = (int)(backWheel.y + (Math.ceil(v0y)))+ ay;
				
				mx = (int)(mx + ((Math.ceil(v0x))) );					
				my = (int)(my + (Math.ceil(v0y) )) + ay;
				theta1 +=1;
					
			}
		}
		
		else
		{
		
			isFlying = false;
			isFallingDown = false;
			
		}
		
	}
	
	public void downRamp(CurrentRamp ramp)
	{
		if((frontWheel.y + frontWheel.r) < 480 && CurrentRamp.y1 != CurrentRamp.y2)
		{
			moveDownBy();
			double dist = ramp.distanceTo(frontWheel.x , frontWheel.y);
			if((CurrentRamp.y2 > frontWheel.y ) && (CurrentRamp.x1  < frontWheel.x)) //&& SmallRamp.x2 > frontWheel.x ))
			{			
				int adj = Math.abs((int)(frontWheel.r - dist)) ;
				
				if(ramp.y2 < ramp.y1)
				{
					frontWheel.x +=  adj*CurrentRamp.unitVectorY % 2; 					
					frontWheel.y += adj*CurrentRamp.unitVectorX + CurrentRamp.unitVectorX ;
			
					backWheel.x += adj*CurrentRamp.unitVectorY % 2;
					backWheel.y += adj*CurrentRamp.unitVectorX +  CurrentRamp.unitVectorX ;
					
					mx += adj*CurrentRamp.unitVectorY % 2;
					my += adj*CurrentRamp.unitVectorX +  CurrentRamp.unitVectorX ;
				}
			}
			else if((CurrentRamp.y2 < frontWheel.y ) && (CurrentRamp.x1  < frontWheel.x))
			{
				moveUpBy();
				if((frontWheel.x )<=(CreateRamps.x4 + 50))
					CreateRamps.create2 = true;
				int adj = Math.abs((int)(frontWheel.r - dist)) ;
				
					frontWheel.x -=  adj*CurrentRamp.unitVectorY + 2; 					
					frontWheel.y += adj*CurrentRamp.unitVectorX + CurrentRamp.unitVectorX ;
			
					backWheel.x -= adj*CurrentRamp.unitVectorY + 2;
					backWheel.y += adj*CurrentRamp.unitVectorX +  CurrentRamp.unitVectorX ;
					
					mx -= adj*CurrentRamp.unitVectorY + 2;
					my += adj*CurrentRamp.unitVectorX + CurrentRamp.unitVectorX ;
					
					
			}
		}
			
			
	}
	
	public void upRamp(CurrentRamp ramp)
	{	
		double dist = ramp.distanceTo(frontWheel.x , frontWheel.y );
		if(((CurrentRamp.y2 ) < frontWheel.y ) && ((CurrentRamp.x2)  > frontWheel.x))
		{
			moveUpBy();			
			
			int adj = (int)(frontWheel.r - dist);
			frontWheel.x += adj*CurrentRamp.unitVectorY ;			
			frontWheel.y -= adj*CurrentRamp.unitVectorX +2;
			
			backWheel.x +=  adj*CurrentRamp.unitVectorY ;
			backWheel.y -=  adj*CurrentRamp.unitVectorX +2;
			
			mx += adj*CurrentRamp.unitVectorY  ;
			my -= adj*CurrentRamp.unitVectorX + 2 ;
			
			
		}
		else if (CurrentRamp.y1 == CurrentRamp.y2)
		{ 			
			if(mx < CreateRamps.x18 && my >= CreateRamps.y17  )	
			{
				isFlying = false;	
				dir = RT;
			}
			else
			{
				isFallingDown = true;				
				projectileMotion();
				
			}
		}
		
	
	}
	
	public boolean hasCollidedWith(int ox,int oy,int ow,int oh)
	{
		
		
		return
			((mx+w >= ox) && 
			(ox + ow >= mx) &&
			(oy + oh >= my) &&
			(my+h >= oy));
	}
		
}
