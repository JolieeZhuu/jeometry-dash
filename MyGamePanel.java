/*
 * Names: Simone Ghosh and Jolie Zhu
 * Teacher: Ms. Strelkovska
 * Course: ICS3U7-1
 * Date: January 18, 2023
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
	private boolean clicked, lvlComp, lvlNotComp, willJump, willFall;

	
	public MyGamePanel() {

		this.setFocusable(true); // request and add focus for keyListener
		this.requestFocus();
		this.addKeyListener(this);

		northP = new JPanel(); // initialize variables
		bgP = new MovingBG();
		lvl = new MyPlatformsPanel();

		backImg = new ImageIcon("Images/backButton.png");
		goMenu = new JButton(backImg);

		platformsLvl = MyPlatformsPanel.getLvl();
		imgID = MyPlatformsPanel.getImgID();
		lvlName = "";
		
		goMenu.setOpaque(false); // button info
		goMenu.setContentAreaFilled(false);
		goMenu.setBorderPainted(false);
		goMenu.addActionListener(this); 
	
		this.setLayout(new BorderLayout(0, 0)); // new panels
		this.setBackground(Color.BLUE);

		this.add(northP, BorderLayout.NORTH); 
		northP.setOpaque(false);
		northP.setLayout(new BorderLayout(0, 0));
		northP.add(goMenu, BorderLayout.WEST); 
		
	} // end of constructor


	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == JeometryDash.gameTimer) {
			bgP.actionPerformed(e); // animations
			lvl.actionPerformed(e);
			checkCollisions(); 
			
			if (clicked) {
				lvl.newGame();
				lvl.setLvl(lvlName);
				clicked = false;
			}
			
			if (lvlName.equals("lvl01.csv")) // panel colour
				setBackground(Color.GREEN);
			else if (lvlName.equals("lvl02.csv"))
				setBackground(Color.MAGENTA);
			else if (lvlName.equals("lvl03.csv"))
				setBackground(Color.RED);

			if (lvlComp) { // restart the game
				MyPlatformsPanel.restart(); 
				JeometryDash.gameTimer.stop();
				JeometryDash.cardsL.last(JeometryDash.c);
				willJump = false;
				willFall = false;
			}
			
			if (willJump) {
				JeometryDash.player.jump();
				willJump = false;
			}
			
			if (willFall) {
				checkCollisions();
				JeometryDash.player.fall();
				willFall = false;
				willJump = false;
			}
			
			MyPlatformsPanel.start();
			
		}

		if (e.getSource() == goMenu) { // back button
			MyPlatformsPanel.restart(); 
			JeometryDash.gameTimer.stop();
			JeometryDash.cardsL.show(JeometryDash.c, "Levels");
			JeometryDash.player.setYs(400, 400);
			willJump = false;
			willFall = false;
		}
		repaint();
		
	} // end of actionPerformed
	
	
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		bgP.paintComponent(g); 
		JeometryDash.player.draw(g);
		
		if (MyPlatformsPanel.getRunning())
			lvl.paintComponent(g); // add obstacles
		
	} // end of paintComponent
	
	
	public void keyTyped(KeyEvent e) {
	} // end of keyTyped
	
	
	public void keyPressed(KeyEvent e) {
		
		if (e.getKeyCode() == 32)  // 32 is space bar
			willJump = true;
		
	} // end of keyPressed

	
	public void keyReleased(KeyEvent e) {
		
		if (e.getKeyCode() == 32) 
			willFall = true;
		
	} // end of keyReleased
	
	
	public void checkCollisions() { // check simple collisions: either set yPlatform or end game
		
		Rectangle r1 = JeometryDash.player.getBounds(); // rectangle of player

		myCollisions: {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 175; j++) {
					if (imgID[i][j] != 0) {
						Rectangle r2 = platformsLvl[i][j].getBounds(); // rectangle of obstacle

						if (r1.intersects(r2) && (imgID[i][j] == 2 || imgID[i][j] == 3 || imgID[i][j] == 4)) {
							JeometryDash.player.setYs(r2.y - 49, r2.y - 49);
							break myCollisions;
						} else if (r1.intersects(r2)) {
							MyPlatformsPanel.restart(); // restart the game (back to beginning)
							JeometryDash.gameTimer.stop();
							JeometryDash.cardsL.last(JeometryDash.c);
							lvlNotComp = true;
							willJump = false;
							willFall = false;
						} else if (JeometryDash.player.getY() >= 400) {
							JeometryDash.player.setYs(400,400);
						}
					}
				}
			}
		}
						
	} // end of checkCollisions
	
	
	public boolean isColliding() { // returns whether any platforms are going to collide with player
		
		boolean collisionsExist = false;
		
		Rectangle r1 = JeometryDash.player.getBounds(); // rectangle of player
		
		myCollisions: {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 175; j++) {
					if (imgID[i][j] != 0) {
						Rectangle r2 = platformsLvl[i][j].getBounds(); // rectangle of obstacle

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
	

	public void setLvlName (String lvl) {
		
		lvlName = lvl;
		
	} // end of setLvlName
	
	
	public void isClicked () {
		
		clicked = true;
		
	} // end of isClicked
	
	
	public boolean getLvlComp () {
		
		return lvlComp;
		
	} // end of getLvlComp
	
	
	public void setLvlComp (boolean tf) {
		
		lvlComp = tf;
		
	} // end of setLvlComp
	
	
	public boolean getLvlNotComp () {
		
		return lvlNotComp;
		
	} // end of getLvlNotComp
	
	
	public void setLvlNotComp (boolean tf) {
		
		lvlNotComp = tf;
		
	} // end of setLvlNotComp
	
	
} // end of MyGamePanel class