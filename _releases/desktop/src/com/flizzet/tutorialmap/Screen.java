package com.flizzet.tutorialmap;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.flizzet.camera.MainCamera;
import com.flizzet.entities.Entity;
import com.flizzet.guicomponents.ButtonComponent;
import com.flizzet.main.GameManager;
import com.flizzet.utils.FontUtils;

/**
 * Displays text for the tutorial.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
class Screen extends Entity {
    
    private Texture bgImage;
    private TutorialAcceptButton acceptButton = new TutorialAcceptButton(this);
    
    /* Text */
    private BitmapFont textFont = FontUtils.UPHEAVAL_75;
    private ScreenCategory currentCategory = ScreenCategory.WELCOME;
    private GlyphLayout textLayout = new GlyphLayout(textFont, currentCategory.getMessage());
    private float textX, textY;
    
    
    private final int MARGIN = 20;

    /** Default instantiable constructor */
    public Screen() {
	this.bgImage = GameManager.getInstance().assetManager.get("tutorial/screen.png");
	this.adjustBoundsToImage(bgImage);
	
	textFont.setUseIntegerPositions(false);
    }

    @Override
    public void update(float delta) {
	bounds.x = MainCamera.getInstance().getCenterX() - (bounds.width / 2);
	bounds.y = MainCamera.getInstance().getHeight() - bounds.height - MARGIN;
	
	/* Position text */
	textLayout.setText(textFont, currentCategory.getMessage(), Color.WHITE, bounds.width - 10, Align.center, true);
	textX = bounds.x + 5;
	textY = bounds.y + bounds.height - 5;
	
	/* Button */
	acceptButton.setX(this.getCenterX() - (acceptButton.getWidth() / 2));
	acceptButton.setY(this.getY() - 4);
	acceptButton.update(delta);
    }

    @Override
    public void render(SpriteBatch batch) {
	batch.draw(bgImage, bounds.x, bounds.y);
	
	textFont.draw(batch, textLayout, textX, textY);
	
	acceptButton.render(batch);
    }
    
    /* Moves to the next category if there is one, otherwise enter the start menu */
    public void nextCategory() {
	if (currentCategory.ordinal() < ScreenCategory.values().length - 1) {
	    currentCategory = ScreenCategory.values()[currentCategory.ordinal() + 1];
	} else {
	    GameManager.getInstance().stateManager.enterState(GameManager.getInstance().stateId_start);
	    GameManager.getInstance().entityContainer.remove(this);
	}
    }

}

class TutorialAcceptButton extends ButtonComponent {
    
    private Screen screen;
    
    public TutorialAcceptButton(Screen screen) {
	super(0, 0);
	
	this.screen = screen;
	
	this.setImage(GameManager.getInstance().assetManager.get("tutorial/acceptButton.png", Texture.class));
	this.setImageHovered(GameManager.getInstance().assetManager.get("tutorial/acceptButton.png", Texture.class));
	this.setImagePushed(GameManager.getInstance().assetManager.get("tutorial/acceptButtonPushed.png", Texture.class));
    }
    
    @Override
    public void triggered() {
	screen.nextCategory();
    }
    
}