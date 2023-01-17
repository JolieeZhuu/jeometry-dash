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
	private boolean willJump, willFall;
	
	
	public Player(int x, int y, String imgName) {
		
		super(x, y, imgName); // initialize variables
		
		yPlatform = 400;
		speed = 9;
		gravity = 1;
		
	} // end of constructor
	
	
	public void setImage(String imgName) {
		
		img = new ImageIcon(imgName).getImage();
		
	} // end of setImage

	
	public void setYPlatform (int y) {
		
		yPlatform = y;
		
	} // end of setYPlatform
	
	
	public void jump() {
		
		for (int i = 400; i >= 325; i -= speed) {
			speed -= gravity;
			y = i;
		}
		y = 325;
		willJump = false;
		willFall = true;
		speed = 0;
		
	} // end of setJumped
	
	
	public void fall() {
		
		for (int i = 325; i <= 400; i += speed) {
			speed += gravity;
			y = i;
		}
		y = 400;
		willJump = false;
		willFall = false;
		speed = 0;
		
	}
		
} // end of Player class