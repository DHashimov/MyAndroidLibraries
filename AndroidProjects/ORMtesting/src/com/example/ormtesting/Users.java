package com.example.ormtesting;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "users")
public class Users {
	@DatabaseField
	int localid;
	@DatabaseField
	int serverid;
	@DatabaseField
	String username;
	@DatabaseField
	String firstname;
	@DatabaseField
	String lastname;
	@DatabaseField
	String accesstoken;

	public int getLocalid() {
		return localid;
	}

	public void setLocalid(int localid) {
		this.localid = localid;
	}

	public int getServerid() {
		return serverid;
	}

	public void setServerid(int severid) {
		this.serverid = severid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getAccesstoken() {
		return accesstoken;
	}

	public void setAccesstoken(String accesstoken) {
		this.accesstoken = accesstoken;
	}

	public Users(int localid, int severid, String username, String firstname,
			String lastname, String accesstoken) {
		this.localid = localid;
		this.serverid = severid;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.accesstoken = accesstoken;
	}

	public Users() {

	}
}
