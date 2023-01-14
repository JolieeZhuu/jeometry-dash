/*
 * Names: Simone Ghosh and Jolie Zhu
 * Teacher: Ms. Strelkovska
 * Course: ICS3U7-1
 * Date: January 18, 2023
 * Description: Player class of Jeometry Dash
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MyPlayerPanel extends JPanel implements ActionListener, KeyListener {
	
	private int x, y; // declare instance variables
	private boolean willJump;
	private long lastPressProcessed = 0L;
	private long lastJump = 0L;
	
	
	public MyPlayerPanel() throws Exception {
		
		this.setFocusable(true); // request and add focus for keyListener
		this.requestFocus();
		this.addKeyListener(this);
		
		y = 400; // initialize variables
		x = 100;
		
		this.setLayout(new BorderLayout(0, 0));

	} // end of constructor
	
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		JeometryDash.player.draw(g); // draw the player
		
	} // end of paintComponent
	
	
	public void actionPerformed(ActionEvent e) {
		
		JeometryDash.player.move();
		
	} // end of actionPerformed
	
	
	public void keyTyped(KeyEvent e) { // uses keyChar
	} // end of keyTyped
	
	
	public void keyPressed(KeyEvent e) { // uses keyCode
	} // end of keyPressed
	
	
	public void keyReleased(KeyEvent e) {
		
		if (e.getKeyCode() == 32 && System.currentTimeMillis() - lastJump > 600) { // 32 is space bar
			willJump = true;
			lastJump = System.currentTimeMillis();
		}
		
	} // end of keyReleased
	
} // end of Player class