package com.flizzet.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.ads.AdManager;
import com.flizzet.controlsettingsmenu.ControlsSettingsMenu;
import com.flizzet.main.GameManager;

/**
 * Concrete control settings state.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class ControlsSettingsState extends GameState {

	private ControlsSettingsMenu controlsMenu;

	/** Default instantiable constructor */
	public ControlsSettingsState(int id, GameManager gameManager) {
		super(id, gameManager);
	}

	@Override
	public void create() {
	}

	@Override
	public void entered() {
		controlsMenu = new ControlsSettingsMenu();
		guiContainer.addToGui(controlsMenu);
	}

	@Override
	public void update(float delta) {
	}

	@Override
	public void render(SpriteBatch batch) {
	}

	@Override
	public void exited() {
		AdManager.getInstance().hideBannerAd();
		entityContainer.clear();
		particleContainer.clear();
		guiContainer.clear();
	}

	@Override
	public void dispose() {
	}

}
