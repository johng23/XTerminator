package Characters;

import java.awt.geom.Rectangle2D;

// TODO: Auto-generated Javadoc
/**
 * The Class Obstacle.
 */
public class Obstacle extends MovingImage{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8972459592452712382L;
	
	/** The Constant IMAGE_WIDTH. */
	public static final int IMAGE_WIDTH = 50;
	
	/** The Constant IMAGE_LENGTH. */
	public static final int IMAGE_LENGTH = 50;
	
	/** The x vel. */
	private int xVel;
	
	/**
	 * Instantiates a new obstacle.
	 *
	 * @param x the x
	 * @param y the y
	 * @param xDispSpeed the x disp speed
	 */
	public Obstacle(int x, int y, int xDispSpeed) {
		super("tnt.png", x, y, IMAGE_WIDTH, IMAGE_LENGTH);
		xVel = -xDispSpeed;
	}
	
	/**
	 * Move.
	 *
	 * @param c the c
	 * @return true, if successful
	 */
	public boolean move(Car c) {
		
		Rectangle2D.Double xMotion = new Rectangle2D.Double(this.x + xVel, this.y, this.width - xVel, this.height);
		x += xVel;

		if(c.intersects(xMotion)){
			
			
			return true;
			
		}
		return false;
	}
	
	
}
