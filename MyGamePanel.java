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
	private MyPlatformsPanel lvl01;
	private MyPlayerPanel player;
	
	private Platforms PlatformsLvl01[][];

	private String lvlName;
	private boolean clicked;

	
	public MyGamePanel() throws Exception {

		this.setFocusable(true); // request and add focus for keyListener
		this.requestFocus();
		this.addKeyListener(this);

		northP = new JPanel(); // initialize variables
		bgP = new MovingBG();
		lvl01 = new MyPlatformsPanel();
		player = new MyPlayerPanel();

		backImg = new ImageIcon("Images/backButton.png");
		goMenu = new JButton(backImg);

		PlatformsLvl01 = MyPlatformsPanel.getLvl01();
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
			lvl01.actionPerformed(e); // add obstacle animation
			
			if (clicked) {
				lvl01.newGame();
				lvl01.setLvl(lvlName);
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
			JeometryDash.player.move(); // add player movement
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
		if (MyPlatformsPanel.getRunning())
			lvl01.paintComponent(g); // add obstacles
		player.paintComponent(g); // add player
		checkCollisions();

	} // end of paintComponent
	
	
	public void keyTyped(KeyEvent e) { // uses keyChar
	} // end of keyTyped
	
	
	public void keyPressed(KeyEvent e) { // uses keyCode
	} // end of keyPressed

	
	public void keyReleased(KeyEvent e) {
		
		JeometryDash.player.move(); // call keyReleased method from Player class
		
	} // end of keyReleased


	public void checkCollisions() {

		Rectangle r1 = JeometryDash.player.getBounds();

		for (Platforms[] innerArr : PlatformsLvl01) {
			for (Platforms obstacle : innerArr) {
				Rectangle r2 = getBounds();

				if (r1.intersects(r2)) {
					if (r1.y + 50 >= r2.y && r1.y + 50 <= r2.y + 50) {
						JeometryDash.player.setYPlatform(obstacle.getY());
						JeometryDash.player.setJumped(true);
					} else {

					}
				}
			}
		}
		
	} // end of checkCollisions


	public void setLvlName (String lvl) {
		
		lvlName = lvl;
		
	} // end of setLvlName
	
	
	public void isClicked () {
		
		clicked = true;
		
	} // end of isClicked

} // end of MyGamePanel class