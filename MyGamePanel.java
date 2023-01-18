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

	
	public MyGamePanel() throws Exception {

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
		
		goMenu.setOpaque(false); // make button transparent
		goMenu.setContentAreaFilled(false);
		goMenu.setBorderPainted(false);

		goMenu.addActionListener(this); // add actionListener to button
		this.setLayout(new BorderLayout(0, 0));
		this.setBackground(Color.BLUE);

		this.add(northP, BorderLayout.NORTH); // add panel to panel
		northP.setOpaque(false);
		northP.setLayout(new BorderLayout(0, 0));
		northP.add(goMenu, BorderLayout.WEST); // add button to panel
		
	} // end of constructor


	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == JeometryDash.gameTimer) {
			bgP.actionPerformed(e); // add background animation
			lvl.actionPerformed(e); // add obstacle animation
			checkAllCollisions();
			
			if (clicked) {
				lvl.newGame();
				lvl.setLvl(lvlName);
				clicked = false;
			}
			
			if (lvlName.equals("lvl01.csv"))
				setBackground(Color.BLUE);
			else if (lvlName.equals("lvl02.csv"))
				setBackground(Color.GREEN);
			else if (lvlName.equals("lvl03.csv"))
				setBackground(Color.RED);

			if (lvlComp) { 
				MyPlatformsPanel.restart(); // restart the game (back to beginning)
				JeometryDash.gameTimer.stop();
				JeometryDash.cardsL.last(JeometryDash.c);
				JeometryDash.player.setY(400);
			}
			
			if (willJump) {
				JeometryDash.player.jump();
				//willJump = false;
			}
			
			if (willFall) {
				if (!willJump) 
					fall();
				if (isColliding() && JeometryDash.player.getY() < 400)
					fall();
			}
				
			MyPlatformsPanel.start();
		}

		if (e.getSource() == goMenu) { // back button
			MyPlatformsPanel.restart(); // restart the game (back to beginning)
			JeometryDash.gameTimer.stop();
			JeometryDash.cardsL.show(JeometryDash.c, "Levels");
			JeometryDash.player.setY(400);
		}
		repaint();
		
	} // end of actionPerformed
	
	
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		bgP.paintComponent(g); // add background
		JeometryDash.player.draw(g);
		
		if (MyPlatformsPanel.getRunning())
			lvl.paintComponent(g); // add obstacles
		
		

	} // end of paintComponent
	
	
	public void keyTyped(KeyEvent e) { // uses keyChar
	} // end of keyTyped
	
	
	public void keyPressed(KeyEvent e) { // uses keyCode
		
		if (e.getKeyCode() == 32)  // 32 is space bar
			willJump = true;
		
	} // end of keyPressed

	
	public void keyReleased(KeyEvent e) {
		
		if (e.getKeyCode() == 32)  // 32 is space bar
			willFall = true;
		
	} // end of keyReleased


	public void checkAllCollisions() { // checks all collisions: includes falling

		if (isColliding())
			checkCollisions();

	} // end of checkEntireCollisions
	
	
	public void checkCollisions() { // check simple collisions: either set yPlatform or end game
		
		Rectangle r1 = JeometryDash.player.getBounds(); // rectangle of player

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 175; j++) {
				if (imgID[i][j] != 0) {
					Rectangle r2 = platformsLvl[i][j].getBounds(); // rectangle of obstacle

					if (r1.intersects(r2) && (imgID[i][j] == 2 || imgID[i][j] == 3 || imgID[i][j] == 4)) {
						JeometryDash.player.setY(r2.y - 49);
					} else if (r1.intersects(r2)) {
						
						MyPlatformsPanel.restart(); // restart the game (back to beginning)
						JeometryDash.gameTimer.stop();
						JeometryDash.cardsL.last(JeometryDash.c);
						JeometryDash.player.setY(400);
						lvlNotComp = true;
						
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

	
	public void fall () {
		
		for (int i = JeometryDash.player.getYPlatform(); i < 400 || !isColliding(); i += JeometryDash.player.getSpeed()) {
			JeometryDash.player.setSpeed(1 + JeometryDash.player.getSpeed());
			JeometryDash.player.setY(i);
		}
		if (isColliding())
			checkCollisions();
		else
			JeometryDash.player.setY(400);
		
		willJump = true;
		
	} // end of fall
	
	
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