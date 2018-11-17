package com.flizzet.guicomponents;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.flizzet.camera.MainCamera;
import com.flizzet.main.GameManager;
import com.flizzet.sound.Sounds;
import com.flizzet.utils.FontUtils;

/**
 * Notification that appears to display information.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class GameNotification extends GuiComponent {

    private Texture image;
    private String message = "";
    private BitmapFont font = FontUtils.UPHEAVAL_NOTIFICATION_SMALL;
    private GlyphLayout layout;
    private int state = 0;		// 0 = Moving up; 1 = Moving down
    private int cooldown = 100;
    private float textX, textY;
    
    /** Default instantiable constructor */
    public GameNotification(String message) {
	super(0, 0);
	
	this.message = message;
	this.image = GameManager.getInstance().assetManager.get("gui/constant/notification.png");
	this.adjustBoundsToImage();
	this.setY(MainCamera.getInstance().getHeight());
	
	layout = new GlyphLayout(font, message);
	
	/* Play sound */
	Sounds.play(Sounds.getInstance().notificationSound, 1.0f);
    }

    @Override
    public void update(float delta) {
	/* Adjusting position */
	this.setX(MainCamera.getInstance().getCenterX() - (this.getWidth() / 2));
	if (state == 0) {
	    bounds.y += ((MainCamera.getInstance().getHeight() - this.getHeight()) - bounds.y) / 5;
	} else {
	    bounds.y += ((MainCamera.getInstance().getHeight() + 5) - bounds.y) / 5;
	}
	
	/* Text positioning */
	layout.setText(font, message, Color.WHITE, this.getWidth(), Align.center, true);
	textX = this.getCenterX() - (this.getWidth() / 2);
	textY = this.getY() + this.getHeight() - 8;
	
	/* Cooldown */
	cooldown--;
	if (cooldown <= 0) {
	    state = 1;
	}
	
	/* Removal */
	if (state == 1 && this.getY() > MainCamera.getInstance().getHeight() + 1) {
	    GameManager.getInstance().guiContainer.removeFromGui(this);
	}
    }

    @Override
    public void render(SpriteBatch batch) {
	batch.draw(image, bounds.x, bounds.y);
	
	font.setUseIntegerPositions(false);
	font.draw(batch, layout, textX, textY);
    }

    @Override
    public void triggered() {}
    
    /** Adjusts dimensions to image width and height */
    private void adjustBoundsToImage() {
	this.setWidth(image.getWidth());
	this.setHeight(image.getHeight());
    }

}
