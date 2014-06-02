

//import Camera;



import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class ImageLayer {
	Image image;

	int x;
	int y;

	int d;

	public ImageLayer(String filename, int x, int y, int d) {
		image = Toolkit.getDefaultToolkit().getImage(filename);

		this.x = x;
		this.y = y;

		this.d = d;
	}

	public void draw(Graphics g) {

		for(int i = -2; i < 20; i++){
			g.drawImage(image, x - Camera.x + i*720 , y, null);
		}

	}

	public void moveForwardBy(int dx)
	{
		x -= dx;
		
	}
	
	public void moveBackwardBy(int dx)
	{
		x += dx;
		
	}

}