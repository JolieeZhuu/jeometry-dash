/*
 * Names: Simone Ghosh and Jolie Zhu
 * Teacher: Ms. Strelkovska
 * Course: ICS3U7-1
 * Date: January 6, 2023
 * Description: Obstacles and platforms of Jeometry Dash
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.Scanner;

/*
public class Platforms extends JPanel implements ActionListener {
	
	private int row, col, x, y;
	private int[][] imgID;
	private static ImageIcon[][] platforms;
	
	
	public Platforms() throws Exception {
		
		row = 9; // initialize variables
		col = 360;
		x = 0;
		imgID = new int[row][col];
		platforms = new ImageIcon[row][col];
		
		newLvl("lvl01.csv"); // call method
		createPlatforms();
		
		this.setLayout(new BorderLayout(0, 0));	
		
	} // end of constructor
	
	
	private void newLvl (String fileName) throws Exception {
		
		Scanner sc = new Scanner(new BufferedReader(new FileReader(fileName)));

		while (sc.hasNextLine()) { // read excel file to get platforms
			for (int i = 0; i < row; i++) {
				String[] line = sc.nextLine().trim().split(",");
				for (int j = 0; j < line.length; j++) {
					imgID[i][j] = Integer.parseInt(line[j]);
				}
			}	
		}
		
	} // end of newLvl
	
	
	private void createPlatforms () {
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
					platforms[i][j] = new ImageIcon("Images/spikeObst02.png");
				else if (imgID[i][j] == 7)
					platforms[i][j] = new ImageIcon("Images/spikeObst02.png");
			}
		}
	}	
	
	
	public void actionPerformed(ActionEvent e) {
		
		x -= 15;
		/*
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				x[i][j] = (j * 50) - (cnt * 15);
				if (x[i][j] == 110) {
					getJ = j;
				}
				y[i][j] = i * 50;
				
				if (x[i][j] < -75)
					platforms[i][j] = 0;
			}
		}
		cnt++; // remove this if we are doing infinite loop
		
		if (cnt > 1200) {
			cnt = 0;
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) 
					platforms[i][j] = old[i][j];
			}
		} else
			cnt++;
		
	} // end of actionPerformed
	
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		for (int i = 0; i < row; i++) { // go through the 2D array and draw each platform and obstacle
			for (int j = 0; j < col; j++) {
				if (imgID[i][j] != 0) {
					g.drawImage(platforms[i][j].getImage(), (j * 50)+x, (i * 50), null);
					y = i * 50;
				}
			}
		}
		
	} // end of paintComponent
	
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, 50, 50);
	}
	
	
	public static ImageIcon[][] getPlatforms() {
		return platforms;
	}

} // end of Platforms class
*/

public class Platforms extends JPanel implements ActionListener {
	
	private ImageIcon triangle, triangle02, square, grid, rect, spike, spike02; // declare instance variables
	private int row, col, cnt;
	private static int getJ;
	boolean isRunning;
	private int[][] platforms;
	private int[][] old;
	private static int[][] x;
	private static int[][] y;

	
	public Platforms() throws Exception {
		
		row = 9; // initialize variables
		col = 360;
		cnt = 0;
		platforms = new int[row][col];
		old = new int[row][col];
		x = new int[row][col];
		y = new int[row][col];

		triangle = new ImageIcon("Images/triangleObst.png");
		triangle02 = new ImageIcon("Images/triangleObst02.png");
		square = new ImageIcon("Images/squareObst.png");
		grid = new ImageIcon("Images/gridObst.png");
		rect = new ImageIcon("Images/slabObst.png");
		spike = new ImageIcon("Images/spikeObst.png");
		spike02 = new ImageIcon("Images/spikeObst02.png");
		
		createPlatforms("lvl01.csv"); // call method
		
		this.setLayout(new BorderLayout(0, 0));	
		
	} // end of constructor
	
	
	private void createPlatforms(String fileName) throws Exception {
		
		Scanner sc = new Scanner(new BufferedReader(new FileReader(fileName)));

		while (sc.hasNextLine()) { // read excel file to get platforms
			for (int i = 0; i < platforms.length; i++) {
				String[] line = sc.nextLine().trim().split(",");
				for (int j = 0; j < line.length; j++) {
					platforms[i][j] = Integer.parseInt(line[j]);
					old[i][j] = platforms[i][j];
				}
			}	
		}
		
	} // end of createPlatforms
	
	
	public void actionPerformed(ActionEvent e) {
			
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				x[i][j] = (j * 50) - (cnt * 15);
				if (x[i][j] >= 100 && x[i][j] <= 100 + 50 && platforms[i][j] > 0) {
					getJ = j;
				}
				y[i][j] = i * 50;
				
				if (x[i][j] < -75)
					platforms[i][j] = 0;
			}
		}
		cnt++; // remove this if we are doing infinite loop
		
		//if (cnt > 1200) {
		//	cnt = 0;
		//	for (int i = 0; i < row; i++) {
		//		for (int j = 0; j < col; j++) 
		//			platforms[i][j] = old[i][j];
		//	}
		//} else
		//	cnt++;
		
		
	} // end of actionPerformed
	
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		for (int i = 0; i < row; i++) { // go through the 2D array and draw each platform and obstacle
			for (int j = 0; j < col; j++) {
				if (platforms[i][j] == 1) {
					g.drawImage(triangle.getImage(), x[i][j], y[i][j], null);
				} else if (platforms[i][j] == 2) {
					g.drawImage(square.getImage(), x[i][j], y[i][j], null);
				} else if (platforms[i][j] == 3) {
					g.drawImage(grid.getImage(), x[i][j], y[i][j], null);
				} else if (platforms[i][j] == 4) {
					g.drawImage(rect.getImage(), x[i][j], y[i][j], null);
				} else if (platforms[i][j] == 5) {
					g.drawImage(spike.getImage(), x[i][j], y[i][j], null);
				} else if (platforms[i][j] == 6) {
					g.drawImage(triangle02.getImage(), x[i][j], y[i][j], null);
				} else if (platforms[i][j] == 7) {
					g.drawImage(spike02.getImage(), x[i][j], y[i][j], null);
				}
			}
		}
		
	} // end of paintComponent

	
	public static int getJ() {
		return getJ;
	} // end of getJ
	
	public static int getXs(int i, int j) {
		return x[i][j];
	} // end of getXs

	public static int getYs(int i, int j) {
		return y[i][j];
	} // end of getYs
	
	
	public void setXandY () {
		
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				x[i][j] = j * 50;
				y[i][j] = i * 50;
			}
		}
		
	} // end of setXandY 
	
	
} // end of Platforms class