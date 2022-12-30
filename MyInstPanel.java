/*
 * Names: Simone Ghosh and Jolie Zhu
 * Teacher: Ms. Strelkovska
 * Course: ICS3U7-1
 * Date: December 28
 * Description: Instructions panel of Jeometry Dash
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MyInstPanel extends JPanel implements ActionListener {

	private JPanel northP;
	private JButton goMenu; // declare instance variables
	private ImageIcon bg, ground, backImg;

	public MyInstPanel() {
		
		// initialize variables		
		northP = new JPanel();
		
		bg = new ImageIcon("Images/bg01.png");
		ground = new ImageIcon("Images/ground01.png");
		backImg = new ImageIcon("Images/backButton.png");
		
		goMenu = new JButton(backImg);
		goMenu.setOpaque(false); // make buttons transparent
		goMenu.setContentAreaFilled(false);
		goMenu.setBorderPainted(false);
		goMenu.addActionListener(this); // formatting
		
		this.setLayout(new FlowLayout());
		this.setBackground(Color.BLUE);
		
		this.add(northP, BorderLayout.NORTH);
		northP.setOpaque(false);
		northP.setLayout(new BorderLayout(0,0));
		northP.add(goMenu, BorderLayout.WEST);
	} // end of constructor

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == goMenu)
			JeometryDash.cardsL.first(JeometryDash.c);

	} // end of actionPerformed

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(bg.getImage(), 0, 0, null);
		g.drawImage(ground.getImage(), 0, 450, null);

	} // end of paintComponent

} // end of MyInstPanel class