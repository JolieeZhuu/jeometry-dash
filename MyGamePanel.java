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

public class MyGamePanel extends JPanel implements ActionListener, KeyListener {
	
	private JButton goMenu; // declare instance variables
	private ImageIcon backImg;
	private JPanel northP;
	
	private MovingBG bgP;
	
	private MyPlatformsPanel lvl01;
	private int x;
	
	private Player player;
	
	private String lvlName;
	private boolean clicked;
	
	public MyGamePanel() throws Exception {
		
		this.setFocusable(true); // request and add focus for keyListener
		this.requestFocus();
		this.addKeyListener(this);
		
		northP = new JPanel();
		bgP = new MovingBG();
		lvl01 = myPlatformsPanel();
		
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
			//lvl011.actionPerformed(e); // add obstacles animation
			MyPlatformsPanel.start();
			JeometryDash.player.move(); // add player movement
		}
		
		if (e.getSource() == goMenu) { // back button
			MyPlatformsPanel.restart();
			JeometryDash.gameTimer.stop();
			//lvl01.setXandY();
			//lvl01.repaint();
			JeometryDash.cardsL.show(JeometryDash.c, "Levels");
			JeometryDash.player.setY(400);
		}
		repaint();
		
	} // end of actionPerformed

	
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		bgP.paintComponent(g); // add background
		checkCollisions();
	
	} // end of paintComponent
	
	
	public void keyTyped(KeyEvent e) { // uses keyChar
	} // end of keyTyped
	
	public void keyPressed(KeyEvent e) { // uses keyCode
	} // end of keyPressed

	public void keyReleased(KeyEvent e) {
		JeometryDash.player.playerJump(); // call keyReleased method from Player class
	} // end of keyReleased

	
	public void checkCollisions() {
			
		Rectangle r1 = getBounds();
			
		for (Platforms[] innerArr : lvl01) {
			for (Platforms obstacle : innerArr) {
				Rectangle r2 = obstacle.getBounds();
				
				if (r1.intersects(r2)) {
					if (r1.y + 50 >= r2.y && r1.y + 50 <= r2.y + 50) {
						JeometryDash.player.setYPlatform(obstacle.getY());
						JeometryDash.player.setJumped(true);
					} else if (r1.y <= r2.y + 50 && r2.y <= r1.y) {
						
					} else {
						
					}
				}
			}
		}
	} // end of checkCollisions

	
	public void setLvlName (String lvl) {
		lvlName = lvl;
	}
	
	public void isClicked () {
		clicked = true;
	}
	
	
} // end of MyGamePanel class