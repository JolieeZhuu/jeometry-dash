/*
 * Names: Simone Ghosh and Jolie Zhu
 * Teacher: Ms. Strelkovska
 * Course: ICS3U7-1
 * Date: December 28
 * Description: Levels panel of Jeometry Dash
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MyLvlPanel extends JPanel implements ActionListener {

	private JButton goMenu, play, next, prev; // declare instance variables
	
	private ImageIcon playImg, backImg, nextImg, lvlImg[], bg2;
	
	private int lvl, delay;
	
	private JPanel northP, centerP;
	private MovingBG bgP;
	
	private Timer timer;

	public MyLvlPanel() {
		
		northP = new JPanel();
		centerP = new JPanel();
		bgP = new MovingBG(); // initialize variables
		
		lvl = 0;
		delay = 1000/30;
		
		bg2 = new ImageIcon("Images/bg02.png");
		playImg = new ImageIcon("Images/playButton.png");
		backImg = new ImageIcon("Images/backButton.png");
		nextImg = new ImageIcon("Images/nextButton.png");
		lvlImg = new ImageIcon[3];
		for (int i = 0; i < 3; i++)
			lvlImg[i] = new ImageIcon("Images/lvl" + (i + 1) + ".png");
		
		goMenu = new JButton(backImg);
		next = new JButton(nextImg);
		prev = new JButton(backImg);
		play = new JButton(playImg) {
			{
				setSize(150, 150);
				setMaximumSize(getSize());
			}
		};
		
		goMenu.setOpaque(false); // make buttons transparent
		goMenu.setContentAreaFilled(false);
		goMenu.setBorderPainted(false);
		
		next.setOpaque(false); // make buttons transparent
		next.setContentAreaFilled(false);
		next.setBorderPainted(false);
		
		prev.setOpaque(false); // make buttons transparent
		prev.setContentAreaFilled(false);
		prev.setBorderPainted(false);
		
		play.setOpaque(false); // make buttons transparent
		play.setContentAreaFilled(false);
		play.setBorderPainted(false);
		
		goMenu.addActionListener(this); // formatting
		prev.addActionListener(this);
		next.addActionListener(this);
		play.addActionListener(this);
		
		this.setLayout(new BorderLayout(0, 0));
		this.setBackground(Color.BLUE);
		
		this.add(northP, BorderLayout.NORTH);
		northP.setOpaque(false);
		northP.setLayout(new BorderLayout(0,0));
		northP.add(goMenu, BorderLayout.WEST);
		
		this.add(centerP, BorderLayout.CENTER); // add panel to frame
		centerP.setOpaque(false);
		centerP.setLayout(new BoxLayout(centerP, BoxLayout.X_AXIS));
		prev.setAlignmentY(Component.CENTER_ALIGNMENT);
		play.setAlignmentY(Component.CENTER_ALIGNMENT);
		next.setAlignmentY(Component.CENTER_ALIGNMENT);
		centerP.add(Box.createRigidArea(new Dimension(120, 0)));
		centerP.add(prev);
		centerP.add(Box.createRigidArea(new Dimension(40, 0)));
		centerP.add(play);
		centerP.add(Box.createRigidArea(new Dimension(40, 0)));
		centerP.add(next);
		
		timer = new Timer(delay, this);
		timer.start();
	} // end of constructor

	public void actionPerformed(ActionEvent e) {
		bgP.actionPerformed(e);
		if (e.getSource() == goMenu)
			JeometryDash.cardsL.first(JeometryDash.c);
		else if (e.getSource() == play)
			JeometryDash.cardsL.next(JeometryDash.c);
		else if (e.getSource() == next){
			++lvl;
			if (lvl == 3)
				lvl = 0;
		}
		else if (e.getSource() == prev){
			--lvl;
			if (lvl == -1)
				lvl = 2;
		}
		repaint();
	} // end of actionPerformed

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		bgP.paintComponent(g);
		g.drawImage(bg2.getImage(), (getWidth() - 550) / 2, 50, null);
		g.drawImage(lvlImg[lvl].getImage(), (getWidth() - 550) / 2, 100, null);
		
	} // end of paintComponent
	
} // end of MyLvlPanel class