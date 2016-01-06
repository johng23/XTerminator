package Graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Timer;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Characters.BezCourseUpdater;
import Characters.BezierCourse;
import Characters.Car;
import Characters.Obstacle;
import Characters.PowerUp;
import Characters.Projectile;
import Characters.Rocket;
import Characters.Zombie;
//import Characters.ZombieHorde;


// TODO: Auto-generated Javadoc
/**
 * The Class AnimPanel.
 */
public class AnimPanel extends JPanel implements Runnable, KeyListener,
		MouseListener {

	Image img;
	private static final int X_DISP_SPEED = 5;
	private static final int BEZ_COURSE_ACCURACY = 50;
	private static final int PROJ_SIZE = 10;
	private static final long serialVersionUID = 8225224525747655350L;
	private int xDisp, yDisp;
	private int jaggedness = 10;
	private boolean goRight;
	
	BezierCourse a;
	BezierCourse b;
	private boolean up, down, left, right, gameRunning;
	private int xPos, yPos;
	private ArrayList<Projectile> projectiles;
	boolean shoot;
	double projX, projY;
	int numShots;
	private ArrayList<PowerUp> powerUps;
	private int numZPassed, numPower;
	private ArrayList<Obstacle> obstacles;
	
	
	Car c;
	private ArrayList<Zombie> zombies;
	int numRockets;
	

	public AnimPanel() {
		xDisp = 0;
		ImageIcon i = new ImageIcon("E:\\workspace\\Animation");
		img = i.getImage();
		setFocusable(true);
		a = new BezierCourse(0, 100, 30, 40, jaggedness);
		b = new BezierCourse(400, 100, 30, 40, jaggedness);
		a.refresh();
		b.refresh();
		c = new Car(0, 200);
		
		up = false;
		down = false;
		left = false;
		right = false;
		xPos = 0;
		yPos = 0;
		gameRunning = true;
		projectiles = new ArrayList<Projectile>();
		powerUps = new ArrayList<PowerUp>();
		zombies = new ArrayList<Zombie>();
		obstacles = new ArrayList<Obstacle>();
		numRockets = 0;
		numShots = 1;
		
		numZPassed=0;
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		g.setColor(Color.WHITE);

		
		setBackground(Color.BLACK);
		for (int i = a.bezCourse.size() - BEZ_COURSE_ACCURACY * 20; i < a.bezCourse.size(); i++) {

			g.fillOval((int) (a.bezCourse.get(i).x - xDisp),
					(int) a.bezCourse.get(i).y, a.w, a.w);
		}
		
		

		for (int i = b.bezCourse.size() - BEZ_COURSE_ACCURACY * 20; i < b.bezCourse.size(); i++) {

			g.fillOval((int) (b.bezCourse.get(i).x - xDisp),
					(int) b.bezCourse.get(i).y, b.w, b.w);
		}

		c.draw(g, this);
		for (Projectile p : projectiles) {
			p.draw(g, this);
		}
		for (PowerUp p : powerUps) {
			p.draw(g, this);
		}
		
		for (Zombie z : zombies) {
			z.draw(g, this);
		}
		
		for (Obstacle o : obstacles) {
			o.draw(g, this);
		}
		
		Font font=new Font("Serif", Font.BOLD, 40);
		g.setFont(font);
		g.setColor(Color.RED);
		if(gameRunning)
			g.drawString(""+(10-numZPassed)+" to Death", 800, 50);
		else {
			font=new Font("Serif", Font.BOLD, getHeight()-400);
			g.setFont(font);
			g.setColor(Color.RED);
			g.drawString("TERMINATED", 0, getHeight()-300);
		}
	}

	public void run() {

		//Zombies
		
		
		
	
		
		while (gameRunning) {

			// MAKE A CHANGE
			
			
			xDisp += X_DISP_SPEED;
			boolean update=b.bezCourse.get(b.bezCourse.size() - 1).x - xDisp - 20 < getWidth();
			BezCourseUpdater bcu1=new BezCourseUpdater(a, update);
			BezCourseUpdater bcu2=new BezCourseUpdater(b, update);
			
			Thread t1=new Thread(bcu1);
			Thread t2=new Thread(bcu2);
			long start=System.currentTimeMillis();
			t1.start();
			t2.start();
			
			a=bcu1.bez;
			b=bcu2.bez;
			
			/*if(b.bezCourse.get(b.bezCourse.size() - 1).x - xDisp - 20 < getWidth()){
				a.refresh();
				b.refresh();
			}*/

			
			
			//Projectile Movement
			if (shoot) {
				shoot = false;
				double dist = Math.sqrt((projX - c.x) * (projX - c.x)+ (projY - c.y) * (projY - c.y));
				double xDif = (projY - c.y) * 50 / dist;
				double yDif = (projX - c.x) * 50 / dist;
				if (numShots % 2 == 1) {
					for (int i = 0; i < numShots; i++) {
						Projectile a = new Projectile(c.x + (numShots / 2 - i)
								* xDif + c.width, c.y - (numShots / 2 - i)
								* yDif + c.height / 2, projX, projY, PROJ_SIZE,
								PROJ_SIZE, 50);
						projectiles.add(a);
					}
				} else {
					for (int i = 0; i < numShots; i++) {
						Projectile a = new Projectile(c.x + (numShots / 2 - i)
								* xDif + xDif / 2 + c.width, c.y
								- (numShots / 2 - i) * yDif + yDif / 2
								+ c.height / 2, projX, projY, PROJ_SIZE,
								PROJ_SIZE, 50);
						projectiles.add(a);
					}
				}
				if (numRockets % 2 == 1) {
					for (int i = 0; i < numRockets; i++) {
						Rocket a = new Rocket(c.x + (numRockets / 2 - i)
								* xDif + c.width, c.y - (numRockets / 2 - i)
								* yDif + c.height / 2, projX, projY, PROJ_SIZE,
								PROJ_SIZE, 50);
						projectiles.add(a);
					}
				} else {
					for (int i = 0; i < numRockets; i++) {
						Rocket a = new Rocket(c.x + (numRockets / 2 - i)
								* xDif + xDif / 2 + c.width, c.y
								- (numRockets / 2 - i) * yDif + yDif / 2
								+ c.height / 2, projX, projY, PROJ_SIZE,
								PROJ_SIZE, 50);
						projectiles.add(a);
					}
				}
			}
			try{
				for (int i = 0; i < projectiles.size(); i++) {
					Projectile p = projectiles.get(i);
					Shape s = p.move(zombies,
							a.bezCourse, b.bezCourse, xDisp);
					if (s instanceof Ellipse2D.Double) {
						projectiles.remove(i);
						i--;
					}
					if (s instanceof Zombie) {
						projectiles.remove(i);
						i--;
						((Zombie) s).health -= p.damage;
					}
					if (p.x < -100 || p.x > 1100) {
						projectiles.remove(i);
						i--;
					}

				}
			}catch(Exception e){
				
			}
			
			
			
			while(projectiles.size()>2)
			{
				projectiles.remove(projectiles.size()-1);
			}
			//Car Moving
			c.setXLimit((int) (getWidth()/2.5));
			try{
				gameRunning = c.act(a.bezCourse, b.bezCourse, xDisp);
			}catch(Exception e){
				
			}
			//Power Ups
			if (xDisp % 800 == 0) {
				powerUps.add(new PowerUp(1000,
						(int) (Math.random() * 200 + 130), X_DISP_SPEED));
			}
			for (int i = 0; i < powerUps.size(); i++) {
				PowerUp p = powerUps.get(i);
				if (p.move(c)) {
					if (numShots < 3)
						numShots++;
					else if(numRockets < 3)
						numRockets++;
					powerUps.remove(i);
					i--;
				}
				if (p.x < -100) {
					powerUps.remove(i);
					i--;
				}

			}
			
			//Obstacles
			if((xDisp%800)==0){
				obstacles.add(new Obstacle(1200, (int)(Math.random()*200 + 130), X_DISP_SPEED));
			}
			
			for (int i = 0; i < obstacles.size(); i++) {
				Obstacle o = obstacles.get(i);
				if (o.move(c)) {
					gameRunning = false;
					i--;
				}
				
				if(o.x<50)
				{
					try{
					obstacles.remove(i);
					i--;
					}catch(Exception ex)
					{
						
					}
				}
				

			}
			
			if(numZPassed>=10){
				gameRunning=false;
			}
			
			
			
			if(Math.random() > .95) {
				zombies.add(new Zombie(1000,(int) (Math.random() * 200 + 130)));
			}
			for(int i = 0; i < zombies.size(); i++) {
				Zombie z = zombies.get(i);
				z.move();
				if(z.x < -5) {
					zombies.remove(i);
					numZPassed++;
					i--;
				}
				else if(z.health <= 0) {
					zombies.remove(i);
					i--;
				}
			}
			
			// REPAINT
			repaint();

			// WAIT
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		yPos = 0;
		xPos = 0;
		if (e.getKeyCode() == KeyEvent.VK_W) {
			c.setUp(true);
			yPos = 0;
		} else if (e.getKeyCode() == KeyEvent.VK_S) {
			c.setDown(true);
			yPos = 0;
		}
		if (e.getKeyCode() == KeyEvent.VK_A) {
			c.setLeft(true);
			xPos = 0;
		} else if (e.getKeyCode() == KeyEvent.VK_D) {
			c.setRight(true);
			xPos = 0;

		}
		// repaint();

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W) {
			c.setUp(false);
		} else if (e.getKeyCode() == KeyEvent.VK_S) {
			c.setDown(false);
		}
		if (e.getKeyCode() == KeyEvent.VK_A) {
			c.setLeft(false);
		} else if (e.getKeyCode() == KeyEvent.VK_D) {
			c.setRight(false);
		}
		// repaint();
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		projX = e.getX();
		projY = e.getY();
		shoot = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
