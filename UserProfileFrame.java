package com.model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

public class UserProfileFrame extends JFrame {

    private JLabel usernameLabel;
    private JLabel likesLabel;
    private JLabel commentsLabel;
    private JLabel followersLabel;
    private JButton likeButton;
    private JButton commentButton;
    private JButton followButton;

    public UserProfileFrame() {
        setTitle("User Profile");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 600);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel topPanel = new JPanel(new GridBagLayout());
        topPanel.setBorder(BorderFactory.createTitledBorder("Profile"));

       
        JLabel profilePicLabel = new JLabel();
        profilePicLabel.setPreferredSize(new Dimension(100, 100));
        profilePicLabel.setIcon((Icon) new ImageIcon("C:\\Users\\ABOLI\\Downloads\\OIP (4).jpg")); // Default profile picture

        profilePicLabel.setIcon(getRoundedIcon(new ImageIcon("C:\\Users\\ABOLI\\Downloads\\OIP (4).jpg"), 100));

      
        usernameLabel = new JLabel("");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 16));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.insets = new Insets(0, 0, 0, 10);
        topPanel.add(profilePicLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        topPanel.add(usernameLabel, gbc);

        // Middle panel for likes, comments, and followers
        JPanel middlePanel = new JPanel(new GridBagLayout());
        middlePanel.setBorder(BorderFactory.createTitledBorder("Stats"));

        likesLabel = new JLabel("Likes: 100");
        likesLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        commentsLabel = new JLabel("Comments: 50");
        commentsLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        followersLabel = new JLabel("Followers: 200");
        followersLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        gbc.gridx = 0;
        gbc.gridy = 0;
        middlePanel.add(likesLabel, gbc);

        gbc.gridy = 1;
        middlePanel.add(commentsLabel, gbc);

        gbc.gridy = 2;
        middlePanel.add(followersLabel, gbc);

        
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBorder(BorderFactory.createTitledBorder("Actions"));

        likeButton = new JButton("Like");
        likeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                likeProfile();
            }
        });

        commentButton = new JButton("Comment");
        commentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                commentOnPost();
            }
        });

        followButton = new JButton("Follow");
        followButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                followUser();
            }
        });

        bottomPanel.add(likeButton);
        bottomPanel.add(commentButton);
        bottomPanel.add(followButton);

        // Add panels to the main panel
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(middlePanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);

        setVisible(true);
    }

    private void likeProfile() {
        String likesText = likesLabel.getText();
        int likes = Integer.parseInt(likesText.split(": ")[1]);
        likes++;
        likesLabel.setText("Likes: " + likes);
    }

    private void commentOnPost() {
        String comment = JOptionPane.showInputDialog(this, "Enter your comment:");
        if (comment != null && !comment.trim().isEmpty()) {
            String commentsText = commentsLabel.getText();
            int comments = Integer.parseInt(commentsText.split(": ")[1]);
            comments++;
            commentsLabel.setText("Comments: " + comments);
            JOptionPane.showMessageDialog(this, "Comment added!");
        }
    }

    private void followUser() {
        String followersText = followersLabel.getText();
        int followers = Integer.parseInt(followersText.split(": ")[1]);
        followers++;
        followersLabel.setText("Followers: " + followers);
        JOptionPane.showMessageDialog(this, "You are now following this user!");
    }

   
    private Icon getRoundedIcon(ImageIcon icon, int diameter) {
        Image img = icon.getImage();
        BufferedImage bi = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = bi.createGraphics();
        g2.setClip(new Ellipse2D.Float(0, 0, diameter, diameter));
        g2.drawImage(img, 0, 0, diameter, diameter, null);
        g2.dispose();
        return (Icon) new ImageIcon(bi);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(UserProfileFrame::new);
    }
}
