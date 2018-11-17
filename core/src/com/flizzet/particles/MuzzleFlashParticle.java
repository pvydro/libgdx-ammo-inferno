package com.flizzet.particles;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.flizzet.main.GameManager;

/**
 * Particle that comes from the front of the weapon upon firing.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class MuzzleFlashParticle extends Particle {

	private Animation<TextureRegion> animation;
	private float rotation;
	private float stateTime;

	private TextureRegion currentFrame;

	/** Default instantiable constructor */
	public MuzzleFlashParticle() {

	}

	@Override
	public void update(float delta) {

		super.update(delta);

		stateTime += delta;

		/* Removal */
		if (animation.getKeyFrameIndex(stateTime) == 3) {
			GameManager.getInstance().particleContainer.remove(this);
		}

	}

	@Override
	public void render(SpriteBatch batch, ShapeRenderer shapeRenderer) {

		currentFrame = animation.getKeyFrame(stateTime, false);
		float originX = 0;// currentFrame.getRegionWidth() / 2;
		float originY = 0;// currentFrame.getRegionHeight() / 2;

		batch.draw(currentFrame, bounds.x, bounds.y, originX, originY,
				bounds.width, bounds.height, 1, 1, rotation);

	}

	/** Set rotation */
	public void setRotation(float rotation) {
		this.rotation = rotation;
	}

	/**
	 * Sets animation for random muzzles
	 * 
	 * @see ParticleFunctions
	 */
	public void setAnimation(Animation<TextureRegion> newAnimation) {
		this.animation = newAnimation;
		adjustBoundsToImage(animation.getKeyFrame(0));
	}

}
