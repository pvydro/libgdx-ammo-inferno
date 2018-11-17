package com.flizzet.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.entities.Entity;
import com.flizzet.headsystem.Head;
import com.flizzet.main.GameManager;

/**
 * Head that appears on top of the player.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class PlayerHead extends Entity {

    private Texture image;
    private Texture[] crowns;
    private Player player;
    private float yChange = 0;
    
    private int currentCrown = 0;	// 0 = No crown; 1 = Wood; 2 = Silver; 3 = Gold
    private float crownX, crownY;
    
    /** Default instantiable constructor */
    public PlayerHead(Player player) {
	this.player = player;
	
	image = GameManager.getInstance().assetManager.get("player/head/miner.png", Texture.class);
	adjustBoundsToImage(image);
	
	crowns = new Texture[] {
		GameManager.getInstance().assetManager.get("player/head/crowns/wood.png"),
		GameManager.getInstance().assetManager.get("player/head/crowns/silver.png"),
		GameManager.getInstance().assetManager.get("player/head/crowns/gold.png")
	};
    }

    @Override
    public void update(float delta) {
	
	float headXOffset = 0;
	float crownXOffset = 0;
	/* Set head positions */
	if (player.getDirection() == 1) {
	    headXOffset = 4.5f;
	    crownXOffset = -1;
	} else {
	    headXOffset = 3.5f;
	    crownXOffset = 0;
	}
	this.setX(player.getX() + headXOffset);
	this.setY(player.getY() + 13);
	
	/* Set crown positions */
	crownX = this.getX() + (this.getWidth() / 2) - (crowns[0].getWidth() / 2) + crownXOffset;
	crownY = this.getY() + this.getHeight();
	
	/* Bounce with player walking */
	int currentFrame = player.getAnimator().getWalkFrameIndex();
	
	if (currentFrame == 0) {
	    yChange += 30 * ((0 - yChange) / 2) * delta;
	} else if (currentFrame > 1 && currentFrame < 5) {
	    yChange = -.5f;
	} else if (currentFrame > 4 && currentFrame < 8) {
	    yChange = .5f;
	} else if (currentFrame > 7 && currentFrame < 11) {
	    yChange = -.5f;
	} else if (currentFrame == 11 || currentFrame < 2) {
	    yChange = .5f;
	} else {
	    yChange += 30 * ((0 - yChange) / 2) * delta;
	}
	
    }

    @Override
    public void render(SpriteBatch batch) {
	
	/* Drawing head with flip */
	if (player.getDirection() == 1) {
	    batch.draw(image, bounds.x, bounds.y + yChange);
	} else {
	    batch.draw(image, bounds.x, bounds.y + yChange, bounds.width, bounds.height, 
		    0, 0, (int) bounds.width, (int) bounds.height, true, false);
	}
	
	/* Drawing crown */
	if (currentCrown != 0) {
	    batch.draw(crowns[currentCrown - 1], crownX, crownY);
	}
	
    }
    
    /** Set the head image */
    public void setHead(Head newHead) {
	this.image = newHead.getImage();
	adjustBoundsToImage(image);
    }

}
