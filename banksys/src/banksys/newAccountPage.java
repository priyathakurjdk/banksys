package banksys;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Random;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Random;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.*;
import java.util.Random;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.*;
import java.util.Random;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.*;
import java.util.Random;

public class newAccountPage {

    private static final int PIN_LENGTH = 4;
    private static final int ACCOUNT_NUMBER_LENGTH = 10;

    JFrame accountPage;

    public newAccountPage() {
        accountPage = new JFrame("Open New Account");
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel lbl = new JLabel("----Open New Account-----");
        lbl.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
        lbl.setBounds(50, 10, 300, 30);

        JLabel namelbl = new JLabel("Enter your name:");
        namelbl.setBounds(10, 50, 150, 30);
        JTextField nameInput = new JTextField(20);
        nameInput.setBounds(170, 50, 150, 30);

        JLabel agelbl = new JLabel("Enter your age:");
        agelbl.setBounds(10, 90, 150, 30);
        JTextField ageInput = new JTextField(20);
        ageInput.setBounds(170, 90, 150, 30);

        JLabel accountlbl = new JLabel("Account number:");
        accountlbl.setBounds(10, 130, 150, 30);
        JTextField accountInput = new JTextField(20);
        accountInput.setBounds(170, 130, 150, 30);
        accountInput.setEditable(false);

        JLabel birthlbl = new JLabel("Date of birth (YYYY-MM-DD):");
        birthlbl.setBounds(10, 170, 200, 30);
        JTextField birthInput = new JTextField(20);
        birthInput.setBounds(220, 170, 150, 30);

        JLabel citylbl = new JLabel("Enter your city:");
        citylbl.setBounds(10, 210, 150, 30);
        JTextField cityInput = new JTextField(20);
        cityInput.setBounds(170, 210, 150, 30);

        JLabel pinlbl = new JLabel("PIN:");
        pinlbl.setBounds(10, 250, 150, 30);
        JTextField pinInput = new JTextField(20);
        pinInput.setBounds(170, 250, 150, 30);
        pinInput.setEditable(false);

        JLabel phonelbl = new JLabel("Phone number:");
        phonelbl.setBounds(10, 290, 150, 30);
        JTextField phoneInput = new JTextField(20);
        phoneInput.setBounds(170, 290, 150, 30);

        

        JLabel depositlbl = new JLabel("Initial deposit:");
        depositlbl.setBounds(10, 370, 150, 30);
        JTextField depositInput = new JTextField(20);
        depositInput.setBounds(170, 370, 150, 30);

        JLabel nationalitylbl = new JLabel("Nationality:");
        nationalitylbl.setBounds(10, 410, 150, 30);
        JTextField nationalityInput = new JTextField(20);
        nationalityInput.setBounds(170, 410, 150, 30);

        JLabel balancelbl = new JLabel("Balance:");
        balancelbl.setBounds(10, 450, 150, 30);
        JTextField balanceInput = new JTextField(20);
        balanceInput.setBounds(170, 450, 150, 30);
        balanceInput.setEditable(true);

        JButton generatePinButton = new JButton("Generate PIN");
        generatePinButton.setBounds(120, 490, 120, 30);
        generatePinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pin = generateRandomPin(PIN_LENGTH);
                pinInput.setText(pin);
            }
        });

        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(250, 490, 100, 30);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = "jdbc:mysql://localhost:3306/managerdata";
                String username = "root";
                String password = "root";
              try (Connection conn = DriverManager.getConnection(url, username, password)) {
                    System.out.println("Database connected");

                    String insertQuery = "INSERT INTO accounts (name, age, accountnumber, birthday, city, pin, phone, initialdeposit, nationality, balance) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    try (PreparedStatement preparedStatement = conn.prepareStatement(insertQuery)) {
                        preparedStatement.setString(1, nameInput.getText());
                        preparedStatement.setInt(2, Integer.parseInt(ageInput.getText()));
                        preparedStatement.setString(3, accountInput.getText());
                        preparedStatement.setString(4, birthInput.getText());
                        preparedStatement.setString(5, cityInput.getText());
                        preparedStatement.setString(6, pinInput.getText());
                        preparedStatement.setString(7, phoneInput.getText());
                        preparedStatement.setBigDecimal(8, new BigDecimal(depositInput.getText()));
                        preparedStatement.setString(9, nationalityInput.getText());                       
                        preparedStatement.setInt(10, Integer.parseInt(balanceInput.getText()));
                        preparedStatement.executeUpdate();
                        JOptionPane.showMessageDialog(submitButton, "Data inserted into database");
                    }
                } catch (SQLException | NumberFormatException ex) {
                    JOptionPane.showMessageDialog(submitButton, "ERROR: Something went wrong - " + ex.getMessage());
                }
            }
        });
      JButton checkBalanceButton = new JButton("Check Balance");
      checkBalanceButton.setBounds(120, 530, 150, 30);
        checkBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = "jdbc:mysql://localhost:3306/managerdata";
                String username = "root";
                String password = "root";
                String accountNumber = JOptionPane.showInputDialog(accountPage, "Enter your account number:");

                try (Connection conn = DriverManager.getConnection(url, username, password)) {
                    System.out.println("Database connected");

                     String selectQuery = "SELECT balance FROM accountdata WHERE accountnumber = ?";
                    try (PreparedStatement preparedStatement = conn.prepareStatement(selectQuery)) {
                        preparedStatement.setString(1, accountNumber);

                        try (ResultSet resultSet = preparedStatement.executeQuery()) {
                            if (resultSet.next()) {
                                BigDecimal balance = resultSet.getBigDecimal("balance");
                                balanceInput.setText(balance.toString());
                            } else {
                                JOptionPane.showMessageDialog(checkBalanceButton, "Account number not found.");
                            }
                        }
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(checkBalanceButton, "ERROR: Something went wrong - " + ex.getMessage());
                }
            }
            });

     accountInput.setText(generateRandomNumber(ACCOUNT_NUMBER_LENGTH));
        panel.add(lbl);
        panel.add(namelbl);
        panel.add(nameInput);
       panel.add(agelbl);
        panel.add(ageInput);
        panel.add(accountlbl);
        panel.add(accountInput);
        panel.add(birthlbl);
        panel.add(birthInput);
        panel.add(citylbl);
        panel.add(cityInput);
        panel.add(pinlbl);
        panel.add(pinInput);
        panel.add(phonelbl);
        panel.add(phoneInput);

        
        
        panel.add(depositlbl);
        panel.add(depositInput);
        panel.add(nationalitylbl);
        panel.add(nationalityInput);
        panel.add(balancelbl);
        panel.add(balanceInput);
        panel.add(generatePinButton);
        panel.add(submitButton);
        panel.add(checkBalanceButton);

        accountPage.add(panel);
        accountPage.setSize(400, 600);
        accountPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        accountPage.setVisible(true);
    }

    private String generateRandomPin(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    private String generateRandomNumber(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(10));
        }
        
        return sb.toString();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new newAccountPage();
            }
        });
    }
}
