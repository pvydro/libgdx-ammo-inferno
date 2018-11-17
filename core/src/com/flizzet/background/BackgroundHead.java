package com.flizzet.background;

import java.util.Random;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.entities.Entity;
import com.flizzet.main.GameManager;

/**
 *
 * Head that appears in the background.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
class BackgroundHead extends Entity {

	private Random random = new Random();
	private Texture headImage;
	private BackgroundComponent background;
	private float rotation;
	private final int scale;
	private float originX, originY;
	private float alpha = 0;
	private float targetExcessRotation = 0;
	private boolean flip = random.nextBoolean();

	public BackgroundHead(int scale, float alpha,
			BackgroundComponent background) {
		this.background = background;
		this.headImage = GameManager.getInstance().assetManager
				.get("enemies/zombie/head/headDefault.png");
		this.scale = scale;
		this.alpha = alpha;
		this.setWidth(headImage.getWidth());
		this.setHeight(headImage.getHeight());
		rotation = random.nextInt(360);
	}

	@Override
	public void update(float delta) {
		originX = getWidth() / 2;
		originY = getHeight() / 2;

		float tr = targetExcessRotation + rotation;
		tr = random.nextBoolean() ? random.nextInt(2) : -random.nextInt(2);
		rotation += (rotation + tr - rotation) / 2;
	}

	@Override
	public void render(SpriteBatch batch) {
		Color tmp = batch.getColor();
		batch.setColor(
				new Color(tmp.r - alpha - background.getScreenFlashAmount(),
						tmp.g - alpha - background.getScreenFlashAmount(),
						tmp.b - alpha - background.getScreenFlashAmount(), 1));

		batch.draw(headImage, bounds.x, bounds.y, originX, originY / 2,
				bounds.width, bounds.height, scale, scale, rotation, 0, 0,
				(int) bounds.width, (int) bounds.height, flip, false);

		batch.setColor(tmp);
	}

}