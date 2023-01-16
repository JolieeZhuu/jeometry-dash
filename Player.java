/*
 * Names: Simone Ghosh and Jolie Zhu
 * Teacher: Ms. Strelkovska
 * Course: ICS3U7-1
 * Date: January 18, 2023
 * Description: Player block of Jeometry Dash
 */

import javax.swing.*;

public class Player extends Platforms {

	private int yPlatform, speed, gravity; // declare instance variables
	
	
	public Player(int x, int y, String imgName) {
		
		super(x, y, imgName); // initialize variables
		
		yPlatform = 400;
		speed = 9;
		gravity = 1;
		
	} // end of constructor
	
	
	public void setImage(String imgName) {
		
		img = new ImageIcon(imgName).getImage();
		
	} // end of setImage

	
	public void setYPlatform(int y) {
		
		yPlatform = y;
		
	} // end of setYPlatform
	
	
	public void jump() {

		for (int i = yPlatform; i >= yPlatform - 75; i -= speed) {
			speed -= gravity;
			y = i;
		}
		y = yPlatform - 75;
		speed = 0;

	} // end of jump

	
	public void fall() {
		
		speed += gravity;
		y += speed;
		
		/*
		for (int i = yPlatform - 75; i <= yPlatform; i += speed) {
			speed += gravity;
			y = i;
		}
		y = yPlatform;
		speed = 0;
		*/

	} // end of fall
	
	
	public void setY (int y) {
		
		this.y = y;
		
	} // end of setY
	
	
	public void setSpeed (int speed) {
		
		this.speed = speed;
		
	} // end of setSpeed
	
} // end of Player class