package banksys;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class afterCustomerLogin {
JFrame afterCustomerFrame;
public afterCustomerLogin(ResultSet result) throws SQLException {
	  afterCustomerFrame = new JFrame();
	  JPanel panel = new JPanel();
	  
	  String userName = result.getString("name");
	  JLabel name = new JLabel("Hello  : " +  userName);
	  
	  
	  String userAge = result.getString("age");
	  JLabel age =new JLabel(" age :  "  + userAge);
	  
	  String userAccount=result.getString("accountnumber");
	  JLabel account=new JLabel("accountnumber  : "+ userAccount);
	  
	  
	  
	  
	  
	  String userBirthday=result.getString("birthday");
	  JLabel birthday=new JLabel("birthday  : "+ userBirthday);
	  
	  String usercity=result.getString("city");
	  JLabel city =new JLabel("city  : "+ usercity);
	  
	  String userpin=result.getString("pin");
	  JLabel pin = new JLabel("pin : "+ userpin); 
	  
	  String userphone=result.getString("phone");
	  JLabel phone = new JLabel("phone : "+ userphone);
	  
	  String userdeposit=result.getString("initialdeposit");
	  JLabel deposit =new JLabel("initialdeposit : "+ userdeposit);
	  
	  
	  String userbalance=result.getString("balance");
	  JLabel balance=new JLabel("balance : "+ userbalance);
	  
	  
	  
	  
	  
	  JButton creditButton = new JButton("Credit");
	  creditButton.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	});
	  
      JButton debitButton = new JButton("Debit");
      debitButton.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			
		}
	});
      JButton transferButton = new JButton("Transfer");
      transferButton.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			
		}
	});
      
      JButton checkBalanceButton = new JButton("Check Balance");
      checkBalanceButton.addActionListener(new ActionListener() {
		
		@Override                                                                                                   
		public void actionPerformed(ActionEvent e) {
			
			
		}
	});

     
	  
	  
	  panel.add(name);
	  panel.add(age);
	  panel.add(account);
	  
	  
	  panel.add(birthday);
	  panel.add(city);
	  panel.add(pin);
	  panel.add(phone);
	  panel.add(deposit);
	  panel.add(balance);
	  
	  
	  panel.add(creditButton);
      panel.add(debitButton);
      panel.add(transferButton);
      panel.add(checkBalanceButton);

	  
	  
	  
	  
	  afterCustomerFrame.add(panel);
	  afterCustomerFrame.setSize(180, 300);
	 afterCustomerFrame.setVisible(true); 


}
}
