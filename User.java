package com.model;



public class User {
    private Long id;
    private String username;
    private String password;
    private String email;
	public Long getId() {
		return id;
	}
	public void setId(long l) {
		this.id = (long) l;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public User(int i, String username, String password, String email) {
		super();
		this.id = (long) i;
		this.username = username;
		this.password = password;
		this.email = email;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + "]";
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

    
}
