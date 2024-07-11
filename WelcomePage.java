package com.model;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class WelcomePage extends JFrame {
    private JButton loginButton;
    private JButton registerButton;

    public WelcomePage() {
        super("Social Media Platform");
        initializeComponents();
        setupLayout();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initializeComponents() {
        loginButton = new JButton("Login");
        registerButton = new JButton("Register");

      
        loginButton.setFocusable(false);
        registerButton.setFocusable(false);
        loginButton.setBackground(Color.GREEN);
        registerButton.setBackground(Color.GREEN);
        loginButton.setFont(new Font("Arial", Font.BOLD, 12));
        registerButton.setFont(new Font("Arial", Font.BOLD, 12));

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              
                LoginFrame loginFrame = new LoginFrame();
                loginFrame.setVisible(true);

               
                dispose();
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                RegistrationFrame registrationFrame = new RegistrationFrame();
                registrationFrame.setVisible(true);

                
                dispose();
            }
        });
    }

    private void setupLayout() {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    Image backgroundImage = ImageIO.read(new File("C:\\Users\\ABOLI\\Downloads\\Best-Social-Media-Platforms-for-Digital-Marketing-2.jpg"));
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        panel.setLayout(null); 
      
        loginButton.setBounds(100, 200, 100, 30);
        registerButton.setBounds(100, 240, 100, 30);

       
        panel.add(loginButton);
        panel.add(registerButton);

     
        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new WelcomePage());
    }
}
