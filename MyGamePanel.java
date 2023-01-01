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

public class MyGamePanel extends JPanel implements ActionListener, KeyListener {
	
	private JButton goMenu; // declare instance variables
	private int delay;
	private Timer timer;
	private MovingBG bgP;
	private Player player;
	
	//private long lastPressProcessed = 0L;

	public MyGamePanel() throws Exception {

		this.setFocusable(true);
		this.requestFocus();
		this.addKeyListener(this);
		
		delay = 1000/30;
		goMenu = new JButton("Levels");
		bgP = new MovingBG();
		player = new Player();
		
		goMenu.addActionListener(this); // formatting and adding interfaces
		this.setLayout(new FlowLayout());
		this.add(goMenu);
		this.setBackground(Color.BLUE);
		this.add(bgP, BorderLayout.NORTH);

		timer = new Timer(delay, this); // add and start a timer
		timer.start();
	} // end of constructor

	public void actionPerformed(ActionEvent e) {
		bgP.actionPerformed(e);
		if (e.getSource() == goMenu)
			JeometryDash.cardsL.show(JeometryDash.c, "Levels");
		repaint();
		
	} // end of actionPerformed

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		bgP.paintComponent(g);
		
		g.drawImage(player.getImg().getImage(), player.getX(), player.getY(), 50, 50, null);
	} // end of paintComponent

	// KeyListener event handlers
	public void keyTyped(KeyEvent e) { // uses keyChar
	} // end of keyTyped
	
	public void keyPressed(KeyEvent e) { // uses keyCode
		if (e.getKeyCode() == 32) { // 32 = space bar
			player.jump();
		repaint();
			/*
			if (System.currentTimeMillis() - lastPressProcessed > 1000) {
				player.fall();
				repaint();
				lastPressProcessed = System.currentTimeMillis();
				System.out.println("Key was pressed");
			}
			*/
		}
		
	} // end of keyPressed

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == 32)
			player.fall();
		repaint();
	} // end of keyReleased

} // end of MyGamePanel class