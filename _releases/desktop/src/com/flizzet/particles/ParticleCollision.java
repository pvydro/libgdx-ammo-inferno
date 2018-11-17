package com.flizzet.particles;

import java.util.ArrayList;

import com.flizzet.interfaces.Updatable;
import com.flizzet.map.MapForeground;
import com.flizzet.map.Platform;
import com.flizzet.map.PlatformGenerator;

/**
 * Abstract collision class for concrete particle collisions.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
class ParticleCollision implements Updatable {

    private Particle particle;
    private ArrayList<Platform> platforms;
    
    /** Default instantiable constructor */
    public ParticleCollision(Particle particle) {
	this.particle = particle;
	this.platforms = PlatformGenerator.getInstance().getPlatforms();
    }

    @Override
    public void update(float delta) {
	/* Stopping on platforms */
	for (Platform p : platforms) {
	    if (particle.getY() > p.getY() + p.getHeight() - 5) {
		if (particle.getBounds().overlaps(p.getBounds())) {
		    particle.bounce(p, true, false);
		}
	    }
	}
	
	/* Stopping on walls */
	if (particle.getBounds().getX() < MapForeground.getInstance().getLeftX() + 11) {
	    particle.bounce(false, true);
	}
	if (particle.getBounds().getX() > MapForeground.getInstance().getRightX()) {
	    particle.bounce(false, true);
	}
	
    }
    
    

}
