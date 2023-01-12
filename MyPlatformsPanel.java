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

public class MyPlatformsPanel extends JPanel implements ActionListener {
	
	private static int row, col, y;
	private static int x;
	private static int[][] imgID;
	private static ImageIcon[][] platforms;
	private static int getJ, xArr[][], yArr[][];
	private static boolean isRunning;
	
	
	public MyPlatformsPanel() throws Exception {
		
		row = 9; // initialize variables
		col = 360;
		x = 0;
		isRunning = true;
		imgID = new int[row][col];
		xArr = new int[row][col];
		yArr = new int[row][col];
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
					platforms[i][j] = new ImageIcon("Images/triangleObst02.png");
				else if (imgID[i][j] == 7)
					platforms[i][j] = new ImageIcon("Images/spikeObst02.png");
			}
		}
	}	
	
	
	public void actionPerformed(ActionEvent e) {
		
		x -= 15;
		
	} // end of actionPerformed
	
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		for (int i = 0; i < row; i++) { // go through the 2D array and draw each platform and obstacle
			for (int j = 0; j < col; j++) {
				if (imgID[i][j] != 0) {
					g.drawImage(platforms[i][j].getImage(), (j * 50)+x, i * 50, null);
					xArr[i][j] = (j * 50)+x;
					if (xArr[i][j] >= 100 && xArr[i][j] <= 100 + 50 && imgID[i][j] > 0) {
						getJ = j;
					}
					yArr[i][j] = i * 50;
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
	
	
	public static int getJ() {
		return getJ;
	} // end of getJ
	
	public static int getXs(int i, int j) {
		return xArr[i][j];
	} // end of getXs

	public static int getYs(int i, int j) {
		return yArr[i][j];
	} // end of getYs
	
	
	public void setXandY () {
		
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				xArr[i][j] = j * 50;
				yArr[i][j] = i * 50;
			}
		}
		
	} // end of setXandY 
	
	public static void restart () {
		x = 0;
		isRunning = false;
	}
	
	public static void start () {
		isRunning = true;
	}
	
	public static boolean getRunning () {
		return isRunning;
	}

} // end of Platforms class