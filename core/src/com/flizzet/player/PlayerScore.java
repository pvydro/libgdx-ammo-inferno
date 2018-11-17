package com.flizzet.player;

import com.flizzet.guicomponents.GameNotification;
import com.flizzet.main.GameManager;
import com.flizzet.sound.Sounds;
import com.flizzet.upgrades.EarlyBirdUpgrade;
import com.flizzet.upgradesystem.Upgrades;

/**
 * Handles money and statistics of the player.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class PlayerScore {

	private int totalCoins = 0;
	private int totalBills = 0;
	private int coins = 0;
	private int platforms = 0;
	private int totalPlatforms = 0;
	private int highScore = 0;
	private boolean highScoreShown = false;

	/** Default instantiable constructor */
	public PlayerScore() {
	}

	/**
	 * Coins
	 */
	/** Adds coins to the players current coins */
	public void addToCurrentCoins(int amount) {
		this.coins += amount;
	}
	/** Removes coins from the players current coins */
	public void removeFromCurrentCoins(int amount) {
		this.totalCoins -= amount;
	}
	/** Adds coins to the players total coins */
	public void addToTotalCoins(int amount) {
		this.totalCoins += amount;
	}
	/** Removes coins from the players total coins */
	public void removeFromTotalCoins(int amount) {
		this.totalCoins -= amount;
	}

	/**
	 * Bills
	 */
	/** Adds bills to the players total bills */
	public void addToTotalBills(int amount) {
		this.totalBills += amount;
	}
	/** Removes bills from the players total bills */
	public void removeFromTotalBills(int amount) {
		this.totalBills -= amount;
	}

	/**
	 * Platforms
	 */
	/** Adds a platform to the players current platforms */
	public void addToCurrentPlatforms(int amount) {
		this.platforms += amount;
		if (this.platforms > highScore) {
			highScore = platforms;
			if (!highScoreShown) {
				GameNotification highScoreNoti = new GameNotification(
						"new high score");
				Sounds.play(Sounds.getInstance().highScoreSound, 1.5f);
				GameManager.getInstance().guiContainer.addToGui(highScoreNoti);
				highScoreShown = true;
			}
		}
	}
	/** Adds a platform the players total platforms */
	public void addToTotalPlatforms(int amount) {
		this.totalPlatforms += amount;
	}

	/** Respawn */
	public void reset() {
		coins = 0;
		if (Upgrades.getInstance().isEquipped(EarlyBirdUpgrade.class)) {
			platforms = 15;
		} else {
			platforms = 1;
		}
		highScoreShown = false;
	}

	/** Combines current with total */
	public void combine() {
		this.addToTotalCoins(this.getCoins());
		this.addToTotalPlatforms(this.getPlatforms());
	}

	/** Returns the players current coins */
	public int getCoins() {
		return this.coins;
	}
	/** Returns the players total coins */
	public int getTotalCoins() {
		return this.totalCoins;
	}
	/** Returns the players total bills */
	public int getTotalBills() {
		return this.totalBills;
	}
	/** Returns the players current platforms */
	public int getPlatforms() {
		return this.platforms;
	}
	/** Returns the players total platforms */
	public int getTotalPlatforms() {
		return this.totalPlatforms;
	}
	/** Returns the players high score */
	public int getHighScore() {
		return this.highScore;
	}
	/** Returns whether or not the player is on a high score run */
	public boolean isHighScoreShown() {
		return this.highScoreShown;
	}

	/**
	 * Saving
	 */
	/** Sets the total coins to a defined amount */
	public void setCoins(int newCoins) {
		this.totalCoins = newCoins;
	}
	/** Sets the total bills to a defined amount */
	public void setBills(int newBills) {
		this.totalBills = newBills;
	}
	/** Sets the total platforms to a defined amount */
	public void setPlatforms(int newPlatforms) {
		this.totalPlatforms = newPlatforms;
	}
	/** Sets the players high score to a defined amount */
	public void setHighScore(int newHighScore) {
		this.highScore = newHighScore;
	}

}
