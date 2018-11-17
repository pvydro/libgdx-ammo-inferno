package com.flizzet.enemies;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.batenemy.FlyingBatEnemySpawner;
import com.flizzet.entities.Entity;
import com.flizzet.eyeenemy.FlyingEyeEnemySpawner;
import com.flizzet.flyzombieenemy.FlyingZombieEnemySpawner;

/**
 * Main spawner for spawning enemies across the game.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class MainEnemySpawner extends Entity {

	private static final MainEnemySpawner INSTANCE = new MainEnemySpawner();

	private FlyingEyeEnemySpawner eyeSpawner = FlyingEyeEnemySpawner
			.getInstance();
	private FlyingBatEnemySpawner batSpawner = FlyingBatEnemySpawner
			.getInstance();
	private FlyingZombieEnemySpawner flyingZombieSpawner = FlyingZombieEnemySpawner
			.getInstance();

	private boolean batsEnabled = false;
	private boolean eyesEnabled = false;
	private boolean flyingZombiesEnabled = false;
	private boolean zombiesEnabled = false;
	private boolean sawsEnabled = false;
	private boolean spikesEnabled = false;
	private boolean trapsEnabled = false;

	private int zombieDifficulty = 1;

	public static MainEnemySpawner getInstance() {
		return INSTANCE;
	}
	/** Single use constructor */
	private MainEnemySpawner() {
	}

	@Override
	public void update(float delta) {
		/* Update eyes */
		eyeSpawner.update(delta);
		/* Update bats */
		batSpawner.update(delta);
		/* Update flying zombies */
		flyingZombieSpawner.update(delta);
	}

	@Override
	public void render(SpriteBatch batch) {
		/* Render eyes */
		eyeSpawner.render(batch);
		/* Render bats */
		batSpawner.render(batch);
		/* Render flying zombies */
		flyingZombieSpawner.render(batch);
	}

	/** Resets enemies across the spawners */
	public void reset() {
		eyeSpawner.reset();
		batSpawner.reset();
		flyingZombieSpawner.reset();
	}

	public boolean isEyesEnabled() {
		return this.eyesEnabled;
	}
	public boolean isBatsEnabled() {
		return this.batsEnabled;
	}
	public boolean isFlyingZombiesEnabled() {
		return this.flyingZombiesEnabled;
	}
	public boolean isZombiesEnabled() {
		return this.zombiesEnabled;
	}
	public int getZombieDifficulty() {
		return this.zombieDifficulty;
	}
	public boolean isSpikesEnabled() {
		return this.spikesEnabled;
	}
	public boolean isSawsEnabled() {
		return this.sawsEnabled;
	}
	public boolean isTrapsEnabled() {
		return this.trapsEnabled;
	}

	public void setEyes(boolean isSpawning) {
		this.eyesEnabled = isSpawning;
		this.eyeSpawner.setEnabled(isSpawning);
	}
	public void setBats(boolean isSpawning) {
		this.batsEnabled = isSpawning;
		this.batSpawner.setEnabled(isSpawning);
	}
	public void setFlyingZombies(boolean isSpawning) {
		this.flyingZombiesEnabled = isSpawning;
		this.flyingZombieSpawner.setEnabled(isSpawning);
	}
	public void setZombies(boolean isSpawning) {
		this.zombiesEnabled = isSpawning;
	}
	public void setZombieDifficulty(int newDifficulty) {
		this.zombieDifficulty = newDifficulty;
	}
	public void setSpikes(boolean isSpawning) {
		this.spikesEnabled = isSpawning;
	}
	public void setSaws(boolean isSpawning) {
		this.sawsEnabled = isSpawning;
	}
	public void setTraps(boolean isSpawning) {
		this.trapsEnabled = isSpawning;
	}

}
