package com.flizzet.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.ads.AdManager;
import com.flizzet.main.GameManager;
import com.flizzet.upgradeshop.UpgradeShop;

/**
 * Shop state for the Upgrades section.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class UpgradeShopState extends GameState {

	private UpgradeShop upgradeShop;

	/** Default instantiable constructor */
	public UpgradeShopState(int id, GameManager gameManager) {
		super(id, gameManager);
	}

	@Override
	public void create() {

	}

	@Override
	public void entered() {
		upgradeShop = new UpgradeShop();
		guiContainer.addToGui(upgradeShop);
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
		guiContainer.clear();
		particleContainer.clear();
	}

	@Override
	public void dispose() {
	}

}
