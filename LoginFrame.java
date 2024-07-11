package com.model;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private UserProfile userProfile; 
    public LoginFrame() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        
        JPanel panel = new JPanel();
        setContentPane(panel);
        panel.setLayout(null);
        
       String username = null;
		JLabel usernameLabel = new JLabel("User");
        usernameLabel.setBounds(50, 50, 100, 30);
        panel.add(usernameLabel);
        
        usernameField = new JTextField();
        usernameField.setBounds(150, 50, 200, 30);
        panel.add(usernameField);
        
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 100, 100, 30);
        panel.add(passwordLabel);
        
        passwordField = new JPasswordField();
        passwordField.setBounds(150, 100, 200, 30);
        panel.add(passwordField);
        
        loginButton = new JButton("Login");
        loginButton.setBounds(150, 150, 100, 30);
        panel.add(loginButton);


        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                
                if (username != null && !username.isEmpty() && password != null && !password.isEmpty()) {
                   
                    userProfile = new UserProfile(username, "path_to_default_profile_image.jpg"); 
                  
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            new UserProfileFrame().setVisible(true);
                        }
                    });
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Please enter username and password");
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginFrame().setVisible(true);
            }
        });
    }
}
