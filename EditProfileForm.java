package com.model;

import javax.swing.*;

import javafx.scene.input.Dragboard;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditProfileForm extends JDialog {

    private static final long serialVersionUID = 1L;
    private UserProfile user;
    private JTextField nameField;
    private JTextField imagePathField;
    private JButton saveButton;
    private SocialMediaDashboard dashboard;

    public EditProfileForm(UserProfile user, SocialMediaDashboard dashboard) {
        this.user = user;
        this.dashboard = dashboard;
        setTitle("Edit Profile");
        setSize(400, 300);
        setLocationRelativeTo(dashboard);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setContentPane(panel);

        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField(user.getName());
        panel.add(nameLabel);
        panel.add(nameField);

        JLabel imagePathLabel = new JLabel("Profile Image Path:");
        imagePathField = new JTextField(user.getProfileImagePath());
        panel.add(imagePathLabel);
        panel.add(imagePathField);

        saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveProfileChanges();
            }
        });
        panel.add(new JLabel());
        panel.add(saveButton);
    }

    private void saveProfileChanges() {
        String newName = nameField.getText();
        String newImagePath = imagePathField.getText();
        if (newName.isEmpty() || newImagePath.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        user.setName(newName);
        user.setProfileImagePath(newImagePath);
        dashboard.updateProfilePanel(null);

        JOptionPane.showMessageDialog(this, "Profile updated successfully.");
        dispose();
    }
}
