package com.flizzet.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.flizzet.interfaces.Renderable;
import com.flizzet.interfaces.Updatable;
import com.flizzet.main.GameManager;
import com.flizzet.settings.CurrentSettings;
import com.flizzet.utils.AnimationUtils;

/**
 * Animates the player entity.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
class PlayerAnimator implements Renderable, Updatable {
    
    private Player player;
    
    private Texture playerIdleImage;
    private Texture playerWalkingSheet;
    private Texture playerJumpingSheet;
       
    private Animation<TextureRegion> walkingAnim;
    private Animation<TextureRegion> jumpingAnim;
    private TextureRegion playerCurrentFrame;
    
    private float walkingStateTime = 0;
    private float jumpingStateTime = 0;
    
    private ShapeRenderer shapeRenderer = new ShapeRenderer();

    /** Default instantiable constructor */
    public PlayerAnimator(Player player) {
	
	this.player = player;
	
	playerIdleImage = GameManager.getInstance().assetManager.get("player/body/bodyIdle.png", Texture.class);
	playerWalkingSheet = GameManager.getInstance().assetManager.get("player/body/bodyWalking.png", Texture.class);
	playerJumpingSheet = GameManager.getInstance().assetManager.get("player/body/bodyJumping.png", Texture.class);
	
	walkingAnim = AnimationUtils.newAnimation(playerWalkingSheet, 12, 1, 40);
	walkingAnim.setPlayMode(PlayMode.LOOP);
	jumpingAnim = AnimationUtils.newAnimation(playerJumpingSheet, 4, 1, 20);
	
	player.adjustBoundsToImage(playerIdleImage);
	
    }

    @Override
    public void update(float delta) {
	
	/* Update animations by delta */
	if (player.getState() == PlayerState.JUMPING) {
	    walkingStateTime = 0;
	    jumpingStateTime += delta;
	} else if (player.getState() == PlayerState.WALKING) {
	    jumpingStateTime = 0;
	    walkingStateTime += delta;
	} else if (player.getState() == PlayerState.IDLE) {
	    walkingStateTime = 0;
	    jumpingStateTime = 0;
	}
	
    }

    @Override
    public void render(SpriteBatch batch) {
	
	/* Drawing based on state */
	if (player.getState() == PlayerState.JUMPING) {
	    playerCurrentFrame = jumpingAnim.getKeyFrame(jumpingStateTime, false);
	} else if (player.getState() == PlayerState.WALKING) {
	    playerCurrentFrame = walkingAnim.getKeyFrame(walkingStateTime, false);
	} else {
	    playerCurrentFrame = walkingAnim.getKeyFrame(0, false);
	}
	
	/* Flipping based on direction */
	if (player.getDirection() == 0) {
	    playerCurrentFrame.flip(true, false);
	}

	batch.draw(playerCurrentFrame, player.getX(), player.getY());
	
	if (playerCurrentFrame.isFlipX()) {
	    playerCurrentFrame.flip(true, false);		// Reset flip
	}
	
	/* Drawing collisions */
	if (CurrentSettings.getInstance().showCollisions) {
	    batch.end();
	    shapeRenderer.begin(ShapeType.Line);
	    shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
	    shapeRenderer.setColor(.5f, .5f, .5f, 1f);
	    shapeRenderer.rect(player.getCollisionBounds().x, player.getCollisionBounds().y,
		    player.getCollisionBounds().width, player.getCollisionBounds().height);
	    shapeRenderer.end();
	    batch.begin();
	}
	
    }
    
    public Texture getIdleTexture() 	{ return playerIdleImage; }
    public int getWalkFrameIndex()	{ return walkingAnim.getKeyFrameIndex(walkingStateTime); }
}
