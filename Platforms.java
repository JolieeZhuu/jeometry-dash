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
import java.util.Arrays;

public class Platforms extends JPanel implements ActionListener {
	
	private int row, col, x, getJ;
	private int[][] imgID;
	private ImageIcon[][] platforms;

	
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
		*/
		
	} // end of actionPerformed
	
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		for (int i = 0; i < row; i++) { // go through the 2D array and draw each platform and obstacle
			for (int j = 0; j < col; j++) {
				if (imgID[i][j] != 0)
					g.drawImage(platforms[i][j].getImage(), (j * 50)+x, (i * 50), null);
			}
		}
		
	} // end of paintComponent

	/*
	public int getJ() {
		return getJ;
	} // end of getJ
	
	public int[][] getPlatforms() {
		return platforms;
	} // end of getPlatforms

	public int getXs(int i, int j) {
		return x[i][j];
	} // end of getXs

	public int getYs(int i, int j) {
		return y[i][j];
	} // end of getYs
	

	public void setXandY () {
		
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				x[i][j] = j * 50;
				y[i][j] = i * 50;
			}
		}
		
	} // end of setXandY */
	
} // end of Platforms class