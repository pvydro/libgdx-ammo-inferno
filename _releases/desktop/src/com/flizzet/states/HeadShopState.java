package com.flizzet.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.ads.AdManager;
import com.flizzet.headshop.HeadShop;
import com.flizzet.main.GameManager;

/**
 * Shop state for the Head section.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class HeadShopState extends GameState {

    private HeadShop headShop;
    
    /** Default instantiable constructor */
    public HeadShopState(int id, GameManager gameManager) {
	super(id, gameManager);
    }

    @Override
    public void create() {}

    @Override
    public void entered() {
	
	headShop = new HeadShop();
	guiContainer.addToGui(headShop);
	
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
