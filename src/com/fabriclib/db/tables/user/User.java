package com.fabriclib.db.tables.user;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "USERS")
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class User implements Serializable {

	
	public User(){
		super();
	}
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "id")
	private long id;

	@Version
	@GeneratedValue
	@Column(name = "version" )
	@JsonIgnore
	private long version;

	@Column(name = "username", unique = true, nullable = false, length = 20)
	@JsonProperty("username")
	private String username;

	@Column(name = "password", length = 20)
	private String password;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
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
		return "User [id=" + id + ", version=" + version + ", username="
				+ username + ", password=" + password + "]";
	}





}
