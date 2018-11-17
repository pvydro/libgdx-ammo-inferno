package com.flizzet.player;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.math.Rectangle;
import com.flizzet.camera.MainCamera;
import com.flizzet.interfaces.Updatable;
import com.flizzet.main.GameManager;
import com.flizzet.map.Lava;
import com.flizzet.map.MapForeground;
import com.flizzet.map.Platform;
import com.flizzet.map.PlatformGenerator;
import com.flizzet.settings.CurrentSettings;
import com.flizzet.sound.Sounds;
import com.flizzet.tutorialmap.TutorialMap;
import com.flizzet.upgrades.LavaBootsUpgrade;
import com.flizzet.upgradesystem.Upgrades;

/**
 * Handles player colliding with platforms etc.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
class PlayerCollision implements Updatable {
    
    private Player player;
    private Rectangle playerBounds;
    private ArrayList<Platform> platforms;
    private Random random = new Random();
    
    private ArrayList<Platform> tutorialPlatforms = new ArrayList<Platform>();

    /** Default instantiable constructor */
    public PlayerCollision(Player player) {
	this.player = player;
	this.tutorialPlatforms.add(TutorialMap.getInstance().getFloor());
	this.tutorialPlatforms.add(TutorialMap.getInstance().getPlatform());
    }

    @Override
    public void update(float delta) {
	
	/* Decide which platforms to use */
	if (GameManager.getInstance().stateManager.getCurrentState()
		== GameManager.getInstance().stateId_play) {
	    this.platforms = PlatformGenerator.getInstance().getPlatforms();
	} else {
	    this.platforms = tutorialPlatforms;
	}
	
	/* Lava collision */
	if (player.getCollisionBounds().overlaps(Lava.getInstance().getBounds())) {
	    if (player.getState() != PlayerState.DEAD) {
		if (player.getState() != PlayerState.DYING && Upgrades.getInstance().isEquipped(LavaBootsUpgrade.class)) {
		    player.getController().setYVel(10);
		} else {
		    player.die();
		}
	    }
	}
	
	/* Block collision if dead or dying */
	if (player.getState() == PlayerState.DYING || player.getState() == PlayerState.DEAD) {
	    return;
	}
	
	boolean colliding = false;
	
	playerBounds = player.getBounds();
	
	/* Stopping on platforms */
	for (Platform p : platforms) {
	    if (playerBounds.overlaps(p.getBounds())) {
		if (player.getY() < p.getY() + (p.getHeight() * (0.75f))) {
		    colliding = false;
		} else {
		    colliding = true;
		    stopPlayer(p);
		}
	    }
	    
	    player.getController().setColliding(colliding);
	}
	
	/* Stopping on sides */
	if (player.getCollisionBounds().x < MapForeground.getInstance().getLeftX() + 11) {
	    player.getController().bounceRight(3);
	}
	if (player.getCollisionBounds().x + player.getCollisionBounds().getWidth() > MapForeground.getInstance().getRightX()) {
	    player.getController().bounceLeft(3);
	}
	
	/* Stopping on "ceiling" */
	if (player.getCollisionBounds().y > MainCamera.getInstance().getHeight()) {
	    if (platforms.get(0).getY() < MainCamera.getInstance().getHeight() - player.getCollisionBounds().height) {
		player.getController().bounceDown();
	    }
	}
	
    }
    
    /** Stops the player upon landing */
    private void stopPlayer(Platform platform) {
	if (player.getController().isJumping()) {
	    player.getController().setJumpCooldown(5);
	    if (player.getY() > platform.getY() + platform.getHeight() - 1 && CurrentSettings.getInstance().playerLand) {
		GameManager.getInstance().particleFunctions.addPlayerLandParticle(player.getCenterX(), 
			platform.getY() + platform.getHeight());
		Sounds.play(Sounds.getInstance().playerLandSound, 1.0f, 0.5f + random.nextFloat());
	    }
	}
	player.getController().setJumping(false);
	player.getController().unsinkFromFloor(platform);
	
    }
    
}
