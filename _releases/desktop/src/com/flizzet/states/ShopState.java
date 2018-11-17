package com.flizzet.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.ads.AdManager;
import com.flizzet.main.GameManager;
import com.flizzet.shopmenu.ShopMenu;

/**
 * Shop state.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class ShopState extends GameState {

    private ShopMenu shopMenu;
    
    /** Default instantiable constructor */
    public ShopState(int id, GameManager gameManager) {
	super(id, gameManager);
    }

    @Override
    public void create() {
    }

    @Override
    public void entered() {
	shopMenu = new ShopMenu();
	guiContainer.addToGui(shopMenu);
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
    public void dispose() {}

}
