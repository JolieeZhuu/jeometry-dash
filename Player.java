import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Player {
	
	private ImageIcon img;
	private int x, y;
	
	public Player(ImageIcon img) {
		this.img = img;
	}
	
	public ImageIcon getImg () {
		return img;
	}
}
