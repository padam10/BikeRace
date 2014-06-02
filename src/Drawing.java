

import java.applet.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

@SuppressWarnings("serial")
public class Drawing extends Applet implements KeyListener, MouseListener, Runnable {
	
	Image offScreen;

	Graphics offScreen_g;
	
	Font font; 
	Font font1;
	
	Image gate1;
	Image gate2 ; 
	Image flag;
	
	Rock [] rocks;
	Rock [] rocks1;
	Rock [] rocks2;

	int increment=0;

	Track track; 

	SpriteAnimated motor; 
	Coin coin1;
	Coin coin1_1;
	Coin coin2;
	Coin coin2_1;
	Coin coin3;
	Coin coin3_1;
	Coin coin4;
	
	Coin coin5;
	Coin coin6;
	
	
	Coin coin7;
	Coin coin8;
	
	Coin coin9;
	Coin coin10;
	Coin coin11;
	Coin coin12;
	Coin coin13;
	
	boolean stopBackground;

	boolean[] pressed ;

	ImageLayer background1;
	ImageLayer background2;
	ImageLayer background3;
	
	Image playButton;
	Image startPage;	
	int count;
	boolean slide ;
	
	boolean stillPlaying;
	boolean pressedStart;
	
	CreateRamps bigRamp;
	
	CurrentRamp ramp;
	
	AudioClip grabCoin;
	
	AudioClip motorSound;
	
	int score;
	
	Image fin;
	
	public void init()
	{
		offScreen = createImage(1500, 750);
		offScreen_g = offScreen.getGraphics();
		
		font = new Font("sans-serif",Font.BOLD,16);
		font1 = new Font("Courier",Font.BOLD,22);

		rocks=new Rock[3];
		rocks1=new Rock[3];
		rocks2=new Rock[3];
		
		gate1 = Toolkit.getDefaultToolkit().getImage("gate1.png");
		gate2 = Toolkit.getDefaultToolkit().getImage("gate2.png");
		flag = Toolkit.getDefaultToolkit().getImage("flag.png");
		playButton = Toolkit.getDefaultToolkit().getImage("startButton.png");
		startPage = Toolkit.getDefaultToolkit().getImage("bikeRace.png");
		fin = Toolkit.getDefaultToolkit().getImage("bikeRace.png");
		
		track = new Track(0, 480, 1500, 480);
		
		motor = new SpriteAnimated(0, 480,"m", 5, 3);
		
		grabCoin = getAudioClip(getDocumentBase(),"coin_flip.wav");
		motorSound = getAudioClip(getDocumentBase(),"motorcycle2.wav");
		
		pressed = new boolean[256];
		
		 background1 = new ImageLayer("grasscopy.png", 0, 50, 0);
		 background2 = new ImageLayer("houses1.png", 0, 250, 1);
		 background3 = new ImageLayer("houses2.png", 0, 0, 1);
		
		 coin1 = new Coin("coin.png", 490, 380);
		 coin1_1 = new Coin("coin.png", 570, 340);
		 
		 coin2 = new Coin("coin.png", 650, 320);
		 coin2_1 = new Coin("coin.png", 850, 230);
		
		 coin3 = new Coin("coin.png", 1470, 190);
		 coin3_1 = new Coin("coin.png", 1710, 190);
		 coin4 = new Coin("coin.png", 1620, 120);
		 
		 
		 coin5 = new Coin("coin.png", 2299, 310);
		 coin6 = new Coin("coin.png", 2500, 280);
		
		 coin9 = new Coin("coin.png", 2700, 470);
		 coin10 = new Coin("coin.png", 2900, 480);
		 coin11 = new Coin("coin.png", 3100, 470);
		 coin12 = new Coin("coin.png", 3300, 320);
		 coin13 = new Coin("coin.png", 4600, 480);
		 
		 coin7 = new Coin("coin.png", 3400, 210);
		 coin8 = new Coin("coin.png", 3600, 200);
		 
		 ramp = CreateRamps.ramp;		
		
		requestFocus();
		addKeyListener(this);
		addMouseListener(this);
		
		initialize();
		
		Thread t = new Thread(this);
		t.start();
		
	}
	
	public void initialize(){
		
		stillPlaying = true;
		pressedStart = false;
		stopBackground=true;
		count = 10;
		motor.v0 = 1;
		slide = false;
		
		for(int i=0;i<rocks.length;i++){
			rocks[i]=new Rock(200+i*420,-10);
		}
		
		for(int i=0;i<rocks1.length;i++){
			rocks1[i]=new Rock(1480+i*420,-10);
		}
		
		for(int i=0;i<rocks2.length;i++){
			rocks2[i]=new Rock(3100+i*420,-10);
		}

		Camera.set(0, 0);
		
	}

	public void run() {
		
			preGameLoop();
			
			inGameLoop();	
	}
	
	public void preGameLoop(){
		
		while(!pressedStart)
			repaint();
		
	}
	
	public void inGameLoop(){
		
		while(pressedStart)
		{	
			CreateRamps.BigRamp(motor);
			ramp = CreateRamps.ramp;
			motorSound.play();
		
			System.out.println("motor.frontWheel.x"+ motor.frontWheel.x );
			if (pressed[KeyEvent.VK_RIGHT] && stopBackground)
			{				
				motor.moveForwardBy(MotorCycle.v0);
				background2.moveForwardBy(2);
				background3.moveForwardBy(1);
				
			}
			if (pressed[KeyEvent.VK_LEFT]) 
			{
				motor.moveBackwardBy(10);
				background2.moveBackwardBy(2);
				background3.moveBackwardBy(1);
			}
		
			Camera.x = motor.frontWheel.x - 480;		
			
			if((CurrentRamp.y1 == CurrentRamp.y2 || (CurrentRamp.y2 > CurrentRamp.y1))
					&& CreateRamps.projectile == true)
				motor.projectileMotion();
					
						
			else if(ramp.hasCollidedWith(motor)
						|| ((motor.frontWheel.y < 480) 
								&& (CurrentRamp.x1  < motor.frontWheel.x + motor.frontWheel.r) 
									&& CreateRamps.projectile == false))					
				
			{		 
				if(pressed[KeyEvent.VK_LEFT]) 					
					motor.downRamp(ramp);						
					
				else if (pressed[KeyEvent.VK_RIGHT]) 
				{
					if(CurrentRamp.y2 <= CurrentRamp.y1)					
						motor.upRamp(ramp); 											
					else 					
						motor.downRamp(ramp);					
				}
				else if(!pressed[KeyEvent.VK_RIGHT] && !pressed[KeyEvent.VK_LEFT] && count != 0) 
				{							
					motor.upRamp(ramp);
					count--;						
				}
				else if(!pressed[KeyEvent.VK_RIGHT] && !pressed[KeyEvent.VK_LEFT] && count == 0)
					slideDown();
			
						
			}
			else if ((((CurrentRamp.x2  )< (motor.frontWheel.x + motor.frontWheel.r) 
						&& ((motor.frontWheel.y) < 480) && CreateRamps.projectile == true )) ) // since your changing ramp all the time
					motor.projectileMotion();							
				
			else if(pressed[KeyEvent.VK_RIGHT] == true && CreateRamps.create11 == false)
				motor.moveForwardBy(10);			
		
			repaint();
		
			try{
				Thread.sleep(16);
			}
			catch(Exception x){};
		}
		
	}
	
	public void slideDown()
	{
		if(((motor.frontWheel.x) > CurrentRamp.x1 ) && ((motor.frontWheel.x) < CurrentRamp.x2))
		{						
			if((motor.frontWheel.x )<=(CreateRamps.x4 + 40))
				CreateRamps.create2 = true;
			motor.downRamp(ramp);
			motor.dir = MotorCycle.UP;						
		}
		else if(CurrentRamp.y1 == CurrentRamp.y2)
		{							
			motor.isFallingDown = true;							
			motor.projectileMotion();					
		}
	}
	
	boolean movingDown = false;
	
	public void paint(Graphics g)
	{
		if(!pressedStart)
		{
			
			g.drawImage(startPage, 0, 0, 1500, 750, null);
			g.drawImage(playButton, 300, 400, 350, 100, null);
		}
		
		else
		{
		
		background1.draw(g);
		background3.draw(g);
		background2.draw(g);
		
		track.draw(g);
		ramp.draw(g);
		
		coin1.draw(g);
		coin1_1.draw(g);
		coin2.draw(g);
		coin2_1.draw(g);
		coin3.draw(g);
		coin3_1.draw(g);
		coin4.draw(g);
		coin5.draw(g);
		coin6.draw(g);
		coin7.draw(g);
		coin8.draw(g);
		
		coin9.draw(g);
		coin10.draw(g);
		coin11.draw(g);
		coin12.draw(g);
		coin13.draw(g);
		
		for(int i=0;i<rocks.length;i++){
			rocks[i].draw(g);
		}
		
		if(Camera.x>=500){
			for(int i=0;i<rocks1.length;i++){
				rocks1[i].draw(g);
			}
		}
		if(Camera.x>=1010){
			for(int i=0;i<rocks2.length;i++){
				rocks2[i].draw(g);
			}
		}

		
		g.drawImage(gate1,4500-Camera.x,320,null);
		motor.draw(g);
		g.drawImage(gate2,4500-Camera.x,320,null);
		
		if (motor.hasCollidedWith(coin1.x,coin1.y,coin1.w, coin1.h )){
			score+=coin1.score;
			grabCoin.play();
			coin1.y = 4000;
		}
		if (motor.hasCollidedWith(coin1_1.x,coin1_1.y,coin1_1.w, coin1_1.h )){
			score+=coin1_1.score;
			grabCoin.play();
			coin1_1.y = 4000;
		}
		
		if (motor.hasCollidedWith(coin2.x,coin2.y,coin2.w, coin2.h )){
			score+=coin2.score;
			grabCoin.play();
			coin2.y = 5000;
		}
		if (motor.hasCollidedWith(coin2_1.x,coin2_1.y,coin2_1.w, coin2_1.h )){
			score+=coin2_1.score;
			grabCoin.play();
			coin2_1.y = 4000;
		}
		if (motor.hasCollidedWith(coin3.x,coin3.y,coin3.w, coin3.h )){
			score+=coin3.score;
			grabCoin.play();
			coin3.y = 4000;
		}
		if (motor.hasCollidedWith(coin3_1.x,coin3_1.y,coin3_1.w, coin3_1.h )){
			score+=coin3_1.score;
			grabCoin.play();
			coin3_1.y = 4000;
		}
		if (motor.hasCollidedWith(coin4.x,coin4.y,coin4.w, coin4.h )){
			score+=coin4.score;
			grabCoin.play();
			coin4.y = 4000;
		}
		
		if (motor.hasCollidedWith(coin5.x,coin5.y,coin5.w, coin5.h )){
			score+=coin5.score;
			grabCoin.play();
			coin5.y = 4000;
		}
		if (motor.hasCollidedWith(coin6.x,coin6.y,coin6.w, coin6.h )){
			score+=coin6.score;
			grabCoin.play();
			coin6.y = 4000;
		}
		if (motor.hasCollidedWith(coin7.x,coin7.y,coin7.w, coin7.h )){
			score+=coin7.score;
			grabCoin.play();
			coin7.y = 4000;
		}
		if (motor.hasCollidedWith(coin8.x,coin8.y,coin8.w, coin8.h )){
			score+=coin8.score;
			grabCoin.play();
			coin8.y = 4000;
		}
		if (motor.hasCollidedWith(coin9.x,coin9.y,coin9.w, coin9.h )){
			score+=coin9.score;
			grabCoin.play();
			coin9.y = 4000;
		}
		
		if (motor.hasCollidedWith(coin10.x,coin10.y,coin10.w, coin10.h )){
			score+=coin10.score;
			grabCoin.play();
			coin10.y = 4000;
		}
		if (motor.hasCollidedWith(coin11.x,coin11.y,coin11.w, coin11.h )){
			score+=coin11.score;
			grabCoin.play();
			coin11.y = 4000;
		}
		if (motor.hasCollidedWith(coin12.x,coin12.y,coin12.w, coin12.h )){
			score+=coin12.score;
			grabCoin.play();
			coin12.y = 4000;
		}
		
		if (motor.hasCollidedWith(coin13.x,coin13.y,coin13.w, coin13.h )){
			score+=coin13.score;
			grabCoin.play();
			coin13.y = 4000;
		}
		
		for(int i=0;i<rocks.length;i++){
			if (motor.hasCollidedWith(rocks[i].x+i*100,rocks[i].y,rocks[i].w, rocks[i].h )){
				//score+=coin11.score;
				grabCoin.play();
				increment++;
				rocks[i].y = 4000;
			}
		}
		for(int i=0;i<rocks1.length;i++){
			if (motor.hasCollidedWith(rocks1[i].x+i*100,rocks1[i].y,rocks1[i].w, rocks1[i].h )){
				//score+=coin11.score;
				increment++;
				grabCoin.play();
				rocks1[i].y = 4000;
			}
		}
		
		for(int i=0;i<rocks2.length;i++){
			if (motor.hasCollidedWith(rocks2[i].x+i*100,rocks2[i].y,rocks2[i].w, rocks2[i].h )){
				//score+=coin11.score;
				increment++;
				grabCoin.play();
				rocks2[i].y = 4000;
			}
		}

		
		g.setColor(new Color(13,222,13));
		g.fillRect(40, 25, 160, 40);
		g.setColor(new Color(255,255,255));
		g.setFont(font);
		g.drawString("Your Score: "+score, 60,50);
		
		g.setColor(Color.RED);
		g.fillRect(5000 -Camera.x, 300, 160, 50);
		g.setColor(Color.WHITE);
		g.setFont(font1);
		g.drawString("FINISHED", 5020-Camera.x, 330);
		
		g.setColor(Color.RED);
		g.fillRect(5300 -Camera.x, 300, 100, 50);
		g.setColor(Color.WHITE);
		g.setFont(font1);
		g.drawString("STOP", 5320-Camera.x, 330);
		g.drawImage(flag, 5410-Camera.x, 280, null);
		if(motor.mx-Camera.x >= 5710 -Camera.x)
			stopBackground=false;
		
		
		}
		
		if(increment==8){	
			pressedStart=false;
			motorSound.stop();
			g.drawImage(fin, 0, 0,1500, 750, null);
		}

	}
	
	public void repaint()  
	{
		Graphics g;
		g = this.getGraphics();
		if(g == null);
		else		
		{			
			update(g);
			g.dispose();
		}
	}
	
	public void update(Graphics g)
	{
		offScreen_g.clearRect(0,  0,  1500, 700); 
		paint(offScreen_g); 
		g.drawImage(offScreen, 0, 0, null); 
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
			pressed[e.getKeyCode()] = true;
			if(MotorCycle.v0 < 13)
				MotorCycle.v0 += 1;

			
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
		pressed[e.getKeyCode()] = false;
		MotorCycle.v0 = 2;
		
		if(ramp.hasCollidedWith(motor) && count == 0 )		
			count+=10;
			
		
		
				
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		pressedStart = true;
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
		System.out.println("pressedStart: "+ pressedStart);
	
		
		pressedStart = true;
		
		try{
			Thread.sleep(16);
		}
		catch(Exception e){};
		repaint();
		//pressedStart = true;
		
	
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		pressedStart = true;
		//repaint();
		//try{
		//	Thread.sleep(16);
		//}
		//catch(Exception e){};
		//pressedStart = true;
	}
	
	
	

}
