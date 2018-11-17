package com.flizzet.main.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.flizzet.ads.AdHandler;
import com.flizzet.main.SuperCaveJumper;

public class DesktopLauncher {
	public static void main(String[] arg) {

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.width = 480; // Set screen width
		config.height = 800; // Set screen height

		new LwjglApplication(new SuperCaveJumper(new BlankAdHandler()), config);
	}
}

class BlankAdHandler implements AdHandler {
	@Override
	public void showBannerAds(boolean show) {
	}
	@Override
	public void showVideoAds(boolean show) {
	}
	@Override
	public void showInterstitialAds(boolean show) {
	}
	@Override
	public void showInterstitialVideoAds(boolean show) {
	}
}