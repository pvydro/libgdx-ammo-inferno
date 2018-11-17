package com.flizzet.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.ads.AdManager;
import com.flizzet.generalsettingsmenu.GeneralSettingsMenu;
import com.flizzet.main.GameManager;

/**
 * General settings state.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class GeneralSettingsState extends GameState {

    private GeneralSettingsMenu menu;
    
    /** Default instantiable constructor */
    public GeneralSettingsState(int id, GameManager gameManager) {
	super(id, gameManager);
    }

    @Override
    public void create() {
    }

    @Override
    public void entered() {
	menu = new GeneralSettingsMenu();
	guiContainer.addToGui(menu);
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
