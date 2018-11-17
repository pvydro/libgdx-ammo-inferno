package com.flizzet.ingamegui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.flizzet.entities.Entity;
import com.flizzet.main.GameManager;

/**
 * Heart that bounces up and down on the player health bar.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 * @see also PlayerHealthBar
 */
class HealthHeart extends Entity {

	private Texture heartImage;

	private float yChange;
	private float stateTime = 0;
	private float lifeTime = 2;

	int seq = 0;

	/** Default instantiable constructor */
	public HealthHeart() {

		heartImage = GameManager.getInstance().assetManager
				.get("gui/inGame/heart.png", Texture.class);
		this.setWidth(heartImage.getWidth());
		this.setHeight(heartImage.getHeight());

	}

	@Override
	public void update(float delta) {

		if (seq == 0)
			stateTime += delta;
		if (seq == 1)
			stateTime -= delta;

		float progress = Math.min(1f, stateTime / lifeTime);
		yChange = new Interpolation.Elastic(1, 3, 3, 3).apply(progress);

		if (progress >= 1.0f) {
			seq = 1;
		} else if (progress <= 0.0f) {
			seq = 0;
		}

	}

	@Override
	public void render(SpriteBatch batch) {

		batch.draw(heartImage, bounds.x, bounds.y + yChange, bounds.width,
				bounds.height);

	}

}
