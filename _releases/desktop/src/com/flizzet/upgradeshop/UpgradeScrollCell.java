package com.flizzet.upgradeshop;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.guicomponents.ButtonComponent;
import com.flizzet.main.GameManager;
import com.flizzet.upgrades.Upgrade;
import com.flizzet.utils.FontUtils;

/**
 * Cell to appear in ScrollView.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 * @see also UpgradeScrollView
 */
class UpgradeScrollCell extends ButtonComponent {
    
    private Texture bgImage;
    private Texture icon;
    private Upgrade upgrade;
    private UpgradeScrollView view;
    private BitmapFont cellFont = FontUtils.UPHEAVAL_UPGRADEMENU_SMALL;
    private float iconX, iconY;
    private float textX, textY;
    private float alpha = 1.0f;
    private float darkness = 0f;

    /** Default instantiable constructor */
    public UpgradeScrollCell(Upgrade upgrade, UpgradeScrollView view) {
	super(0, 0);
	
	this.view = view;
	this.upgrade = upgrade;
	this.icon = upgrade.getIcon();
	this.setImage(bgImage = GameManager.getInstance().assetManager.get("gui/upgradeShop/scrollCell.png"));
	adjustBoundsToImage();
    }

    @Override
    public void update(float delta) {
	/* Update when not covered by a upgrade slot selector */
	if (bounds.y > view.getMenuBuilder().getCurrentBar().getHeight()) {
	    super.update(delta);
	}
	/* Set icon position */
	iconX = this.getX() + 7;
	iconY = this.getY() + 11;
	
	/* Set text position */
	textX = iconX + icon.getWidth() + 4;
	textY = iconY + (icon.getHeight() / 2) + cellFont.getCapHeight() / 2;
	
	/* Set alpha based on position */
	if (bounds.y > 200) {
	    alpha = (375 - bounds.y) / 100;
	} else {
	    alpha = (25 + bounds.y) / 100;
	}
	if (alpha < 0) alpha = 0;
	if (alpha > 1) alpha = 1;
	
	/* Darken when pressed */
	if (this.isPushed()) {
	    darkness = 0.5f;
	} else {
	    darkness += (0 - darkness) / 4f;
	}
	
    }

    @Override
    public void render(SpriteBatch batch) {
	Color tmp = batch.getColor();
	
	batch.setColor(tmp.r - darkness, tmp.g - darkness, tmp.b - darkness, alpha);
	batch.draw(bgImage, bounds.x, bounds.y);
	batch.draw(icon, iconX, iconY);
	cellFont.setUseIntegerPositions(false);
	cellFont.setColor(tmp.r, tmp.g, tmp.b, alpha);
	cellFont.draw(batch, upgrade.getName(), textX, textY);
	cellFont.setColor(tmp);
	
	batch.setColor(tmp);
    }

    @Override
    public void triggered() {
	view.openPrompt(upgrade);
    }

}
