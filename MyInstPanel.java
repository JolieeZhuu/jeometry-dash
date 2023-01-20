/*
 * Names: Simone Ghosh and Jolie Zhu
 * Teacher: Ms. Strelkovska
 * Course: ICS3U7-1
 * Date: January 19, 2023
 * Description: Instructions panel of Jeometry Dash
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MyInstPanel extends JPanel implements ActionListener {

	private JPanel northP;  // declare instance variables
	private JButton goMenu;
	private ImageIcon bg, backImg;

	
	public MyInstPanel() {
		
		northP = new JPanel(); // initialize variables	
		
		bg = new ImageIcon("Images/instructions.png"); // images
		backImg = new ImageIcon("Images/backButton.png");
		
		goMenu = new JButton(backImg);
		
		goMenu.setOpaque(false); // buttons: transparent format
		goMenu.setContentAreaFilled(false);
		goMenu.setBorderPainted(false);
		goMenu.addActionListener(this); 
		
		this.setLayout(new BorderLayout(0, 0));
		this.setBackground(Color.BLUE); // set base panel to be blue
		
		this.add(northP, BorderLayout.NORTH); 
		northP.setOpaque(false); // set northP to be transparent
		northP.setLayout(new BorderLayout(0, 0));
		northP.add(goMenu, BorderLayout.WEST); // add button to northP
		
	} // end of constructor

	
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == goMenu) // go back to main menu
			JeometryDash.cardsL.first(JeometryDash.c);

	} // end of actionPerformed

	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		g.drawImage(bg.getImage(), 0, 0, null); // background image

	} // end of paintComponent

	
} // end of MyInstPanel class