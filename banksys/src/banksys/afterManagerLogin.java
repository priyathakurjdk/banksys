package banksys;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;      

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.FontUIResource;

public class afterManagerLogin {
	JFrame aftermanager;
public afterManagerLogin() {
	aftermanager = new JFrame(" after manager");
	JPanel panel =new JPanel();

	JLabel lbl = new JLabel("----after Manager  Login page-----");
	lbl.setFont(new FontUIResource("arial",Font.BOLD|Font.ITALIC,20));

	JButton btn = new JButton("Welcome back Manager");
	
	JButton btn1 = new JButton("open new account");
	btn1.addActionListener(new ActionListener() {
		
@Override
		public void actionPerformed(ActionEvent e) {
			 newAccountPage nap =new newAccountPage();
			nap.accountPage.setVisible(true);
		nap.accountPage.setLocationRelativeTo(aftermanager);
			aftermanager.setVisible(false);
			
		}
	});

	
	
	

	
	
	
	      

	
	
	
	panel.add(lbl);
	panel.add(btn);
    panel.add(btn1);






    																																																																																																																																																																																							




	aftermanager.add(panel);
	aftermanager.setSize(270,300);
    aftermanager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);






	
	
}
}
