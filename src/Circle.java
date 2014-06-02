

import java.awt.Color;
import java.awt.Graphics;


public class Circle 
{
	
	int x;
	int y;
	
	int r;
	

	int angle; 
	
	

	public Circle( int x, int y, int r, int angle)
	{
		this.y = y;
		this.x = x;
		
		this.r = r;
		this.angle = angle;
		
	}
	
	
	public double distanceTo(int tx, int ty) 
	{	
		return (tx -x) * Lookup.sin[angle] - (ty -y) * Lookup.cos[angle];		 
		
	}
	
	
	public void draw(Graphics g)
	{
		
		
	}
	
	public void moveForwardBy(int dist)  // i think we don't need this
	{
		x += dist  * Math.cos(angle * Math.PI/180);
		
		y += dist * Math.sin(angle * Math.PI/180);
		
		
	}
	
	public void moveBackwardBy(int dist)
	{
		x -= dist  * Math.cos(angle * Math.PI/180);
		
		y -= dist * Math.sin(angle * Math.PI/180);
		
		
	}
	
	
	public void rotateLeftBy(int dangle)
	{
		angle -= dangle;
		
		if (angle < 0) angle = 359;
	}
	

	public void rotateRightBy(int dangle)
	{
		angle += dangle;
		
		if (angle > 359) angle = 0;
		
	}
	
	public boolean contains(int mx, int my)
	{
		return((x-mx) * (x-mx) + (y-my)*(y-my) < r * r);
	}
	
	public void moveTo(int x, int y)
	{
		this.x = x;
		this.y = y;
		
	}
}
