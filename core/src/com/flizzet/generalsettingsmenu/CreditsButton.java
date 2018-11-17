package com.flizzet.generalsettingsmenu;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.guicomponents.ButtonComponent;
import com.flizzet.guicomponents.GuiComponent;
import com.flizzet.guicomponents.InfoPrompt;
import com.flizzet.main.GameManager;

/**
 * Credits button.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class CreditsButton extends ButtonComponent {

	private Texture shineImage = GameManager.getInstance().assetManager
			.get("gui/settingsMenu/categories/buttonShine.png");

	/** Default instantiable constructor */
	public CreditsButton() {
		super(0, 0);

		this.setImage(GameManager.getInstance().assetManager.get(
				"gui/settingsMenu/categories/general/creditsButton.png",
				Texture.class));
		this.setImageHovered(GameManager.getInstance().assetManager.get(
				"gui/settingsMenu/categories/general/creditsButton.png",
				Texture.class));
		this.setImagePushed(GameManager.getInstance().assetManager.get(
				"gui/settingsMenu/categories/general/creditsButtonPushed.png",
				Texture.class));

		this.subtext = "credits";
	}

	@Override
	public void update(float delta) {
		for (GuiComponent g : GameManager.getInstance().guiContainer
				.getGuiComponents()) {
			if (g instanceof InfoPrompt) {
				return;
			}
		}
		super.update(delta);
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
		GameManager.getInstance().stateManager
				.enterState(GameManager.getInstance().stateId_credits);
	}

}
