import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Player extends JPanel implements ActionListener, KeyListener {
	private ImageIcon img;
	private int x, y;
	
	private int speed, gravity, delay;
	private boolean isJump;
	private Timer timer;
	private long lastPressProcessed = 0L;
	
	public Player() {
		
		this.setFocusable(true);
		this.requestFocus();
		this.addKeyListener(this);
		
		x = 100; // initialize variables
		y = 400;
		speed = 0;
		gravity = 5;
		delay = 1000/30;
		
		isJump = false;
		
		img = new ImageIcon("Images/cube03.png");
		
		//timer = new Timer(delay, this); // add and start a timer
		//timer.start();
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img.getImage(), x, y, 50, 50, null);
		
	} // end of paintComponent
	
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == timer && !isJump) {
			if (y+speed<=400) {
				speed += gravity;
				y += speed;
			} else {
				speed = 0;
				y = 400;
			}
		}
		if (e.getSource() == timer && isJump) {
			if (y-speed>=325) {
				speed += gravity;
				y -= speed;
			} else {
				speed = 0;
				y = 325;
			}
			isJump = false;
		}
		else 
			y=400;
	}
	
	
	
	public void keyTyped(KeyEvent e) { // uses keyChar
	} // end of keyTyped
	
	public void keyPressed(KeyEvent e) { // uses keyCode			
	} // end of keyPressed

	public void keyReleased(KeyEvent e) {
	} // end of keyReleased
	
	
	
	
	public ImageIcon getImg () {
		return img;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int changeY) {
		y += changeY;
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
		this.x = x + 50; 
	}
	
	public void setTop(int y) {
		this.y = y; 
	}
	
	public void setBottom(int y) {
		this.y = y - 50; 
	}
	
	public void setIsJump (boolean tf) {
		isJump = tf;
	}
}


/*
public class Player { 
	
	private ImageIcon img;
	private int x, y;
	
	private int speed, gravity, delay;
	private boolean isJump;
	private Timer timer;
	private long lastPressProcessed = 0L;
	
	public Player() {
		x = 100; // initialize variables
		y = 400;
		speed = 0;
		gravity = 5;
		delay = 1000/30;
		
		isJump = false;
		
		img = new ImageIcon("Images/cube03.png");
		
		//timer = new Timer(delay, this); // add and start a timer
		//timer.start();
	}
	
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == timer && !isJump) {
			if (y+speed<=400) {
				speed += gravity;
				y += speed;
			} else {
				speed = 0;
				y = 400;
			}
		}
		if (e.getSource() == timer && isJump) {
			if (y-speed>=325) {
				speed += gravity;
				y -= speed;
			} else {
				speed = 0;
				y = 325;
			}
			isJump = false;
		}
		else 
			y=400;
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
	
	public void setY(int changeY) {
		y += changeY;
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
		this.x = x + 50; 
	}
	
	public void setTop(int y) {
		this.y = y; 
	}
	
	public void setBottom(int y) {
		this.y = y - 50; 
	}
	
	public void setIsJump (boolean tf) {
		isJump = tf;
	}
}
*/