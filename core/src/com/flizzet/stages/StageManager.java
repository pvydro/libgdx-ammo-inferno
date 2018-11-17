package com.flizzet.stages;

import com.flizzet.batenemy.FlyingBatEnemySpawner;
import com.flizzet.enemies.MainEnemySpawner;
import com.flizzet.eyeenemy.FlyingEyeEnemySpawner;
import com.flizzet.flyzombieenemy.FlyingZombieEnemySpawner;
import com.flizzet.guicomponents.GameNotification;
import com.flizzet.interfaces.Updatable;
import com.flizzet.main.GameManager;
import com.flizzet.map.MapManager;
import com.flizzet.player.Player;
import com.flizzet.sound.Sounds;
import com.flizzet.upgrades.EarlyBirdUpgrade;
import com.flizzet.upgradesystem.Upgrades;

/**
 * Manages stage switching and handling.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class StageManager implements Updatable {

	private static final StageManager INSTANCE = new StageManager();
	private StageFileReader fileReader = new StageFileReader();
	private int currentStage = 0;

	private int currentPlatformLimit = 0;
	private final int PLATFORM_LIMIT = 15;

	public static StageManager getInstance() {
		return INSTANCE;
	}
	/** Single use constructor */
	private StageManager() {
		// nextStage();
	}

	@Override
	public void update(float delta) {
		/* Move the player to the next stage based on their platform */
		if (Player.getInstance().getScore()
				.getPlatforms() >= currentPlatformLimit) {
			nextStage();
			MapManager.getInstance().nextMap();
		}
	}

	/** Enters the next stage */
	public void nextStage() {
		/* Play sound */
		Sounds.play(Sounds.getInstance().nextStageSound, 1.2f);

		currentPlatformLimit += PLATFORM_LIMIT;
		currentStage++;

		if (currentStage < 11) {
			fileReader.setStage(currentStage);
		} else {
			/* Cycle through the 3 flying enemies when above the max level */
			if (MainEnemySpawner.getInstance().isBatsEnabled()) {
				MainEnemySpawner.getInstance().setBats(false);
				MainEnemySpawner.getInstance().setEyes(true);
				MainEnemySpawner.getInstance().setFlyingZombies(false);
				FlyingEyeEnemySpawner.getInstance().setCooldown(200);
			} else if (MainEnemySpawner.getInstance().isEyesEnabled()) {
				MainEnemySpawner.getInstance().setBats(false);
				MainEnemySpawner.getInstance().setEyes(false);
				MainEnemySpawner.getInstance().setFlyingZombies(true);
				FlyingZombieEnemySpawner.getInstance().setCooldown(200);
			} else if (MainEnemySpawner.getInstance()
					.isFlyingZombiesEnabled()) {
				MainEnemySpawner.getInstance().setBats(true);
				MainEnemySpawner.getInstance().setEyes(false);
				MainEnemySpawner.getInstance().setFlyingZombies(false);
				FlyingBatEnemySpawner.getInstance().setCooldown(200);
			}
		}

		/* Notify player that they've reached a new stage */
		if (Upgrades.getInstance().isEquipped(EarlyBirdUpgrade.class)) {
			if (currentStage != 2) {
				GameManager.getInstance().particleFunctions
						.addNextStageParticle();
			}
		} else {
			if (currentStage != 1) {
				GameManager.getInstance().particleFunctions
						.addNextStageParticle();
			}
		}
		GameNotification stageNumNoti = new GameNotification(
				"stage " + String.valueOf(currentStage));
		GameManager.getInstance().guiContainer.addToGui(stageNumNoti);
	}

	/** Resets stages */
	public void reset() {
		currentPlatformLimit = 0;
		currentStage = 0;
		nextStage();
	}

}