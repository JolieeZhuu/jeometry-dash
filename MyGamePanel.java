/*
 * Names: Simone Ghosh and Jolie Zhu
 * Teacher: Ms. Strelkovska
 * Course: ICS3U7-1
 * Date: December 28
 * Description: Game panel of Jeometry Dash
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class MyGamePanel extends JPanel implements ActionListener {
	
	private JButton goMenu; // declare instance variables
	private int delay;
	
	private int gravity = 10, changeY = -10;
	
	private Timer timer;
	private MovingBG bgP;
	private Player player;
	private Platforms lvl01;
	
	//private long lastPressProcessed = 0L;

	public MyGamePanel() throws Exception {

		
		delay = 1000/30;
		goMenu = new JButton("Levels");
		bgP = new MovingBG();
		player = new Player();
		
		lvl01 = new Platforms();
		
		goMenu.addActionListener(this); // formatting and adding interfaces
		this.setLayout(new FlowLayout());
		this.add(goMenu);
		this.setBackground(Color.BLUE);
		this.add(bgP, BorderLayout.NORTH);
		this.add(lvl01, BorderLayout.EAST);

		timer = new Timer(delay, this); // add and start a timer
		timer.start();

	} // end of constructor

	public void actionPerformed(ActionEvent e) {
		bgP.actionPerformed(e);
		lvl01.actionPerformed(e);
		player.actionPerformed(e);
		//player.actionPerformed(e);
		if (e.getSource() == goMenu)
			JeometryDash.cardsL.show(JeometryDash.c, "Levels");
		repaint();
		
	} // end of actionPerformed

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		bgP.paintComponent(g);
		lvl01.paintComponent(g);
		player.paintComponent(g);
		resolvePlatformCollisions(lvl01.getPlatforms());
		
	} // end of paintComponent
	
	/*
	// COLLISIONS -------------------------------------------------------------------------------------------------------------------------
	
	PSEUDOCODE ----------------------------------------------------------------------------------------------------------------------------
	
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
			
	// method 1
	public boolean checkCollision(int i, int j) {
		boolean noXOverlap = player.getRight() <= lvl01.getLeft(i, j) || player.getLeft() >= lvl01.getRight(i, j);
		boolean noYOverlap = player.getBottom() <= lvl01.getTop(i, j) || player.getTop() >= lvl01.getBottom(i, j);
		if (noXOverlap || noYOverlap)
			return false;
		return true;
	} // end of method 1
	
	
	// method 2
	public int[][] checkCollisionList(int[][] platforms) {
		ArrayList<Integer> arrL1 = new ArrayList<Integer>();
		ArrayList<Integer> arrL2 = new ArrayList<Integer>();
		
		for (int i = 0; i < platforms.length; i++) {
			for (int j = 0; j < platforms[0].length; j++) {
				if (checkCollision(i, j)) {
					arrL1.add(i);
					arrL2.add(j);
				}
			}			
		}
		
		int[][] collisionList = new int[arrL1.size()][2];
		
		for (int i = 0; i < arrL1.size(); i++) {
			Integer int1 = arrL1.get(i);
			int int2 = int1;
			collisionList[i][0] = int2;
			int1 = arrL2.get(i);
			int2 = int1;
			collisionList[i][1] = int2;
		}
		return collisionList;
	} // end of method 2
	
	// method 3
	public void resolvePlatformCollisions(int[][] platforms) {
		
		//changeY += gravity;
		//player.setY(changeY);
		//System.out.println(player.getY());
		
		
		int[][] collisionList = checkCollisionList(platforms);
		
		if (collisionList.length > 0) {
			if (changeY > 0) { // when the player is falling
				player.setBottom(lvl01.getTop(collisionList[0][0], collisionList[0][1]));
			} else if (changeY < 0)  { // when the player is jumping
				player.setTop(lvl01.getBottom(collisionList[0][0], collisionList[0][1]));
			}
			changeY = 0;
		}
		
		//collisionList = checkCollisionList(platforms);
		//if (collisionList.length > 0) {
			//player.setRight(lvl01.getLeft(collisionList[0][0], collisionList[0][1]));
		//}
	} // end of method 3
	
	
	public boolean isOnPlatform(int[][] platforms) {
		player.setY(5);
		int[][] collisionList = checkCollisionList(platforms);
		player.setY(-5);
		if (collisionList.length > 0 || player.getY() == 400)
			return true;
		return false;
	}
	
	
	
	// KEYLISTENER ------------------------------------------------------------------------------------------------------------------------
	/*
	PSEUDOCODE --------------------------------------------------------------------------------------------------------------------
	new position = old position + velocity
	new velocity = old velocity + acceleration
	
	y = position (new and old)
	changeY = velocity (new and old)
	gravity = acceleration
	
	changeY += gravity
	y += changeY
	
	Note - gravity pulls the character down (+), while changeY pulls the character up (-)
	
	If changeY = -12, gravity = 4...
	- player moves 12 pixels up
	- with gravity, there is that parabola effect: 
			-12 + 4 = -8....-8 + 4 = -4....-4 + 4 = 0 // goes up
			4 + 4 = 8....8 + 4 = 12 // goes down
	
	*/
} // end of MyGamePanel class