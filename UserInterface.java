package com.model;



import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

public class UserInterface {
    private JFrame frame;
    private JTextField usernameField;
    private JTextArea resultArea;

    public UserInterface() {
        frame = new JFrame("User Information");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(null);

        JLabel usernameLabel = new JLabel("Enter Username:");
        usernameLabel.setBounds(50, 30, 100, 30);
        frame.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(160, 30, 150, 30);
        frame.add(usernameField);

        JButton searchButton = new JButton("Search");
        searchButton.setBounds(320, 30, 80, 30);
        frame.add(searchButton);

        resultArea = new JTextArea();
        resultArea.setBounds(50, 70, 300, 150);
        resultArea.setEditable(false);
        frame.add(resultArea);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
					searchUser();
				} catch (SQLException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });

        frame.setVisible(true);
    }

    private void searchUser() throws SQLException, IOException {
        String username = usernameField.getText();
        if (username.isEmpty()) {
            resultArea.setText("Please enter a username.");
            return;
        }

        User user = UserUtil.getUserByUsername(username);
        if (user != null) {
            resultArea.setText("User found:\n" +
                    "ID: " + user.getId() + "\n" +
                    "Username: " + user.getUsername() + "\n" +
                    "Password: " + user.getPassword());
        } else {
            resultArea.setText("User not found.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new UserInterface();
            }
        });
    }
}
