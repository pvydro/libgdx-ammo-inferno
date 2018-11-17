package com.flizzet.headshop;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.guicomponents.GuiComponent;
import com.flizzet.headsystem.Heads;
import com.flizzet.main.GameManager;

/**
 * Displays which head is currently selected.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
class HeadIndicator extends GuiComponent {

	private Texture image;
	private IconView view;

	/** Default instantiable constructor */
	public HeadIndicator(IconView view) {
		super(0, 0);
		this.view = view;
		this.image = GameManager.getInstance().assetManager
				.get("gui/headShop/indicator.png", Texture.class);
	}

	@Override
	public void update(float delta) {

		HeadIcon icon = view.getHeadIcon(Heads.getInstance().getHeads()
				.indexOf(Heads.getInstance().getEquippedHead()));
		bounds.x += ((icon.getX() - 1) - bounds.x) / 5;
		bounds.y += ((icon.getY() - 1) - bounds.y) / 5;

	}

	@Override
	public void render(SpriteBatch batch) {

		batch.draw(image, bounds.x, bounds.y);

	}

	@Override
	public void triggered() {
	}

}
