import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class Platforms extends Sprite implements ActionListener {
	
	private static int row, col;
	private static int[][] imgID;
	private static ImageIcon[][] platforms;
	private static int getJ, xArr[][], yArr[][];
	private static boolean isRunning;
	
	public Platforms(int x, int y) throws Exception {
		
		super(x, y); // initialize variables
		
		row = 9;
		col = 360;
		//x = 0;
		isRunning = true;
		imgID = new int[row][col];
		xArr = new int[row][col];
		yArr = new int[row][col];
		platforms = new ImageIcon[row][col];
		
		newLvl("lvl01.csv"); // call method
		createPlatforms();
		
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
	} // end of createPlatforms
	
	
	public void actionPerformed(ActionEvent e) {
		
		x -= 15;
		
	} // end of actionPerformed
	
	public static ImageIcon[][] getPlatforms() {
		return platforms;
	} // end of getPlatforms
	
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
	} // end of restart
	
	public static void start () {
		isRunning = true;
	} // end of start
	
	public static boolean getRunning () {
		return isRunning;
	} // end of getRunning
	
} // end of Platforms class
