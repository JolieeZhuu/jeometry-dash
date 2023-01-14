/*
 * Names: Simone Ghosh and Jolie Zhu
 * Teacher: Ms. Strelkovska
 * Course: ICS3U7-1
 * Date: January 18, 2023
 * Description: Instructions panel of Jeometry Dash
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MyCustomPanel extends JPanel implements ActionListener {

	private JPanel northP, playerP;  // declare instance variables
	
	private JButton goMenu;
	private JButton b01, b02, b03, b04, b05;
	
	private ImageIcon bg, ground, backImg;
	private ImageIcon cube01, cube02, cube03, cube04, cube05;

	
	public MyCustomPanel() {
		
		northP = new JPanel(); // initialize variables
		playerP = new JPanel();
		
		bg = new ImageIcon("Images/bg01.png");
		ground = new ImageIcon("Images/ground01.png");
		backImg = new ImageIcon("Images/backButton.png");
		
		cube01 = new ImageIcon("Images/cube01.png");
		cube02 = new ImageIcon("Images/cube02.png");
		cube03 = new ImageIcon("Images/cube03.png");
		cube04 = new ImageIcon("Images/cube04.png");
		cube05 = new ImageIcon("Images/cube05.png");
		
		b01 = new JButton(cube01);
		b02 = new JButton(cube02);
		b03 = new JButton(cube03);
		b04 = new JButton(cube04);
		b05 = new JButton(cube05);
		
		b01.setPreferredSize(new Dimension(100, 100)); // set button size
		b02.setPreferredSize(new Dimension(100, 100));
		b03.setPreferredSize(new Dimension(100, 100));
		b04.setPreferredSize(new Dimension(100, 100));
		b05.setPreferredSize(new Dimension(100, 100));
		
		goMenu = new JButton(backImg);
		
		goMenu.setOpaque(false); // make button transparent
		goMenu.setContentAreaFilled(false);
		goMenu.setBorderPainted(false);
		goMenu.addActionListener(this); // add actionListener to button
		
		this.setLayout(new BorderLayout(0, 0));
		this.setBackground(Color.BLUE);
		
		this.add(northP, BorderLayout.NORTH); // add panel to panel
		northP.setOpaque(false);
		northP.setLayout(new BorderLayout(0,0));
		northP.add(goMenu, BorderLayout.WEST); // add button to panel
		
		this.add(playerP, BorderLayout.CENTER);  // add panel to panel
		playerP.setOpaque(false);
		playerP.setLayout(new GridLayout(3, 2, 40, 40));
		playerP.add(b01); // add buttons to panel
		playerP.add(b02);
		playerP.add(b03);
		playerP.add(b04);
		playerP.add(b05);
		
	} // end of constructor

	
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == goMenu) // back button
			JeometryDash.cardsL.first(JeometryDash.c);
		else if (e.getSource() == b01)
			JeometryDash.player.setImage("Images/cube01.png");
		else if (e.getSource() == b02)
			JeometryDash.player.setImage("Images/cube02.png");
		else if (e.getSource() == b03)
			JeometryDash.player.setImage("Images/cube03.png");
		else if (e.getSource() == b04)
			JeometryDash.player.setImage("Images/cube04.png");
		else if (e.getSource() == b05)
			JeometryDash.player.setImage("Images/cube05.png");
		repaint();
		
	} // end of actionPerformed

	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		g.drawImage(bg.getImage(), 0, 0, null); // draw background image (no animation)
		g.drawImage(ground.getImage(), 0, 450, null); // draw ground image

	} // end of paintComponent

} // end of MyInstPanel class