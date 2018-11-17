package com.flizzet.ads;

/**
 * Handles advertisement enabling and disabling
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */

public interface AdHandler {

    public void showBannerAds(boolean show);
    public void showInterstitialAds(boolean show);
    public void showInterstitialVideoAds(boolean show);
    public void showVideoAds(boolean show);

}
