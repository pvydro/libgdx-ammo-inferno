package com.flizzet.flyzombieenemy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.camera.MainCamera;
import com.flizzet.entities.Entity;
import com.flizzet.map.Platform;
import com.flizzet.player.Player;

/**
 * Spawns enemy bats.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class FlyingZombieEnemySpawner extends Entity {

	private static final FlyingZombieEnemySpawner INSTANCE = new FlyingZombieEnemySpawner();
	private ArrayList<FlyingZombieEnemy> zombies = new ArrayList<FlyingZombieEnemy>();
	private Queue<FlyingZombieEnemy> toBeRemoved = new LinkedList<FlyingZombieEnemy>();
	private int topCooldown = 500;
	private int cooldown = 200;

	private boolean enabled = false;

	/** Returns and instance of the FlyingBatEnemySpawner class */
	public static FlyingZombieEnemySpawner getInstance() {
		return INSTANCE;
	}

	/** Single use constructor */
	private FlyingZombieEnemySpawner() {
	}

	@Override
	public void update(float delta) {

		zombies.removeAll(toBeRemoved);

		if (enabled) {
			cooldown--;

			if (cooldown <= 0) {
				cooldown = topCooldown;
				spawnBat();
			}
		}

		for (FlyingZombieEnemy e : zombies) {
			e.update(delta);
		}
	}

	@Override
	public void render(SpriteBatch batch) {

		for (FlyingZombieEnemy e : zombies) {
			e.render(batch);
		}

	}

	/** Builds a new enemy eye and adds it to the screen */
	private void spawnBat() {
		FlyingZombieEnemy newZombie = new FlyingZombieEnemy();
		Platform targetPlatform = Player.getInstance().getController()
				.getTargetPlatform();
		int side = targetPlatform.getSide();
		switch (side) {
			case 0:
				newZombie.setX(MainCamera.getInstance().getWidth() + 20
						+ newZombie.getWidth());
				break;
			case 1:
				newZombie.setX(-20);
				break;
			case 2:
				newZombie.setX(new Random().nextBoolean()
						? -20
						: MainCamera.getInstance().getWidth() + 20
								+ newZombie.getWidth());
		}

		newZombie.setY(targetPlatform.getCenterY());

		zombies.add(newZombie);
	}

	/** Removes an eye from the ArrayList of eyes */
	public void removeFromZombies(FlyingZombieEnemy zombie) {
		this.toBeRemoved.add(zombie);
	}

	/** Respawn */
	public void reset() {
		this.toBeRemoved.addAll(zombies);
	}

	public ArrayList<FlyingZombieEnemy> getZombies() {
		return this.zombies;
	}

	public void setCooldown(int newCooldown) {
		this.topCooldown = newCooldown;
	}
	public void setEnabled(boolean isEnabled) {
		this.enabled = isEnabled;
	}

}
