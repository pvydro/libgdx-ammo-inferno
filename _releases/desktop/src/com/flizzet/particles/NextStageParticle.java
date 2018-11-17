package com.flizzet.particles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.flizzet.camera.MainCamera;
import com.flizzet.main.GameManager;

/**
 * Next Stage Notification.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class NextStageParticle extends Particle {

    private Texture textImage;
    private Texture bgImage;
    
    private float alpha = 0.8f;
    private float textX, textY;
    private float bgX, bgY;
    private int cooldown = 100;
    
    /** Default instantiable constructor */
    public NextStageParticle() {
	
	this.textImage = GameManager.getInstance().assetManager.get("gui/inGame/nextStageNoti.png");
	this.bgImage = GameManager.getInstance().assetManager.get("gui/inGame/nextStageBg.png");
	
	/* Set initial positions */
	textX = 1000;
	textY = MainCamera.getInstance().getCenterY() - (textImage.getHeight() / 2);
	bgX = -1000 - bgImage.getWidth();
	bgY = MainCamera.getInstance().getCenterY() - (bgImage.getHeight() / 2);
    }

    @Override
    public void update(float delta) {
	
	/* Changing state */
	if (cooldown > 0) cooldown--;
	if (cooldown <= 0) {
	    alpha -= 0.05f;
	}
	
	/* Removal */
	if (alpha <= 0) {
	    GameManager.getInstance().particleContainer.remove(this);
	}
	
	/* Ease */
	float centerTextX = MainCamera.getInstance().getCenterX() - (textImage.getWidth() / 2);
	float centerBgX = MainCamera.getInstance().getCenterX() - (bgImage.getWidth() / 2);
	textX += (centerTextX - textX) / 3;
	bgX += (centerBgX - bgX) / 3;
	
    }

    @Override
    public void render(SpriteBatch batch, ShapeRenderer shapeRenderer) {
	Color tmp = batch.getColor();
	batch.setColor(new Color(tmp.r, tmp.g, tmp.b, alpha));
	batch.draw(bgImage, bgX, bgY);
	batch.draw(textImage, textX, textY);
	batch.setColor(tmp);
    }
}
