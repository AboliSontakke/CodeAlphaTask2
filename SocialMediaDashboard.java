package com.model;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SocialMediaDashboard extends JFrame {

    private List<UserProfile> profiles;
    private UserProfile currentUser; // Current logged-in user

    public SocialMediaDashboard() {
        setTitle("Social Media Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        profiles = new ArrayList<>();
        initializeUsers();
        currentUser = profiles.get(0); // Assuming the first profile is the current user

        JPanel mainPanel = new JPanel(new BorderLayout());
        setContentPane(mainPanel);

        JPanel profilesPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        profilesPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        mainPanel.add(profilesPanel, BorderLayout.CENTER);

        for (UserProfile profile : profiles) {
            JPanel profilePanel = createProfilePanel(profile);
            profilesPanel.add(profilePanel);
        }

        JButton newPostButton = new JButton("New Post", new ImageIcon("icons/new_post.png"));
        newPostButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String postText = JOptionPane.showInputDialog(SocialMediaDashboard.this, "Enter your new post:");
                if (postText != null && !postText.isEmpty()) {
                    currentUser.addPost(new Post(currentUser.getName(), postText));
                    updateProfilePanel(profilesPanel);
                }
            }
        });

        JButton viewStoriesButton = new JButton("View Stories", new ImageIcon("icons/view_stories.png"));
        viewStoriesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(SocialMediaDashboard.this, "Stories feature coming soon!");
            }
        });

        JButton editProfileButton = new JButton("Edit Profile");
        editProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showEditProfileDialog(currentUser);
                updateProfilePanel(profilesPanel);
            }
        });

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        topPanel.add(newPostButton);
        topPanel.add(viewStoriesButton);
        topPanel.add(editProfileButton);
        mainPanel.add(topPanel, BorderLayout.NORTH);

        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logout();
            }
        });

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        bottomPanel.add(logoutButton);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
    }

    private void initializeUsers() {
        profiles.add(new UserProfile("User1", "C:\\Users\\ABOLI\\Downloads\\OIP (2).jpg"));
        profiles.add(new UserProfile("User2", "C:\\Users\\ABOLI\\Downloads\\virat-kohli-of-indian-cricket-j5l0ih02jiv4j3iv.jpg"));
        profiles.add(new UserProfile("User3", "C:\\Users\\ABOLI\\Downloads\\OIP (1).jpg"));
        profiles.add(new UserProfile("User4", "C:\\Users\\ABOLI\\Downloads\\Nature-wallpapers-Full-HD-backgroud.jpg"));

        profiles.get(0).follow(profiles.get(1));
        profiles.get(1).follow(profiles.get(0));
        profiles.get(2).follow(profiles.get(0));
        profiles.get(3).follow(profiles.get(1));
    }

    private JPanel createProfilePanel(UserProfile profile) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JLabel nameLabel = new JLabel(profile.getName());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(nameLabel, BorderLayout.NORTH);

        JLabel imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        ImageIcon profileImage = new ImageIcon(resizeImage(profile.getProfileImagePath(), 150, 150));
        imageLabel.setIcon(profileImage);
        panel.add(imageLabel, BorderLayout.CENTER);

        JButton viewProfileButton = new JButton("View Profile");
        viewProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showProfileDetailsDialog(profile);
            }
        });
        panel.add(viewProfileButton, BorderLayout.SOUTH);

        JButton followButton = new JButton(currentUser.isFollowing(profile) ? "Following" : "Follow");
        followButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentUser != profile) { // Prevent following oneself
                    if (!currentUser.isFollowing(profile)) {
                        currentUser.follow(profile);
                        followButton.setText("Following");
                    } else {
                        currentUser.unfollow(profile);
                        followButton.setText("Follow");
                    }
                    updateProfilePanel((JPanel) panel.getParent());
                }
            }
        });
        panel.add(followButton, BorderLayout.WEST);

        return panel;
    }

    private void showProfileDetailsDialog(UserProfile profile) {
        JDialog dialog = new JDialog(this, "Profile Details: " + profile.getName(), true);
        dialog.setSize(600, 400);
        dialog.setLocationRelativeTo(this);

        JPanel contentPanel = new JPanel(new BorderLayout());

        JLabel profileImageLabel = new JLabel();
        profileImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        ImageIcon profileImage = new ImageIcon(resizeImage(profile.getProfileImagePath(), 200, 200));
        profileImageLabel.setIcon(profileImage);
        contentPanel.add(profileImageLabel, BorderLayout.NORTH);

        JTextArea profileInfoArea = new JTextArea("Bio: " + profile.getBio() + "\nFollowers: " + profile.getFollowers().size() + "\nFollowing: " + profile.getFollowing().size());
        profileInfoArea.setEditable(false);
        JScrollPane infoScrollPane = new JScrollPane(profileInfoArea);
        contentPanel.add(infoScrollPane, BorderLayout.CENTER);

        JPanel postsPanel = new JPanel(new GridLayout(profile.getPosts().size(), 1));
        for (Post post : profile.getPosts()) {
            JPanel postPanel = createPostPanel(post);
            postsPanel.add(postPanel);
        }
        JScrollPane postsScrollPane = new JScrollPane(postsPanel);
        contentPanel.add(postsScrollPane, BorderLayout.SOUTH);

        dialog.add(contentPanel);
        dialog.setVisible(true);
    }

    private JPanel createPostPanel(Post post) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JLabel postLabel = new JLabel(post.getAuthor() + ": " + post.getText());
        panel.add(postLabel, BorderLayout.NORTH);

        JPanel actionsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JButton likeButton = new JButton("Like (" + post.getLikes() + ")");
        likeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                post.like();
                likeButton.setText("Like (" + post.getLikes() + ")");
            }
        });
        actionsPanel.add(likeButton);

        JButton commentButton = new JButton("Comment");
        commentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String commentText = JOptionPane.showInputDialog(SocialMediaDashboard.this, "Enter your comment:");
                if (commentText != null && !commentText.isEmpty()) {
                    currentUser.addPost(new Post(currentUser.getName(), commentText));
                    updateProfilePanel((JPanel) panel.getParent().getParent().getParent());
                }
            }
        });
        actionsPanel.add(commentButton);

        panel.add(actionsPanel, BorderLayout.SOUTH);

        return panel;
    }

    void updateProfilePanel(JPanel profilesPanel) {
        profilesPanel.removeAll();
        for (UserProfile profile : profiles) {
            JPanel profilePanel = createProfilePanel(profile);
            profilesPanel.add(profilePanel);
        }
        profilesPanel.revalidate();
        profilesPanel.repaint();
    }

    private Image resizeImage(String imagePath, int width, int height) {
        try {
            BufferedImage originalImage = ImageIO.read(new File(imagePath));
            return originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void showEditProfileDialog(UserProfile profile) {
        if (profile == null) {
            System.out.println("Profile is null");
            return;
        }

        JDialog dialog = new JDialog(this, "Edit Profile: " + profile.getName(), false);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(this);

        JPanel contentPanel = new JPanel(new BorderLayout());

        JLabel nameLabel = new JLabel("Name: ");
        JTextField nameField = new JTextField(profile.getName());
        nameField.setEditable(false);
        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        namePanel.add(nameLabel);
        namePanel.add(nameField);
        contentPanel.add(namePanel, BorderLayout.NORTH);

        JLabel bioLabel = new JLabel("Bio: ");
        JTextArea bioArea = new JTextArea(profile.getBio());
        JScrollPane bioScrollPane = new JScrollPane(bioArea);
        JPanel bioPanel = new JPanel(new BorderLayout());
        bioPanel.add(bioLabel, BorderLayout.NORTH);
        bioPanel.add(bioScrollPane, BorderLayout.CENTER);
        contentPanel.add(bioPanel, BorderLayout.CENTER);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                profile.setBio(bioArea.getText());
                dialog.dispose();
            }
        });
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(saveButton);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        dialog.add(contentPanel);
        dialog.setVisible(true);
    }

    private void logout() {
        JOptionPane.showMessageDialog(this, "Logged out successfully!");
        System.exit(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                SocialMediaDashboard dashboard = new SocialMediaDashboard();
                dashboard.setVisible(true);
            }
        });
    }

    private static class UserProfile {
        private String name;
        private String profileImagePath;
        private String bio;
        private List<Post> posts;
        private List<UserProfile> followers;
        private List<UserProfile> following;

        public UserProfile(String name, String profileImagePath) {
            this.name = name;
            this.profileImagePath = profileImagePath;
            this.posts = new ArrayList<>();
            this.followers = new ArrayList<>();
            this.following = new ArrayList<>();
            this.bio = "This is a default bio.";
        }

        public String getName() {
            return name;
        }

        public String getProfileImagePath() {
            return profileImagePath;
        }

        public String getBio() {
            return bio;
        }

        public List<Post> getPosts() {
            return posts;
        }

        public List<UserProfile> getFollowers() {
            return followers;
        }

        public List<UserProfile> getFollowing() {
            return following;
        }

        public void setBio(String bio) {
            this.bio = bio;
        }

        public void addPost(Post post) {
            posts.add(post);
        }

        public void follow(UserProfile user) {
            following.add(user);
            user.addFollower(this);
        }

        public void unfollow(UserProfile user) {
            following.remove(user);
            user.removeFollower(this);
        }

        public boolean isFollowing(UserProfile user) {
            return following.contains(user);
        }

        private void addFollower(UserProfile follower) {
            followers.add(follower);
        }

        private void removeFollower(UserProfile follower) {
            followers.remove(follower);
        }
    }

    private static class Post {
        private String author;
        private String text;
        private int likes;
        private List<Comment> comments;

        public Post(String author, String text) {
            this.author = author;
            this.text = text;
            this.likes = 0;
            this.comments = new ArrayList<>();
        }

        public String getAuthor() {
            return author;
        }

        public String getText() {
            return text;
        }

        public int getLikes() {
            return likes;
        }

        public List<Comment> getComments() {
            return comments;
        }

        public void like() {
            likes++;
        }

        public void addComment(Comment comment) {
            comments.add(comment);
        }
    }

    private static class Comment {
        private String author;
        private String text;

        public Comment(String author, String text) {
            this.author = author;
            this.text = text;
        }

        public String getAuthor() {
            return author;
        }

        public String getText() {
            return text;
        }
    }
}
