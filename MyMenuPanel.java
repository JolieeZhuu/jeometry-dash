/*
 * Names: Simone Ghosh and Jolie Zhu
 * Teacher: Ms. Strelkovska
 * Course: ICS3U7-1
 * Date: January 18, 2023
 * Description: Main menu panel of Jeometry Dash
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MyMenuPanel extends JPanel implements ActionListener {

	private JButton inst, play, exit; // declare instance variables

	private JPanel buttonP;
	private MovingBG bgP;

	private ImageIcon title, instImg, playImg, exitImg;
	
	
	public MyMenuPanel() throws Exception {

		buttonP = new JPanel(); // initialize variables
		bgP = new MovingBG();

		title = new ImageIcon("Images/title.png");
		instImg = new ImageIcon("Images/instButton.png");
		playImg = new ImageIcon("Images/playButton.png");
		exitImg = new ImageIcon("Images/exitButton.png");
		
		inst = new JButton(instImg);
		play = new JButton(playImg);
		exit = new JButton(exitImg);

		//custom.setPreferredSize(new Dimension(100, 100)); // set button size
		play.setPreferredSize(new Dimension(150, 150));
		exit.setPreferredSize(new Dimension(100, 100));
				
		inst.setOpaque(false); // make button transparent
		inst.setContentAreaFilled(false);
		inst.setBorderPainted(false);
		
		play.setOpaque(false); // make button transparent
		play.setContentAreaFilled(false);
		play.setBorderPainted(false);

		exit.setOpaque(false); // make button transparent
		exit.setContentAreaFilled(false);
		exit.setBorderPainted(false);

		inst.addActionListener(this);
		play.addActionListener(this);
		exit.addActionListener(this);
		
		this.setLayout(new BorderLayout(0, 0));
		setBackground(Color.BLUE);
		
		this.add(buttonP, BorderLayout.CENTER);  // add panels to panel
		buttonP.setOpaque(false);
		buttonP.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 200));

		buttonP.add(inst); // add buttons to panel
		buttonP.add(play);
		buttonP.add(exit);

		
	} // end of constructor

	
	public void actionPerformed(ActionEvent e) {
		
		bgP.actionPerformed(e); // add background animation from MovingBG class
		
		if (e.getSource() == play) { // button to game panel
			JeometryDash.cardsL.show(JeometryDash.c, "Levels");
		} else if (e.getSource() == inst) // button to instructions panel
			JeometryDash.cardsL.show(JeometryDash.c, "Instructions");
		else if (e.getSource() == exit) // button to exit
			System.exit(0);
		repaint();
		
	} // end of actionPerformed
	
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		bgP.paintComponent(g); // add background
		g.drawImage(title.getImage(), (getWidth() - 550) / 2, 75, 550, 70, null); // title image of menu panel

	} // end of paintComponent
	
} // end of MyMenuPanel