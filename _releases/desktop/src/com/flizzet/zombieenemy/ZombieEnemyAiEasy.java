package com.flizzet.zombieenemy;

import java.util.Random;

import com.flizzet.enemies.EnemyAi;
import com.flizzet.map.Platform;
import com.flizzet.map.PlatformGenerator;

/**
 * Handles movement for the zombie enemy.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 * @see also ZombieEnemy
 */
class ZombieEnemyAiEasy extends EnemyAi {
    
    private ZombieEnemy zombie;
    private Platform platform;
    
    private Random random = new Random();
    
    private int side;				// 0 = left; 1 = right
    private int moveCooldown = 100;
    private int direction = random.nextInt(3);	// 0 = left; 1 = right
    private float speed = .5f;
    private float yVel = 0;
    private boolean landed = false;

    /** Default instantiable constructor */
    public ZombieEnemyAiEasy(ZombieEnemy zombie, Platform platform) {
	this.zombie = zombie;
	this.platform = platform;
	
	/* Intially position zombie */
	zombie.setX(platform.getCenterX());
	
	/* Make sure the zombie is on top of the platform */
	zombie.setY(platform.getY() + platform.getHeight() - 1);
    }

    @Override
    public void update(float delta) {
	
	/* Fall on platform */
	if (zombie.getState() == ZombieState.DEAD) {
	    if (zombie.getY() > (platform.getY() + platform.getHeight() - 1)) {
		if (!landed) {
		    yVel += 0.5f;
		    zombie.setY(zombie.getY() - yVel);
		} else {
		    zombie.setY(zombie.getY() + PlatformGenerator.getInstance().getSpeed());
		}
	    } else {
		landed = true;
		yVel = 0;
		zombie.setY(platform.getY() + platform.getHeight() - 1);
	    }
	    
	    speed += 10 * ((0 - speed) / 2) * delta;
	} else {
	    zombie.setY(platform.getY() + platform.getHeight() - 1);
	}

	
	/* Prevent from falling */
	if (side == 0) {
	    if (zombie.getX() < platform.getX() + 11) {
		move(1);
	    }
	    if (zombie.getX() > (platform.getX() + platform.getWidth() - 20 - zombie.getCollisionBounds().getWidth())) {
		move(0);
	    }
	} else if (side == 1) {
	    if (zombie.getX() < platform.getX()) {
		move(1);
	    }
	    if (zombie.getX() > platform.getX() + (platform.getWidth() - 20)) {
		move(0);
	    }
	} else if (side == 2) {
	    if (zombie.getX() < platform.getX()) {
		move(1);
	    }
	    if ((zombie.getX() + zombie.getWidth()) > platform.getX() + platform.getWidth()) {
		move(0);
	    }
	}
	
	/* Move around move around
	 * Nina on me lay you down */
	if (zombie.getState() == ZombieState.DEAD) {
	    if (direction == 0 || direction == 1) {
		zombie.setX(zombie.getX() - speed);
	    } else {
		zombie.setX(zombie.getX() + speed);
	    }
	} else {
	    /* Toggle move */
	    moveCooldown--;
	    if (moveCooldown <= 0) {
		move();
	    }
	    if (direction == 0) { 		// Left
		zombie.setX(zombie.getX() - speed);
		zombie.setState(ZombieState.WALKING);
	    } else if (direction == 1) { 	// Right
		zombie.setX(zombie.getX() + speed);
		zombie.setState(ZombieState.WALKING);
	    } else {				// Standing still
		zombie.setState(ZombieState.IDLE);
	    }
	}
	
    }
    
    /** Alters direction randomly */
    private void move() {
	moveCooldown = random.nextInt(100);
	direction = random.nextInt(3);
    }
    
    /** Alters direction specifically */
    private void move(int direction) {
	moveCooldown = random.nextInt(100);
	this.direction = direction;
    }
    
    /** Throws zombie in the air */
    public void jump(float amount) {
	yVel = 0;
	yVel += amount;
    }
    
    public void setSpeed(float newSpeed)	{ this.speed = newSpeed; }
    public void setSide(int newSide)		{ this.side = newSide; }
    public int getDirection()			{ return this.direction; }

}
