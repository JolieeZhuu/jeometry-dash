/*
 * Names: Simone Ghosh and Jolie Zhu
 * Teacher: Ms. Strelkovska
 * Course: ICS3U7-1
 * Date: January 19, 2023
 * Description: Game panel of Jeometry Dash
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MyGamePanel extends JPanel implements ActionListener, KeyListener {

	private JButton goMenu; // declare instance variables
	private ImageIcon backImg;
	
	private JPanel northP;
	private MovingBG bgP;
	private MyPlatformsPanel lvl;

	private Platforms platformsLvl[][];
	private int imgID[][];

	private String lvlName;
	private boolean isClicked, isLvlComp, isLvlIncomp, willJump, willFall;

	
	public MyGamePanel() {

		this.setFocusable(true); // request and add focus for keyListener
		this.requestFocus();
		this.addKeyListener(this);

		northP = new JPanel(); // initialize variables
		bgP = new MovingBG();
		lvl = new MyPlatformsPanel();

		backImg = new ImageIcon("Images/backButton.png");
		goMenu = new JButton(backImg);

		platformsLvl = MyPlatformsPanel.getLvl(); // get the same lvl array from MyPlatformsPanel
		imgID = MyPlatformsPanel.getImgID(); // get the same imgID array from MyPlatfromsPanel
		lvlName = "";
		
		goMenu.setOpaque(false); // buttons: transparent format
		goMenu.setContentAreaFilled(false);
		goMenu.setBorderPainted(false);
		goMenu.addActionListener(this); 
	
		this.setLayout(new BorderLayout(0, 0));
		this.setBackground(Color.BLUE); // set base panel to be blue

		this.add(northP, BorderLayout.NORTH); 
		northP.setOpaque(false); // set northP to be transparent
		northP.setLayout(new BorderLayout(0, 0));
		northP.add(goMenu, BorderLayout.WEST); // add button to northP
		
	} // end of constructor


	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == JeometryDash.gameTimer) {
			bgP.actionPerformed(e); // background movement and animations
			lvl.actionPerformed(e); // obstacle movement and animations
			checkCollisions(); 
			
			if (isClicked) { // create specific obstacles of specific level
				lvl.newGame();
				lvl.setLvl(lvlName);
				isClicked = false;
			}
			
			if (lvlName.equals("lvl01.csv")) // set panel colour for each level
				setBackground(Color.GREEN);
			else if (lvlName.equals("lvl02.csv"))
				setBackground(Color.MAGENTA);
			else if (lvlName.equals("lvl03.csv"))
				setBackground(Color.RED);

			if (isLvlComp) { // restart the game
				MyPlatformsPanel.restart(); 
				JeometryDash.gameTimer.stop(); // stopping timer resets actionPerformed
				JeometryDash.cardsL.last(JeometryDash.c);
				willJump = false; // player cannot fall and jump
				willFall = false;
			}
			
			if (willJump) { // player jumps
				JeometryDash.player.jump();
				willJump = false; // player cannot jump
			}
			
			if (willFall) { // player falls and collides
				checkCollisions(); // every timer player falls, check to see if it collides with anything
				JeometryDash.player.fall();
				willFall = false; // player cannot fall and jump
				willJump = false;
			}
			
			MyPlatformsPanel.start();
			
		}

		if (e.getSource() == goMenu) { // back to levels menu
			MyPlatformsPanel.restart(); 
			JeometryDash.gameTimer.stop();
			JeometryDash.cardsL.show(JeometryDash.c, "Levels");
			JeometryDash.player.setYs(400, 400); // reset yPlatform and y to be 400
			willJump = false; // player cannot fall and jump
			willFall = false;
		}
		repaint();
		
	} // end of actionPerformed
	
	
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		bgP.paintComponent(g); // background and ground images
		JeometryDash.player.draw(g);
		
		if (MyPlatformsPanel.getRunning()) // restarts level animation when false (i.e., user fails level and restarts)
			lvl.paintComponent(g); // obstacle and platform images
		
	} // end of paintComponent
	
	
	public void keyTyped(KeyEvent e) { } // end of keyTyped
	
	
	public void keyPressed(KeyEvent e) {
		
		if (e.getKeyCode() == 32) // 32 is space bar
			willJump = true; // player can jump (in actionPerformed)
		
	} // end of keyPressed

	
	public void keyReleased(KeyEvent e) {
		
		if (e.getKeyCode() == 32)  // 32 is space bar
			willFall = true; // player can fall (in actionPerformed)
		
	} // end of keyReleased
	
	
	public void checkCollisions() {
		
		Rectangle r1 = JeometryDash.player.getBounds(); // rectangle of player

		myCollisions: {
			for (int i = 0; i < 9; i++) { // rows
				for (int j = 0; j < 175; j++) { // columns 
					if (imgID[i][j] != 0) { // imgID with 0 is null
						Rectangle r2 = platformsLvl[i][j].getBounds(); // rectangle of obstacle
						
						// if player collides with a platform (2, 3, 4), set player's bottom to be platform's top
						if (r1.intersects(r2) && (imgID[i][j] == 2 || imgID[i][j] == 3 || imgID[i][j] == 4)) {
							JeometryDash.player.setYs(r2.y - 49, r2.y - 49); // set y and yPlatform to be r2.y - 49
							break myCollisions;
						
						// if player collides with any other obstacle (1, 5, 6, 7, 8, 9), show the popUp panel
						} else if (r1.intersects(r2)) {
							MyPlatformsPanel.restart(); // restart the game (back to beginning)
							JeometryDash.gameTimer.stop();
							JeometryDash.cardsL.last(JeometryDash.c);
							isLvlIncomp = true;
							willJump = false; // player cannot fall and jump
							willFall = false;
						
						// set player's yPlatform and y to be 400, when player collides with the ground
						} else if (JeometryDash.player.getY() >= 400) {
							JeometryDash.player.setYs(400, 400);
						}
					}
				}
			}
		}
						
	} // end of checkCollisions
	
	
	public boolean isColliding() {
		
		boolean collisionsExist = false;
		
		Rectangle r1 = JeometryDash.player.getBounds(); // rectangle of player
		
		myCollisions: {
			for (int i = 0; i < 9; i++) { // rows
				for (int j = 0; j < 175; j++) { // columns
					if (imgID[i][j] != 0) { // imgID with 0 is null
						Rectangle r2 = platformsLvl[i][j].getBounds(); // rectangle of obstacle
						
						// returns true if a platform or the ground will collide with player
						if (r1.intersects(r2) || JeometryDash.player.getY() >= 400) {
							collisionsExist = true;
							break myCollisions;
						}
					}
				}
			}
		}
		return collisionsExist;
		
	} // end of isColliding
	

	public void setLvlName (String lvl) { // used to create obstacle of selected lvl
		
		lvlName = lvl;
		
	} // end of setLvlName
	
	
	public void setIsClicked () { // checking if play button is clicked
		
		isClicked = true;
		
	} // end of setIsClicked
	
	
	public boolean getIsLvlComp () { // used to set up end panel and reset platforms
		
		return isLvlComp;
		
	} // end of getIsLvlComp
	
	
	public void setIsLvlComp (boolean tf) {
		
		isLvlComp = tf;
		
	} // end of setIsLvlComp
	
	
	public boolean getIsLvlIncomp () { // used to set up end panel and reset platforms
		
		return isLvlIncomp;
		
	} // end of getIsLvlIncomp
	
	
	public void setIsLvlIncomp (boolean tf) {
		
		isLvlIncomp = tf;
		
	} // end of setIsLvlIncomp
	
	
} // end of MyGamePanel class