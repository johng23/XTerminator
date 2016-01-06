package Characters;


import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.*;

import javax.swing.*;


/*
 * Represents a moving image.
 *
 * by: Shelby
 * on: 5/3/13
 */
 
/**
 * The Class MovingImage, designed to represent any image that would move onscreen
 */
public class MovingImage extends Rectangle2D.Double {
	
	// FIELDS
	
	private java.awt.Image image;
	// CONSTRUCTORS
	/**
	 * Instantiates a new MovingImage.
	 *
	 * @param filename the filename of the image that would move on screen
	 * @param x  The xCoordinate of the image
	 * @param y  The yCoordinate of the image
	 * @param w  The Image width
	 * @param h  The Image height
	 */
	public MovingImage(String filename, int x, int y, int w, int h) {
		this((new ImageIcon(filename)).getImage(),x,y,w,h);
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Instantiates a new moving image.
	 *
	 * @param image2 the actual image that would be miving on screen
	 * @param x The xCoordinate of the image
	 * @param y  The yCoordinate of the image
	 * @param w  The Image width
	 * @param h  The Image height
	 */
	public MovingImage(java.awt.Image image2, int x, int y, int w, int h) {
		super(x,y,w,h);
		image = image2;
		this.x = x;
		this.y = y;
	}
	
	
	// METHODS	
	/**
	 * Move to location.
	 *
	 * @param x the x
	 * @param y the y
	 */
	public void moveToLocation(int x, int y) {
		super.x = x;
		super.y = y;
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Move by amount.
	 *
	 * @param x the x
	 * @param y the y
	 */
	public void moveByAmount(int x, int y) {
		super.x += x;
		super.y += y;
		this.x = this.x + x;
		this.y = this.y + y;
	}
	
	/**
	 * Apply window limits.
	 *
	 * @param windowWidth the window width
	 * @param windowHeight the window height
	 */
	public void applyWindowLimits(int windowWidth, int windowHeight) {
		x = Math.min(x,windowWidth-width);
		y = Math.min(y,windowHeight-height);
		x = Math.max(0,x);
		y = Math.max(0,y);
	}
	
	
	/**
	 * Draw.
	 *
	 * @param g the g
	 * @param io the io
	 */
	public void draw(Graphics g, ImageObserver io) {
		g.drawImage(image,(int)x,(int)this.y,(int)width,(int)height,io);
	}
	
	/**
	 * Rotate.
	 *
	 * @param deg the deg
	 * @param io the io
	 */
	public void rotate(double deg, ImageObserver io)
	{
		ImageIcon im = new ImageIcon(this.image);
		BufferedImage pane = new BufferedImage(im.getIconWidth(),im.getIconHeight(),BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = (Graphics2D)pane.getGraphics();
		g.rotate(Math.toRadians(deg), im.getIconWidth()/2, im.getIconHeight()/2);
		g.drawImage(this.image,0,0, io);
		this.image = pane;
	}

	
	
}

