package com.flizzet.weapons;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.camera.MainCamera;
import com.flizzet.enemies.Enemy;
import com.flizzet.entities.Entity;
import com.flizzet.main.GameManager;
import com.flizzet.map.PlatformGenerator;
import com.flizzet.particles.MuzzleFlashParticle;
import com.flizzet.particles.Particle;
import com.flizzet.player.Player;
import com.flizzet.player.PlayerState;
import com.flizzet.projectiles.Bullet;
import com.flizzet.settings.CurrentSettings;
import com.flizzet.sound.Sounds;

/**
 * Abstract weapon class for concrete weapons to extend from.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class Weapon extends Entity {

	protected Texture image;
	protected Texture imageGold;
	protected Texture imageNoHand;
	protected Texture imageGoldNoHand;
	protected float muzzleLength;
	protected float muzzleHeight;
	protected float topFireRate;
	private float fireRate = 200;
	protected float recoilAmt;

	private float muzzleX;
	private float muzzleY;
	private float recoilX = 0;
	private float recoilRotation = 0;
	private float originX;
	private float originY;
	private float rotation;
	private int direction = 1;
	private int totalKills = 0;

	protected boolean bought = false;
	protected boolean equipped = false;
	protected boolean gold = false;
	protected String name;
	protected int price = 100;
	protected int killsToGold = 100;

	private Enemy target;
	private Random random = new Random();

	@Override
	public void update(float delta) {

		/* Rotation */
		originX = image.getWidth() / 2; // Set center
		originY = image.getHeight() / 2;

		double rotRad = Math.toRadians(rotation + recoilRotation);

		rotate(delta); // Point weapon

		/* Asserting recoil */
		recoilRotation += 20 * ((0 - recoilRotation) / 2) * delta;
		recoilX += 10 * ((0 - recoilX) / 2) * delta;

		/* Shooting if not dead */
		if (Player.getInstance().getState() != PlayerState.DEAD
				&& Player.getInstance().getState() != PlayerState.DYING) {
			fireRate--;
			if (CurrentSettings.getInstance().autoFire) {
				if (fireRate <= 0) {
					fireRate = topFireRate;
					shoot();
				}
			} else {
				if (Gdx.input.isTouched()) {
					if (fireRate <= 0) {
						fireRate = topFireRate;
						shoot();
					}
				}
			}
		}

		/* Muzzle Position */
		float mx = (float) (muzzleLength * Math.cos(rotRad)
				- muzzleHeight * Math.sin(rotRad));
		float my = (float) (muzzleLength * Math.sin(rotRad)
				+ muzzleHeight * Math.cos(rotRad));
		muzzleX = bounds.x + originX + mx;
		muzzleY = bounds.y + originY + my;

		for (Particle p : GameManager.getInstance().particleContainer
				.getParticles()) {
			if (p instanceof MuzzleFlashParticle) {
				p.setX(muzzleX);
				p.setY(Player.getInstance().getDirection() < 1
						? muzzleY
						: muzzleY + 5);
				((MuzzleFlashParticle) p).setRotation(rotation);
			}
		}
	}

	@Override
	public void render(SpriteBatch batch) {
		if (direction == 1) {
			batch.draw(gold ? imageGold : image, bounds.x - recoilX, bounds.y,
					originX, originY, bounds.width, bounds.height, 1, 1,
					rotation + recoilRotation, 0, 0, (int) bounds.width,
					(int) bounds.height, false, false);
		} else {
			batch.draw(gold ? imageGold : image, bounds.x + recoilX, bounds.y,
					originX, originY, bounds.width, bounds.height, 1, 1,
					rotation - recoilRotation, 0, 0, (int) bounds.width,
					(int) bounds.height, false, true);
		}
	}

	public void rotate(float delta) {

		if (CurrentSettings.getInstance().mouseAim) {
			float angle = (float) Math.atan2(
					MainCamera.getInstance().getMousePos().y - bounds.y,
					MainCamera.getInstance().getMousePos().x - bounds.x);
			rotation = (float) Math.toDegrees(angle);

			if (rotation > -90 && rotation < 90) {
				direction = 1;
				Player.getInstance().setDirection(1);
			} else {
				direction = 0;
				Player.getInstance().setDirection(0);
			}
		} else {
			if (target != null) {
				if (!target.isDead()) {
					/* Point to target */
					float angle = (float) Math.atan2(
							(target.getY() + PlatformGenerator.getInstance()
									.getSpeed()) - bounds.y,
							target.getX() - bounds.x);
					angle = (float) Math.toDegrees(angle);
					rotation += (angle - rotation) / 2;

					/* Change player and weapon direction */
					if (rotation > -90 && rotation < 90) {
						direction = 1;
						Player.getInstance().setDirection(1);
					} else {
						direction = 0;
						Player.getInstance().setDirection(0);
					}
					if (target.getY() < 0) {
						target = null;
					}
				} else {
					target = null;
				}
			} else {
				direction = Player.getInstance().getDirection();

				/* Change idle weapon rotation based on direction */
				switch (direction) {
					case 0:
						rotation = -180;
						break;
					case 1:
						rotation = 0;
						break;
				}
			}
		}

	}

	public void shoot() {

		/* Play shoot sound */
		Sounds.play(Sounds.getInstance().shootSound, 0.4f,
				0.8f + random.nextFloat());
		/* Randomly play shell sound */
		if (random.nextInt(10) == 5) {
			Sounds.play(Sounds.getInstance().shellSound, 0.5f,
					1.0f + random.nextFloat());
		}
		if (random.nextInt(40) == 9) {
			Sounds.play(Sounds.getInstance().reloadSound, 1.0f,
					0.8f + random.nextFloat());
		}

		/* Determining strength of particles between -2 and 2 */
		float strength = (float) (Math.random() * 4) - 2;

		/* Adding to recoil */
		recoilRotation += recoilAmt; // Add recoil
		recoilX += recoilAmt / 3;

		/* Adding weapon pushback using recoil */
//		if (Player.getInstance().getDirection() == 1) {
//			if (!Upgrades.getInstance().isEquipped(StickySolesUpgrade.class)) { // Check if the StickySoles upgrade is equipped, if not
//				Player.getInstance().getController().bounceLeft(recoilAmt / 9); // Apply recoil bounceback
//			}
//		} else {
//			if (!Upgrades.getInstance().isEquipped(StickySolesUpgrade.class)) { // Check if the StickySoles upgrade is equipped, if not
//				Player.getInstance().getController().bounceRight(recoilAmt / 9);// Apply recoil bounceback
//			}
//		}

		/* Adding bullet */
		Bullet newBullet = new Bullet(getCenterX(), getCenterY(), rotation);
		GameManager.getInstance().entityContainer.add(newBullet);

		/* Adding particles */
		GameManager.getInstance().particleFunctions.addMuzzleFlash(muzzleX,
				muzzleY, rotation);
		if (CurrentSettings.getInstance().screenShake
				&& GameManager.getInstance().stateManager
						.getCurrentState() != GameManager
								.getInstance().stateId_tutorial) {
			MainCamera.getInstance().shake(strength / 3, true, true); // Shake camera
		}
		if (CurrentSettings.getInstance().screenFlash) {
			GameManager.getInstance().particleFunctions
					.addScreenFlash(Math.abs(strength) / 5);
		}

	}

	protected void setImage(Texture newImage) {
		this.image = newImage;
		adjustBoundsToImage(image);
	}
	protected void setImageNoHand(Texture newImage) {
		this.imageNoHand = newImage;
	}
	protected void setImageGold(Texture newImageGold) {
		this.imageGold = newImageGold;
	}
	protected void setImageGoldNoHand(Texture newImageGoldNoHand) {
		this.imageGoldNoHand = newImageGoldNoHand;
	}

	/** Respawn */
	public void reset() {
		fireRate = 200;
		target = null;
	}

	public void setBought(boolean isBought) {
		this.bought = isBought;
	}
	public void setEquipped(boolean isEquipped) {
		this.equipped = isEquipped;
	}
	public void setGold(boolean isGold) {
		this.gold = isGold;
	}
	public void setTarget(Enemy newTarget) {
		this.target = newTarget;
	}
	public void setTotalKills(int newTotalKills) {
		this.totalKills = newTotalKills;
	}
	public void setFireRate(int newFireRate) {
		this.fireRate = newFireRate;
	}

	public Texture getImageNoHand() {
		return this.imageNoHand;
	}
	public Texture getImageGoldNoHand() {
		return this.imageGoldNoHand;
	}
	public Enemy getTarget() {
		return this.target;
	}
	public boolean isBought() {
		return this.bought;
	}
	public boolean isEquipped() {
		return this.equipped;
	}
	public boolean isGold() {
		return this.gold;
	}
	public int getPrice() {
		return this.price;
	}
	public int getTotalKills() {
		return this.totalKills;
	}
	public int getKillsToGold() {
		return this.killsToGold;
	}
	public String getName() {
		return this.name;
	}

}
