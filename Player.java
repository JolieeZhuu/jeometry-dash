import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Player {
	
	private ImageIcon img;
	private int x, y, fallingSpeed, gravity;
	
	public Player(){
		x = 100; // initialize variables
		y = 400;
		
		fallingSpeed = 0;
		gravity = 1;
		
		img = new ImageIcon("Images/cube03.png");
	}
	
	public ImageIcon getImg () {
		return img;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getLeft() {
		return x;
	}
	
	public int getRight() {
		return x + 50;
	}
	
	public int getTop() {
		return y;
	}
	
	public int getBottom() {
		return y + 50;
	}
	
	public void setLeft(int x) {
		this.x = x; 
	}
	
	public void setRight(int x) {
		this.x = x; 
	}
	
	public void setTop(int y) {
		this.y = y; 
	}
	
	public void setBottom(int y) {
		this.y = y; 
	}
	
	public void jump() {
		if (y == 400)
			y -= 100;
	}

	public void fall() {
		while (y + fallingSpeed <= 400) {
			fallingSpeed += gravity;
			y += fallingSpeed;			
		}
		y = 400;
		fallingSpeed = 0;
	}
}