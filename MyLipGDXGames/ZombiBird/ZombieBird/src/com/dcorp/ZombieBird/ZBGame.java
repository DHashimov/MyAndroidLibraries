package com.dcorp.ZombieBird;

import com.badlogic.gdx.Game;
import com.dcorp.Screens.SplashScreen;
import com.dcorp.ZBHelpers.AssetLoader;

public class ZBGame extends Game {

	@Override
	public void create() {
		AssetLoader.load();
		setScreen(new SplashScreen(this));
	}

	@Override
	public void dispose() {
		super.dispose();
		AssetLoader.dispose();
	}

}