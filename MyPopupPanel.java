/*
 * Names: Simone Ghosh and Jolie Zhu
 * Teacher: Ms. Strelkovska
 * Course: ICS3U7-1
 * Date: January 6, 2023
 * Description: Instructions panel of Jeometry Dash
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MyPopupPanel extends JPanel implements ActionListener {
	
	private JPanel buttonP;
	private JButton restart, backToMenu;
	private ImageIcon bg, popUp;
	
	
	public MyPopupPanel() {
		
		buttonP = new JPanel();
		
		bg = new ImageIcon("Images/bg01.png");
		popUp = new ImageIcon("Images/popUpImg.png");
		
		restart = new JButton("Images/restartButton.png");
		backToMenu = new JButton("Images/backToMenu.png");
		
		restart.setOpaque(false); // make button transparent
		restart.setContentAreaFilled(false);
		restart.setBorderPainted(false);
		restart.addActionListener(this);
		
		backToMenu.setOpaque(false); // make button transparent
		backToMenu.setContentAreaFilled(false);
		backToMenu.setBorderPainted(false);
		backToMenu.addActionListener(this);
		
		this.setLayout(new BorderLayout(0, 0));
		this.setBackground(Color.BLUE);
		
		this.add(buttonP, BorderLayout.CENTER);
		buttonP.setOpaque(false);
		buttonP.setLayout(new BoxLayout(buttonP, BoxLayout.X_AXIS));
		
		buttonP.add(Box.createRigidArea(new Dimension(45, 0))); // format buttons in terms of x
		buttonP.add(restart);
		buttonP.add(Box.createRigidArea(new Dimension(25, 0)));
		buttonP.add(backToMenu);
		
	} // end of constructor

	
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == backToMenu) // back button
			JeometryDash.cardsL.show(JeometryDash.c, "Levels");
		else if (e.getSource() == restart)
			// do something
		repaint();
		
	} // end of actionPerformed

	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		g.drawImage(bg.getImage(), 0, 0, null); // draw background image (no animation)
		g.drawImage(popUp.getImage(), 100, 100, null); // draw pop-up image

	} // end of paintComponent

} // end of MyInstPanel class