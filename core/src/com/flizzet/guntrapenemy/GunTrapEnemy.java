package com.flizzet.guntrapenemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.enemies.Enemy;
import com.flizzet.main.GameManager;
import com.flizzet.map.Platform;
import com.flizzet.map.PlatformGenerator;
import com.flizzet.projectiles.GunTrapBullet;
import com.flizzet.sound.Sounds;
import com.flizzet.upgrades.TrapJammerUpgrade;
import com.flizzet.upgradesystem.Upgrades;

/**
 * Gun trap enemy to spawn on platforms.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class GunTrapEnemy extends Enemy {

	private GunTrapEnemyPad pad;
	private Texture image;
	private Platform platform;

	private boolean shot = false;

	/** Default instantiable constructor */
	public GunTrapEnemy(Platform platform) {
		this.platform = platform;
		this.pad = new GunTrapEnemyPad(this); // Create in constructor because
												// it gets the platform

		if (Upgrades.getInstance().isEquipped(TrapJammerUpgrade.class)) {
			this.image = GameManager.getInstance().assetManager
					.get("enemies/gunTrap/shooterJammed.png");
		} else {
			this.image = GameManager.getInstance().assetManager
					.get("enemies/gunTrap/shooter.png", Texture.class);
		}
		adjustBoundsToImage(image);
	}

	@Override
	public void update(float delta) {
		/* Set position */
		this.setY(platform.getY() + platform.getHeight()
				+ PlatformGenerator.getInstance().getSpeed());
		if (platform.getSide() == 0) { // Left
			this.setX(platform.getX() + 20);
		} else if (platform.getSide() == 1) { // Right
			this.setX(platform.getX() + platform.getWidth() - 20
					- this.getWidth());
		}

		/* Set collision bounds */
		this.setCollisionX(this.getX());
		this.setCollisionY(this.getY());
		this.setCollisionWidth(this.getWidth());
		this.setCollisionHeight(this.getHeight());

		pad.update(delta);
		
		indicator.update(delta);
		indicator.setX(pad.getCenterX() - (indicator.getWidth() / 2));
		indicator.setY(pad.getCenterY() - (indicator.getHeight() / 2));
	}

	@Override
	public void render(SpriteBatch batch) {
		/* Draw in a direction depending on the side of the platform */
		if (platform.getSide() == 0) {
			batch.draw(image, bounds.x, bounds.y, bounds.width, bounds.height,
					0, 0, (int) bounds.width, (int) bounds.height, true, false);
		} else if (platform.getSide() == 1) {
			batch.draw(image, bounds.x, bounds.y, bounds.width, bounds.height);
		}

		/* Draw pad */
		pad.render(batch);

		indicator.render(batch);
		
	}

	/** Fires a bullet from the trap */
	public void shoot() {
		if (!shot) {
			shot = true;
			Sounds.play(Sounds.getInstance().trapShootSound, 0.7f);
			GunTrapBullet newBullet = new GunTrapBullet(this);
			GameManager.getInstance().entityContainer.add(newBullet);
		}
	}

	@Override
	public void hit() {

	}

	@Override
	public void die() {
		/** TODO Auto-generated method stub */
	}

	public Platform getPlatform() {
		return this.platform;
	}
	public int getSide() {
		return this.platform.getSide();
	}

}
