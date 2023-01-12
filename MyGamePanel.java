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
	
	private Platforms lvl01[][];
	private ImageIcon platforms[][];
	private int row = Platforms.getRows();
	private int col = Platforms.getColumns();
	private int imgID[][] = Platforms.getImgID();
	private int x;
	
	private Player player;
	
	public MyGamePanel() throws Exception {
		
		this.setFocusable(true); // request and add focus for keyListener
		this.requestFocus();
		this.addKeyListener(this);
		
		northP = new JPanel();
		bgP = new MovingBG();
		
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
		
		lvl01 = new Platforms[row][col];
		platforms = new ImageIcon[row][col];
		
		player = new Player(100, 400);
		player.setImage("Images/cube01.png");
		createPlatforms();
		x = 0;

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
		
		x -= 15;
		
		repaint();
		
	} // end of actionPerformed

	
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		bgP.paintComponent(g); // add background
		if (MyPlatformsPanel.getRunning()) 
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					g.drawImage(platforms[i][j].getImage(), x, lvl01[i][j].getY(), null);
				}
			} // add obstacles
		
		g.drawImage(JeometryDash.player.getImage(), Player.getX(), JeometryDash.player.getY(), null); // add player
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
	

	private void createPlatforms () throws Exception { // please call this somewhere
		
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (imgID[i][j] == 1)
					platforms[i][j] = new ImageIcon("Images/triangleObst.png");
				else if (imgID[i][j] == 2)
					platforms[i][j] = new ImageIcon("Images/squareObst.png");
				else if (imgID[i][j] == 3)
					platforms[i][j] = new ImageIcon("Images/gridObst.png");
				else if (imgID[i][j] == 4)
					platforms[i][j] = new ImageIcon("Images/slabObst.png");
				else if (imgID[i][j] == 5)
					platforms[i][j] = new ImageIcon("Images/spikeObst.png");
				else if (imgID[i][j] == 6)
					platforms[i][j] = new ImageIcon("Images/triangleObst02.png");
				else if (imgID[i][j] == 7)
					platforms[i][j] = new ImageIcon("Images/spikeObst02.png");
				lvl01[i][j] = new Platforms((j * 50) + x, i * 50); // x -= 15 later in actionListener?
			}
		}
		
	} // end of createPlatforms
	
} // end of MyGamePanel class