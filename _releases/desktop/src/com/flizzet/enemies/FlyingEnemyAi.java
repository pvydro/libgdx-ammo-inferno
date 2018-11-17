package com.flizzet.enemies;

import java.util.ArrayList;

import com.flizzet.eyeenemy.FlyingEyeEnemy;
import com.flizzet.eyeenemy.FlyingEyeEnemySpawner;
import com.flizzet.map.Platform;
import com.flizzet.map.PlatformGenerator;
import com.flizzet.player.Player;

/**
 * Enemy AI for flying enemies navigating platforms.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class FlyingEnemyAi extends EnemyAi {
    
    private ArrayList<Platform> platforms = new ArrayList<Platform>();
    private Platform targetPlatform;
    private FlyingEnemy enemy;
    
    private FlyingEnemyState state = FlyingEnemyState.CHASING_PLATFORM;
    private int totalPlatforms = 0;
    private int totalPlatformsRemoved = 0;
    private float targetX = 0;
    private float targetY = 0;
    
    private float yVel = -3;

    /** Default instantiable constructor */
    public FlyingEnemyAi(FlyingEnemy flyingEnemy) {
	this.enemy = flyingEnemy;
	this.platforms = PlatformGenerator.getInstance().getPlatforms();
	this.targetPlatform = platforms.get(totalPlatforms);
    }

    @Override
    public void update(float delta) {
	
	/* Death and removal */
	if (state == FlyingEnemyState.DEAD) {
	    yVel += 0.2f;
	    enemy.setY(enemy.getY() - yVel);
	    if ( enemy.getY() <= -100 ) {
		remove();
	    }
	    return;
	}
	
	/* Matching target position to side of platforms or 
	 * Moving target position to player position */
	if (state != FlyingEnemyState.CHASING_PLAYER && enemy.getY() < Player.getInstance().getCenterY()) {
	    /* Move enemy up platforms */
	    state = FlyingEnemyState.CHASING_PLATFORM;		// Set state to moving towards platforms
	    if (enemy.getY() < targetPlatform.getY() - 65) {
		enemy.setY(enemy.getY() + 2);			// Move the enemy up to the platform
	    } else {
		targetNextPlatform();				// If reached, find the next platform
	    }

	    /* Decide targetX based on side */
	    if (targetPlatform.getSide() == 2 || targetPlatform.getSide() == 1) { 		// Right
		targetX = targetPlatform.getX() + targetPlatform.getWidth() - 20;
	    } else { 						// Left
		targetX = targetPlatform.getX() + 20;
	    }
	    
	    /* Move enemy down with platform speed */
	    enemy.setY(enemy.getY() + PlatformGenerator.getInstance().getSpeed());
	} else {
	    state = FlyingEnemyState.CHASING_PLAYER;		// Set state to moving towards player
	    targetX = Player.getInstance().getX();
	    targetY = Player.getInstance().getY();
	    float distanceY = 7 * ((targetY - enemy.getY()) / 2) * delta;
	    enemy.setY(enemy.getY() + distanceY);		// Move towards player
	}

	/* Easing into target position */
	float distanceX = 3 * ((targetX - enemy.getX()) / 2) * delta;
	enemy.setX(enemy.getX() + distanceX);
	
    }
    
    private void targetNextPlatform() {
	totalPlatforms++;
	if (totalPlatforms - totalPlatformsRemoved <= platforms.size() - 1) {
	    targetPlatform = platforms.get(totalPlatforms - totalPlatformsRemoved);
	}  else {
	    enemy.die();
	}
    }
    
    private void remove() {
	if (enemy instanceof FlyingEyeEnemy) {
	    FlyingEyeEnemySpawner.getInstance().removeFromEyes((FlyingEyeEnemy) enemy);
	}
	// TODO ADD OTHER INSTANCEOF STATEMENTS FOR OTHER FLYING ENEMIES
    }
    
    public FlyingEnemyState getState()				{ return this.state; }
    public int getTotalPlatforms() 				{ return this.totalPlatforms; }
    public Platform getTargetPlatform()				{ return this.targetPlatform; }
    public void setTotalPlatforms(int newTotalPlatforms) 	{ this.totalPlatforms = newTotalPlatforms; }
    public void platformRemoved()				{ this.totalPlatformsRemoved++; }
    public void setState(FlyingEnemyState newState)		{ this.state = newState; }
}
