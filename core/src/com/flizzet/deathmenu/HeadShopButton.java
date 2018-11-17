package com.flizzet.deathmenu;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.guicomponents.ButtonComponent;
import com.flizzet.main.GameManager;

/**
 * Concrete head button shop component.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class HeadShopButton extends ButtonComponent {

	private Texture shineImage = GameManager.getInstance().assetManager
			.get("gui/deathMenu/shopSelection/buttonShine.png");

	/** Default instantiable constructor */
	public HeadShopButton() {
		super(0, 0);

		this.setImage(GameManager.getInstance().assetManager.get(
				"gui/deathMenu/shopSelection/headButton.png", Texture.class));
		this.setImageHovered(GameManager.getInstance().assetManager.get(
				"gui/deathMenu/shopSelection/headButton.png", Texture.class));
		this.setImagePushed(GameManager.getInstance().assetManager.get(
				"gui/deathMenu/shopSelection/headButtonPushed.png",
				Texture.class));

		this.subtext = "heads";
	}

	@Override
	public void render(SpriteBatch batch) {
		super.render(batch);
		if (isPushed()) {
			batch.draw(shineImage, bounds.x + 4, subtextY + 1 + 4,
					bounds.width - 8, bounds.height - 8);
		} else {
			batch.draw(shineImage, bounds.x, subtextY + 1, bounds.width,
					bounds.height);
		}
	}

	@Override
	public void triggered() {
		GameManager.getInstance().guiContainer.clear();
		GameManager.getInstance().stateManager
				.enterState(GameManager.getInstance().stateId_headShop);
	}

}
