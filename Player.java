/*
 * Names: Simone Ghosh and Jolie Zhu
 * Teacher: Ms. Strelkovska
 * Course: ICS3U7-1
 * Date: January 19, 2023
 * Description: Player block of Jeometry Dash
 */

public class Player extends Platforms {

	private int yPlatform, speed, gravity; // declare instance variables
	
	
	public Player(int x, int y, String imgName) {
		
		super(x, y, imgName); // initialize variables
		
		yPlatform = 400;
		speed = 9;
		gravity = 1;
		
	} // end of constructor
	
	
	public void setYs(int yPlatform, int y) {	
		
		this.yPlatform = yPlatform;
		this.y = y;
		
	} // end of setYs
	
	
	public void jump() {
		
		// player jumps up 75 pixels
		for (int i = yPlatform; i >= yPlatform - 75; i -= speed) {
			speed -= gravity;
			y = i;
		}
		y = yPlatform - 75;
		speed = 0;
		
	} // end of jump
	
	
	public void fall () {
		
		// player falls until it collides with the ground or a platform
		for (int i = yPlatform - 75; i < 400 || !JeometryDash.gameP.isColliding(); i += speed) {
			speed += gravity;
			y = i;
		}
		if (JeometryDash.gameP.isColliding())
			JeometryDash.gameP.checkCollisions();
		else {
			y = 400;
			yPlatform = 400;
		}
		speed = 9;
				
	} // end of fall
	
	
} // end of Player class