package com.flizzet.eyeenemy;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.flizzet.enemies.EnemyScoreDealer;
import com.flizzet.enemies.FlyingEnemy;
import com.flizzet.enemies.FlyingEnemyAi;
import com.flizzet.enemies.FlyingEnemyState;
import com.flizzet.main.GameManager;
import com.flizzet.player.Player;
import com.flizzet.settings.CurrentSettings;
import com.flizzet.utils.AnimationUtils;

/**
 * Flying eye concrete enemy.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class FlyingEyeEnemy extends FlyingEnemy {

	private Animation<TextureRegion> animation;
	private TextureRegion currentFrame;
	private FlyingEyeEnemyCollision collision = new FlyingEyeEnemyCollision(
			this);
	private EnemyScoreDealer scoreDealer = new EnemyScoreDealer(this);
	private float stateTime = 0;
	private ShapeRenderer shapeRenderer = new ShapeRenderer();

	/** Default instantiable constructor */
	public FlyingEyeEnemy() {
		animation = AnimationUtils.newAnimation(
				GameManager.getInstance().assetManager
						.get("enemies/flyingEye/flyingEye.png", Texture.class),
				8, 1, 300);
		animation.setPlayMode(PlayMode.LOOP);
		adjustBoundsToImage(animation.getKeyFrame(0));
		buildCollision();
		this.setAi(new FlyingEnemyAi(this));
		this.setHealthBar(new FlyingEyeHealthBar(this));
	}

	@Override
	public void update(float delta) {

		super.update(delta);

		/* Set up currentFrame */
		stateTime += delta;
		currentFrame = animation.getKeyFrame(stateTime, false);

		/* Collision position */
		this.setCollisionX(bounds.x + 1);
		this.setCollisionY(bounds.y + 1);

		collision.update(delta);
		
		indicator.update(delta);

	}

	@Override
	public void render(SpriteBatch batch) {

		super.render(batch);

		/*
		 * Flip the eye direction to 1: Face the platform it's chasing 2: Face
		 * the player
		 */
		if (getAi().getState() == FlyingEnemyState.CHASING_PLATFORM) {
			if (getAi().getTargetPlatform().getSide() == 1
					|| getAi().getTargetPlatform().getSide() == 2) {
				currentFrame.flip(true, false);
			}
		} else {
			if (bounds.x < Player.getInstance().getX()) {
				currentFrame.flip(true, false);
			}
		}

		batch.draw(currentFrame, bounds.x, bounds.y, bounds.width,
				bounds.height);

		/* Reset flip */
		if (currentFrame.isFlipX()) {
			currentFrame.flip(true, false);
		}

		/* Draw collisions */
		if (CurrentSettings.getInstance().showCollisions) {
			batch.end();
			shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
			shapeRenderer.begin(ShapeType.Line);
			shapeRenderer.setColor(Color.BLUE);
			shapeRenderer.rect(getCollisionBounds().x, getCollisionBounds().y,
					getCollisionBounds().width, getCollisionBounds().height);
			shapeRenderer.end();
			batch.begin();
		}
		
		indicator.render(batch);

	}

	@Override
	public void hit() {
		super.hit();

		if (CurrentSettings.getInstance().blood) {
			GameManager.getInstance().particleFunctions.addBloodParticle(
					getCenterX(), getCenterY(),
					CurrentSettings.getInstance().bloodAmount);
		}
		if (CurrentSettings.getInstance().textParticles) {
			GameManager.getInstance().particleFunctions
					.addTextParticle(getCenterX(), getCenterY(), "-1");
		}
	}

	@Override
	public void die() {
		super.die();
		this.getAi().setState(FlyingEnemyState.DEAD);
		scoreDealer.newPoint();
		GameManager.getInstance().particleFunctions.addTextParticle(getCenterX(), getCenterY(), "kill!");
	}

	/** Sets collision */
	private void buildCollision() {
		this.setCollisionWidth(animation.getKeyFrame(0).getRegionWidth() - 2);
		this.setCollisionHeight(animation.getKeyFrame(0).getRegionHeight() - 2);
	}

	public FlyingEnemyAi getAi() {
		return (FlyingEnemyAi) this.ai;
	}

}
