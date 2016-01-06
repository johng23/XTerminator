package Graphics;


import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.*;


// TODO: Auto-generated Javadoc
/**
 * The Class Animoto.
 */
public class Xterminator extends JFrame {
	
	JPanel cardPanel;
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	AnimPanel panel=new AnimPanel();
	/**
	 * Instantiates a new animoto.
	 *
	 * @param x the x
	 * @param y the y
	 */
	public Xterminator(int x, int y){
		super();
		this.setBounds(x, y, 1000, 540);
		setBackground(Color.WHITE);
		
		
		panel=new AnimPanel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.setBackground(Color.WHITE);
		//panel.start(false);
		add(panel);
		//an.addKeyListener(panel);
		panel.addKeyListener(panel);
		panel.addMouseListener(panel);
		setVisible(true);
		panel.setVisible(true);
		panel.repaint();
		setResizable(false);
		//new Thread(panel).start();
		
	
	    
	    cardPanel = new JPanel();
	    CardLayout cl = new CardLayout();
	    cardPanel.setLayout(cl);
	    
		OptionPanel panel1 = new OptionPanel(this, panel); 
		panel1.setBackground(Color.BLACK);
	    
	    addKeyListener(panel);
	
	    cardPanel.add(panel1,"1");
	    cardPanel.add(panel,"2");
	    
	    add(cardPanel);
	
	    setVisible(true);
		
		
		
		
	}
	public void changePanel() {
		((CardLayout)cardPanel.getLayout()).next(cardPanel);
		requestFocus();
		
		//panel.start(true);
		
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[]args){
		new Xterminator(200, 200);
		

	}

}
