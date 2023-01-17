/*
 * Names: Simone Ghosh and Jolie Zhu
 * Teacher: Ms. Strelkovska
 * Course: ICS3U7-1
 * Date: January 18, 2023
 * Description: Instructions panel of Jeometry Dash
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MyPopupPanel extends JPanel implements ActionListener {
	
	private JPanel buttonP; // declare instance variables
	private JButton restartB, toMenuB;
	private ImageIcon bg, ground, popUp, restart, toMenu, title;
	
	
	public MyPopupPanel() {
		
		buttonP = new JPanel(); // initialize variables
		
		bg = new ImageIcon("Images/bg01.png");
		ground = new ImageIcon("Images/ground01.png");
		
		popUp = new ImageIcon("Images/popUpImg.png");
		restart = new ImageIcon("Images/restartButton.png");
		toMenu = new ImageIcon("Images/backToMenu.png");
		title = new ImageIcon("Images/lvlIncomp.png");
		
		restartB = new JButton(restart);
		toMenuB = new JButton(toMenu);
		
		restartB.setOpaque(false); // make button transparent
		restartB.setContentAreaFilled(false);
		restartB.setBorderPainted(false);
		restartB.addActionListener(this);
		
		toMenuB.setOpaque(false); // make button transparent
		toMenuB.setContentAreaFilled(false);
		toMenuB.setBorderPainted(false);
		toMenuB.addActionListener(this);
		
		this.setLayout(new BorderLayout(0, 0));
		this.setBackground(Color.BLUE);
		
		this.add(buttonP, BorderLayout.CENTER);
		buttonP.setOpaque(false);
		buttonP.setLayout(new BoxLayout(buttonP, BoxLayout.X_AXIS));
		
		buttonP.add(Box.createRigidArea(new Dimension(150, 0))); // format buttons in terms of x
		buttonP.add(restartB);
		buttonP.add(Box.createRigidArea(new Dimension(24, 0)));
		buttonP.add(toMenuB);
		
	} // end of constructor

	
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == toMenuB) { // back button
			JeometryDash.cardsL.show(JeometryDash.c, "Levels");
			JeometryDash.gameP.setLvlComp(false);
		}
			
		else if (e.getSource() == restartB) {
			JeometryDash.cardsL.show(JeometryDash.c, "JeometryDash");
			JeometryDash.gameTimer.start();
			JeometryDash.gameP.isClicked();
			JeometryDash.gameP.setLvlComp(false);
			JeometryDash.gameP.setLvlName("lvl0"+(JeometryDash.lvlP.getLvl()+1)+".csv");
			JeometryDash.gameP.setFocusable(true);
			JeometryDash.gameP.requestFocus();
		}
		
		if (JeometryDash.gameP.getLvlComp()) {
			title = new ImageIcon("Images/lvlComp.png");
		}	
		else
			title = new ImageIcon("Images/lvlIncomp.png");
		
		repaint();
		
	} // end of actionPerformed

	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		g.drawImage(bg.getImage(), 0, 0, null); // draw background image (no animation)
		g.drawImage(ground.getImage(), 0, 450, null);
		g.drawImage(popUp.getImage(), 50, 50, null); // draw pop-up image
		g.drawImage(title.getImage(), (getWidth() - 338) / 2, 75, null);

	} // end of paintComponent

} // end of MyInstPanel class