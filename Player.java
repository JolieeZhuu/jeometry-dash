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

public class Player extends JPanel implements ActionListener, KeyListener {
	
	private ImageIcon player; // declare instance variables
	private int x, y, yPlatform;
	private double speed, gravity;
	private boolean willJump, jumped;
	private long lastPressProcessed = 0L;
	private long lastJump = 0L;
	
	
	public Player() throws Exception {
		
		this.setFocusable(true); // request and add focus for keyListener
		this.requestFocus();
		this.addKeyListener(this);
		
		y = 400; // initialize variables
		yPlatform = 400;
		x = 100;
		speed = 0;
		gravity = 0.5;
		
		if (player == null)
			player = new ImageIcon("Images/cube01.png");
		
		this.setLayout(new BorderLayout(0, 0));

	}
	
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		checkCollisions();
		g.drawImage(player.getImage(), x, y, 50, 50, null); // draw the player
		
	} // end of paintComponent
	
	
	public void actionPerformed(ActionEvent e) {
		
		if (willJump && System.currentTimeMillis() - lastPressProcessed > 300) {
			y -= 75; // player jumps
			lastPressProcessed = System.currentTimeMillis();
			willJump = false;
			jumped = true;
		}
		
		if (jumped && System.currentTimeMillis() - lastPressProcessed > 300) {
			while (y < yPlatform) {
				y += speed; // player falls
				speed += gravity;
			}
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

	/*
	public void checkCollisions() {
		
		Rectangle r1 = getBounds();
		
		ImageIcon[][] platforms = Platforms.getPlatforms();
		
		for (ImageIcon[] row : platforms) {
			for (ImageIcon platform : row) {
				Rectangle r2 = ((Shape) platform).getBounds(); // doesn't work
				
				if (r1.intersects(r2)) {
					if (r1.y + 50 >= r2.y && r1.y + 50 <= r2.y + 50) {
						System.out.println("player colliding");
					} else if (r1.y <= r2.y + 50 && r2.y <= r1.y) {
						
					} else {
						
					}
				}
			}
		}
	}
	*/

	public void checkCollisions() {
		
		for (int i = 0; i < 8; i++) {
			if (Platforms.getJ() > 0) {
				System.out.println(Platforms.getJ());
				if (y + 50 >= Platforms.getYs(i, Platforms.getJ()) && y + 50 <= Platforms.getYs(i, Platforms.getJ()) + 50) { // player lower bound >= platform upper bound
					// works when jumping
					yPlatform = Platforms.getYs(i, Platforms.getJ());
					System.out.println(yPlatform);
					jumped = true;
				} else if (y <= Platforms.getYs(i, Platforms.getJ()) + 50 && Platforms.getYs(i, Platforms.getJ()) <= y) { // player upper bound >= platform lower bound
					// works when jumping
					
				} else { // player x and platform x
					// works when passing through every obstacle
				}
			}
		}
		
	} // end of checkCollisions

	
	public boolean isOnPlatform() {
		
		for (int i = 0; i < 8; i++) {
			if (Platforms.getJ() > 0)
				if ((y + 50 >= Platforms.getYs(i, Platforms.getJ()) && y <= Platforms.getYs(i, Platforms.getJ()))
					|| (x + 50 >= Platforms.getXs(i, Platforms.getJ()) && x <= Platforms.getXs(i, Platforms.getJ())))
					return true;
		} return false;
		
	} // end of isOnPlatform
	
} // end of Player class