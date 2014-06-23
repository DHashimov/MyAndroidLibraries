package com.example.ormtesting;

import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		DatabaseManager.init(this);

		Users user = new Users();
		user.setAccesstoken("askjaldkjajsd");
		user.setFirstname("Deniz");
		user.setLastname("Hashimov");
		user.setLocalid(1231);
		user.setServerid(14141);
		user.setUsername("dhashimov");
		DatabaseManager.getInstance().addUser(user);

		Prayers prayer = new Prayers();
		prayer.setHeadurl("http://www.google.com");
		prayer.setPrayerdate("20.14.56.4314");
		prayer.setPrayertipe(2);
		prayer.setUrl("http://www.google.com");
		DatabaseManager.getInstance().addPrayers(prayer);

		List<Users> users = DatabaseManager.getInstance().getAllUsers();
		List<Prayers> prayers = DatabaseManager.getInstance().getAllPrayesr();

	}
}
