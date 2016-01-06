package Characters;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.*;

// TODO: Auto-generated Javadoc
/**
 * The Class Car.
 */
public class Car extends MovingImage {

	
	private static final long serialVersionUID = 1L;
	
	/** The Width of the Car. */
	public static final int CAR_WIDTH = 60;
	
	/** The Height of the Car. */
	public static final int CAR_HEIGHT = 30;
	
	/** */
	private double yVel, xVel;
	
	/***/
	
	private int maxSpeed;
	
	/** The down. */
	private boolean notCollided, right, left, up, down;

	private int xLimit;
	
	/**
	 * Creates a new Car object with a location at x and y.
	 *
	 * @param x the initial X-Coordinate of the Car
	 * @param y the initial Y-Coordinate of the Car
	 */
	public Car(int x, int y) {
		
		super("Batmobile.png", x, y, CAR_WIDTH, CAR_HEIGHT);
		yVel = 0;
		xVel = 0;
		notCollided = true;
		right=false;
		left=false;
		up=false;
		down=false;
		maxSpeed=7;

	}

	// METHODS

	/**
	 * Accelerate.
	 */
	private void accelerate(){
		if(xVel<maxSpeed){
			xVel+=.5;
		}
	}
	
	/**
	 * Decelerate.
	 */
	private void decelerate(){
		if(xVel>0){
			xVel*=.25;
		}
	}

	
	/**
	 * Moves car forward based on the velocity of the car in the horizontal direction
	 */
	public void forward(){
		if(notCollided)
			x = ((int)(x + xVel));
	}
	
	/**
	 * Moves car backward based on the velocity of the car in the horizontal direction
	 * 
	 */
	public void backward(){
		if(notCollided)
			x = ((int)(x - xVel));
	}

	
	
	/**
	 * Moves the car to its left
	 */
	public void up(){
		if(notCollided)
			y = (y - 4);
	}
	
	/**
	 * Moves the car to its right
	 */
	public void down(){
		if(notCollided)
			y = (y + 4);
	}
	
	
	/**
	 * Explodes the car upon the detection of a collision
	 * @post If a collision is detected, the car's image is replaced by an Explosion gif
	 */
	public void explode(){

	}
	
	/**
	 * Moves the car based on whether the user wants it to go up, down, right, or left
	 */
	public void move(){
		if(right || left){
			accelerate();
		}
		else{
			decelerate();
		}
		if(right && x<=xLimit)
			forward();
		else if(left)
			backward();
		
		
		if(down)
			down();
		else if(up)
			up();
	}



	/**
	 * The method to simulate the actions of the car based on its location and movement
	 *
	 * @param obstacles the obstacles
	 * @return true, if successful
	 */
	public boolean act(ArrayList<Ellipse2D.Double> obstacles, ArrayList<Ellipse2D.Double> obstacles2, double xDisp) {
		
		

		Rectangle2D.Double yMotion;
		Rectangle2D.Double xMotion;
		
		if(yVel > 0)
			yMotion = new Rectangle2D.Double(this.x + xDisp, this.y, this.width, this.height + yVel);
		else
			yMotion = new Rectangle2D.Double(this.x + xDisp, this.y + yVel, this.width, this.height - yVel);
		if(xVel > 0)
			xMotion = new Rectangle2D.Double(this.x + xDisp, this.y, this.width + xVel, this.height);
		else
			xMotion = new Rectangle2D.Double(this.x + xVel + xDisp, this.y, this.width - xVel, this.height);

		for(Ellipse2D.Double s : obstacles)
		{

			if(s.intersects(yMotion))
			{
				notCollided = false;
				move();
			}
		}
		for(Ellipse2D.Double s : obstacles)
		{

			if(s.intersects(xMotion))
			{
				notCollided = false;
				move();

			}
		}
		for(Ellipse2D.Double s : obstacles2)
		{

			if(s.intersects(yMotion))
			{
				notCollided = false;
				move();

			}
		}
		for(Ellipse2D.Double s : obstacles2)
		{

			if(s.intersects(xMotion))
			{
				notCollided = false;
				move();
			}
		}
		if(!notCollided) {
			System.out.println("swag");
		}
		move();
		return notCollided;
	
	}
	
	/**
	 *Returns X-Coordinate of the Car
	 *
	 * @return xPos The X-ccordinate of the Car
	 */
	
	/**
	 * Checks if the user wants the car to move upwards.
	 *
	 * @return true, if is up
	 */
	public boolean isUp(){
		return up;
	}
	
	/**
	 * Checks if the 
	 *
	 * @return true, if is down
	 */
	public boolean isDown(){
		return down;
	}
	
	/**
	 * Checks if is left.
	 *
	 * @return true, if is left
	 */
	public boolean isLeft(){
		return left;
	}
	
	/**
	 * Checks if is right.
	 *
	 * @return true, if is right
	 */
	public boolean isRight(){
		return right;
	}
	
	/**
	 * Collision.
	 *
	 * @return true, if successful
	 */
	public boolean collision(){
		return notCollided;
	}
	
	/**
	 * Sets the up.
	 *
	 * @param up the new up
	 */
	public void setUp(boolean up){
		this.up=up;
	}
	
	/**
	 * Sets the down.
	 *
	 * @param down the new down
	 */
	public void setDown(boolean down){
		this.down=down;
	}
	
	/**
	 * Sets the left.
	 *
	 * @param left the new left
	 */
	public void setLeft(boolean left){
		this.left=left;
	}
	
	/**
	 * Sets the right.
	 *
	 * @param right the new right
	 */
	public void setRight(boolean right){
		this.right=right;
	}
	
	public void setXLimit(int x){
		xLimit=x;
	}
}
