package com.flizzet.ingamegui;

import com.badlogic.gdx.graphics.Texture;
import com.flizzet.guicomponents.ButtonComponent;
import com.flizzet.main.GameManager;
import com.flizzet.main.GameState;
import com.flizzet.main.PauseManager;
import com.flizzet.pausemenu.PauseMenu;

/**
 * Concrete pause button.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
class PauseButton extends ButtonComponent {

	/** Default instantiable constructor */
	public PauseButton() {
		super(0, 0);

		this.setImage(GameManager.getInstance().assetManager
				.get("gui/inGame/pauseButton.png", Texture.class));
		this.setImageHovered(GameManager.getInstance().assetManager
				.get("gui/inGame/pauseButton.png", Texture.class));
		this.setImagePushed(GameManager.getInstance().assetManager
				.get("gui/inGame/pauseButtonPushed.png", Texture.class));
	}

	@Override
	public void triggered() {
		switch (PauseManager.getInstance().getState()) {
			case PAUSE:
				PauseManager.getInstance().setState(GameState.PLAY);
				PauseMenu.getInstance().disable();
				break;
			case PLAY:
				PauseManager.getInstance().setState(GameState.PAUSE);
				break;
			default:
				break;
		}
	}

}
