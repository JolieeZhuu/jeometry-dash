/*
 * Names: Simone Ghosh and Jolie Zhu
 * Teacher: Ms. Strelkovska
 * Course: ICS3U7-1
 * Date: January 6, 2023
 * Description: Levels panel of Jeometry Dash
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MyLvlPanel extends JPanel implements ActionListener {

	private JButton goMenu, play, next, prev; // declare instance variables
	private ImageIcon playImg, backImg, nextImg, lvlImg[], bg2;
	private int lvl;
	
	private JPanel northP, centerP;
	private MovingBG bgP;

	
	public MyLvlPanel() {
		
		northP = new JPanel(); // initialize variables
		centerP = new JPanel();
		bgP = new MovingBG();
		
		lvl = 0;
		
		bg2 = new ImageIcon("Images/bg02.png");
		playImg = new ImageIcon("Images/playButton.png");
		backImg = new ImageIcon("Images/backButton.png");
		nextImg = new ImageIcon("Images/nextButton.png");
		lvlImg = new ImageIcon[3];
		
		for (int i = 0; i < 3; i++) // add the title images of each level
			lvlImg[i] = new ImageIcon("Images/lvl" + (i + 1) + ".png");
		
		goMenu = new JButton(backImg);
		next = new JButton(nextImg);
		prev = new JButton(backImg);
		play = new JButton(playImg) { // set play button size
			{
				setSize(150, 150);
				setMaximumSize(getSize());
			}
		};
		
		goMenu.setOpaque(false); // make button transparent
		goMenu.setContentAreaFilled(false);
		goMenu.setBorderPainted(false);
		
		next.setOpaque(false); // make button transparent
		next.setContentAreaFilled(false);
		next.setBorderPainted(false);
		
		prev.setOpaque(false); // make button transparent
		prev.setContentAreaFilled(false);
		prev.setBorderPainted(false);
		
		play.setOpaque(false); // make button transparent
		play.setContentAreaFilled(false);
		play.setBorderPainted(false);
		
		goMenu.addActionListener(this); // add actionListener to buttons
		prev.addActionListener(this);
		next.addActionListener(this);
		play.addActionListener(this);
		
		this.setLayout(new BorderLayout(0, 0));
		this.setBackground(Color.BLUE);
		
		this.add(northP, BorderLayout.NORTH); // add panel to panel
		northP.setOpaque(false);
		northP.setLayout(new BorderLayout(0,0));
		northP.add(goMenu, BorderLayout.WEST); // add button to panel
		
		this.add(centerP, BorderLayout.CENTER); // add panel to panel
		centerP.setOpaque(false);
		centerP.setLayout(new BoxLayout(centerP, BoxLayout.X_AXIS));
		prev.setAlignmentY(Component.CENTER_ALIGNMENT); // center-align buttons in terms of y
		play.setAlignmentY(Component.CENTER_ALIGNMENT);
		next.setAlignmentY(Component.CENTER_ALIGNMENT);
		centerP.add(Box.createRigidArea(new Dimension(120, 0))); // format buttons in terms of x
		centerP.add(prev);
		centerP.add(Box.createRigidArea(new Dimension(40, 0)));
		centerP.add(play);
		centerP.add(Box.createRigidArea(new Dimension(40, 0)));
		centerP.add(next);
		
	} // end of constructor

	
	public void actionPerformed(ActionEvent e) {
		
		bgP.actionPerformed(e); // add background animation
		
		if (e.getSource() == goMenu) // back button
			JeometryDash.cardsL.first(JeometryDash.c);
		else if (e.getSource() == play) { // play button
			JeometryDash.cardsL.show(JeometryDash.c, "JeometryDash");
			JeometryDash.gameTimer.start();
			JeometryDash.gameP.isClicked();
			JeometryDash.gameP.setLvlName("lvl0"+(lvl+1)+".csv");
			JeometryDash.gameP.setFocusable(true);
			JeometryDash.gameP.requestFocus();
		} else if (e.getSource() == next) { // next level button
			++lvl;
			if (lvl == 3)
				lvl = 0;
		} else if (e.getSource() == prev) { // previous level button
			--lvl;
			if (lvl == -1)
				lvl = 2;
		}
		repaint();
		
	} // end of actionPerformed

	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		bgP.paintComponent(g); // add background
		g.drawImage(lvlImg[lvl].getImage(), (getWidth() - 550) / 2, 100, null); // draw title level
		
	} // end of paintComponent
	
} // end of MyLvlPanel class