

public class Camera {
	
	static int x;
	static int y;
	


	public static void set(int xp, int yp) {
		x = xp;
		y = yp;

	}

	public static void moveForwardBy(int dx) {
		x += dx;

	}

	public static void moveBackwardBy(int dx) {
		x -= dx;

	}

}