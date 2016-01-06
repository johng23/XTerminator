package Characters;

import java.awt.geom.Rectangle2D;

public class PowerUp extends MovingImage{
	public static final int IMAGE_WIDTH = 50;
	public static final int IMAGE_LENGTH = 50;
	private int xVel;
	/**
	 * 
	 * @param image - the filename of the image of the powerup
	 * @param x x position of powerup
	 * @param y y position of powerup
	 * @param xDispSpeed used to calculate the speed at which the powerup should move
	 */
	public PowerUp(int x, int y, int xDispSpeed) {
		super("powerup1.png", x, y, IMAGE_WIDTH, IMAGE_LENGTH);
		xVel = -xDispSpeed;
	}
	/**
	 * moves the powerup and returns whether it intersected with the car
	 * @param Car c must not be null
	 * @return true if c intersects with this powerup, else returns false
	 */
	public boolean move(Car c) {
		
		Rectangle2D.Double xMotion = new Rectangle2D.Double(this.x + xVel, this.y, this.width - xVel, this.height);
		x += xVel;

		if(c.intersects(xMotion))
			return true;
		return false;
	}
	
}
