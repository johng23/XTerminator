package Characters;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

// TODO: Auto-generated Javadoc
/**
 * The Class BezierCourse.
 */
public class BezierCourse {
	private static final double accuracyinv = .02;
	private static final double oneminusai = .999;
	/** The j. */
	private int h, l, j;
	
	/** The w. */
	public int w;
	
	/** The bez course. */
	public ArrayList<Ellipse2D.Double> bezCourse;
	
	/** The circle xy. */
	
	/** The bez points. */
	private Point[] bezPoints;
	private int y;
	/**
	 * Instantiates a new bezier course.
	 *
	 * @param height the height of the bezier course
	 * @param width the width of individual ellipses
	 * @param length the length of the bezier course
	 * @param jaggedness how complicated the course is
	 */
	public BezierCourse(int startY, int height, int width, int length, int jaggedness) {
		y = startY;
		h = height;
		w = width;
		j = jaggedness * 3 + 1;
		l = length * 30 / j;
		bezPoints = new Point[j];
		bezCourse = new ArrayList<Ellipse2D.Double>();
		makePoints(0);
		makeBezierCourseCircles();
	}

	/**
	 * refreshes the beziercourse so that it continues its path
	 */
	public void refresh() {
		Point temp = bezPoints[j - 1];
		makePoints(temp.x);
		bezPoints[0] = temp;
		makeBezierCourseCircles();
	}


	private void makePoints(int start) {
		for (int i = 0; i < j; i++) {
			bezPoints[i] = new Point(start + i * l, (int) (Math.random() * (h)) + y);
		}
	}

	private Point2D.Double bezierFunction(int x, int y, int x1, int y1, int x2,
			int y2, int x3, int y3, double t)// generates a point that follows a
												// Bezier
												// Curve at parametric t
	{
		double xCoord = (1 - t) * (1 - t) * (1 - t) * x + 3 * (1 - t) * (1 - t)
				* t * x1 + 3 * (1 - t) * t * t * x2 + t * t * t * x3;
		double yCoord = (1 - t) * (1 - t) * (1 - t) * y + 3 * (1 - t) * (1 - t)
				* t * y1 + 3 * (1 - t) * t * t * y2 + t * t * t * y3;

		return new Point2D.Double((xCoord), (yCoord));
	}
	private void makeBezierCourseCircles() {
		for (int i = 0; i < j - 1; i += 3) {
			int x = bezPoints[i].x;
			int y = bezPoints[i].y;
			int x1 = bezPoints[i + 1].x;
			int y1 = bezPoints[i + 1].y;
			int x2 = bezPoints[i + 2].x;
			int y2 = bezPoints[i + 2].y;
			int x3 = bezPoints[i + 3].x;
			int y3 = bezPoints[i + 3].y;
			for (double t = 0; t < oneminusai; t += accuracyinv) {
				Point2D.Double a = bezierFunction(x, y, x1, y1, x2, y2, x3, y3, t);
				bezCourse.add(new Ellipse2D.Double(a.x, a.y, w, w));
			}

		}
	}

		
}
