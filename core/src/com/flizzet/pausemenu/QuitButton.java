package com.flizzet.pausemenu;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.camera.MainCamera;
import com.flizzet.guicomponents.ButtonComponent;
import com.flizzet.main.GameManager;
import com.flizzet.main.GameState;
import com.flizzet.main.PauseManager;

/**
 * Concrete quit button.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class QuitButton extends ButtonComponent {

	private Texture shineImage = GameManager.getInstance().assetManager
			.get("gui/startMenu/buttonShine.png");

	/** Default instantiable constructor */
	public QuitButton() {
		super(0, 0);

		this.setImage(GameManager.getInstance().assetManager
				.get("gui/pauseMenu/quitButton.png", Texture.class));
		this.setImageHovered(GameManager.getInstance().assetManager
				.get("gui/pauseMenu/quitButton.png", Texture.class));
		this.setImagePushed(GameManager.getInstance().assetManager
				.get("gui/pauseMenu/quitButtonPushed.png", Texture.class));
	}

	@Override
	public void render(SpriteBatch batch) {
		super.render(batch);

		float shakeXOffset = (MainCamera.getInstance().getCamera().position.x
				- MainCamera.getInstance().getTargetX());
		float shakeYOffset = (MainCamera.getInstance().getCamera().position.y
				- MainCamera.getInstance().getTargetY());

		if (isPushed()) {
			batch.draw(shineImage, bounds.x + 2 + shakeXOffset,
					subtextY + 1 + 2 + shakeYOffset, bounds.width - 4,
					bounds.height - 4);
		} else {
			batch.draw(shineImage, bounds.x + shakeXOffset,
					subtextY + 1 + shakeYOffset, bounds.width, bounds.height);
		}
	}

	@Override
	public void triggered() {
		GameManager.getInstance().guiContainer.clear();
		GameManager.getInstance().stateManager
				.enterState(GameManager.getInstance().stateId_start);
		PauseManager.getInstance().setState(GameState.PLAY);
		PauseMenu.getInstance().disable();
	}

}
