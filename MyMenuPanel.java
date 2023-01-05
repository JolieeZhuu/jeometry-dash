/*
 * Names: Simone Ghosh and Jolie Zhu
 * Teacher: Ms. Strelkovska
 * Course: ICS3U7-1
 * Date: December 28
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
	
	private Timer timer;
	
	private int delay;

	public MyMenuPanel() throws Exception {

		buttonP = new JPanel(); // initialize variables
		bgP = new MovingBG();

		title = new ImageIcon("Images/title.png");
		instImg = new ImageIcon("Images/instButton.png");
		playImg = new ImageIcon("Images/playButton.png");
		exitImg = new ImageIcon("Images/exitButton.png");
		inst = new JButton(instImg) {
			{
				setSize(100, 100);
				setMaximumSize(getSize());
			}
		};
		play = new JButton(playImg) {
			{
				setSize(150, 150);
				setMaximumSize(getSize());
			}
		};
		exit = new JButton(exitImg) {
			{
				setSize(100, 100);
				setMaximumSize(getSize());
			}
		};
		
		delay = 1000/30;

		inst.setOpaque(false); // make buttons transparent
		inst.setContentAreaFilled(false);
		inst.setBorderPainted(false);
		
		play.setOpaque(false); // make buttons transparent
		play.setContentAreaFilled(false);
		play.setBorderPainted(false);

		exit.setOpaque(false); // make buttons transparent
		exit.setContentAreaFilled(false);
		exit.setBorderPainted(false);

		inst.addActionListener(this); // add actionListener to buttons
		play.addActionListener(this);
		exit.addActionListener(this);
		
		this.setLayout(new BorderLayout(0, 0));
		setBackground(Color.BLUE);
		
		this.add(buttonP, BorderLayout.CENTER); // add panel to frame
		buttonP.setOpaque(false);
		buttonP.setLayout(new BoxLayout(buttonP, BoxLayout.X_AXIS));
		inst.setAlignmentY(Component.CENTER_ALIGNMENT);
		play.setAlignmentY(Component.CENTER_ALIGNMENT);
		exit.setAlignmentY(Component.CENTER_ALIGNMENT);
		buttonP.add(Box.createRigidArea(new Dimension(85, 0)));
		buttonP.add(inst);
		buttonP.add(Box.createRigidArea(new Dimension(40, 0)));
		buttonP.add(play);
		buttonP.add(Box.createRigidArea(new Dimension(40, 0)));
		buttonP.add(exit);
		
		timer = new Timer(delay, this);
		timer.start();
		
	} // end of constructor

	public void actionPerformed(ActionEvent e) {
		bgP.actionPerformed(e);
		if (e.getSource() == play)
			JeometryDash.cardsL.next(JeometryDash.c);
		else if (e.getSource() == inst)
			JeometryDash.cardsL.last(JeometryDash.c);
		else if (e.getSource() == exit)
			System.exit(0);
		repaint();
	} // end of actionPerformed

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		bgP.paintComponent(g);
		g.drawImage(title.getImage(), (getWidth() - 550) / 2, 75, 550, 70, null); // title image of menu panel

	} // end of paintComponent

} // end of MyMenuPanel class