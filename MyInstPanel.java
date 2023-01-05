/*
 * Names: Simone Ghosh and Jolie Zhu
 * Teacher: Ms. Strelkovska
 * Course: ICS3U7-1
 * Date: January 5, 2023
 * Description: Instructions panel of Jeometry Dash
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MyInstPanel extends JPanel implements ActionListener {

	private JPanel northP;  // declare instance variables
	private JButton goMenu;
	private ImageIcon bg, ground, backImg;

	public MyInstPanel() {
		
		northP = new JPanel(); // initialize variables	
		
		bg = new ImageIcon("Images/bg01.png");
		ground = new ImageIcon("Images/ground01.png");
		backImg = new ImageIcon("Images/backButton.png");
		
		goMenu = new JButton(backImg);
		
		goMenu.setOpaque(false); // make button transparent
		goMenu.setContentAreaFilled(false);
		goMenu.setBorderPainted(false);
		goMenu.addActionListener(this); // add actionListener to button
		
		this.setLayout(new BorderLayout(0, 0));
		this.setBackground(Color.BLUE);
		
		this.add(northP, BorderLayout.NORTH); // add panel to panel
		northP.setOpaque(false); // formatting
		northP.setLayout(new BorderLayout(0,0));
		northP.add(goMenu, BorderLayout.WEST); // add button to panel
		
	} // end of constructor

	
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == goMenu) // back button
			JeometryDash.cardsL.first(JeometryDash.c);

	} // end of actionPerformed

	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		g.drawImage(bg.getImage(), 0, 0, null); // draw background image (no animation)
		g.drawImage(ground.getImage(), 0, 450, null); // draw ground image

	} // end of paintComponent

} // end of MyInstPanel class