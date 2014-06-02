
//Sprite: anything that moves on the screen
//abstract class bcuz what sprite is, is different every time

import java.awt.*;

public abstract class Sprite {
	int w;
	int h;
	
	int y;
	int x;
	
	int dir = DN;
	
	
	boolean moving = false;
	
	
	final static int UP = 0;
	final static int DN = 1;
	final static int LT = 2;
	final static int RT = 3;
	
	
	public Sprite(int x, int y, int w, int h)
	{
		this.x = x;
		this.y = y;
		
		this.w = w;
		this.h = h;
		
	}
	
	
	
//	public void moveUpBy(int dy)
//	{
//		y -= dy;
//		
//		dir = UP;
//		
//		moving = true;
//	}
	
	public void moveDownBy(int dy)
	{
		
		y += dy;
		
		dir = DN;
		
		moving = true;
	}
	
	public void moveLeftBy(int dx)
	{
		x -= dx;
		
		dir = LT;
		
		moving = true;
	}
	public void moveRightBy(int dx)
	{
		x += dx;
		
		dir = RT;
		
		moving = true;
	}
//	public boolean hasCollidedWith(Rect r)
//	{
//		
//		// to determine if 2 rectangles have collided.
//		return
//			((x+w >= r.x) && 
//			(r.x + r.w >= x) &&
//			(r.y + r.h >= y) &&
//			(y+h >= r.y));
//		
//	}
	
	public boolean contains(int mx, int my)
	{
		return((my < y+h) && (my > y) && (mx > x) && (mx < x+w));
	}
	
	public abstract void draw(Graphics g);
	
}