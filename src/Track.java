

import java.awt.*;

public class Track {
	
		int x;
		int y;
		int x1;
		int y1;
		
		int w = 1500;
		int h = 450;
		
		Image track;
		Image fence; 
		
		

		public Track(int x, int y, int x1, int y1) {

			track = Toolkit.getDefaultToolkit().getImage("Track.png");
			fence = Toolkit.getDefaultToolkit().getImage("fence.png");
			this.x = x;
			this.y = y;
			this.x1 = x1;
			this.y1 = y1;
			
		}

		
		public void draw(Graphics g) {
			
			for(int i = -2; i < 20; i++)
			{
				g.drawImage(track, x  - (Camera.x + 200) + i*720, y , w , h, null);
				
				i++;
			}
				//g.drawImage(track, 0 , 450, 1500 , 450, null);
			//g.drawLine(x, y, x1, y1);
			//for(int i = 0 ; i < 20; i++)
			//{
			
			for(int i = -2; i < 100 ; i++)
			{
				g.drawImage(fence,  i*100 - (Camera.x + 200) , 420 , 200, 60,  null);
				
				
			}
				
				
				
			//}

		}

	

}
