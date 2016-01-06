package Characters;
/**
 * A Class that contains methods to update a BezierCourse with new points if the Current amount of points is not sufficient for the course to appear on screen
 * 
 * */
public class BezCourseUpdater implements Runnable {
	public BezierCourse bez;
	public boolean update;

	/**
	 * Creates a BezCourseUpdater object with a list BezPoints and a boolean whether to update the course or not
	 * 
	 * @param b An ArrayList of Ellipse2D.Double objects 
	 * @param u A boolean whether to update the BezCourse or not
	 * 
	 * */
	public BezCourseUpdater(BezierCourse b, boolean u){
		bez=b;
		update=u;
	}
	
	/**
	 * Based on the state of update, updates the given ArrayList or does not make any changes
	 * 
	 * @postcondition The 
	 * */
	public void run() {
		if(update)
			bez.refresh();
		
	}
	
	

}
