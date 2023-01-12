import java.awt.event.*;

public class Player extends Sprite implements KeyListener {
	
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
		
		if (willJump) {
			y -= 75; // player jumps
			lastPressProcessed = System.currentTimeMillis();
			willJump = false;
			jumped = true;
		}
		
		if (jumped && System.currentTimeMillis() - lastPressProcessed > 200) {
			System.out.println(yPlatform);
			for (int i=y; i<yPlatform; i++)
				y++;
			lastPressProcessed = System.currentTimeMillis();
			jumped = false;
		}
		
		/*
		if (jumped && System.currentTimeMillis() - lastPressProcessed > 300) {
			for (int i = 0; i <= 75; i++) // perform the fall (go down) action of the player
				y += speed;
			lastPressProcessed = System.currentTimeMillis();
			jumped = false;
		}
		*/
		
	} // end of move
	
	
	public void keyTyped(KeyEvent e) { // uses keyChar
	} // end of keyTyped
	
	public void keyPressed(KeyEvent e) { // uses keyCode
	} // end of keyPressed

	public void keyReleased(KeyEvent e) {
		
		if (e.getKeyCode() == 32 && System.currentTimeMillis() - lastJump > 600) { // 32 is space bar
			willJump = true;
			lastJump = System.currentTimeMillis();
		}
		
	} // end of keyReleased
	

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