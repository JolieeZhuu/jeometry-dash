/*
 * Names: Simone Ghosh and Jolie Zhu
 * Teacher: Ms. Strelkovska
 * Course: ICS3U7-1
 * Date: January 19, 2023
 * Description: Levels panel of Jeometry Dash
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MyLvlPanel extends JPanel implements ActionListener {

	private JButton[] buttons; // declare instance variables
	private ImageIcon[] imgs;
	private ImageIcon[] lvlImg;
	private int lvl;
	
	private JPanel northP, centerP;
	private MovingBG bgP;

	
	public MyLvlPanel() {
		
		northP = new JPanel(); // initialize variables
		centerP = new JPanel();
		bgP = new MovingBG();
		
		imgs = new ImageIcon[4];
		buttons = new JButton[4];
		
		lvl = 0;
		
		imgs[0] = new ImageIcon("Images/backButton.png"); // images
		imgs[1] = new ImageIcon("Images/playButton.png");
		imgs[2] = new ImageIcon("Images/nextButton.png");
		
		lvlImg = new ImageIcon[3];
		for (int i = 0; i < 3; i++) 
			lvlImg[i] = new ImageIcon("Images/lvl" + (i + 1) + ".png");
					
		this.setLayout(new BorderLayout(0, 0)); // new panels
		this.setBackground(Color.BLUE);
		
		this.add(northP, BorderLayout.NORTH); 
		northP.setOpaque(false);
		northP.setLayout(new BorderLayout(0,0));
		
		this.add(centerP, BorderLayout.CENTER);
		centerP.setOpaque(false);
		centerP.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 150));
		
		for (int i = 0; i < 4; i++) { // buttons 
			if (i < 3) {
				buttons[i] = new JButton(imgs[i]);
				centerP.add(buttons[i]);
			} else {
				buttons[i] = new JButton(imgs[0]);
				northP.add(buttons[i], BorderLayout.WEST);
			}
			buttons[i].setOpaque(false);
			buttons[i].setContentAreaFilled(false);
			buttons[i].setBorderPainted(false);
			buttons[i].addActionListener(this);
		}
		
	} // end of constructor

	
	public void actionPerformed(ActionEvent e) {
		
		bgP.actionPerformed(e); 
		
		if (e.getSource() == buttons[3]) // go back to main menu
			JeometryDash.cardsL.first(JeometryDash.c);
			
		else if (e.getSource() == buttons[1]) { // start the game
			JeometryDash.cardsL.show(JeometryDash.c, "JeometryDash");
			JeometryDash.gameTimer.start();
			JeometryDash.gameP.setIsClicked();
			JeometryDash.gameP.setLvlName("lvl0" + (lvl + 1) + ".csv");
			JeometryDash.gameP.setFocusable(true);
			JeometryDash.gameP.requestFocus();
			
		} else if (e.getSource() == buttons[2]) { // switch between levels
			++lvl;
			if (lvl == 3)
				lvl = 0;
		} else if (e.getSource() == buttons[0]) { // switch between levels
			--lvl;
			if (lvl == -1)
				lvl = 2;
		}
		repaint();
		
	} // end of actionPerformed

	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		bgP.paintComponent(g);
		g.drawImage(lvlImg[lvl].getImage(), (getWidth() - lvlImg[lvl].getIconWidth()) / 2, 100, null); // title level
		
	} // end of paintComponent
	
	
	public int getLvl () {
		
		return lvl;
	
	} // end of getLvl
	
	
} // end of MyLvlPanel class