package com.flizzet.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.ads.AdManager;
import com.flizzet.creditsmenu.CreditsMenu;
import com.flizzet.main.GameManager;

/**
 * Concrete credits state.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class CreditsState extends GameState {

	private CreditsMenu creditsMenu;

	/** Default instantiable constructor */
	public CreditsState(int id, GameManager gameManager) {
		super(id, gameManager);
	}

	@Override
	public void create() {
	}

	@Override
	public void entered() {
		creditsMenu = new CreditsMenu();
		guiContainer.addToGui(creditsMenu);
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
