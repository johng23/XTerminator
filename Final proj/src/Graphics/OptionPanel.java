package Graphics;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class OptionPanel extends JPanel implements ActionListener {
	
	Xterminator w;
	AnimPanel p;
	
	public OptionPanel(Xterminator w, AnimPanel pane) {
		this.w = w;
		p = pane;
		JButton button = new JButton("Begin");
		button.addActionListener(this);
		add(button);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		w.changePanel();
		Thread t = new Thread(p);
		t.start();
		
	}
	
}