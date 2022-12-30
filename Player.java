import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Player {
	
	private ImageIcon img;
	private int x, y;
	
	public Player(ImageIcon img) {
		this.img = img;
	}
	
	public void paintComponent (Graphics g) {
		g.drawImage(img.getImage(), x, y, 50, 50, null);
	}
}
