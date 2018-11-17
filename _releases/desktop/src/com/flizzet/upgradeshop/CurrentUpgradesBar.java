package com.flizzet.upgradeshop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.camera.MainCamera;
import com.flizzet.guicomponents.GuiComponent;
import com.flizzet.main.GameManager;
import com.flizzet.upgrades.Upgrade;
import com.flizzet.upgradesystem.Upgrades;
import com.flizzet.utils.FontUtils;

/**
 * Displays all current upgrades equipped.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class CurrentUpgradesBar extends GuiComponent {
    
    private Upgrade[] currentlyEquipped = Upgrades.getInstance().getEquippedUpgrades();
    private Texture barImage;
    private Texture indicatorImage;
    private BitmapFont font = FontUtils.UPHEAVAL_UPGRADEMENU_SMALL;
    private String topText = "choose slot";
    private GlyphLayout layout = new GlyphLayout(font, topText);
    private float indicatorX, indicatorY;
    private float iconY;
    private float firstX, secondX, thirdX;
    private float topTextX, topTextY;
    private float initialY;
    private boolean pushed = false;
    
    /** Default instantiable constructor */
    public CurrentUpgradesBar() {
	super(0, 0);
	
	this.barImage = GameManager.getInstance().assetManager.get("gui/upgradeShop/currentUpgrades.png");
	this.indicatorImage = GameManager.getInstance().assetManager.get("gui/upgradeShop/currentSlotIndicator.png");
	adjustBoundsToImage();
	
	Upgrades.getInstance().setCurrentSlot(1);
    }


    @Override
    public void update(float delta) {
	this.setX(MainCamera.getInstance().getCenterX() - (this.getWidth() / 2));
	
	/* Set icon positions */
	iconY = this.getY() + 13;
	firstX = this.getX() + 9;
	secondX = firstX + 28;
	thirdX = this.getX() + this.getWidth() - 31;
	
	/* Set upper text position */
	topTextX = this.getCenterX() - (layout.width / 2);
	topTextY = this.getY() + this.getHeight() + layout.height + 2;
	
	/* Set indicator positions */
	indicatorY = iconY - 1;
	switch (Upgrades.getInstance().getCurrentSlot()) {
	default:
	case 1:
	    indicatorX += ((firstX - 1) - indicatorX) / 2;
	    break;
	case 2:
	    indicatorX += ((secondX - 1) - indicatorX) / 2;
	    break;
	case 3:
	    indicatorX += ((thirdX - 1) - indicatorX) / 2;
	    break;
	}
	
	/* Check mouse touching for current slot selection */
	float mouseX = MainCamera.getInstance().getMousePos().x;
	if (Gdx.input.isTouched()) {
	    if (!pushed) {
		/* Single call */
		initialY = MainCamera.getInstance().getMousePos().y;
		pushed = true;
	    }
	    if (mouseX < getX() + getWidth()
	    && MainCamera.getInstance().getMousePos().y < this.getY() + this.getHeight()
	    && initialY < this.getY() + this.getHeight()) {
		if (mouseX > thirdX) {
		    Upgrades.getInstance().setCurrentSlot(3);
		} else if (mouseX > secondX) {
		    Upgrades.getInstance().setCurrentSlot(2);
		} else if (mouseX > firstX) {
		    Upgrades.getInstance().setCurrentSlot(1);
		}
	    }
	} else {
	    pushed = false;
	}
	
    }


    @Override
    public void render(SpriteBatch batch) {
	batch.draw(barImage, bounds.x, bounds.y);
	
	/* Draw the icons for all three currently equipped */
	if (currentlyEquipped[2] != null) {
	    batch.draw(currentlyEquipped[2].getIcon(), thirdX, iconY);
	}
	if (currentlyEquipped[1] != null) {
	    batch.draw(currentlyEquipped[1].getIcon(), secondX, iconY);
	}
	if (currentlyEquipped[0] != null) {
	    batch.draw(currentlyEquipped[0].getIcon(), firstX, iconY);
	}
	
	/* Draw the indicator */
	batch.draw(indicatorImage, indicatorX, indicatorY);
	
	/* Draw upper text */
	font.setColor(Color.WHITE);
	font.draw(batch, topText, topTextX, topTextY);
    }


    @Override
    public void triggered() {}
    
    /** Sets dimension based on image width and height */
    private void adjustBoundsToImage() {
	this.setWidth(barImage.getWidth());
	this.setHeight(barImage.getHeight());
    }
}
