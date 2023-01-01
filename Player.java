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
	
	public int getX () {
		return x;
	}
	
	public int getY () {
		return y;
	}
	
	public void jump() {
		if (y==400)
			y-=75;
	}

	public void fall() {
		fallingSpeed += gravity;
		if (y+fallingSpeed <= 400)
			y+= fallingSpeed;
		else {
			y=400;
			fallingSpeed = 0;
		}
	}
}
