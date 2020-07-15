package com.test.rupeek.firstRound.modals;

public class Token {
	private String username;
	private String password;

	/**
	 * No args constructor for use in serialization
	 *
	 */
	public Token() {
	}

	/**
	 *
	 * @param password
	 * @param username
	 */
	public Token(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Token withUsername(String username) {
		this.username = username;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Token withPassword(String password) {
		this.password = password;
		return this;
	}

}
