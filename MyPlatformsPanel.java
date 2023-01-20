/*
* Names: Simone Ghosh and Jolie Zhu
* Teacher: Ms. Strelkovska
* Course: ICS3U7-1
* Date: January 19, 2023
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


	public MyPlatformsPanel() {

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
			
			// read excel file to get the numbers or imgID (excel file is filled with numbers from 0 to 9) 
			while (sc.hasNextLine()) {
				for (int i = 0; i < row; i++) {
					String[] line = sc.nextLine().trim().split(","); // read each line
					for (int j = 0; j < line.length; j++) {
						imgID[i][j] = Integer.parseInt(line[j]); // store each number
						if (imgID[i][j] != 0) { // finds the occurrences of the last obstacles in the 2D array
							lastI = i;
							lastJ = j;
						}
					}
				}
			}		
		} catch (Exception e) {
			System.out.println("Please redownload the JeometryDash.zip application");
		}

	} // end of newLvl

	
	private void createPlatforms () {
		try {
			// store each platform's information (x, y, imgStringName) in a Platforms 2D array
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					if (imgID[i][j] != 0) // imgID 0 does not have useful information, so it is skipped
						lvl[i][j] = new Platforms(j * 50 + x, i * 50, "Images/Obst0" + imgID[i][j] + ".png");
				}
			}
		} catch (Exception e) {
			System.out.println("Please redownload the JeometryDash.zip application");
		}
		
	} // end of createPlatforms
	
	
	public void actionPerformed(ActionEvent e) {
		
		x -= 15; // obstacles and platforms move 15 pixels to the left every tick
		for (int i = 0; i < row; i++) { // obstacles animation
			for (int j = 0; j < col; j++) {
				if (imgID[i][j] != 0) {
					newX[i][j] = j * 50 + x; // set each x again, since x has changed
					lvl[i][j].setX(newX[i][j]);
				}
				
				// level is completed when the last obstacle disappears off the screen
				if (i == lastI && j == lastJ && newX[i][j] < -300) {
					JeometryDash.gameP.setIsLvlComp(true);
					x = 0;
				}
			}
		}
		
		if (newLvl) { // creates obstacles and platforms of appropriate level
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
	
	
	public static Platforms[][] getLvl() { // used to check collisions in gameP
		
		return lvl;
		
	} // end of getLvl01
	
	
	public static int[][] getImgID() { // used to check collision in gameP

		return imgID;

	} // end of getImgID

	
	public static void restart() { // allows user to restart game by stopping all loops and resetting variables
		
		x = 0;
		isRunning = false;
		
	} // end of restart
	
	
	public static void start() { // restarts all loops from begining
		
		isRunning = true;
		
	} // end of start
	
	
	public static boolean getRunning() { // used in gameP to check if platforms should be painted or not
		
		return isRunning;
		
	} // end of getRunning

	
	public void setLvl(String lvlName) { // allows to create platforms objects for specific levels
		
		this.lvlName = lvlName;
		
	} // end of setLvl

	
	public void newGame() { // reloads arrays with newLvl values
		
		newLvl = true;
		
	} // end of newGame
	
	
} // end of Platforms class