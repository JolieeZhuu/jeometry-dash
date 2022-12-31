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
	private int x, y, delay;
	private Timer timer;
	private MovingBG bgP;
	
	private ImageIcon img;
	private Player player;
	
	private boolean onGround;

	public MyGamePanel() {

		this.setFocusable(true);
		this.requestFocus();
		this.addKeyListener(this);
		
		x = 100; // initialize variables
		y = 400;
		delay = 1000/30;
		goMenu = new JButton("Go to menu panel");
		bgP = new MovingBG();
		
		goMenu.addActionListener(this); // formatting and adding interfaces
		this.setLayout(new FlowLayout());
		this.add(goMenu);
		this.setBackground(Color.BLUE);
		this.add(bgP, BorderLayout.NORTH);


		timer = new Timer(delay, this); // add and start a timer
		timer.start();
		
		img = new ImageIcon("Images/cube03.png");
		player = new Player(img);
		
		onGround = true;

	} // end of constructor

	public void actionPerformed(ActionEvent e) {
		bgP.actionPerformed(e);
		if (e.getSource() == goMenu)
			JeometryDash.cardsL.first(JeometryDash.c);
		repaint();
		
	} // end of actionPerformed

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		bgP.paintComponent(g);
		
		g.drawImage(player.getImg().getImage(), x, y, 50, 50, null);
		
		g.setColor(Color.white);
		g.fillRect(400, 400, 50, 50);

	} // end of paintComponent

	// KeyListener event handlers
	public void keyTyped(KeyEvent e) { // uses keyChar
	} // end of keyTyped
	
	public void keyPressed(KeyEvent e) { // uses keyCode
		if (e.getKeyCode() == 32 && onGround) { // 32 is key code for space
			y -= 50;
			repaint();
			onGround = false;
		}
	} // end of keyPressed

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == 32) { // 32 is key code for space
			y = 400;
			repaint();
			onGround = true;
		}
	} // end of keyReleased

} // end of MyGamePanel class