package com.flizzet.dink;

import java.util.Random;

import com.flizzet.interfaces.Updatable;
import com.flizzet.main.GameManager;
import com.flizzet.map.Platform;
import com.flizzet.map.PlatformGenerator;
import com.flizzet.player.Player;

/**
 * Controls the movement of the Dink upgrade.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 * @see also Dink
 */
class DinkAi implements Updatable {

    private Dink dink;
    private Player player = Player.getInstance();
    private Random random = new Random();
    
    private float targetX, targetY;
    private int movementCooldown = 20;
    private int attackCooldown = 75;
    private boolean attacking;
    
    /** Default instantiable constructor */
    public DinkAi(Dink dink) {
	this.dink = dink;
    }

    @Override
    public void update(float delta) {
	
	/* *
	 * State
	 */
	/* Movement cooldown */
	movementCooldown--;
	if (movementCooldown <= 0) {
	    movementCooldown = 20;
	    findNewPosition();
	}
	/* Attack cooldown */
	attackCooldown--;
	if (attackCooldown <= 0) {
	    attackCooldown = 75;
	    attacking = !attacking;	// Flip attacking
	    if (attacking) GameManager.getInstance().particleFunctions.addTextParticle(dink.getCenterX(), dink.getY() + 30, "!");
	}
	
	/* Setting state */
	dink.setState(attacking ? DinkState.ATTACKING : DinkState.FOLLOWING);
	
	/* Follow player along with new position
	 * Or follow enemy */
	float newX = 0;
	float newY = 0;
	boolean targetSelected = false;
	if (dink.getState() == DinkState.FOLLOWING) {
	    /* Bouncing around effect */
	    newX = player.getCenterX() + targetX;
	    newY = player.getCenterY() + 40 + targetY;
	} else if (dink.getState() == DinkState.ATTACKING) {
	    /* Set the target to the lowest possible zombie */
	    for (Platform p : PlatformGenerator.getInstance().getPlatforms()) {
		if (p.getZombieEnemy() != null && !p.getZombieEnemy().isDead() && p.getZombieEnemy().getY() < 400) {
		    targetSelected = true;
		    newX = p.getZombieEnemy().getCenterX() - (dink.getWidth() / 2);
		    newY = p.getZombieEnemy().getCenterY() - (dink.getHeight() / 2);
		}
	    }
	    if (!targetSelected) {
		newX = player.getCenterX() + targetX;
		newY = player.getCenterY() + 40 + targetY;
	    }
	}
	
	/* Ease towards newly established position */
	if (targetSelected) {
	    dink.setX(dink.getX() + (newX - dink.getX()) / 30);
	    dink.setY(dink.getY() + (newY - dink.getY()) / 30);
	} else {
	    dink.setX(dink.getX() + (newX - dink.getX()) / 15);
	    dink.setY(dink.getY() + (newY - dink.getY()) / 15);
	}
    }
    
    /** Finds a new position to move to */
    private void findNewPosition() {
	targetX = random.nextBoolean() ? random.nextInt(20) : -random.nextInt(20);
	targetY = random.nextBoolean() ? random.nextInt(20) : -random.nextInt(10);
    }

}
