package banksys;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.FontUIResource;

public class mainPage {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		JLabel lbl = new JLabel("welcome to bank management system");
		lbl.setFont(new FontUIResource("arial",Font.BOLD|Font.ITALIC,20));
		JButton btn1 = new JButton("Login as Manager");
		JButton btn2 = new JButton("Login as Customer");
		btn1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				managerlogin ml = new managerlogin();
				ml.managerloginframe.setVisible(true);
				ml.managerloginframe.setLocationRelativeTo(frame);
				frame.setVisible(false);
			}
		});
		
		
		btn2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				customerLogin cl = new customerLogin();
				cl.customerLogin.setVisible(true);
				cl.customerLogin.setLocationRelativeTo(frame);
				frame.setVisible(false);
			}
		});
		
		
		panel.add(lbl);
		panel.add(btn1);
		panel.add(btn2);
		frame.add(panel);
		frame.setVisible(true);
		frame.setSize(400,400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
	}
}
