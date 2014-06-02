


import java.awt.*;

public class SpriteAnimated extends MotorCycle 
{
	Animation[] animation;
	
	String[] pose = {"rt","up", "dn"};
	
	public SpriteAnimated(int x, int y, String name, int count, int size)
	{
		super(name, x, y);
		
		animation = new Animation[size];
		
		for(int i = 0; i<animation.length; i++)
			animation[i] = new Animation(name+"_"+pose[i], count);
			
	}
	
	public void draw(Graphics g)
	{
		if(moving )
		{
			g.drawImage(animation[dir].currentImage(), mx - (Camera.x + 200), my - 100, null);
			
		}
		else
		{
			g.drawImage(animation[dir].staticImage(), mx  - (Camera.x + 200), my - 100 , null);
		}
		moving = false;	
		
	}
	
	
}
