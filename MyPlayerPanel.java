/*
 * Names: Simone Ghosh and Jolie Zhu
 * Teacher: Ms. Strelkovska
 * Course: ICS3U7-1
 * Date: January 6, 2023
 * Description: Player class of Jeometry Dash
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MyPlayerPanel extends JPanel implements ActionListener, KeyListener {
	
	private ImageIcon player; // declare instance variables
	private int x, y, yPlatform;
	private double speed, gravity;
	private boolean willJump, jumped;
	private long lastPressProcessed = 0L;
	private long lastJump = 0L;
	
	
	public MyPlayerPanel() throws Exception {
		
		this.setFocusable(true); // request and add focus for keyListener
		this.requestFocus();
		this.addKeyListener(this);
		
		y = 400; // initialize variables
		yPlatform = 400;
		x = 100;
		gravity = 1;
		speed = 0;
		
		if (player == null)
			player = new ImageIcon("Images/cube01.png");
		
		this.setLayout(new BorderLayout(0, 0));

	}
	
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		g.drawImage(player.getImage(), x, y, 50, 50, null); // draw the player
		
	} // end of paintComponent
	
	
	public void actionPerformed(ActionEvent e) {
		
		if (willJump) {
			y -= 75; // player jumps
			lastPressProcessed = System.currentTimeMillis();
			willJump = false;
			jumped = true;
		}
		
		if (jumped && System.currentTimeMillis() - lastPressProcessed > 200) {
			System.out.println(yPlatform);
			for (int i=y; i<yPlatform; i++)
				y++;
			lastPressProcessed = System.currentTimeMillis();
			jumped = false;
		}
		
		/*
		if (jumped && System.currentTimeMillis() - lastPressProcessed > 300) {
			for (int i = 0; i <= 75; i++) // perform the fall (go down) action of the player
				y += speed;
			lastPressProcessed = System.currentTimeMillis();
			jumped = false;
		}
		*/
		
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
	
	
	public void setImage(String img) {
		player = new ImageIcon(img);
	} // end of setImage


	public Rectangle getBounds() {
		return new Rectangle(x, y, 50, 50);
	}
	
	public int getY () {
		return y;
	}
	
	public void setY (int y) {
		this.y = y;
	}
	
	public int getX () {
		return x;
	}
	
	public void setYPlatform (int y) {
		yPlatform = y;
	}
	
	public void setJumped (boolean tf) {
		jumped = tf;
	}
	
} // end of Player class