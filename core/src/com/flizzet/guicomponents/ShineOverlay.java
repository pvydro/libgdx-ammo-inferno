package com.flizzet.guicomponents;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.main.GameManager;

/**
 * Shine to appear in the backgrounds of menus for effect.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class ShineOverlay extends GuiComponent {

	private Texture overlay;

	/** Default instantiable constructor */
	public ShineOverlay() {
		super(0, 0);
		overlay = GameManager.getInstance().assetManager
				.get("gui/constant/menuBgOverlayOpaque.png", Texture.class);
	}

	@Override
	public void update(float delta) {
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(overlay, -50, -50);
	}

	@Override
	public void triggered() {
		/** TODO Auto-generated method stub */

	}

}
