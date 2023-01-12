import java.awt.*;
import javax.swing.*;

public class Sprite {
	
	protected static int x;
	protected int y;
	protected int width;
	protected int height;
	protected Image img;
	
	
	public Sprite(int x, int y) {
		
		this.x = x;
		this.y = y;
		
	} // end of constructor
	
	
	protected void getImageDimensions() {
		
		width = img.getWidth(null);
		width = img.getHeight(null);
		
	} // end of getImageDimensions
	
	
	protected void setImage(String imgName) {
		
		ImageIcon img2 = new ImageIcon(imgName);
		img = img2.getImage();
		
	} // end of setImage
	
	
	public Image getImage() {
		return img;
	} // end of getImage	
	
	public static int getX() {
		return x;
	} // end of getX
	
	public int getY() {
		return y;
	} // end of getY
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	} // end of getBounds

}