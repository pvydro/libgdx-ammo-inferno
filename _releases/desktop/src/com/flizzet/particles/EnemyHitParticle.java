package com.flizzet.particles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.flizzet.main.GameManager;
import com.flizzet.utils.AnimationUtils;

/**
 * Emits when an enemy is hit.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class EnemyHitParticle extends Particle {

    private Animation<TextureRegion> anim;
    
    private float stateTime = 0;
    
    /** Default instantiable constructor */
    public EnemyHitParticle() {
	anim = AnimationUtils.newAnimation((Texture) GameManager.getInstance().assetManager.get("particles/gameParticles/enemyHitParticleSheet.png"),
		4, 1, 40);
	anim.setPlayMode(PlayMode.NORMAL);
	
	this.adjustBoundsToImage(anim.getKeyFrame(0));
    }

    @Override
    public void update(float delta) {
	stateTime += delta;
	if (anim.isAnimationFinished(stateTime)) {
	    GameManager.getInstance().particleContainer.remove(this);
	}
    }
    
    @Override
    public void render(SpriteBatch batch, ShapeRenderer shapeRenderer) {
	TextureRegion currentFrame = anim.getKeyFrame(stateTime);
	
	batch.draw(currentFrame, bounds.x, bounds.y);
    }

}
