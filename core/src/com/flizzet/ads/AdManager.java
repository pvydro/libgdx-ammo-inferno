package com.flizzet.ads;

import com.flizzet.deathmenu.DeathMenu;
import com.flizzet.guicomponents.GameNotification;
import com.flizzet.interfaces.Updatable;
import com.flizzet.main.GameManager;
import com.flizzet.player.Player;
import com.flizzet.settings.CurrentSettings;

/**
 * Handles displaying and not displaying ads.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class AdManager implements Updatable {

	private static final AdManager INSTANCE = new AdManager();
	private AdHandler handler;

	/** Returns an instance of the AdManager class */
	public static AdManager getInstance() {
		return INSTANCE;
	}
	/** Default instantiable constructor */
	public AdManager() {
	}

	@Override
	public void update(float delta) {
	}

	/** Displays a banner ad */
	public void showBannerAd() {
		handler.showBannerAds(true);
	}
	/** Hides a banner ad if it's displayed */
	public void hideBannerAd() {
		handler.showBannerAds(false);
	}
	/** Displays an interstitial ad */
	public void showInterstitialAd() {
		handler.showInterstitialAds(true);
	}
	/** Displays an interstitial video ad */
	public void showInterstitialVideoAd() {
		handler.showInterstitialVideoAds(true);
	}
	/** Plays a video ad if it's loaded */
	public void showVideoAd() {
		handler.showVideoAds(true);
	}

	/** Called when a video is loaded */
	public void videoLoaded() {
		CurrentSettings.getInstance().adsPlayable = true;
	}
	/** Called when a video failed to load */
	public void videoFailed() {
		CurrentSettings.getInstance().adsPlayable = false;
	}
	
	/** Called when a video is completed and rewarded */
	public void videoRewarded() {

		/* Revive the player or give them money */
		if (DeathMenu.getInstance().isEnabled()
				|| GameManager.getInstance().stateManager
						.getCurrentState() != GameManager
								.getInstance().stateId_play) {
			CurrentSettings.getInstance().moneyAdded = true;
			Player.getInstance().getScore().setCoins(
					Player.getInstance().getScore().getTotalCoins() + 30);
			GameNotification noti = new GameNotification("+30 coins");
			GameManager.getInstance().guiContainer.addToGui(noti);
		}
	}

	public void setHandler(AdHandler newHandler) {
		this.handler = newHandler;
	}

}
