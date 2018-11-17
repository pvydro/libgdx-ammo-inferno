package com.flizzet.zombieenemy;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.flizzet.interfaces.Renderable;
import com.flizzet.main.GameManager;
import com.flizzet.settings.CurrentSettings;
import com.flizzet.utils.AnimationUtils;

/**
 * Animates the zombie enemy.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 * @see also ZombieEnemy
 */
class ZombieEnemyAnimator implements Renderable {

    private ZombieEnemy zombie;
    private Animation<TextureRegion> walkingAnim;
    private Animation<TextureRegion> deathAnim;
    private TextureRegion currentFrame;
    private ShapeRenderer shapeRenderer = new ShapeRenderer();
    private float walkingStateTime = 0;
    private float deathStateTime = 0;
    private int lastDirection = 0;
    
    /** Default instantiable constructor */
    public ZombieEnemyAnimator(ZombieEnemy zombie) {
	this.zombie = zombie;
	
	Texture walkingSheet = GameManager.getInstance().assetManager.get("enemies/zombie/body/bodyWalking.png", Texture.class);
	Texture deathSheet = GameManager.getInstance().assetManager.get("enemies/zombie/body/bodyDying.png", Texture.class);
	this.walkingAnim = AnimationUtils.newAnimation(walkingSheet, 12, 1, 60);
	this.walkingAnim.setPlayMode(PlayMode.LOOP);
	this.deathAnim = AnimationUtils.newAnimation(deathSheet, 4, 1, 60);
	this.deathAnim.setPlayMode(PlayMode.NORMAL);
	zombie.setWidth(this.walkingAnim.getKeyFrame(0).getRegionWidth());
	zombie.setHeight(this.walkingAnim.getKeyFrame(0).getRegionWidth());
	
    }
    
    public void update(float delta) {
	if (zombie.getState() == ZombieState.DEAD) {
	    deathStateTime += delta;
	}
	
	walkingStateTime += delta;
    }

    @Override
    public void render(SpriteBatch batch) {
	
	if (zombie.getAi().getDirection() == 1 || zombie.getAi().getDirection() == 0) {
	    lastDirection = zombie.getAi().getDirection();
	}
	
	/* Get either walk or idle animation */
	if (zombie.getState() == ZombieState.DEAD) {
	    currentFrame = deathAnim.getKeyFrame(deathStateTime);
	} else if (zombie.getState() == ZombieState.IDLE) {
	    currentFrame = walkingAnim.getKeyFrame(0);
	} else if (zombie.getState() == ZombieState.WALKING) {
	    currentFrame = walkingAnim.getKeyFrame(walkingStateTime);
	}
	
	/* Flip */
	if (lastDirection == 0) {
	    currentFrame.flip(true, false);
	}
	
	/* Draw currentframe */
	batch.draw(currentFrame, zombie.getX(), zombie.getY(), zombie.getWidth(), zombie.getHeight());
	
	/* Drawing collision */
	if (CurrentSettings.getInstance().showCollisions) {
	    batch.end();
	    shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
	    shapeRenderer.begin(ShapeType.Line);
	    shapeRenderer.setColor(Color.GREEN);
	    shapeRenderer.rect(zombie.getCollisionBounds().x, zombie.getCollisionBounds().y,
		    zombie.getCollisionBounds().width, zombie.getCollisionBounds().height);
	    shapeRenderer.end();
	    batch.begin();
	}

	/* Reset flip */
	if (currentFrame.isFlipX()) {
	    currentFrame.flip(true, false);
	}
	
	
	
    }
    
    public int getWalkFrameIndex()	{ return walkingAnim.getKeyFrameIndex(walkingStateTime); }

}
