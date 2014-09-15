package com.test.db.table;


public class User {

    private long id;

    private int version;

    private String username;

    private String password;

    public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
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

	@Override
    public String toString() {
        return "username:" + username ;
    }

}
