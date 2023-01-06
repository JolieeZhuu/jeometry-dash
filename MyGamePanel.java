/*
 * Names: Simone Ghosh and Jolie Zhu
 * Teacher: Ms. Strelkovska
 * Course: ICS3U7-1
 * Date: January 5, 2023
 * Description: Game panel of Jeometry Dash
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MyGamePanel extends JPanel implements ActionListener, KeyListener {
	
	private JButton goMenu; // declare instance variables
	private ImageIcon backImg;
	private JPanel northP;
	
	private int delay;
	
	private Timer timer;
	
	private MovingBG bgP;
	private Player player;
	private Platforms lvl01;

	
	public MyGamePanel() throws Exception {

		delay = 1000 / 30; // initialize variables

		northP = new JPanel();
		bgP = new MovingBG();
		player = new Player();
		lvl01 = new Platforms();
		
		backImg = new ImageIcon("Images/backButton.png");
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

		timer = new Timer(delay, this);
		timer.start();

	} // end of constructor

	
	public void actionPerformed(ActionEvent e) {
		
		bgP.actionPerformed(e); // add background animation
		lvl01.actionPerformed(e); // add obstacles animation
		player.actionPerformed(e); // add player movement
		
		if (e.getSource() == goMenu) // back button
			JeometryDash.cardsL.show(JeometryDash.c, "Levels");
		repaint();
		
	} // end of actionPerformed

	
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		bgP.paintComponent(g); // add background
		lvl01.paintComponent(g); // add obstacles
		player.paintComponent(g); // add player
    	// resolvePlatformCollisions(lvl01.getPlatforms());
	
	} // end of paintComponent
	
	
	public void keyTyped(KeyEvent e) { // uses keyChar
	} // end of keyTyped
	
	public void keyPressed(KeyEvent e) { // uses keyCode
	} // end of keyPressed

	public void keyReleased(KeyEvent e) {
		
		player.keyReleased(e); // call keyReleased method from Player class
		
	} // end of keyReleased
	
	
	/*
	// KEYLISTENER ------------------------------------------------------------------------------------------------------------------------

	PSEUDOCODE --------------------------------------------------------------------------------------------------------------------
	new position = old position + velocity
	new velocity = old velocity + acceleration
	y = position (new and old)
	changeY = velocity (new and old)
	gravity = acceleration
	
	changeY += gravity
	y += changeY
	
	Note - gravity pulls the character down (+), while changeY pulls the character up (-)
	
	If changeY = -12, gravity = 4...
	- player moves 12 pixels up
	- with gravity, there is that parabola effect: 
			-12 + 4 = -8....-8 + 4 = -4....-4 + 4 = 0 // goes up
			4 + 4 = 8....8 + 4 = 12 // goes down
	
	*/
  
} // end of MyGamePanel class