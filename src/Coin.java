


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Coin {

	Image image;
	int x;
	int y;
	
	int w = 25;
	int h = 25;
	
	MotorCycle m;
	
	int r;
	final int score;

	public Coin(String image, int x, int y) {
		this.image = Toolkit.getDefaultToolkit().getImage(image);
		this.x = x;
		this.y = y;
		r = 12;
		score=20;
	}
	


	public void draw(Graphics g) {

		g.drawImage(image, x - Camera.x - 200, y, w, h, null);
		
		g.setColor(new Color(255,255,255,0));
		g.fillRect(x, y, w, h);
		

	}
	
	public boolean contains(int mx, int my){
		return (x-mx)*(x-mx)+ (y-my)*(y-my) < r*r;
	}
	
	public boolean hasCollidedWith(int mx,int my,int mw,int mh)
	{
		
		
		return
			((x+w >= mx) && 
			(mx + mw >= x) &&
			(my + mh >= y) &&
			(y+h >= my));
	}

}
