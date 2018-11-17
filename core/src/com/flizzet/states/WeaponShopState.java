package com.flizzet.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.ads.AdManager;
import com.flizzet.main.GameManager;
import com.flizzet.weaponshop.WeaponShop;

/**
 * Concrete weapon shop state.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class WeaponShopState extends GameState {

	private WeaponShop weaponShop;

	/** Default instantiable constructor */
	public WeaponShopState(int id, GameManager gameManager) {
		super(id, gameManager);
	}

	@Override
	public void create() {
	}

	@Override
	public void entered() {
		weaponShop = new WeaponShop();
		guiContainer.addToGui(weaponShop);
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
