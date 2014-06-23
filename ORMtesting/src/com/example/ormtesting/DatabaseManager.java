package com.example.ormtesting;

import java.sql.SQLException;
import java.util.List;

import android.content.Context;

public class DatabaseManager {

	static private DatabaseManager instance;

	public static DatabaseManager getInstance() {
		return instance;
	}

	static public void init(Context ctx) {
		if (instance == null) {
			instance = new DatabaseManager(ctx);
		}
	}

	private DatabaseHelper helper;

	private DatabaseManager(Context ctx) {
		helper = new DatabaseHelper(ctx);
	}

	private DatabaseHelper getHelper() {
		return helper;
	}

	public List<Users> getAllUsers() {
		List<Users> users = null;
		try {
			users = getHelper().getUsersDao().queryForAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}

	public List<Prayers> getAllPrayesr() {
		List<Prayers> prayers = null;
		try {
			prayers = getHelper().getPrayesrDao().queryForAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prayers;
	}

	public void addUser(Users user) {
		try {
			getHelper().getUsersDao().create(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateUsers(Users user) {
		try {
			getHelper().getUsersDao().update(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addPrayers(Prayers prayer) {
		try {
			getHelper().getPrayesrDao().create(prayer);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updatePrayers(Prayers prayers) {
		try {
			getHelper().getPrayesrDao().update(prayers);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Users getUserWithId(int userId) {
		Users user = null;
		try {
			user = getHelper().getUsersDao().queryForId(userId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public Prayers getPrayerWithId(int prayerId) {
		Prayers prayer = null;
		try {
			prayer = getHelper().getPrayesrDao().queryForId(prayerId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return prayer;
	}

}
