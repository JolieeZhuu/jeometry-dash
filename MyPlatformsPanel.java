	

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
	
	private int row, col; // declare instance variables
	private static int x;
	private int[][] imgID;
	private static boolean isRunning;
	private static Platforms lvl[][];
	private String lvlName;
	private boolean newLvl, returnLvl;


	public MyPlatformsPanel() throws Exception {

		row = 9; // initialize variables
		col = 175;
		x = 0;
		isRunning = true;
		lvlName = "";
		imgID = new int[row][col];
		lvl = new Platforms[row][col];

		this.setLayout(new BorderLayout(0, 0));	

	} // end of constructor


	private void newLvl (String fileName) {

		try {
			Scanner sc = new Scanner(new BufferedReader(new FileReader(fileName)));

			while (sc.hasNextLine()) { // read excel file to get platforms
				for (int i = 0; i < row; i++) {
					String[] line = sc.nextLine().trim().split(",");
					for (int j = 0; j < line.length; j++)
						imgID[i][j] = Integer.parseInt(line[j]);
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
					if (imgID[i][j] == 1)
						lvl[i][j] = new Platforms(j * 50 + x, i * 50, "Images/triangleObst.png");
					else if (imgID[i][j] == 2)
						lvl[i][j] = new Platforms(j * 50 + x, i * 50, "Images/squareObst.png");
					else if (imgID[i][j] == 3)
						lvl[i][j] = new Platforms(j * 50 + x, i * 50, "Images/gridObst.png");
					else if (imgID[i][j] == 4)
						lvl[i][j] = new Platforms(j * 50 + x, i * 50, "Images/slabObst.png");
					else if (imgID[i][j] == 5)
						lvl[i][j] = new Platforms(j * 50 + x, i * 50, "Images/spikeObst.png");
					else if (imgID[i][j] == 6)
						lvl[i][j] = new Platforms(j * 50 + x, i * 50, "Images/triangleObst02.png");
					else if (imgID[i][j] == 7)
						lvl[i][j] = new Platforms(j * 50 + x, i * 50, "Images/spikeObst02.png");
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
					lvl[i][j].setX(j * 50 + x);
				}
			}
		}
		
		if (newLvl) {
			newLvl(lvlName);
			createPlatforms();
			newLvl = false;
		}

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (i == row && j == col)
					returnLvl = true;
			}
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

	
	public void setLvl(String lvl) {
		
		lvlName = lvl;
		
	} // end of setLvl

	
	public void newGame() {
		
		newLvl = true;
		
	} // end of newGame

	
	public void setReturnLvl(boolean tf) {
		
		returnLvl = tf;
		
	} // end of setReturnLvl

	
	public boolean getReturnLvl() {
		
		return returnLvl;
		
	} // returnLvl
	
} // end of Platforms class