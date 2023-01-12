import javax.swing.*;
import java.io.*;
import java.util.*;

public class Platforms extends Sprite {
	
	private static int row, col;
	private static int[][] imgID;
	private static boolean isRunning;
	
	public Platforms(int x, int y) throws Exception {
		
		super(x, y); // initialize variables
		
		row = 9;
		col = 360;
		//x = 0;
		isRunning = true;
		imgID = new int[row][col];
		
		newLvl("lvl01.csv"); // call method
		
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
	
	
	public static int getRows () {
		return row;
	} // end of getRows
	
	public static int getColumns () {
		return col;
	} // end of getColumns
	
	public static int[][] getImgID () {
		return imgID;
	} // end of getImgID
	
} // end of Platforms class
