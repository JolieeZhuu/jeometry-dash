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
	
	private Platforms PlatformsLvl[][];
	private Player player;

	private String lvlName;
	private boolean clicked;
	

	
	public MyGamePanel() throws Exception {

		this.setFocusable(true); // request and add focus for keyListener
		this.requestFocus();
		this.addKeyListener(this);

		northP = new JPanel(); // initialize variables
		bgP = new MovingBG();
		lvl = new MyPlatformsPanel();
		
		player = new Player(100, 400, "Images/cube03.png");

		backImg = new ImageIcon("Images/backButton.png");
		goMenu = new JButton(backImg);

		PlatformsLvl = MyPlatformsPanel.getLvl();
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

			/*if (lvl01.getReturnLvl()) {
				JeometryDash.cardsL.show(JeometryDash.c, "Levels");
				lvl01.setReturnLvl(false);
			}*/
			
			MyPlatformsPanel.start();
		}

		if (e.getSource() == goMenu) { // back button
			MyPlatformsPanel.restart(); // restart the game (back to beginning)
			JeometryDash.gameTimer.stop();
			JeometryDash.cardsL.show(JeometryDash.c, "Levels");
			player.setY(400);
		}
		repaint();
		
	} // end of actionPerformed
	
	
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		bgP.paintComponent(g); // add background
		player.draw(g);
		
		if (MyPlatformsPanel.getRunning())
			lvl.paintComponent(g); // add obstacles
		
		//checkCollisions();

	} // end of paintComponent
	
	
	public void keyTyped(KeyEvent e) { // uses keyChar
	} // end of keyTyped
	
	
	public void keyPressed(KeyEvent e) { // uses keyCode
		
		if (e.getKeyCode() == 32)  // 32 is space bar
			player.jump();
		repaint();
		
	} // end of keyPressed

	
	public void keyReleased(KeyEvent e) {
		
		if (e.getKeyCode() == 32) // 32 is space bar
			player.fall();
		repaint();
		
	} // end of keyReleased


	public void checkCollisions() {
/*
		Rectangle r1 = JeometryDash.player.getBounds();

		for (Platforms[] innerArr : PlatformsLvl) {
			for (Platforms obstacle : innerArr) {
				Rectangle r2 = getBounds();

				if (r1.intersects(r2)) {
					if (r1.y + 50 >= r2.y && r1.y + 50 <= r2.y + 50) {
						JeometryDash.player.setYPlatform(obstacle.getY());
						//JeometryDash.player.setJumped(true);
					} else 
						//System.out.println("Collision");
				}
			}
		}*/
		
	} // end of checkCollisions


	public void setLvlName (String lvl) {
		
		lvlName = lvl;
		
	} // end of setLvlName
	
	
	public void isClicked () {
		
		clicked = true;
		
	} // end of isClicked

} // end of MyGamePanel class