package com.flizzet.guicomponents;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.flizzet.main.GameManager;
import com.flizzet.utils.AnimationUtils;

/**
 * "FlizLuse" logo to appear on splash screen.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class SplashTitleComponent extends GuiComponent {
    
    private Texture titleImage;
    private Animation<TextureRegion> titleAnimation;
    private TextureRegion titleCurrentFrame;
    
    private float targetX;
    private float targetY;
    private float logoX;
    private float logoY;
    private float stateTime;	// Used for animation with delta VERY IMPORTANT
    private float overlayDarkness;
    private int animStartCooldown = 50;
    
    

    /** Default instantiable constructor */
    public SplashTitleComponent(float x, float y) {
	super(x, y);
	
	titleImage = GameManager.getInstance().assetManager.get("gui/splashScreen/flizLuseLogo.png", Texture.class);
	titleAnimation = AnimationUtils.newAnimation(titleImage, 7, 4, 20);
	adjustBoundsToImage();
	
	overlayDarkness = 0f;
	stateTime = 0f;
    }

    @Override
    public void update(float delta) {
	
	/* Animation starting with delay */
	animStartCooldown--;
	if (animStartCooldown <= 0) {
	    stateTime += delta;								// Start animation
	}
	
	/* Movement */
	bounds.x += (20 * (targetX - bounds.x) / 2) * delta;
	bounds.y += (20 * (targetY - bounds.y) / 2) * delta;
	
	/* Positioning FlizLuse logo */
	logoX = bounds.x + (bounds.width / 2) - (titleImage.getWidth() / 14);		// Use /14 because the picture is 7 frames long and 14 is equivalent of one half a frame
	logoY = bounds.y + (bounds.height / 2) - (titleImage.getHeight() / 8);
	
	/* Changing darkness of batch */
	overlayDarkness += 10 * ((1 - overlayDarkness) / 5) * delta;
	
    }

    @Override
    public void render(SpriteBatch batch) {
	
	/* Locals */
	titleCurrentFrame = titleAnimation.getKeyFrame(stateTime, false);		// Get the key frame of the animation
	Color c = batch.getColor();
	
	Gdx.gl.glEnable(GL20.GL_BLEND);
	Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
	
	batch.setColor(new Color(c.r, c.g, c.b, overlayDarkness));

	batch.draw(titleCurrentFrame, logoX, logoY);
	
	batch.setColor(c.r, c.g, c.b, 1);
	
    }

    @Override
    public void triggered() {
	
    }
    
    public void adjustBoundsToImage() {
	this.setWidth(titleAnimation.getKeyFrame(0, false).getRegionWidth());
	this.setHeight(titleAnimation.getKeyFrame(0, false).getRegionHeight());
    }
    
    public void setTargetX(float newTargetX) {
	this.targetX = newTargetX;
    }
    
    public void setTargetY(float newTargetY) {
	this.targetY = newTargetY;
    }

}
