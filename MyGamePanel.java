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

public class MyGamePanel extends JPanel implements ActionListener, KeyListener {
	
	private JButton goMenu; // declare instance variables
	private ImageIcon backImg;
	private JPanel northP;
	
	private int delay;
	private Timer timer;
	
	private MovingBG bgP;
	private Player player;
	private Platforms lvl01;
	
	//private long lastPressProcessed = 0L;

	public MyGamePanel() throws Exception {

		this.setFocusable(true);
		this.requestFocus();
		this.addKeyListener(this);
		
		delay = 1000/30;
		
		backImg = new ImageIcon("Images/backButton.png");
		goMenu = new JButton(backImg);
		goMenu.setOpaque(false); // make buttons transparent
		goMenu.setContentAreaFilled(false);
		goMenu.setBorderPainted(false);
		
		northP = new JPanel();
		bgP = new MovingBG();
		player = new Player();
		lvl01 = new Platforms();
		
		goMenu.addActionListener(this); // formatting and adding interfaces
		this.setLayout(new FlowLayout());
		this.setBackground(Color.BLUE);
		
		this.add(northP, BorderLayout.NORTH);
		northP.setOpaque(false);
		northP.setLayout(new BorderLayout(0,0));
		northP.add(goMenu, BorderLayout.WEST);

		timer = new Timer(delay, this); // add and start a timer
		timer.start();

	} // end of constructor

	public void actionPerformed(ActionEvent e) {
		bgP.actionPerformed(e);
		lvl01.actionPerformed(e);
		player.actionPerformed(e);
		if (e.getSource() == goMenu)
			JeometryDash.cardsL.show(JeometryDash.c, "Levels");
		repaint();
		
	} // end of actionPerformed

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		bgP.paintComponent(g);
		lvl01.paintComponent(g);
		player.paintComponent(g);
	} // end of paintComponent
	
	// COLLISIONS ------------------------------------------------------------------------------------------------
	
	/*public boolean checkCollision(int i, int j) {
		boolean noXOverlap = player.getRight() <= lvl01.getLeft(i, j) || player.getLeft() >= lvl01.getRight(i, j);
		boolean noYOverlap = player.getBottom() <= lvl01.getTop(i, j) || player.getTop() >= lvl01.getBottom(i, j);
		if (noXOverlap || noYOverlap)
			return false;
		return true;
	}
	
	public ArrayList<ArrayList<Integer>> checkCollisionList(int[][] list) {
		ArrayList<ArrayList<Integer>> collisionList = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < list.length; i++) {
			for (int j = 0; j < list[0].length; j++) {
				if (checkCollision(i, j)) {
					ArrayList<Integer> iJ = new ArrayList<Integer>();
					iJ.add(i);
					iJ.add(j);
					collisionList.add(iJ);
				}
			}
		}
		return collisionList;
	}
	
	public void resolvePlatformCollisions(int[][] platforms) {
		ArrayList<ArrayList<Integer>> collisionList = checkCollisionList(platforms);
		if (collisionList.size() > 0) {
			
		}
	}*/
	
	//-----------------------------------------------------------------------------------------------------------
	
	
	// KEYLISTENER-----------------------------------------------------------------------------------------------
	public void keyTyped(KeyEvent e) { // uses keyChar
	} // end of keyTyped
	
	public void keyPressed(KeyEvent e) { // uses keyCode
	} // end of keyPressed

	public void keyReleased(KeyEvent e) {
		player.keyReleased(e);
	} // end of keyReleased

} // end of MyGamePanel class