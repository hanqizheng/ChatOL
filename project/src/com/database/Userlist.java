package com.database;

/**
 * Userlist entity. @author MyEclipse Persistence Tools
 */

public class Userlist implements java.io.Serializable {

	// Fields

	private Integer id;
	private String username;
	private String password;
	private String memo1;

	// Constructors

	/** default constructor */
	public Userlist() {
	}

	/** full constructor */
	public Userlist(String username, String password, String memo1) {
		this.username = username;
		this.password = password;
		this.memo1 = memo1;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMemo1() {
		return this.memo1;
	}

	public void setMemo1(String memo1) {
		this.memo1 = memo1;
	}

	@Override
	public String toString() {
		return "Userlist [id=" + id + ", username=" + username + ", password="
				+ password + ", memo1=" + memo1 + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((memo1 == null) ? 0 : memo1.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Userlist other = (Userlist) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (memo1 == null) {
			if (other.memo1 != null)
				return false;
		} else if (!memo1.equals(other.memo1))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

}