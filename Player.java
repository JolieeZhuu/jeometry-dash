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
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(player.getImage(), 100, y, 45, 45, null);
	} // end of paintComponent
	
	
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
	
  public void keyTyped(KeyEvent e) { // uses keyChar
	} // end of keyTyped
	
	public void keyPressed(KeyEvent e) { // uses keyCode
	} // end of keyPressed

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == 32) // 32 = space bar
			willJump = true;
	} // end of keyReleased
	
/*	
	public ImageIcon getImg () {
		return img;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int changeY) {
		y += changeY;
	}
	
	public int getLeft() {
		return x;
	}
	
	public int getRight() {
		return x + 50;
	}
	
	public int getTop() {
		return y;
	}
	
	public int getBottom() {
		return y + 50;
	}
	
	public void setLeft(int x) {
		this.x = x; 
	}
	
	public void setRight(int x) {
		this.x = x + 50; 
	}
	
	public void setTop(int y) {
		this.y = y; 
	}
	
	public void setBottom(int y) {
		this.y = y - 50; 
	}
	
	public void setIsJump (boolean tf) {
		isJump = tf;
	}
 */
}