/*
 * Names: Simone Ghosh and Jolie Zhu
 * Teacher: Ms. Strelkovska
 * Course: ICS3U7-1
 * Date: January 6, 2023
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
	static MyCustomPanel customP;
	
	static Timer timer, gameTimer;
	static int delay;
	static MyPlayerPanel player;

	
	public JeometryDash() throws Exception {
		
		c = getContentPane(); // initialize variables
		cardsL = new CardLayout();
		c.setLayout(cardsL);
		menuP = new MyMenuPanel();
		gameP = new MyGamePanel();
		lvlP = new MyLvlPanel();
		instP = new MyInstPanel();
		customP = new MyCustomPanel();
		
		gameP.addKeyListener(gameP); // gameP is focused for keyListener
		gameP.setFocusable(true);
		
		c.add("MainMenu", menuP); // add panels to container
		c.add("JeometryDash", gameP);
		c.add("Levels", lvlP);
		c.add("Instructions", instP);
		c.add("Customization", customP);
		
		player = new MyPlayerPanel();
		delay = 1000/30;
		timer = new Timer(delay, this);
		gameTimer = new Timer(delay, this);
		timer.start();

	} // end of constructor

	
	public void actionPerformed(ActionEvent e) {
		
		menuP.actionPerformed(e);
		gameP.actionPerformed(e);
		lvlP.actionPerformed(e);
		instP.actionPerformed(e);
		customP.actionPerformed(e);
		
	} // end of actionPerformed
	
	
	public static void main(String[] args) throws Exception {
		
		JeometryDash frame = new JeometryDash(); // create object frame
		frame.setSize(600, 600);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // make frame closed when x button is pressed
		
	} // end of main

} // end of JeometryDash class