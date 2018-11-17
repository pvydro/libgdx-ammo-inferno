package com.flizzet.score;

import com.flizzet.interfaces.Updatable;
import com.flizzet.main.GameManager;
import com.flizzet.player.Player;
import com.flizzet.settings.CurrentSettings;

/**
 * Collides coins with the player.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
class ScoreCollision implements Updatable {
    
    private Player player = Player.getInstance();
    private ScoreObject object;

    /** Default instantiable constructor */
    public ScoreCollision(ScoreObject object) {
	this.object = object;
    }

    @Override
    public void update(float delta) {
	if (object.getBounds().overlaps(player.getCollisionBounds())) {
	    if (object instanceof Coin) {
		Player.getInstance().getScore().addToCurrentCoins(1);
		if (CurrentSettings.getInstance().textParticles) {
		    GameManager.getInstance().particleFunctions.addTextParticle(object.getCenterX(), object.getCenterY(), "+1");
		}
	    } else if (object instanceof Bill) {
		Player.getInstance().getScore().addToTotalBills(1);
	    }
	    GameManager.getInstance().entityContainer.remove(object);
	}
    }
    

}
