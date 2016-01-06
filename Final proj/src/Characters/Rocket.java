package Characters;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;


public class Rocket extends Projectile{
	
	private static final double MAX_SPEED = 20;
	/**
	 * A rocket follows the nearest zombie
	 * @param x the starting x position
	 * @param y the starting y position
	 * @param x1 used with x1 to calculate the velocity 
	 * @param y1 used with y1 to calculate the velocity
	 * @param width the width of the rocket
	 * @param height the height of the rocket
	 * @param damage how much damage the rocket will do
	 */
	public Rocket(double x, double y, double x1, double y1, double width, double height,
			int damage) {
		super(x, y, x1, y1, width, height, damage);
		
	}
	private void setVelocity(ArrayList<Zombie> zombies) {
		if(zombies.size() == 0)
			return;
		double distance = Math.sqrt((zombies.get(0).getCenterX() - x) * (zombies.get(0).getCenterX() - x) + (zombies.get(0).getCenterY() - y) * (zombies.get(0).getCenterY() - y));
		int index = 0;
		for(int i = 1; i < zombies.size(); i++) {
			double newDistance = Math.sqrt((zombies.get(i).getCenterX() - x) * (zombies.get(i).getCenterX() - x) + (zombies.get(i).getCenterY() - y) * (zombies.get(i).getCenterY() - y));
			
			if(newDistance < distance) {
				distance = newDistance;
				index = i;
			}
		}
		double distanceX = zombies.get(index).getCenterX() - x;
		double distanceY = zombies.get(index).getCenterY() - y;
		double scale = distance / MAX_SPEED;
		vx = distanceX / scale;
		vy = distanceY / scale;
	}
	/**
	 * sets the velocity before moving. returns the first shape that it collides with. if it doesnt collide then returns null
	 * 
	 */
	public Shape move(ArrayList<Zombie> zombies, ArrayList<Ellipse2D.Double> bezBounds, ArrayList<Ellipse2D.Double> bezBounds1, int xDisp) {
		setVelocity(zombies);
		return super.move(zombies, bezBounds, bezBounds1, xDisp);
	}
}
