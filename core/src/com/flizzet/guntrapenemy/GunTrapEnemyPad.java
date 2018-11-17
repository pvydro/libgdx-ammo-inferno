package com.flizzet.guntrapenemy;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.entities.Entity;
import com.flizzet.main.GameManager;
import com.flizzet.map.Platform;
import com.flizzet.upgrades.TrapJammerUpgrade;
import com.flizzet.upgradesystem.Upgrades;

/**
 * Pad to trigger the gun trap.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class GunTrapEnemyPad extends Entity {

	private GunTrapEnemyPadCollision collision;
	private GunTrapEnemy trap;
	private Platform platform;
	private Texture image;
	private Random random = new Random();

	/** Default instantiable constructor */
	public GunTrapEnemyPad(GunTrapEnemy trap) {
		this.trap = trap;
		this.platform = trap.getPlatform();
		this.collision = new GunTrapEnemyPadCollision(this, trap);

		this.image = GameManager.getInstance().assetManager
				.get("enemies/gunTrap/pressurePad.png", Texture.class);
		this.adjustBoundsToImage(image);
	}

	@Override
	public void update(float delta) {
		this.setY(trap.getY());
		if (platform.getSide() == 0) { // Left
			this.setX(platform.getX() + platform.getWidth() - 20
					- this.getWidth());
		} else if (platform.getSide() == 2) {
			this.setX(platform.getX() + random
					.nextInt((int) (platform.getWidth() - this.getWidth())));
		} else { // Right
			this.setX(platform.getX() + 20);
		}

		if (!Upgrades.getInstance().isEquipped(TrapJammerUpgrade.class)) {
			collision.update(delta);
		}
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(image, bounds.x, bounds.y, bounds.width, bounds.height);
	}

	public GunTrapEnemy getTrap() {
		return this.trap;
	}

}
