import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Player extends JPanel implements ActionListener, KeyListener{
	
	private ImageIcon player;
	private int y, speed, delay;
	private boolean willJump, jumped;
	private Timer timer;
	private long lastPressProcessed = 0L;
	
	public Player() {
		
		this.setFocusable(true);
		this.requestFocus();
		this.addKeyListener(this);
		
		y = 405;
		speed = 1;
		delay = 1000/30;
				
		player = new ImageIcon("Images/cube03.png");
		
		this.setLayout(new BorderLayout(0, 0));
		timer = new Timer(delay, this); // add and start a timer
		timer.start();
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == timer && willJump && System.currentTimeMillis() - lastPressProcessed > 400) {
			for (int i=0; i<=75; i++)
				if (y>=330)
					y -= speed;
			lastPressProcessed = System.currentTimeMillis();
			willJump = false;
			jumped = true;
		}
		if (e.getSource() == timer && jumped && System.currentTimeMillis() - lastPressProcessed > 400) {
			for (int i=0; i<=75; i++)
				y += speed;
			lastPressProcessed = System.currentTimeMillis();
			jumped = false;
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(player.getImage(), 100, y, 45, 45, null);
	}
	
	public void keyTyped(KeyEvent e) { // uses keyChar
	} // end of keyTyped
	
	public void keyPressed(KeyEvent e) { // uses keyCode
	} // end of keyPressed

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == 32) // 32 = space bar
			willJump = true;
	} // end of keyReleased
}