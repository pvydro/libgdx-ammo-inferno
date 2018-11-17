package com.flizzet.main;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.flizzet.ads.AdHandler;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.RelativeLayout;

public class AndroidLauncher extends AndroidApplication implements RewardedVideoAdListener, AdHandler {

    private final int SHOW_BANNER_ADS = 			1;
    private final int HIDE_BANNER_ADS = 			0;
    private final int SHOW_INTERSTITIAL_ADS = 		1;
    private final int LOAD_INTERSTITIAL_ADS =		0;
    private final int SHOW_INTERSTITIAL_VIDEO_ADS =	1;
    private final int LOAD_INTERSTITIAL_VIDEO_ADS =	0;
    private final int SHOW_VIDEO_ADS = 				2;
    private final int HIDE_VIDEO_ADS = 				1;
    private final int LOAD_VIDEO_ADS = 				0;
    
    private AdView bannerAdView;
    private RewardedVideoAd videoAdView;
    private InterstitialAd interstitialAdView;
    private InterstitialAd interstitialVideoAdView;
    private int interstitialLoadAttempts = 0;
    
    private boolean initializedAds = false;
    private int initCycles = 0;
    
    private SuperCaveJumper game;

	private Handler bannerHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
				case SHOW_BANNER_ADS :
					bannerAdView.setVisibility(View.VISIBLE);
					break;
				case HIDE_BANNER_ADS :
					bannerAdView.setVisibility(View.GONE);
					break;
			}
		}
	};
	private Handler interstitialHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
				case LOAD_INTERSTITIAL_ADS :
					loadInterstitial();
					break;
				case SHOW_INTERSTITIAL_ADS :
					if (interstitialAdView.isLoaded()) {
						interstitialAdView.show();
					} else {
						loadInterstitial();
					}
					break;
			}
		}
	};
	private Handler interstitialVideoHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
				case LOAD_INTERSTITIAL_VIDEO_ADS :
					loadInterstitialVideo();
					break;
				case SHOW_INTERSTITIAL_VIDEO_ADS :
					if (interstitialVideoAdView.isLoaded()) {
						interstitialVideoAdView.show();
					} else {
						loadInterstitialVideo();
					}
					break;
			}
		}
	};
	private Handler videoHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
				case LOAD_VIDEO_ADS :
					loadVideoAd();
					break;
				case SHOW_VIDEO_ADS :
					if (videoAdView.isLoaded()) {
						System.out.println("loaded!");
						videoAdView.show();
					} else {
						System.out.println("not loaded!");
					}
					break;
				case HIDE_VIDEO_ADS :
					break;
			}
		}
	};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

		/* Application */
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useAccelerometer = true;
		RelativeLayout layout = new RelativeLayout(this);

		game = new SuperCaveJumper(this) {
			@Override
			public void render() {
				super.render();
				if (!initializedAds) {
					initCycles++;
					if (initCycles < 2)
						return;
					initializedAds = true;
					videoHandler.sendEmptyMessage(LOAD_VIDEO_ADS);
					interstitialVideoHandler
							.sendEmptyMessage(LOAD_INTERSTITIAL_VIDEO_ADS);
					interstitialHandler.sendEmptyMessage(LOAD_INTERSTITIAL_ADS);
				}
			}
		};

		View gameView = initializeForView(game, config);
		/* Add game to relative layout */
		layout.addView(gameView);
		/* Set the Application view to the content view */
		setContentView(layout);

//	  _____ _____ _____ _____ _____ _____ 
//	 | __  |  _  |   | |   | |   __| __  |
//	 | __ -|     | | | | | | |   __|    -|
//	 |_____|__|__|_|___|_|___|_____|__|__|

		/* Create the new AdView */
		bannerAdView = new AdView(this);
		/* Listen for when the ad is loaded, and make it visible when it is */
		bannerAdView.setAdListener(new AdListener() {
			@Override
			public void onAdLoaded() {
				int visibility = bannerAdView.getVisibility();
				bannerAdView.setVisibility(AdView.GONE);
				bannerAdView.setVisibility(visibility);
			}
		});

		/* Set the add size to a autochanging banner */
		bannerAdView.setAdSize(AdSize.SMART_BANNER);
		/* Set the unit id */
		bannerAdView.setAdUnitId("ca-app-pub-5382312635749592/9526629180");

		/* Create a new ad request */
		AdRequest.Builder builder = new AdRequest.Builder();
		/* Apply it to a test device, remove this when publishing */
		builder.addTestDevice("4771F6688358B3CBC92CDC8632AEE77D");
		/* Create a new LayoutParams for the ad */
		RelativeLayout.LayoutParams adParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		adParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		/* Add the ad to the view */
		layout.addView(bannerAdView, adParams);
		/* Load the ad */
		bannerAdView.loadAd(builder.build());

//	  _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ __    
//	 |     |   | |_   _|   __| __  |   __|_   _|     |_   _|     |  _  |  |   
//	 |-   -| | | | | | |   __|    -|__   | | | |-   -| | | |-   -|     |  |__ 
//	 |_____|_|___| |_| |_____|__|__|_____| |_| |_____| |_| |_____|__|__|_____|

		interstitialAdView = new InterstitialAd(this);
		interstitialAdView
				.setAdUnitId("ca-app-pub-5382312635749592/4054858381");

		interstitialAdView.setAdListener(new AdListener() {
			@Override
			public void onAdClosed() {
				loadInterstitial();
			}
			@Override
			public void onAdLoaded() {
				System.out.println("[FLIZ] Interstitial Ad Loaded");
			}
			@Override
			public void onAdFailedToLoad(int errorCode) {
				System.out.println(
						"[FLIZ] Interstitial Ad Failed: Error " + errorCode);
				if (interstitialLoadAttempts < 5) {
					loadInterstitial();
					interstitialLoadAttempts++;
				}
			}
		});

		/* Video */
		interstitialVideoAdView = new InterstitialAd(this);
		interstitialVideoAdView
				.setAdUnitId("ca-app-pub-5382312635749592/8066255588");

		interstitialVideoAdView.setAdListener(new AdListener() {
			@Override
			public void onAdClosed() {
				loadInterstitialVideo();
			}
			@Override
			public void onAdLoaded() {
				System.out.println("[FLIZ] Interstitial Video Ad Loaded");
			}
			@Override
			public void onAdFailedToLoad(int errorCode) {
				System.out.println("[FLIZ] Interstitial Video Ad Failed: Error "
						+ errorCode);
			}
		});

//	  _____ _____ ____  _____ _____ 
//	 |  |  |     |    \|   __|     |
//	 |  |  |-   -|  |  |   __|  |  |
//	  \___/|_____|____/|_____|_____|

		videoAdView = MobileAds.getRewardedVideoAdInstance(this);
		videoAdView.setRewardedVideoAdListener(this);

	}

	@Override
	public void showBannerAds(boolean show) {
		bannerHandler
				.sendEmptyMessage(show ? SHOW_BANNER_ADS : HIDE_BANNER_ADS);
	}
	@Override
	public void showInterstitialAds(boolean show) {
		interstitialHandler.sendEmptyMessage(SHOW_INTERSTITIAL_ADS);
	}
	@Override
	public void showInterstitialVideoAds(boolean show) {
		interstitialVideoHandler.sendEmptyMessage(SHOW_INTERSTITIAL_VIDEO_ADS);
	}
	@Override
	public void showVideoAds(boolean show) {
		videoHandler.sendEmptyMessage(show ? SHOW_VIDEO_ADS : HIDE_VIDEO_ADS);
		game.getSettings().adsPlayable = false;
	}

	/** Loads and caches a new interstitial */
	private void loadInterstitial() {
		if (!interstitialAdView.isLoaded()) {
			AdRequest adRequest = new AdRequest.Builder()
					.addTestDevice("4771F6688358B3CBC92CDC8632AEE77D").build();

			interstitialAdView.loadAd(adRequest);
		}
	}

	/** Loads and caches a new interstitial video */
	private void loadInterstitialVideo() {
		if (!interstitialVideoAdView.isLoaded()) {
			AdRequest adRequest = new AdRequest.Builder()
					// .addTestDevice("4771F6688358B3CBC92CDC8632AEE77D")
					.build();

			interstitialVideoAdView.loadAd(adRequest);
		}
	}

	/** Loads and caches a new video ad */
	private void loadVideoAd() {
		if (!videoAdView.isLoaded()) {
			AdRequest adRequest = new AdRequest.Builder()
					// .addTestDevice("4771F6688358B3CBC92CDC8632AEE77D")
					.build();
			videoAdView.loadAd("ca-app-pub-5382312635749592/3104992387",
					adRequest);
		}
	}

	@Override
	public void onRewarded(RewardItem arg0) {
		System.out.println("[FLIZ] Video Rewarded");
		game.getAdManager().videoRewarded();
	}

	@Override
	public void onRewardedVideoAdClosed() {
		loadVideoAd();
	}

	@Override
	public void onRewardedVideoAdFailedToLoad(int arg0) {
		System.out.println("[FLIZ] Video ad didn't load: Error code " + arg0);
		game.getSettings().adsPlayable = false;
	}

	@Override
	public void onRewardedVideoAdLeftApplication() {
	}

	@Override
	public void onRewardedVideoAdLoaded() {
		System.out.println("[FLIZ] Video ad loaded");
		game.getSettings().adsPlayable = true;
	}

	@Override
	public void onRewardedVideoAdOpened() {
		game.pause();
	}

	@Override
	public void onRewardedVideoStarted() {
	}
    
}
