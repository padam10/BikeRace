



import java.awt.*;

public class CurrentRamp {
	
	static int x1 ;  
	static int y1 ;
	
	static int x2;
	static int y2;
	
	static double unitVectorX;
	static double unitVectorY;
	
	public CurrentRamp(){
		
	}

	public CurrentRamp (int x1, int y1, int x2, int y2)
	{
		CurrentRamp.x1 = x1;		
		CurrentRamp.y1 = y1;
		CurrentRamp.x2 = x2;
		CurrentRamp.y2 = y2;
		int dx = x2 - x1;
		int dy = y2 - y1;
		
		double mag = Math.sqrt(dx*dx + dy*dy); 
		
		unitVectorX = dx/mag;
		unitVectorY = dy/mag;
	}

	
	public double distanceTo(int Wheelx, int Wheely)
	{	
		return -unitVectorX * (Wheely - y2) + unitVectorY * (Wheelx - x2);
	}
	
	public boolean hasCollidedWith (MotorCycle motor)
	{	
		if(y2 < y1)
		{
			if(motor.frontWheel.x <= x2)
			{						
				return distanceTo(motor.frontWheel.x , motor.frontWheel.y) <= (motor.frontWheel.r + 20 ) ; 
			}
			else
				return false;
		}
		else
		{
			if(motor.frontWheel.x <= x2 )  
				return distanceTo(motor.frontWheel.x, motor.frontWheel.y) <= motor.frontWheel.r; //was -(car.x_Rect +60), car.y_Rect + 20)
			else
				return false;
		}
	}
	
	
	public void draw(Graphics g)
	{	
		g.drawImage(CreateRamps.ramp1, CreateRamps.x1 - 60 - (Camera.x + 200), 350 , 400 , 100 + 40 , null );
		g.drawImage(CreateRamps.ramp2, CreateRamps.x3 - 80 - (Camera.x + 200), 250, 400 , 200 + 40 , null );
		g.drawImage(CreateRamps.ramp3, CreateRamps.x8 - 45 - (Camera.x + 200), 350, 400 , 100 + 40 , null );
		g.drawImage(CreateRamps.ramp4, CreateRamps.x13 - 55 - (Camera.x +200), 250, 400 , 200 + 40 , null );
		g.drawImage(CreateRamps.ramp5, CreateRamps.x17  - (Camera.x+200), 260, CreateRamps.x18 - CreateRamps.x17, 50, null);

		
	}

}
