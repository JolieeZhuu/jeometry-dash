	

/*
* Names: Simone Ghosh and Jolie Zhu
* Teacher: Ms. Strelkovska
* Course: ICS3U7-1
* Date: January 18, 2023
* Description: Obstacles and platforms of Jeometry Dash
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.Scanner;

public class MyPlatformsPanel extends JPanel implements ActionListener {
	
	private int row, col, lastI, lastJ; // declare instance variables
	private static int x;
	private static int[][] imgID;
	private int[][] newX;
	private static boolean isRunning;
	private static Platforms lvl[][];
	private String lvlName;
	private boolean newLvl;


	public MyPlatformsPanel() throws Exception {

		row = 9; // initialize variables
		col = 175;
		x = 0;
		isRunning = true;
		lvlName = "";
		imgID = new int[row][col];
		lvl = new Platforms[row][col];
		newX = new int[row][col];

		this.setLayout(new BorderLayout(0, 0));	

	} // end of constructor


	private void newLvl (String fileName) {

		try {
			Scanner sc = new Scanner(new BufferedReader(new FileReader(fileName)));

			while (sc.hasNextLine()) { // read excel file to get platforms
				for (int i = 0; i < row; i++) {
					String[] line = sc.nextLine().trim().split(",");
					for (int j = 0; j < line.length; j++) {
						imgID[i][j] = Integer.parseInt(line[j]);
						if (imgID[i][j] != 0) {
							lastI = i;
							lastJ = j;
						}
					}
				}
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}

	} // end of newLvl

	
	private void createPlatforms () {
		try {
			for (int i = 0; i < row; i++) { // create Platforms 2D array to store all platforms' information
				for (int j = 0; j < col; j++) {
					if (imgID[i][j] != 0)
						lvl[i][j] = new Platforms(j * 50 + x, i * 50, "Images/Obst0" + imgID[i][j] + ".png");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	} // end of createPlatforms
	
	
	public void actionPerformed(ActionEvent e) {
		
		x -= 15;
		for (int i = 0; i < row; i++) { // obstacles animation
			for (int j = 0; j < col; j++) {
				if (imgID[i][j] != 0) {
					newX[i][j] = j * 50 + x;
					lvl[i][j].setX(newX[i][j]);
				}
				
				if (i == lastI && j == lastJ && newX[i][j] < -300) {
					JeometryDash.gameP.setLvlComp(true);
					lastI = 1000;
					lastJ = 1000;
					newX[i][j] = 1000;
					x = 0;
				}
			}
		}
		
		if (newLvl) {
			newLvl(lvlName);
			createPlatforms();
			newLvl = false;
		}

	} // end of actionPerformed


	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		for (int i = 0; i < row; i++) { // go through the 2D array and draw each platform and obstacle
			for (int j = 0; j < col; j++) {
				if (imgID[i][j] != 0) {
					lvl[i][j].draw(g);
				}
			}
		}

	} // end of paintComponent
	
	
	public static Platforms[][] getLvl() {
		
		return lvl;
		
	} // end of getLvl01
	
	
	public static int[][] getImgID() {

		return imgID;

	} // end of getImgID

	
	public static void restart() {
		
		x = 0;
		isRunning = false;
		
	} // end of restart
	
	
	public static void start() {
		
		isRunning = true;
		
	} // end of start
	
	
	public static boolean getRunning() {
		
		return isRunning;
		
	} // end of getRunning

	
	public void setLvl(String lvlName) {
		
		this.lvlName = lvlName;
		
	} // end of setLvl

	
	public void newGame() {
		
		newLvl = true;
		
	} // end of newGame
	
} // end of Platforms class