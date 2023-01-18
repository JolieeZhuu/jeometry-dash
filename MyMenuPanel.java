/*
 * Names: Simone Ghosh and Jolie Zhu
 * Teacher: Ms. Strelkovska
 * Course: ICS3U7-1
 * Date: January 18, 2023
 * Description: Main menu panel of Jeometry Dash
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MyMenuPanel extends JPanel implements ActionListener {
	
	// declare instance variables
	private JButton[] buttons; 

	private JPanel buttonP;
	private MovingBG bgP;

	private ImageIcon[] imgs;
	
	
	public MyMenuPanel() {

		buttonP = new JPanel(); // initialize variables
		bgP = new MovingBG();
		
		imgs = new ImageIcon[4];
		buttons = new JButton[3];
		
		imgs[0] = new ImageIcon("Images/instButton.png");
		imgs[1] = new ImageIcon("Images/playButton.png");
		imgs[2] = new ImageIcon("Images/exitButton.png");
		imgs[3] = new ImageIcon("Images/title.png");
		
		//play.setPreferredSize(new Dimension(150, 150));
		//exit.setPreferredSize(new Dimension(100, 100));
		
		this.setLayout(new BorderLayout(0, 0));
		setBackground(Color.BLUE);
		
		// add panels to panel
		this.add(buttonP, BorderLayout.CENTER);  
		buttonP.setOpaque(false);
		buttonP.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 200));
		
		// add buttons to panel
		for (int i = 0; i < 3; i++) {
			buttons[i] = new JButton(imgs[i]);
			buttons[i].setOpaque(false);
			buttons[i].setContentAreaFilled(false);
			buttons[i].setBorderPainted(false);
			buttons[i].addActionListener(this);
			buttonP.add(buttons[i]);
		}

	} // end of constructor

	
	public void actionPerformed(ActionEvent e) {
		
		bgP.actionPerformed(e); // add background animation from MovingBG class
		
		if (e.getSource() == buttons[1]) { 
			JeometryDash.cardsL.show(JeometryDash.c, "Levels");
		} else if (e.getSource() == buttons[0]) 
			JeometryDash.cardsL.show(JeometryDash.c, "Instructions");
		else if (e.getSource() == buttons[2]) 
			System.exit(0);
		repaint();
		
	} // end of actionPerformed
	
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		bgP.paintComponent(g); // add background
		g.drawImage(imgs[3].getImage(), (getWidth() - 550) / 2, 75, 550, 70, null); // title image of menu panel

	} // end of paintComponent
	
} // end of MyMenuPanel