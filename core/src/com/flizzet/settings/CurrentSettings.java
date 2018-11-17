package com.flizzet.settings;

/**
 * Controls debug and options in the game.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class CurrentSettings {

	private static final CurrentSettings INSTANCE = new CurrentSettings();

	/* Overall */
	public boolean playedBefore = false;
	public int totalDays = 0;
	public int lastPlayedDay = -2;
	public int totalPlays = 0;
	public boolean askedToRate = false;

	/* Ads */
	public boolean adsPlayable = true;
	public boolean moneyAdded = false;

	/* Debug */
	public boolean showCollisions = false;
	public boolean loseHealth = true; // Only to be used for testing when I'm not trying to die
	public boolean brokeBoi = true;
	public String version = "0.3.5 blu";

	/* Gameplay */
	public boolean autoFire = true;
	public boolean mouseAim = true;
	public boolean doubleTapSelect = false;
	public ControlType controls;

	/* Particles */
	// Animated
	public boolean enemyHits = true;
	public boolean enemyDeath = true;
	public boolean playerLand = true;

	// General
	public boolean particles = true;
	public boolean collideParticles = false;
	public boolean lighting = true;

	// Screen effects
	public boolean screenShake = true; // Disable in debug
	public boolean screenFlash = false;
	public int screenFlashQuality = 2;

	// Blood
	public boolean blood = true;
	public int bloodParticleLifetime = 200;
	public int bloodAmount = 4;
	public boolean headSplatter = false;
	public int headSplatterAmount = 0;

	// Lighting
	public boolean softLight = true;
	public boolean playerLight = true;
	public boolean platformLight = true;
	public boolean platformLightParticles = true;
	public int platformLightParticleAmount = 20; // Less is more
	public boolean bulletLight = true;
	public boolean lavaLight = true;
	public boolean lavaParticles = true;
	public boolean lightMovement = true;

	// Ground particles
	public boolean groundParticles = true;
	public int groundParticleAmount = 3;
	public int groundParticleSize = 2;
	public int groundParticleTotal = 10; // Less is more, total time between
											// these particles being created
	public int groundParticleLifetime = 200;

	// Text particles
	public boolean textParticles = true;

	// Bullet hits
	public boolean bulletHits = true;

	// Bugs
	public boolean bugs = true;

	// Dialogue
	public boolean zombieDialogue = true;

	/* Sounds */
	public float soundVolume = 1.0f;
	public boolean sound = true;
	public float musicVolume = 1.0f;
	public boolean music = true;

	/** Returns an instance of the current settings */
	public static CurrentSettings getInstance() {
		return INSTANCE;
	}

	/** Private constructor for single use */
	private CurrentSettings() {
		if (!lighting) {
			playerLight = false;
			platformLight = false;
			bulletLight = false;
			lightMovement = false;
		}
	}

	public void setParticles(boolean showParticles) {
		if (showParticles) {
			blood = true;
			groundParticles = true;
			bulletHits = true;
			lavaParticles = true;
			platformLightParticles = true;
		} else {
			blood = false;
			groundParticles = false;
			bulletHits = false;
			lavaParticles = false;
			platformLightParticles = false;
		}
	}

}
