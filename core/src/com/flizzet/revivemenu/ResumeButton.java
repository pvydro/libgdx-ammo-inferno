package com.flizzet.revivemenu;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.camera.MainCamera;
import com.flizzet.guicomponents.ButtonComponent;
import com.flizzet.main.GameManager;
import com.flizzet.main.GameState;
import com.flizzet.main.PauseManager;

/**
 * Concrete resume button class.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class ResumeButton extends ButtonComponent {

	private Texture shineImage = GameManager.getInstance().assetManager
			.get("gui/startMenu/buttonShine.png");

	public ResumeButton() {
		super(0, 0);

		this.setImage(GameManager.getInstance().assetManager
				.get("gui/pauseMenu/resumeButton.png", Texture.class));
		this.setImageHovered(GameManager.getInstance().assetManager
				.get("gui/pauseMenu/resumeButton.png", Texture.class));
		this.setImagePushed(GameManager.getInstance().assetManager
				.get("gui/pauseMenu/resumeButtonPushed.png", Texture.class));
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
		PauseManager.getInstance().setState(GameState.PLAY);
		RevivedMenu.getInstance().disable();
	}

}
