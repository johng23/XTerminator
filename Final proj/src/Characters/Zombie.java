package Characters;



import java.awt.*;

import java.awt.geom.Rectangle2D;
import java.util.*;

// TODO: Auto-generated Javadoc
/**
 * The Class Zombie.
 */
public class Zombie extends MovingImage {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The Constant WIDTH. */
	public static final int WIDTH = 40;
	
	/** The Constant HEIGHT. */
	public static final int HEIGHT = 60;
	
	/** The health. */
	public int health;
	
	/** The x vel. */
	double yVel, xVel;
	
	/** The hit. */
	private boolean hit;
	
	/**
	 * Instantiates a new zombie.
	 *
	 * @param x the x
	 * @param y the y
	 */
	public Zombie(int x, int y) {
		super("zombie.gif", x, y, WIDTH, HEIGHT);
		yVel = 0;
		xVel = 7;		
		health = 100;
		hit=false;
	}
	
	/**
	 * Move.
	 */
	public void move() {
		x -= xVel;
		
	}
	
	/**
	 * Explode.
	 */
	public void explode(){
		
	}


}
