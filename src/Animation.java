
import java.awt.*;



//next week we will see how we can control the character
//detect collisions for circles and rectangles line vs a point
//how to make something move on its own
//how to deal with collision
//will show linear motion a ball bounce around...etc. We may be able to figure out on our own 
//animated sprites, turn, jump 
public class Animation 
{
	Image[] image;
	 Image staticImage = Toolkit.getDefaultToolkit().getImage("m_rt_0.png");
	//keep track of which image I am currently looking at
	static int current = 1;
	
	//delay in showing image
	int delay=0;
	
	
	
	//constructor that loads all images
	public Animation(String name, int count)
	{
		image = new Image[count];
		
		for(int i = 0; i < count; i++)
		{
			//loads all images
			image[i] = Toolkit.getDefaultToolkit().getImage(name + "_" + i + ".png");
		}
		
		
	}
	
	//get next image
	public Image currentImage()
	{
		if (delay == 2)
		{
			//in book, looks at how much time has passed. must take into account for game over the internet.
			//lag is different from moment to moment-thats how you see how much time has passed
			delay = -1;
					//make sure modular is always by a power of two. Very powerful.
					// set image.length to be a power of 2
		    //current = (current + 1) % image.length;
			
			current++;
			
			
			if(current >= image.length) current = 1;
		}
		System.out.println("                        current: "+current);
		delay+=1 ;
		
		return image[current];
	}
	public Image staticImage()
	{
		return staticImage;//image[0]
	}
}
