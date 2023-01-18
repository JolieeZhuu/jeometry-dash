/*
 * Names: Simone Ghosh and Jolie Zhu
 * Teacher: Ms. Strelkovska
 * Course: ICS3U7-1
 * Date: January 18, 2023
 * Description: Player block of Jeometry Dash
 */

import javax.swing.*;

public class Player extends Platforms{

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
	
	
	public void jump() {
		
		for (int i = yPlatform; i >= yPlatform - 75; i -= speed) {
			speed -= gravity;
			y = i;
		}
		y = yPlatform - 75;
		speed = 0;
		
	} // end of jump
	
	/*
	public void fall(int y) {
		
		this.y = y;
		speed += gravity;
		y += speed;
		
		for (int i = yPlatform - 75; i <= yPlatform; i += speed) {
			speed += gravity;
			y = i;
		}
		y = yPlatform;
		speed = 0;
		
	} // end of fall
	*/
	
	public void setSpeed(int speed) {
		
		this.speed = speed;
		
	} // end of setWillJump
		
	
	public int getYPlatform() {
		
		return yPlatform;
		
	} // end of getYPlatform

	
	public int getSpeed() {
		
		return speed;
		
	} // end of getSpeed	
	
} // end of Player class