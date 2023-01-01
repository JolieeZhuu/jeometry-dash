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

import java.util.ArrayList;

public class MovingBG extends JPanel implements ActionListener {
	
	private ArrayList<ImageIcon> bg; // declare instance variables
	private ArrayList<ImageIcon> ground;
	
	private Platforms lvl01;
	
	private int bg_x, g_x, delay;
	private Timer timer;

	public MovingBG() throws Exception{
		bg = new ArrayList <ImageIcon>(); // initialize variables
		ground = new ArrayList <ImageIcon>();
		
		bg_x = 0;
		g_x = 0;
		delay = 1000/30;
		
		lvl01 = new Platforms();
		
		for (int i = 0; i < 2; i++){
			bg.add(new ImageIcon("Images/bg01.png"));
			ground.add(new ImageIcon("Images/ground01.png"));
		}
		
		this.setLayout(new BorderLayout(0, 0));
		this.add(lvl01, BorderLayout.NORTH);
		timer = new Timer(delay, this);
		timer.start();
				
	} // end of constructor
	
	// animation 
	public void actionPerformed(ActionEvent e){
		lvl01.actionPerformed(e);
		
		if (e.getSource() == timer){
			bg_x -= 10;
			g_x -= 15;
			
			// removes/adds img
			if (bg_x == -600){ 
				bg.remove(0);
				bg.add(new ImageIcon("Images/bg01.png"));
				bg_x = 0;
			}
			
			if (g_x == -600){
				ground.remove(0);
				ground.add(new ImageIcon("Images/ground01.png"));
				g_x = 0;
			}
		}
		
	} // end of actionPerformed
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		lvl01.paintComponent(g);
		for (int i = 0; i < 2; i++) {
			g.drawImage(bg.get(i).getImage(), bg_x + (i * 600), 0, null);
			g.drawImage(ground.get(i).getImage(), g_x + (i * 600), 450, null);
		}
		
	} // end of paintComponent
	
} // end of MovingBG class