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
	
	private int row, col, y, x;
	private static int[][] imgID;
	private ImageIcon[][] platforms;
	private static boolean isRunning;
	private String lvl;
	private boolean newLvl;
	
	private Platforms lvl01[][];
	
	public MyPlatformsPanel() {
		
		row = Platforms.getRows(); // initialize variables
		col = Platforms.getColumns();
		x = 0;
		isRunning = true;
		lvl = "";
		imgID[][] = Platforms.getImgID();
		platforms = new ImageIcon[row][col];
		lvl01 = new Platforms[row][col];
		
		this.setLayout(new BorderLayout(0, 0));	
		this.setBackground(Color.BLUE);
		
	} // end of constructor
	
	
	private void newLvl (String fileName) {
		
		try {
			Scanner sc = new Scanner(new BufferedReader(new FileReader(fileName)));

			while (sc.hasNextLine()) { // read excel file to get platforms
				for (int i = 0; i < row; i++) {
					String[] line = sc.nextLine().trim().split(",");
					for (int j = 0; j < line.length; j++) {
						imgID[i][j] = Integer.parseInt(line[j]);
					}
				}	
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
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
		if (newLvl) {
			newLvl(lvl);
			createPlatforms();
			newLvl = false;
		}
		
	} // end of actionPerformed
	
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				g.drawImage(platforms[i][j].getImage(), x, lvl01[i][j].getY(), null);
			}
		} // add obstacles
		
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
	
	public void setLvl (String lvl) {
		this.lvl = lvl;
	}
	
	public void newGame () {
		newLvl = true;
	}
} // end of Platforms class