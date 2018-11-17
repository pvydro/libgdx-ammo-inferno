package com.flizzet.score;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.entities.Entity;
import com.flizzet.lighting.ChestLight;
import com.flizzet.main.GameManager;
import com.flizzet.map.Platform;
import com.flizzet.map.PlatformGenerator;
import com.flizzet.settings.CurrentSettings;
import com.flizzet.sound.Sounds;
import com.flizzet.upgrades.ExcavationistUpgrade;
import com.flizzet.upgradesystem.Upgrades;

/**
 * Treasure chest item carrying loot.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class TreasureChest extends Entity {

	private TreasureChestCollision collision = new TreasureChestCollision(this);
	private ChestLight light = new ChestLight(this);
	private Random random = new Random();
	private Texture image;
	private Texture luckyIndicatorImage;
	private Texture excavationistIndicatorImage;
	private ChestType type;
	private Platform platform;

	private boolean opened = false;
	private boolean lucky = false;
	private boolean excavationist = false;
	private float luckyIndicatorX, luckyIndicatorY;
	private float excaIndicatorX, excaIndicatorY;
	private int xOffset;

	/** Default instantiable constructor */
	public TreasureChest(Platform platform) {
		this.platform = platform;

		/* Randomly decide the type and assign the image */
		int chance = random.nextInt(100);
		if (chance >= 99) {
			this.type = ChestType.DIAMOND;
			this.image = GameManager.getInstance().assetManager
					.get("items/diamondChest.png", Texture.class);
		} else if (chance >= 90) {
			this.type = ChestType.GOLD;
			this.image = GameManager.getInstance().assetManager
					.get("items/goldChest.png", Texture.class);
		} else {
			this.type = ChestType.IRON;
			this.image = GameManager.getInstance().assetManager
					.get("items/ironChest.png", Texture.class);
		}
		this.luckyIndicatorImage = GameManager.getInstance().assetManager
				.get("upgrades/luckyDogIndicator.png", Texture.class);
		this.excavationistIndicatorImage = GameManager
				.getInstance().assetManager.get(
						"upgrades/excavationistIndicator.png", Texture.class);
		this.luckyIndicatorX = this.getX();
		this.luckyIndicatorY = this.getY();

		this.adjustBoundsToImage(image);

		/* Build offset from platform x position */
		this.xOffset = random.nextInt(100);

		/* Attach upgrades */
		if (Upgrades.getInstance().isEquipped(ExcavationistUpgrade.class)) {
			this.excavationist = true;
		}
	}

	@Override
	public void update(float delta) {
		/* Set position with offset */
		this.setX(platform.getSide() == 0
				? platform.getX() + xOffset + 11
				: platform.getX() + xOffset);
		this.setY(platform.getY() + platform.getHeight()
				+ PlatformGenerator.getInstance().getSpeed());

		/* Set lucky indicator position */
		luckyIndicatorX = this.getCenterX()
				- (luckyIndicatorImage.getWidth() / 2);
		luckyIndicatorY = this.getY() + this.getHeight() + 3;
		/* Set excavationist indicator position */
		excaIndicatorX = this.getX() + this.getWidth() + 3;
		excaIndicatorY = this.getCenterY()
				- (excavationistIndicatorImage.getHeight() / 2);

		if (!opened)
			collision.update(delta);
		light.update(delta);
	}

	@Override
	public void render(SpriteBatch batch) {
		if (lucky)
			batch.draw(luckyIndicatorImage, luckyIndicatorX, luckyIndicatorY);
		if (excavationist)
			batch.draw(excavationistIndicatorImage, excaIndicatorX,
					excaIndicatorY);

		light.render(batch);
		batch.draw(image, bounds.x, bounds.y, bounds.width, bounds.height);
	}

	/** Opens the chest and adds all the score to the screen */
	public void open() {
		opened = true;
		int coins = 0;
		int bills = 0;

		/* Play sound */
		Sounds.play(Sounds.getInstance().chestOpenSound, 1.3f,
				0.5f + random.nextFloat());

		/*
		 * Choose a random amount of loot depending on the type and set to
		 * opened image
		 */
		if (type == ChestType.IRON) {
			coins = random.nextInt(20) + 10;
			bills = random.nextInt(2);
			image = GameManager.getInstance().assetManager
					.get("items/ironChest_opened.png", Texture.class);
		} else if (type == ChestType.GOLD) {
			coins = random.nextInt(40) + 20;
			bills = random.nextInt(3);
			image = GameManager.getInstance().assetManager
					.get("items/goldChest_opened.png", Texture.class);
		} else if (type == ChestType.DIAMOND) {
			coins = random.nextInt(200) + 100;
			bills = random.nextInt(10);
			image = GameManager.getInstance().assetManager
					.get("items/diamondChest_opened.png", Texture.class);
		}

		/* Apply excavationist upgrade */
		if (Upgrades.getInstance().isEquipped(ExcavationistUpgrade.class)) {
			coins *= 2;
		}

		/* Add loot objects */
		for (int i = 0; i < coins; i++) {
			Coin coin = new Coin();
			coin.setX(random.nextBoolean()
					? getCenterX() + random.nextInt(10)
					: getCenterX() - random.nextInt(10));
			coin.setX(random.nextBoolean()
					? getCenterY() + random.nextInt(10)
					: getCenterY() - random.nextInt(10));
			GameManager.getInstance().entityContainer.add(coin);
		}
		for (int i = 0; i < bills; i++) {
			Bill bill = new Bill();
			bill.setX(random.nextBoolean()
					? getCenterX() + random.nextInt(10)
					: getCenterX() - random.nextInt(10));
			bill.setX(random.nextBoolean()
					? getCenterY() + random.nextInt(10)
					: getCenterY() - random.nextInt(10));
			GameManager.getInstance().entityContainer.add(bill);
		}

		/* Add text particles */
		if (CurrentSettings.getInstance().textParticles) {
			GameManager.getInstance().particleFunctions
					.addTextParticle(getCenterX(), getCenterY() + 10, "loot!");
			GameManager.getInstance().particleFunctions.addTextParticle(
					getCenterX(), getCenterY() + 20, coins + " coins");
			if (bills > 0) {
				GameManager.getInstance().particleFunctions.addTextParticle(
						getCenterX(), getCenterY() + 30,
						bills == 1 ? bills + " bill" : bills + " bills");
			}
		}

	}

	public void setLucky(boolean isLucky) {
		this.lucky = isLucky;
	}

	public ChestType getType() {
		return this.type;
	}
	public boolean isOpened() {
		return this.opened;
	}

}
