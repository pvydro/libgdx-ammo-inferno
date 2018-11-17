package com.flizzet.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.ads.AdManager;
import com.flizzet.main.GameManager;
import com.flizzet.settingsmenu.SettingsMenu;

/**
 * Concrete settings state.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class SettingsState extends GameState {

	private SettingsMenu settingsMenu;

	/** Default instantiable constructor */
	public SettingsState(int id, GameManager gameManager) {
		super(id, gameManager);
	}

	@Override
	public void create() {

	}

	@Override
	public void entered() {
		settingsMenu = new SettingsMenu();
		guiContainer.addToGui(settingsMenu);
		AdManager.getInstance().showBannerAd();
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
