import java.awt.*;
import javax.swing.*;

public class Sprite {
	
	protected static int x;
	protected int y;
	protected int width;
	protected int height;
	protected ImageIcon img;
	protected Image img2;
	
	
	public Sprite(int x, int y) {
		
		this.x = x;
		this.y = y;
		
	} // end of constructor
	
	
	protected void getImageDimensions() {
		
		width = img2.getWidth(null);
		height = img2.getHeight(null);
		
	} // end of getImageDimensions
	
	
	protected void setImg(String imgName) {
		
		img = new ImageIcon(imgName);
		img2 = img.getImage();
		
	} // end of setImage
	
	public Image getImg() {
		return img2;
	} // end of getImage	
	
	public ImageIcon getImgIcon() {
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
	
	
	public void myDraw(Graphics g) {
		
	}
}