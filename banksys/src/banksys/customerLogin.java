package banksys;

import java.awt.ActiveEvent;
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

public class customerLogin {
	JFrame customerLogin;
public customerLogin() {
	

customerLogin = new JFrame(" customer login stage");
JPanel panel =new JPanel();

JLabel lbl = new JLabel("----customer  Loginnnnnnn platee-----");
lbl.setFont(new FontUIResource("arial",Font.BOLD|Font.ITALIC,20));

JLabel lbl1=new JLabel("Account ka number");
JTextField tf=new JTextField(20);

JLabel pin=new JLabel("pin");
JTextField tf2 =new JTextField(20);

JButton btn =new JButton("thumsup");

btn.addActionListener(new ActionListener() {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		String accountnumber=tf.getText();
		String pin =tf2.getText();
		System.out.println("accountnumber:"+accountnumber+"\npin:"+pin);
		
			try {
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/managerdata","root","root");
				System.out.println("database connected");
				Statement statement=connection.createStatement();
				String signup ="select * FROM accounts where(accountnumber='"+accountnumber+ "'and pin ='"+pin +"')";
				System.out.println(signup);
	            ResultSet resultset = statement.executeQuery(signup);
				if(resultset.next()) {
					JOptionPane.showMessageDialog(customerLogin, "you are signup");
					
					 afterCustomerLogin acl=new afterCustomerLogin(resultset);
						acl.afterCustomerFrame.setVisible(true);
					acl.afterCustomerFrame.setLocationRelativeTo(customerLogin);
						customerLogin.setVisible(false);
						
					
					 
					
				}
				else {
					JOptionPane.showMessageDialog(customerLogin,"invalid");
				}
			}catch(SQLException e1) {
				e1.printStackTrace();
			}
			
		}
	});
		

		
panel.add(lbl);
panel.add(lbl1);
panel.add(tf);
panel.add(pin);
panel.add(tf2);
panel.add(btn);
customerLogin.add(panel);
customerLogin.setSize(230,300);
customerLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);






}
}