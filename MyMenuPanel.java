/*
 * Names: Simone Ghosh and Jolie Zhu
 * Teacher: Ms. Strelkovska
 * Course: ICS3U7-1
 * Date: January 6, 2023
 * Description: Main menu panel of Jeometry Dash
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MyMenuPanel extends JPanel implements ActionListener {

	private JButton custom, inst, play, exit; // declare instance variables

	private JPanel buttonP, buttonP2;
	private MovingBG bgP;

	private ImageIcon title, customImg, instImg, playImg, exitImg;
	
	public MyMenuPanel() throws Exception {

		buttonP = new JPanel(); // initialize variables
		buttonP2 = new JPanel();
		bgP = new MovingBG();

		title = new ImageIcon("Images/title.png");
		customImg = new ImageIcon("Images/instButton.png");
		instImg = new ImageIcon("Images/instButton.png");
		playImg = new ImageIcon("Images/playButton.png");
		exitImg = new ImageIcon("Images/exitButton.png");
		custom = new JButton(customImg) { // set custom button size
			{
				setSize(100, 100);
				setMaximumSize(getSize());
			}
		};
		inst = new JButton("Instructions");
		play = new JButton(playImg) { // set play button size
			{
				setSize(150, 150);
				setMaximumSize(getSize());
			}
		};
		exit = new JButton(exitImg) { // set exit button size
			{
				setSize(100, 100);
				setMaximumSize(getSize());
			}
		};
				
		custom.setOpaque(false); // make button transparent
		custom.setContentAreaFilled(false);
		custom.setBorderPainted(false);
		
		inst.setOpaque(false); // make button transparent
		inst.setContentAreaFilled(false);
		inst.setBorderPainted(false);
		
		play.setOpaque(false); // make button transparent
		play.setContentAreaFilled(false);
		play.setBorderPainted(false);

		exit.setOpaque(false); // make button transparent
		exit.setContentAreaFilled(false);
		exit.setBorderPainted(false);

		custom.addActionListener(this); // add actionListener to buttons
		inst.addActionListener(this);
		play.addActionListener(this);
		exit.addActionListener(this);
		
		this.setLayout(new BorderLayout(0, 0));
		setBackground(Color.BLUE);
		
		this.add(buttonP, BorderLayout.CENTER);  // add panels to panel
		buttonP.setOpaque(false);
		buttonP.setLayout(new BoxLayout(buttonP, BoxLayout.X_AXIS));
		this.add(buttonP2, BorderLayout.SOUTH);
		buttonP2.setOpaque(false);
		
		buttonP.add(Box.createRigidArea(new Dimension(85, 0))); // format buttons in terms of x
		buttonP.add(custom);
		buttonP.add(Box.createRigidArea(new Dimension(40, 0)));
		buttonP.add(play);
		buttonP.add(Box.createRigidArea(new Dimension(40, 0)));
		buttonP.add(exit);
		
		buttonP2.add(inst);
	} // end of constructor

	
	public void actionPerformed(ActionEvent e) {
		
		bgP.actionPerformed(e); // add background animation from MovingBG class
		
		if (e.getSource() == play) { // button to game panel
			JeometryDash.cardsL.show(JeometryDash.c, "Levels");
		} else if (e.getSource() == inst) // button to instructions panel
			JeometryDash.cardsL.show(JeometryDash.c, "Instructions");
		else if (e.getSource() == custom) // button to customization panel
			JeometryDash.cardsL.last(JeometryDash.c);
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