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
	private MyPlatformsPanel lvl01;
	
	
	public MyGamePanel() throws Exception {
		
		this.setFocusable(true); // request and add focus for keyListener
		this.requestFocus();
		this.addKeyListener(this);
		
		northP = new JPanel();
		bgP = new MovingBG();
		lvl01 = new MyPlatformsPanel();
		
		backImg = new ImageIcon("Images/backButton.png");
		goMenu = new JButton(backImg);
		
		goMenu.setOpaque(false); // make button transparent
		goMenu.setContentAreaFilled(false);
		goMenu.setBorderPainted(false);
		
		goMenu.addActionListener(this); // add actionListener to button
		this.setLayout(new BorderLayout(0, 0));
		this.setBackground(Color.BLUE);
		
		this.add(northP, BorderLayout.NORTH); // add panel to panel
		northP.setOpaque(false);
		northP.setLayout(new BorderLayout(0,0));
		northP.add(goMenu, BorderLayout.WEST); // add button to panel

	} // end of constructor

	
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == JeometryDash.gameTimer) {
			bgP.actionPerformed(e); // add background animation
			lvl01.actionPerformed(e); // add obstacles animation
			MyPlatformsPanel.start();
			JeometryDash.player.actionPerformed(e); // add player movement
		}
		
		if (e.getSource() == goMenu) { // back button
			MyPlatformsPanel.restart();
			JeometryDash.gameTimer.stop();
			lvl01.setXandY();
			lvl01.repaint();
			JeometryDash.cardsL.show(JeometryDash.c, "Levels");
			JeometryDash.player.setY(400);
		}
		repaint();
		
	} // end of actionPerformed

	
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		bgP.paintComponent(g); // add background
		if (MyPlatformsPanel.getRunning()) 
			lvl01.paintComponent(g); // add obstacles
			
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
			if (lvl01.getJ() > 0) {
				if (JeometryDash.player.getY() + 50 >= lvl01.getYs(i, lvl01.getJ()) && JeometryDash.player.getY() + 50 <= lvl01.getYs(i, lvl01.getJ()) + 50) { // player lower bound >= platform upper bound
					// works when jumping
					JeometryDash.player.setYPlatform(lvl01.getYs(i, lvl01.getJ()+1));
					JeometryDash.player.setJumped(true);
				} else if (JeometryDash.player.getY() <= lvl01.getYs(i, lvl01.getJ()) + 50 && lvl01.getYs(i, lvl01.getJ()) <= JeometryDash.player.getY()) { // player upper bound >= platform lower bound
					// works when jumping
					
				} else { // player x and platform x
					// works when passing through every obstacle
				}
			}
		}
		
	} // end of checkCollisions

	
	public boolean isOnPlatform() {
		
		for (int i = 0; i < 8; i++) {
			if (lvl01.getJ() > 0)
				if ((JeometryDash.player.getY() + 50 >= lvl01.getYs(i, lvl01.getJ()) && JeometryDash.player.getY() <= lvl01.getYs(i, lvl01.getJ()))
					|| (JeometryDash.player.getX() + 50 >= lvl01.getXs(i, lvl01.getJ()) && JeometryDash.player.getX() <= lvl01.getXs(i, lvl01.getJ())))
					return true;
		} return false;
		
	} // end of isOnPlatform
  
} // end of MyGamePanel class