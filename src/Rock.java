

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Rock {
	int x;
	int y;
	final int w =50;
	final int h=60;
	Image rock = Toolkit.getDefaultToolkit().getImage("rock.png");
	
	int speed= 1+(int)(Math.random()*2);
	
	public Rock(int x,int y){
		this.x=x;
		this.y=y;
	}
	
	public void draw(Graphics g){
		rockDrop(speed);
		g.drawImage(rock, x-Camera.x, y, w, h, null);
	}
	
	public void rockDrop(int speed){
		y +=speed;
	}
	
	public boolean contains(int mx, int my,int ow,int oh){
		return (x+w<mx)&&(mx+ow<x)&&(my+oh<h)&&(y+h<my);
	}
}
