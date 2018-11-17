package com.flizzet.tutorialmap;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.camera.MainCamera;
import com.flizzet.main.GameManager;
import com.flizzet.map.Platform;

/**
 * Floor for the player to walk on in the tutorial state.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class Floor extends Platform {

	private Texture image;

	/** Default instantiable constructor */
	public Floor() {
		this.image = GameManager.getInstance().assetManager
				.get("tutorial/floor.png");
		this.adjustBoundsToImage(image);
		this.setHeight(this.getHeight() + 2);
	}

	@Override
	public void update(float delta) {
		this.setX(MainCamera.getInstance().getCenterX() - (bounds.width / 2));
		this.setY(0);
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(image, bounds.x, bounds.y);
	}

}
