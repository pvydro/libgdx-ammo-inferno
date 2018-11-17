package com.flizzet.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.ads.AdManager;
import com.flizzet.effectssettingsmenu.EffectsSettingsMenu;
import com.flizzet.main.GameManager;

/**
 * Effects setting state.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class EffectsSettingsState extends GameState {

	private EffectsSettingsMenu effectsMenu;

	/** Default instantiable constructor */
	public EffectsSettingsState(int id, GameManager gameManager) {
		super(id, gameManager);
	}

	@Override
	public void create() {
	}

	@Override
	public void entered() {
		effectsMenu = new EffectsSettingsMenu();
		guiContainer.addToGui(effectsMenu);
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
