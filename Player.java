/*
 * Names: Simone Ghosh and Jolie Zhu
 * Teacher: Ms. Strelkovska
 * Course: ICS3U7-1
 * Date: January 6, 2023
 * Description: Player class of Jeometry Dash
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Player extends JPanel implements ActionListener, KeyListener {
	
	private static ImageIcon player; // declare instance variables
	private int x, y;
	private double speed, gravity;
	private boolean willJump, jumped;
	private long lastPressProcessed = 0L;
	private long lastJump = 0L;
	private Platforms lvl01 = new Platforms();
	
	
	public Player() throws Exception {
		
		this.setFocusable(true); // request and add focus for keyListener
		this.requestFocus();
		this.addKeyListener(this);
		
		y = 400; // initialize variables
		x = 100;
		speed = 1;
		//speed = -4;
		//gravity = 0.5;
		
		if (player == null)
			player = new ImageIcon("Images/cube01.png");
		
		this.setLayout(new BorderLayout(0, 0));

	}
	
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		g.drawImage(player.getImage(), x, y, 50, 50, null); // draw the player
		
	} // end of paintComponent
	
	
	public void actionPerformed(ActionEvent e) {
		
		/*
		while (y < 400 && !willJump) {
			y += speed;
			speed += gravity;
		}
		*/
		
		if (willJump && System.currentTimeMillis() - lastPressProcessed > 300) {
			for (int i = 0; i <= 75; i++) // perform the jump (go up) action of the player
				if (y >= 330)
					y -= speed;
			lastPressProcessed = System.currentTimeMillis();
			willJump = false;
			jumped = true;
		}
		
		if (jumped && System.currentTimeMillis() - lastPressProcessed > 300) {
			for (int i = 0; i <= 75; i++) // perform the fall (go down) action of the player
				y += speed;
			lastPressProcessed = System.currentTimeMillis();
			jumped = false;
		}
		
	} // end of actionPerformed
	
	
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
	
	
	public static void setImage(ImageIcon img) {
		player = img;
	} // end of setImage

	
	public void collisions() {
		
		/*int[] collisionListY = new int[9];
		for (int i = 0; i < 9; i++) {
			collisionListY[i] = lvl01.getYs(i, lvl01.getJ());
			
		}
		
		/*
		 * PSEUDOCODE ----------------------------------------------------------------------------------------------------------------------------------
		 
		 * Check the platforms that are approaching the player at 150 (right bound of the player)
		 	* From what I printed out, every platform will have an x-axis of 110, so getJ looks for x[i][j] with x = 110 (within the Platforms class)
		 
		 CODE STOPPED UP UNTIL HERE!
		 * Collect every platform of x = 110, and compare x and y coordinates with player
		 
		 * Check to see if the player's upper bound >= platform's lower bound
		 	* If these standards are not met, then set the y value accordingly
		 
		 * Check to see if the player's lower bound <= platform's upper bound
		 	* If these standards are not met, then set the y value accordingly
		 
		 * Same goes for x...but leave this part out for now, because we need to figure out how to restart the game
		 
		 
		 * Also, there should be another method that checks if the player is in the air
		 	* Then gravity (basically when the player falls down in the actionPerformed method) takes the player down on to another platform
		*/
		
	}

	
	
	
	
	
	
/* RANDOM CODE YOU DON'T NEED TO USE, BUT POSSIBLY AS A REFERENCE IF IT'S OF ANY HELP -------------------------------------------------------------------

	public boolean checkCollision(int i) {
		
		boolean noXOverlap = x <= lvl01.getXs(i, lvl01.getJ()); // check to see if player and platform overlap in terms of x
		boolean noYOverlap = y <= lvl01.getYs(i, lvl01.getJ()) || y >= lvl01.getYs(i, lvl01.getJ()) + 50; // check to see if player and platform overlap in terms of y
		if (noXOverlap || noYOverlap)
			return false;
		return true;
		
	} // end of checkCollision
	
	
	public int[] checkCollisionList(int[] platforms) {
		
		ArrayList<Integer> arrL = new ArrayList<Integer>();
		
		for (int i = 0; i < platforms.length; i++) {
			if (checkCollision(platforms[i]))
				arrL.add(platforms[i]);
		}
		
		int[] collisionList = new int[arrL.size()];
		for (int i = 0; i < arrL.size(); i++) {
			Integer int1 = arrL.get(i);
			int int2 = int1;
			collisionList[i] = int2;
		}
		return collisionList;
		
	} // end of checkCollisionList
	
	
	public void resolvePlatformCollisions(int[] platforms) {
		
		int[] collisionList = checkCollisionList(platforms);
		
		if (collisionList.length > 0) {
			if (speed > 0) { // when the player is falling
				y = collisionList[0] - 50;
			} else if (speed < 0)  { // when the player is jumping
				y = collisionList[0] + 50;
			}
			//speed = 0;
		}
		
		
		//For this, if there are obstacles the player will collide with, restart the game
		//collisionList = checkCollisionList(platforms);
		//if (collisionList.length > 0) {
		//	x = 110;
		//}
		
		
	} // end of resolvePlatformCollisions
	
	
	public boolean isOnPlatform(int[] platforms) {
		
		y += 5; // move the player down and check if it is colliding with platforms
		int[] collisionList = checkCollisionList(platforms);
		y -= 5;
		if (collisionList.length > 0 || y == 405)
			return true;
		return false;
		
	} // end of isOnPlatform


PSEUDOCODE (Code above is for this pseudocode) ---------------------------------------------------------------------------------------------------
	
	METHOD 1:
	- boolean method, checkCollision
	- takes in the player sprite and a specific platform sprite, as parameters
	
	- boolean variable, noXOverlap
		- checks if player's right <= platform's left OR if player's left >= platform's right
	- boolean variable, noYOverlap
		- checks if player's bottom <= platform's top OR if player's top >= platform's bottom
	
	- if noXOverlap OR noYOverlap, return false; otherwise, return true
	
	
	
	
	METHOD 2:
	- method which returns an ArrayList, checkCollisionList
	- takes in the player sprite and an ArrayList of platform sprites
	
	- sprite ArrayList, collisionList
	
	- in a for loop, go through every sprite within the ArrayList of platform sprites
		- if true, when you call the checkCollision method
			- add that platform sprite element to the collisionList ArrayList
	
	
	
	METHOD 3:
	- a void method, resolvePlatformCollisions
	- takes in the player sprite and an ArrayList of platform sprites
	
	- sprite ArrayList, collisionList: call the checkCollisionList method to initialize this ArrayList
	
	- if there are items within collisionList (collisionList > 0): this means that the player will be colliding with these items
		- if changeY > 0
			- then set the player's bottom to be the specific platform's top, that you collided with
			- Note: if there are multiple ones you collided, they are most likely on equal level, so just take the first one from the ArrayList
			- we also need to make sure that what we are landing on is not a triangle or spike
		- else, if changeY < 0
			- then set the player's top to be the specific platform's bottom, that you collided with
			- same note as the previous if statement
			- however, in our case, we also need to make sure the game restarts (timer resets?)
				- clarification: because the player cannot be hitting upside down spikes, etc.
		- then change the y velocity, changeY to be 0
			- clarification: this allows the player to stop jumping, and now the platform they are on is the "ground"
	
	- using the same sprite ArrayList, collisionList, initialize it again
		- clarification: we will be doing the exact same thing, but with x
		
	- if there are items within collisionList (collisionList > 0): this means that the player will be colliding with these items
		- we don't have a changeX, since the background and obstacles are moving instead of the player, so we only need to...
		
		- set the player's right to be the specific platform's left, that you collided with
		- however, in our case, we also need to make sure the game restarts (timer resets?)
			- clarification: because the player cannot be hitting the sides of platforms, spikes, etc.
	*/
	
} // end of Player class