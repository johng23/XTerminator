package Characters;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class Projectile.
 */
public class Projectile extends MovingImage{
	private static final double MAX_SPEED = 30;

	/**
	 * how much damage the projectile will do to a zombie
	 */
	public int damage;
	
	/**
	 * the velocity of the projectile
	 */
	public double vx, vy;
	
	/**
	 * 
	 * @param x1 the original x position of the projectile
	 * @param y1 the original y position of the projectile
	 * @param x2 used with y2 to determine the velocity of the projectile
	 * @param y2 used with x2 to determine the velocity of the projectile
	 * @param width the width of the projectile
	 * @param height the height of the projectile
	 * @param damage how much damage the projectile will do to a zombie
	 */
	public Projectile(double x1, double y1, double x2, double y2, double width, double height, int damage) {
		super("Batmobile.png", (int)x1, (int)y1, (int)width, (int)height);

		double distance = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
		this.vx = (x2 - x1) / distance * MAX_SPEED;
		this.vy = (y2 - y1) / distance * MAX_SPEED;
		this.damage = damage;
	}
	
	/**
	 * Move the projectile and returns the first shape it collides with.
	 *
	 * @param zombies the list of zombies
	 * @param bezBounds the upper bezier course boundary
	 * @param bezBounds1 the lower bezier course boundary
	 * @param xDisp to account for the scrolling of the course
	 * @return the first shape that the projectile collides with. if it doesnt collide, then return null
	 */
	public Shape move(ArrayList<Zombie> zombies, ArrayList<Ellipse2D.Double> bezBounds, ArrayList<Ellipse2D.Double> bezBounds1, int xDisp) {

		Rectangle2D.Double yMotion;
		Rectangle2D.Double xMotion;
		
		if(vy > 0)
			yMotion = new Rectangle2D.Double(x, y, width, height + vy);
		else
			yMotion = new Rectangle2D.Double(x, y + vy, width, height - vy);
		if(vx > 0)
			xMotion = new Rectangle2D.Double(x, y, width + vx, height);
		else
			xMotion = new Rectangle2D.Double(x + vx, y, width - vx, height);
		
		for(Zombie s : zombies) {
			if(s.intersects(yMotion)) {
				return s;
			}
			if(s.intersects(xMotion)) {

				return s;
			}
		}

		xMotion.x += xDisp;
		yMotion.y += xDisp;
		for(Ellipse2D.Double e : bezBounds) {
			if(e.intersects(yMotion)) {
				return e;
			}
			if(e.intersects(xMotion)) {
				return e;
			}
		}
		for(Ellipse2D.Double e : bezBounds1) {
			if(e.intersects(yMotion)) {
				return e;
			}
			if(e.intersects(xMotion)) {
				return e;
			}
		}
	

		x += vx;
		y += vy;
		return null;
	}

	
}
