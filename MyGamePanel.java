/*
 * Names: Simone Ghosh and Jolie Zhu
 * Teacher: Ms. Strelkovska
 * Course: ICS3U7-1
 * Date: January 6, 2023
 * Description: Game panel of Jeometry Dash
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class MyGamePanel extends JPanel implements ActionListener, KeyListener {
	
	private JButton goMenu; // declare instance variables
	private ImageIcon backImg;
	private JPanel northP;
	
	private MovingBG bgP;
	private MyPlatformsPanel lvl;
	
	private String lvlName;
	private boolean clicked;
	
	public MyGamePanel() {
		
		this.setFocusable(true); // request and add focus for keyListener
		this.requestFocus();
		this.addKeyListener(this);
		
		northP = new JPanel();
		bgP = new MovingBG();
		lvl = new MyPlatformsPanel();
		
		lvlName = "";
		
		backImg = new ImageIcon("Images/backButton.png");
		goMenu = new JButton(backImg);
		
		goMenu.setOpaque(false); // make button transparent
		goMenu.setContentAreaFilled(false);
		goMenu.setBorderPainted(false);
		
		goMenu.addActionListener(this); // add actionListener to button
		this.setLayout(new BorderLayout(0, 0));
		
		this.add(northP, BorderLayout.NORTH); // add panel to panel
		northP.setOpaque(false);
		northP.setLayout(new BorderLayout(0,0));
		northP.add(goMenu, BorderLayout.WEST); // add button to panel

	} // end of constructor

	
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == JeometryDash.gameTimer) {
			bgP.actionPerformed(e); // add background animation
			lvl.actionPerformed(e); // add obstacles animation
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
			
			//if (lvl.getReturnLvl()) {
			//	JeometryDash.cardsL.show(JeometryDash.c, "Levels");
			//	lvl.setReturnLvl(false);
			//}
		
			MyPlatformsPanel.start();
			JeometryDash.player.actionPerformed(e); // add player movement
		}
		
		if (e.getSource() == goMenu) { // back button
			MyPlatformsPanel.restart();
			JeometryDash.gameTimer.stop();
			lvl.setXandY();
			lvl.repaint();
			JeometryDash.cardsL.show(JeometryDash.c, "Levels");
			JeometryDash.player.setY(400);
		}
		repaint();
		
	} // end of actionPerformed

	
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		bgP.paintComponent(g); // add background
		if (MyPlatformsPanel.getRunning()) 
			lvl.paintComponent(g); // add obstacles
			
		JeometryDash.player.paintComponent(g); // add player
		checkCollisions();
	
	} // end of paintComponent
	
	
	public void keyTyped(KeyEvent e) { // uses keyChar
	} // end of keyTyped
	
	public void keyPressed(KeyEvent e) { // uses keyCode
	} // end of keyPressed

	public void keyReleased(KeyEvent e) {
		JeometryDash.player.keyReleased(e); // call keyReleased method from Player class
	} // end of keyReleased
	
	public void checkCollisions() {
		
		for (int i = 0; i < 8; i++) {
			if (lvl.getJ() > 0) {
				if (JeometryDash.player.getY() + 50 >= lvl.getYs(i, lvl.getJ()) && JeometryDash.player.getY() + 50 <= lvl.getYs(i, lvl.getJ()) + 50) { // player lower bound >= platform upper bound
					// works when jumping
					JeometryDash.player.setYPlatform(lvl.getYs(i, lvl.getJ()+1));
					JeometryDash.player.setJumped(true);
				} else if (JeometryDash.player.getY() <= lvl.getYs(i, lvl.getJ()) + 50 && lvl.getYs(i, lvl.getJ()) <= JeometryDash.player.getY()) { // player upper bound >= platform lower bound
					// works when jumping
					
				} else { // player x and platform x
					// works when passing through every obstacle
				}
			}
		}
		
	} // end of checkCollisions

	
	public boolean isOnPlatform() {
		
		for (int i = 0; i < 8; i++) {
			if (lvl.getJ() > 0)
				if ((JeometryDash.player.getY() + 50 >= lvl.getYs(i, lvl.getJ()) && JeometryDash.player.getY() <= lvl.getYs(i, lvl.getJ()))
					|| (JeometryDash.player.getX() + 50 >= lvl.getXs(i, lvl.getJ()) && JeometryDash.player.getX() <= lvl.getXs(i, lvl.getJ())))
					return true;
		} return false;
		
	} // end of isOnPlatform
	
	public void setLvlName (String lvl) {
		lvlName = lvl;
	}
	
	public void isClicked () {
		clicked = true;
	}
  
} // end of MyGamePanel class