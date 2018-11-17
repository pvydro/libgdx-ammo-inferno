package com.flizzet.particles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.flizzet.main.GameManager;
import com.flizzet.utils.AnimationUtils;

/**
 * Emits when an enemy dies.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class EnemyDeathParticle extends Particle {
    
    private Animation<TextureRegion> anim;
    private float stateTime = 0;

    /** Default instantiable constructor */
    public EnemyDeathParticle() {
	anim = AnimationUtils.newAnimation(GameManager.getInstance().assetManager.get("particles/gameParticles/enemyDeathSheet.png", Texture.class),
		5, 1, 50);
	this.adjustBoundsToImage(anim.getKeyFrame(0));
    }
    
    @Override
    public void update(float delta) {
	super.update(delta);
	
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
