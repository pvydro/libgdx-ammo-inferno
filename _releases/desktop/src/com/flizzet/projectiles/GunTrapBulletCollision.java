package com.flizzet.projectiles;

import com.badlogic.gdx.math.Rectangle;
import com.flizzet.interfaces.Updatable;
import com.flizzet.main.GameManager;
import com.flizzet.map.MapForeground;
import com.flizzet.player.Player;
import com.flizzet.settings.CurrentSettings;

/**
 * Handles collision for the gun trap bullet.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 * @see also GunTrapBullet
 */
public class GunTrapBulletCollision implements Updatable {
    
    private GunTrapBullet bullet;

    /** Default instantiable constructor */
    public GunTrapBulletCollision(GunTrapBullet bullet) {
	this.bullet = bullet;
    }

    @Override
    public void update(float delta) {
	
	Rectangle bulletBounds = bullet.getBounds();
	
	/* Hitting player */
	if (Player.getInstance().getBounds().contains(bulletBounds)) {
	    Player.getInstance().hit();
	    bullet.remove();
	    if (CurrentSettings.getInstance().blood) {
		GameManager.getInstance().particleFunctions.addBloodParticle(bullet.getCenterX(), bullet.getCenterY(), 
			CurrentSettings.getInstance().bloodAmount);
	    }
	}
	
	/* Removing on sides */
	if (bulletBounds.x < MapForeground.getInstance().getLeftX() + 11) {
	    bullet.remove();
	    if (CurrentSettings.getInstance().bulletHits) {
		GameManager.getInstance().particleFunctions.addGroundParticle(
			bullet.getCenterX(), bullet.getCenterY(), 2);
	    }
	}
	if (bulletBounds.x + bulletBounds.getWidth() > MapForeground.getInstance().getRightX()) {
	    bullet.remove();
	    if (CurrentSettings.getInstance().bulletHits) {
		GameManager.getInstance().particleFunctions.addGroundParticle(
			bullet.getCenterX(), bullet.getCenterY(), 2);
	    }
	}
	
    }

}
