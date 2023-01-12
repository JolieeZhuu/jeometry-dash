import java.awt.event.*;

public class Player extends Sprite {
	
	private int yPlatform; // declare instance variables
	private double speed, gravity;
	private boolean willJump, jumped;
	private long lastPressProcessed = 0L;
	private long lastJump = 0L;
	
	
	public Player(int x, int y) {
		
		super(x, y); // initialize variables
		yPlatform = 400;
		gravity = 1;
		speed = 0;
		
	} // end of constructor
	

	public void move() {
		
	} // end of move
	
	
	public void playerJump () {
		/*
		if (e.getKeyCode() == 32 && System.currentTimeMillis() - lastJump > 600) { // 32 is space bar
			willJump = true;
			lastJump = System.currentTimeMillis();
		}
		*/
	}
	

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