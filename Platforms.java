/*
 * Names: Simone Ghosh and Jolie Zhu
 * Teacher: Ms. Strelkovska
 * Course: ICS3U7-1
 * Date: December 28
 * Description: Animations of Jeometry Dash
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.io.BufferedReader;
import java.io.FileReader;

import java.util.Arrays;
import java.util.Scanner;

public class Platforms extends JPanel implements ActionListener {
	
	private ImageIcon triangle, square, grid, rect, spike;
	private int row, col, cnt, delay;
	int [][] myArray;
	private Timer timer;

	public Platforms() throws Exception{
		row=8;
		col=360;
		cnt=0;
		delay = 1000/10;
		myArray = new int[row][col];
		
		createPlatforms("lvl01.csv");
		
		triangle = new ImageIcon("Images/triangleObst.png");
		square = new ImageIcon("Images/squareObst.png");
		grid = new ImageIcon("Images/gridObst.png");
		rect = new ImageIcon("Images/slabObst.png");
		spike = new ImageIcon("Images/spikeObst.png");
		
		this.setLayout(new BorderLayout(0, 0));	
		timer = new Timer(delay, this);
		timer.start();
	} // end of constructor
	
	private void createPlatforms(String fileName) throws Exception {
		Scanner sc = new Scanner(new BufferedReader(new FileReader(fileName)));

		while(sc.hasNextLine()) {
			for (int i=0; i<myArray.length; i++) {
				String[] line = sc.nextLine().trim().split(",");
				for (int j=0; j<line.length; j++)
					myArray[i][j] = Integer.parseInt(line[j]);
			}	
		}
	}
	
	// animation 
	public void actionPerformed(ActionEvent e){
		if (e.getSource() == timer){
			cnt++;
		}
		
	} // end of actionPerformed
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < row; i++) {
			for (int j=0; j<col; j++) {
				if (myArray[i][j] == 1)
					g.drawImage(triangle.getImage(), (j*50), (i*50)-cnt, null);
				else if (myArray[i][j] == 2)
					g.drawImage(square.getImage(), (j*50), (i*50)-cnt, null);
				else if (myArray[i][j] == 3)
					g.drawImage(grid.getImage(), (j*50), (i*50)-cnt, null);
				else if (myArray[i][j] == 4)
					g.drawImage(rect.getImage(), (j*50), (i*50)-cnt, null);
				else if (myArray[i][j] == 5)
					g.drawImage(spike.getImage(), (j*50), (i*50)-cnt, null);
			}
		}
	} // end of paintComponent
	
} // end of MovingBG class