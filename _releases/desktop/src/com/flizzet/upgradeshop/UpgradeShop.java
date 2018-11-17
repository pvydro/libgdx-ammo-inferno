package com.flizzet.upgradeshop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.camera.MainCamera;
import com.flizzet.guicomponents.GuiComponent;
import com.flizzet.guicomponents.TransitionComponent;
import com.flizzet.main.GameManager;
import com.flizzet.menubuilders.UpgradeMenuBuilder;
import com.flizzet.utils.FontUtils;

/**
 * Main Upgrade Shop with all elements in it.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class UpgradeShop extends GuiComponent {
    
    private UpgradeMenuBuilder menuBuilder = new UpgradeMenuBuilder();
    private BitmapFont headerFont = FontUtils.UPHEAVAL_UPGRADEMENU_LARGE_BORDERED;
    private GlyphLayout headerLayout = new GlyphLayout(headerFont, "upgrades");
    private BackButton backButton = new BackButton();
    private float headerX, headerY;
    private static float MARGIN = 20;
    
    /** Default instantiable constructor */
    public UpgradeShop() {
	super(0, 0);
	menuBuilder.buildMenu();
    }

    @Override
    public void update(float delta) {
	/* Set header position */
	float totalWidth = headerLayout.width + backButton.getWidth() + 10;
	headerX = menuBuilder.getScrollView().getX() + MainCamera.getInstance().getCenterX() + (totalWidth / 2) - headerLayout.width;
	headerY = MainCamera.getInstance().getHeight() - MARGIN;
	
	/* Set back button position */
	backButton.setX(menuBuilder.getScrollView().getX() + MainCamera.getInstance().getCenterX() - (totalWidth / 2));
	backButton.setY(headerY - (headerLayout.height / 2) - (backButton.getHeight() / 2));
	backButton.update(delta);
	
	/* Back button */
	if (Gdx.input.isKeyJustPressed(Keys.BACK) || Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
	    /* Suppress if states are transitioning */
	    for (GuiComponent g : GameManager.getInstance().guiContainer.getGuiComponents()) {
		if (g instanceof TransitionComponent) {
		    return;
		}
	    }
	    if (menuBuilder.getScrollView().getState() == 1) {
		menuBuilder.getScrollView().closePrompt();
	    } else {
		GameManager.getInstance().stateManager.enterState(GameManager.getInstance().stateId_shop);
	    }
	}
    }

    @Override
    public void render(SpriteBatch batch) {
	
	headerFont.setColor(Color.WHITE);
	headerFont.setUseIntegerPositions(false);
	headerFont.draw(batch, "upgrades", headerX, headerY);
	backButton.render(batch);

    }

    @Override
    public void triggered() {
	
    }

}
