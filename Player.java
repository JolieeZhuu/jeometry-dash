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
	
	
	public void fall () {
		
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

	
	public void setYs(int yPlatform, int y) {	
		
		this.yPlatform = yPlatform;
		this.y = y;
		
	} // end of setYs
	
	
} // end of Player class