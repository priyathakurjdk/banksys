package banksys;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.plaf.FontUIResource;

public class managerlogin {
	JFrame managerloginframe;
public managerlogin() {
managerloginframe = new JFrame(" manager login page");
JPanel panel =new JPanel();


JLabel lbl = new JLabel("----manager  Login page-----");
lbl.setFont(new FontUIResource("arial",Font.BOLD|Font.ITALIC,20));



JLabel lbl1=new JLabel("enter manager email");
JTextField tf=new JTextField(20);

JLabel password=new JLabel("password");
JTextField tf2 =new JTextField(20);
JButton btn =new JButton("signup");
btn.addActionListener(new ActionListener() {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	String useremail=tf.getText();
	String userpassword =tf2.getText();
	System.out.println("email:"+useremail+"\npassword:"+userpassword);
	
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/managerdata","root","root");
			System.out.println("database connected");
			Statement statement=connection.createStatement();
			String signup ="select email,password FROM managerlogin where(email='"+useremail+ "'and password ='"+userpassword +"')";
			System.out.println(signup);
            ResultSet resultset = statement.executeQuery(signup);
			if(resultset.next()) {
				JOptionPane.showMessageDialog(managerloginframe, "you are signup");
				
				afterManagerLogin aml = new afterManagerLogin();
				aml.aftermanager.setVisible(true);
				aml.aftermanager.setLocationRelativeTo(managerloginframe);
				managerloginframe.setVisible(false);
				
				
			}
			else {
				JOptionPane.showMessageDialog(managerloginframe,"invalid");
			}
		}catch(SQLException e1) {
			e1.printStackTrace();
		}
		
	}
});
	



panel.add(lbl);
panel.add(lbl1);
panel.add(tf);
panel.add(password);
panel.add(tf2);
panel.add(btn);




managerloginframe.add(panel);
managerloginframe.setSize(250,300);
managerloginframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

}
}
