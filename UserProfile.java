package com.model;

import java.util.ArrayList;
import java.util.List;

public class UserProfile {
    private String uername;
    private String profileImagePath;
    private List<Post> posts;
	private Object username;

    public UserProfile(String name, String profileImagePath) {
        this.username = username;
        this.profileImagePath = profileImagePath;
        this.posts = new ArrayList<>();
    }

    public String getName() {
        return (String) username;
    }

    public String getProfileImagePath() {
        return profileImagePath;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void addPost(Post post) {
        posts.add(post);
    }

	public void setName(String newName) {
		// TODO Auto-generated method stub
		
	}

	public void setProfileImagePath(String newImagePath) {
		// TODO Auto-generated method stub
		
	}
}
