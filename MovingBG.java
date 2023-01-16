/*
 * Names: Simone Ghosh and Jolie Zhu
 * Teacher: Ms. Strelkovska
 * Course: ICS3U7-1
 * Date: January 18, 2023
 * Description: Background animations of Jeometry Dash
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class MovingBG extends JPanel implements ActionListener {
	
	private ArrayList<ImageIcon> bg; // declare instance variables
	private ArrayList<ImageIcon> ground;
	
	private int bg_x, g_x;

	
	public MovingBG() throws Exception{
		
		bg = new ArrayList <ImageIcon>(); // initialize variables
		ground = new ArrayList <ImageIcon>();
		
		bg_x = 0;
		g_x = 0;
				
		for (int i = 0; i < 2; i++) { // add images to ArrayLists
			bg.add(new ImageIcon("Images/bg01.png"));
			ground.add(new ImageIcon("Images/ground01.png"));
		}
		
		this.setLayout(new BorderLayout(0, 0));
				
	} // end of constructor

	
	public void actionPerformed(ActionEvent e) {
		bg_x -= 8;
		g_x -= 12;
		
		if (bg_x == -600) { // remove and add background image
			bg.remove(0);
			bg.add(new ImageIcon("Images/bg01.png"));
			bg_x = 0;
		}
		
		if (g_x == -600) { // remove and add ground image
			ground.remove(0);
			ground.add(new ImageIcon("Images/ground01.png"));
			g_x = 0;
		}
		
	} // end of actionPerformed
	
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		for (int i = 0; i < 2; i++) { // draw background and ground images infinitely
			g.drawImage(bg.get(i).getImage(), bg_x + (i * 600), 0, null);
			g.drawImage(ground.get(i).getImage(), g_x + (i * 600), 450, null);
		}
		
	} // end of paintComponent
	
} // end of MovingBG class