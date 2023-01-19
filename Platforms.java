/*
 * Names: Simone Ghosh and Jolie Zhu
 * Teacher: Ms. Strelkovska
 * Course: ICS3U7-1
 * Date: January 18, 2023
 * Description: Blocks of Jeometry Dash
 */

import java.awt.*;
import javax.swing.*;

public class Platforms {
	
	protected int x, y; // declare instance variables
	protected Image img;
	

	public Platforms(int x, int y, String imgName) {
		
		this.x = x; // initialize variables
		this.y = y;
		img = new ImageIcon(imgName).getImage();

	} // end of constructor
	
	
	public void setX (int x) {
		
		this.x = x;
	
	}
	
	public int getY () {
		
		return y;
		
	}
	
	
	public void setYs(int yPlatform, int y) {
		
		this.y = y;
		
	} // end of setY
	
	
	public void setImage(String imgName) {
		
		img = new ImageIcon(imgName).getImage();
		
	} // end of setImage
	
	
	public void draw(Graphics g) {
		
		g.drawImage(img, x, y, 50, 50, null);
		
	} // end of draw
	
	
	public Rectangle getBounds() {
		
		return new Rectangle(x, y, 50, 50);
		
	} // end of getBounds
	
	
	public void jump() { } // end of jump
	
	
	public void fall() { } // end of fall


} // end of Platforms class