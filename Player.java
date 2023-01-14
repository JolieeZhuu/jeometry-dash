/*
 * Names: Simone Ghosh and Jolie Zhu
 * Teacher: Ms. Strelkovska
 * Course: ICS3U7-1
 * Date: January 18, 2023
 * Description: Player block of Jeometry Dash
 */

import javax.swing.*;

public class Player extends Platforms {

	private int yPlatform; // declare instance variables
	private boolean jumped;
	
	
	public Player(int x, int y, String imgName) throws Exception {
		
		super(x, y, imgName); // initialize variables
		
		yPlatform = 400;
		
	} // end of constructor


	public void move() {
		
		/*
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
		
		// add jumping action (gravity and speed)
		
	} // end of move
	
	
	public void setImage(String imgName) {
		
		img = new ImageIcon(imgName).getImage();
		
	} // end of setImage

	
	public void setYPlatform(int y) {
		
		yPlatform = y;
		
	} // end of setYPlatform
	
	
	public void setJumped(boolean tf) {
		
		jumped = tf;
		
	} // end of setJumped

	
	public void setY (int y) {
		
		this.y = y;
		
	} // end of setY
	
} // end of Player class