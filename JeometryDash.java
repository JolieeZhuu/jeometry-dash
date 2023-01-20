/*
 * Names: Simone Ghosh and Jolie Zhu
 * Teacher: Ms. Strelkovska
 * Course: ICS3U7-1
 * Date: January 19, 2023
 * Description: Main (running) program of Jeometry Dash
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JeometryDash extends JFrame implements ActionListener {
	
	static CardLayout cardsL; // declare instance variables
	static Container c;
	
	static MyMenuPanel menuP;
	static MyGamePanel gameP;
	static MyLvlPanel lvlP;
	static MyInstPanel instP;
	static MyPopupPanel endP;
	
	static Timer timer, gameTimer;
	static int delay;
	static Platforms player;
	
	private static ImageIcon icon;

	
	public JeometryDash() {
		
		c = getContentPane(); // initialize variables
		cardsL = new CardLayout();
		c.setLayout(cardsL);
		menuP = new MyMenuPanel();
		gameP = new MyGamePanel();
		lvlP = new MyLvlPanel();
		instP = new MyInstPanel();
		endP = new MyPopupPanel();
		
		c.add("MainMenu", menuP); // add panels to container
		c.add("JeometryDash", gameP);
		c.add("Levels", lvlP);
		c.add("Instructions", instP);
		c.add("End", endP);
		
		icon = new ImageIcon("Images/cube03.png");
		player = new Player(100, 400, "Images/cube03.png");
		
		delay = 1000 / 30;
		gameTimer = new Timer(delay, this); // create timer
		timer = new Timer (delay, this);
		timer.start();
		
	} // end of constructor

	
	public void actionPerformed(ActionEvent e) {
		
		menuP.actionPerformed(e); // button actions
		gameP.actionPerformed(e); // game actions
		lvlP.actionPerformed(e); // switch between level actions
		endP.actionPerformed(e); // popUp panel actions
		 
	} // end of actionPerformed
	
	
	public static void main(String[] args) throws Exception {
		
		JeometryDash frame = new JeometryDash(); // create object frame
		frame.setSize(600, 600);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // make frame closed when x button is pressed
		frame.setIconImage(icon.getImage()); // set the frame's icon
		
	} // end of main

	
} // end of JeometryDash class