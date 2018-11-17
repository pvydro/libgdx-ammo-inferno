package com.flizzet.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.ads.AdManager;
import com.flizzet.main.GameManager;
import com.flizzet.menubuilders.SplashMenuBuilder;

/**
 * Splash screens state.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class SplashState extends GameState {

	SplashMenuBuilder menuBuilder = new SplashMenuBuilder();

	private int cooldown = 200;

	/** Default instantiable constructor */
	public SplashState(int id, GameManager gameManager) {
		super(id, gameManager);
	}

	@Override
	public void create() {
	}

	@Override
	public void entered() {
		menuBuilder.buildMenu();
	}

	@Override
	public void update(float delta) {

		cooldown--;

		// XXX TEMPORARY
		if (cooldown == 0) {
			gameManager.stateManager.enterState(gameManager.stateId_start);
		}

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
