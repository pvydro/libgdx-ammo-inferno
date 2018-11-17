package com.flizzet.dink;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.flizzet.entities.Entity;
import com.flizzet.main.GameManager;
import com.flizzet.utils.AnimationUtils;

/**
 * Flying robot that follows the player around and attacks.
 * Purchasable upgrade.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class Dink extends Entity {

    private Texture bodyImage;
    private Texture rotorSheet;
    private TextureRegion rotorCurrentFrame;
    private Animation<TextureRegion> rotorAnim;
    private DinkState state;
    private DinkAi ai;
    private DinkCollision collision;
    
    private float stateTime = 0;
    
    /** Default instantiable constructor */
    public Dink() {
	this.bodyImage = GameManager.getInstance().assetManager.get("upgrades/dink/body.png");
	this.adjustBoundsToImage(bodyImage);
	this.rotorSheet = GameManager.getInstance().assetManager.get("upgrades/dink/rotorSheet.png");
	this.rotorAnim = AnimationUtils.newAnimation(rotorSheet, 4, 1, 50);
	rotorAnim.setPlayMode(PlayMode.LOOP_PINGPONG);
	
	ai = new DinkAi(this);
	collision = new DinkCollision(this);
    }

    @Override
    public void update(float delta) {
	
	ai.update(delta);
	collision.update(delta);
	
	stateTime += delta;
	rotorCurrentFrame = rotorAnim.getKeyFrame(stateTime);
	
    }

    @Override
    public void render(SpriteBatch batch) {
	batch.draw(bodyImage, bounds.x, bounds.y);
	batch.draw(rotorCurrentFrame, bounds.x, bounds.y - 1);	// Draw rotor just below body
    }
    
    public void setState(DinkState newState)	{ this.state = newState; }
    
    public DinkState getState()			{ return this.state; }

}
