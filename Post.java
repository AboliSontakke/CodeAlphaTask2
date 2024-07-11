package com.model;

import java.awt.Component;

public class Post {
    private String author;
    private String text;

    public Post(String author, String text) {
        this.author = author;
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public String getText() {
        return text;
    }

	public void like() {
		// TODO Auto-generated method stub
		
	}

	public String getLikes() {
		// TODO Auto-generated method stub
		return null;
	}

	public Component getComments() {
		// TODO Auto-generated method stub
		return null;
	}

	public void addComment(Comment comment) {
		// TODO Auto-generated method stub
		
	}
}
