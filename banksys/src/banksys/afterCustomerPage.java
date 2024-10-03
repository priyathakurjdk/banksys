package banksys;

	import javax.swing.*;
	import java.awt.*;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.math.BigDecimal;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.util.Random;

	public class afterCustomerPage {

	    private static final int ACCOUNT_NUMBER_LENGTH = 10;
	    JFrame afterCustomer;

	    public afterCustomerPage() {
			// TODO Auto-generated constructor stub
		
	        afterCustomer = new JFrame("Account Transactions");
	        JPanel panel = new JPanel();
	        panel.setLayout(null);

	        JLabel lbl = new JLabel("----Account Transactions-----");
	        lbl.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
	        lbl.setBounds(50, 10, 300, 30);

	        JLabel accountNumberLbl = new JLabel("Account Number:");
	        accountNumberLbl.setBounds(10, 50, 150, 30);
	        JTextField accountNumberInput = new JTextField(20);
	        accountNumberInput.setBounds(170, 50, 150, 30);

	        JLabel amountLbl = new JLabel("Amount:");
	        amountLbl.setBounds(10, 90, 150, 30);
	        JTextField amountInput = new JTextField(20);
	        amountInput.setBounds(170, 90, 150, 30);

	        JButton creditButton = new JButton("Credit Money");
	        creditButton.setBounds(50, 130, 120, 30);
	        creditButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                performTransaction(accountNumberInput.getText(), amountInput.getText(), true);
	            }
	        });

	        JButton debitButton = new JButton("Debit Money");
	        debitButton.setBounds(180, 130, 120, 30);
	        debitButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                performTransaction(accountNumberInput.getText(), amountInput.getText(), false);
	            }
	        });

	        JButton transferButton = new JButton("Transfer Money");
	        transferButton.setBounds(50, 170, 250, 30);
	        transferButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                String toAccountNumber = JOptionPane.showInputDialog(afterCustomer, "Enter recipient account number:");
	                performTransfer(accountNumberInput.getText(), toAccountNumber, amountInput.getText());
	            }
	        });

	        JButton checkBalanceButton = new JButton("Check Balance");
	        checkBalanceButton.setBounds(50, 210, 250, 30);
	        checkBalanceButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                checkBalance(accountNumberInput.getText());
	            }
	        });

	        panel.add(lbl);
	        panel.add(accountNumberLbl);
	        panel.add(accountNumberInput);
	        panel.add(amountLbl);
	        panel.add(amountInput);
	        panel.add(creditButton);
	        panel.add(debitButton);
	        panel.add(transferButton);
	        panel.add(checkBalanceButton);

	        afterCustomer.add(panel);
	       afterCustomer.setSize(400, 300);
	        afterCustomer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       afterCustomer.setVisible(true);
	    }

	    private void performTransaction(String accountNumber, String amountText, boolean isCredit) {
	        String url = "jdbc:mysql://localhost:3306/managerdata";
	        String username = "root";
	        String password = "root";

	        try (Connection conn = DriverManager.getConnection(url, username, password)) {
	            System.out.println("Database connected");

	            BigDecimal amount = new BigDecimal(amountText);

	            String selectQuery = "SELECT balance FROM accounts WHERE accountnumber = ?";
	            String updateQuery = "UPDATE accounts SET balance = ? WHERE accountnumber = ?";

	            try (PreparedStatement selectStmt = conn.prepareStatement(selectQuery)) {
	                selectStmt.setString(1, accountNumber);
	                try (ResultSet rs = selectStmt.executeQuery()) {
	                    if (rs.next()) {
	                        BigDecimal currentBalance = rs.getBigDecimal("balance");
	                        BigDecimal newBalance = isCredit ? currentBalance.add(amount) : currentBalance.subtract(amount);

	                        try (PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {
	                            updateStmt.setBigDecimal(1, newBalance);
	                            updateStmt.setString(2, accountNumber);
	                            updateStmt.executeUpdate();
	                            JOptionPane.showMessageDialog(afterCustomer, "Transaction successful. New balance: " + newBalance);
	                        }
	                    } else {
	                        JOptionPane.showMessageDialog(afterCustomer, "Account number not found.");
	                    }
	                }
	            }
	        } catch (SQLException | NumberFormatException ex) {
	            JOptionPane.showMessageDialog(afterCustomer, "ERROR: Something went wrong - " + ex.getMessage());
	        }
	    }

	    private void performTransfer(String fromAccountNumber, String toAccountNumber, String amountText) {
	        String url = "jdbc:mysql://localhost:3306/managerdata";
	        String username = "root";
	        String password = "root";

	        try (Connection conn = DriverManager.getConnection(url, username, password)) {
	            System.out.println("Database connected");

	            BigDecimal amount = new BigDecimal(amountText);

	            // Check balance of the sender
	            String selectQuery = "SELECT balance FROM accounts WHERE accountnumber = ?";
	            String updateQuery = "UPDATE accounts SET balance = ? WHERE accountnumber = ?";

	            try (PreparedStatement selectStmt = conn.prepareStatement(selectQuery)) {
	                selectStmt.setString(1, fromAccountNumber);
	                try (ResultSet rs = selectStmt.executeQuery()) {
	                    if (rs.next()) {
	                        BigDecimal currentBalance = rs.getBigDecimal("balance");
	                        if (currentBalance.compareTo(amount) < 0) {
	                            JOptionPane.showMessageDialog(afterCustomer, "Insufficient funds.");
	                            return;
	                        }

	                        // Deduct from sender's account
	                        BigDecimal newBalanceSender = currentBalance.subtract(amount);
	                        try (PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {
	                            updateStmt.setBigDecimal(1, newBalanceSender);
	                            updateStmt.setString(2, fromAccountNumber);
	                            updateStmt.executeUpdate();
	                        }

	                        // Add to receiver's account
	                        selectStmt.setString(1, toAccountNumber);
	                        try (ResultSet rsTo = selectStmt.executeQuery()) {
	                            if (rsTo.next()) {
	                                BigDecimal currentBalanceReceiver = rsTo.getBigDecimal("balance");
	                                BigDecimal newBalanceReceiver = currentBalanceReceiver.add(amount);
	                                try (PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {
	                                    updateStmt.setBigDecimal(1, newBalanceReceiver);
	                                    updateStmt.setString(2, toAccountNumber);
	                                    updateStmt.executeUpdate();
	                                }
	                                JOptionPane.showMessageDialog(afterCustomer, "Transfer successful.");
	                            } else {
	                                JOptionPane.showMessageDialog(afterCustomer, "Recipient account number not found.");
	                            }
	                        }
	                    } else {
	                        JOptionPane.showMessageDialog(afterCustomer, "Sender account number not found.");
	                    }
	                }
	            }
	        } catch (SQLException | NumberFormatException ex) {
	            JOptionPane.showMessageDialog(afterCustomer, "ERROR: Something went wrong - " + ex.getMessage());
	        }
	    }

	    private void checkBalance(String accountNumber) {
	        String url = "jdbc:mysql://localhost:3306/managerdata";
	        String username = "root";
	        String password = "root";

	        try (Connection conn = DriverManager.getConnection(url, username, password)) {
	            System.out.println("Database connected");

	            String selectQuery = "SELECT balance FROM accounts WHERE accountnumber = ?";

	            try (PreparedStatement selectStmt = conn.prepareStatement(selectQuery)) {
	                selectStmt.setString(1, accountNumber);
	                try (ResultSet rs = selectStmt.executeQuery()) {
	                    if (rs.next()) {
	                        BigDecimal balance = rs.getBigDecimal("balance");
	                        JOptionPane.showMessageDialog(afterCustomer, "Current balance: " + balance);
	                    } else {
	                        JOptionPane.showMessageDialog(afterCustomer, "Account number not found.");
	                    }
	                }
	            }
	        } catch (SQLException ex) {
	            JOptionPane.showMessageDialog(afterCustomer, "ERROR: Something went wrong - " + ex.getMessage());
	        }
	    }

	   
	    }
	


