/*
 * Names: Simone Ghosh and Jolie Zhu
 * Teacher: Ms. Strelkovska
 * Course: ICS3U7-1
 * Date: January 5, 2023
 * Description: Main (running) program of Jeometry Dash
 */

import java.awt.*;
import javax.swing.*;

public class JeometryDash extends JFrame {
	
	static CardLayout cardsL; // declare instance variables
	static Container c;

	static MyMenuPanel menuP;
	static MyLvlPanel lvlP;
	static MyGamePanel gameP;
	static MyInstPanel instP;
	static MyCustomPanel customP;

	public JeometryDash() throws Exception {
		
		c = getContentPane(); // initialize variables
		cardsL = new CardLayout();
		c.setLayout(cardsL);
		menuP = new MyMenuPanel();
		lvlP = new MyLvlPanel();
		gameP = new MyGamePanel();
		instP = new MyInstPanel();
		customP = new MyCustomPanel();
		
		gameP.addKeyListener(gameP); // gameP is focused for keyListener
		gameP.setFocusable(true);
		
		c.add("MainMenu", menuP); // add panels to container
		c.add("Levels", lvlP);
		c.add("JeometryDash", gameP);
		c.add("Instructions", instP);
		c.add("Customization", customP);

	} // end of constructor

	public static void main(String[] args) throws Exception {
		JeometryDash frame = new JeometryDash(); // create object frame
		frame.setSize(600, 600);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // make frame closed when x button is pressed
	} // end of main

} // end of JeometryDash class