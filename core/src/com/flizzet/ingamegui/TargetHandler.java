package com.flizzet.ingamegui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.batenemy.FlyingBatEnemy;
import com.flizzet.batenemy.FlyingBatEnemySpawner;
import com.flizzet.camera.MainCamera;
import com.flizzet.enemies.Enemy;
import com.flizzet.entities.Entity;
import com.flizzet.eyeenemy.FlyingEyeEnemy;
import com.flizzet.eyeenemy.FlyingEyeEnemySpawner;
import com.flizzet.flyzombieenemy.FlyingZombieEnemy;
import com.flizzet.flyzombieenemy.FlyingZombieEnemySpawner;
import com.flizzet.main.GameManager;
import com.flizzet.main.GameState;
import com.flizzet.main.PauseManager;
import com.flizzet.map.Platform;
import com.flizzet.map.PlatformGenerator;
import com.flizzet.player.Player;
import com.flizzet.sound.Sounds;
import com.flizzet.tutorial.DummyEnemy;

/**
 * Handles target selection when in selection mode.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class TargetHandler extends Entity {

	private static final TargetHandler INSTANCE = new TargetHandler();
	private boolean pushed = false;

	/** Returns an instance of the TargetHandler class */
	public static TargetHandler getInstance() {
		return INSTANCE;
	}
	/** Single use constructor */
	private TargetHandler() {
	}

	@Override
	public void update(float delta) {
		if (PauseManager.getInstance().getState() == GameState.SELECT) {

			if (Gdx.input.isTouched() && !pushed) {

				pushed = true;

				/* Platform enemies */
				for (Platform p : PlatformGenerator.getInstance()
						.getPlatforms()) {
					/* Zombie enemies */
					performCheckWithEnemy(p.getZombieEnemy());
					/* Trap enemies */
					performCheckWithEnemy(p.getGunTrapEnemy());
					/* Saw enemies */
					performCheckWithEnemy(p.getSawEnemy());
					/* Spike enemies */
					performCheckWithEnemy(p.getSpikeEnemy());
				}

				/* Eye enemies */
				for (FlyingEyeEnemy e : FlyingEyeEnemySpawner.getInstance()
						.getEyes()) {
					performCheckWithEnemy(e);
				}

				/* Bat enemies */
				for (FlyingBatEnemy e : FlyingBatEnemySpawner.getInstance()
						.getBats()) {
					performCheckWithEnemy(e);
				}

				/* Flying zombie enemies */
				for (FlyingZombieEnemy e : FlyingZombieEnemySpawner
						.getInstance().getZombies()) {
					performCheckWithEnemy(e);
				}

				/* Dummy enemy */
				if (GameManager.getInstance().stateManager
						.getCurrentState() == GameManager
								.getInstance().stateId_tutorial) {
					performCheckWithEnemy(DummyEnemy.getInstance());
				}

			}

			if (!Gdx.input.isTouched()) {
				pushed = false;
			}
		}
	}

	@Override
	public void render(SpriteBatch batch) {
	}

	/** Checks if the mouse is intersecting with enemy bounds */
	private void performCheckWithEnemy(Enemy e) {
		if (e != null) {
			if (!e.isDead()) {
				if (e.getBounds().contains(
						MainCamera.getInstance().getMousePos().x,
						MainCamera.getInstance().getMousePos().y)) {
					Player.getInstance().getWeapon().setTarget(e);
					Sounds.play(Sounds.getInstance().selectSelectSound, 1.8f);
				}
			}
		}
	}
}
