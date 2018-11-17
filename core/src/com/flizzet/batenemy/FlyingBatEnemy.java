package com.flizzet.batenemy;

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
import com.flizzet.enemies.FlyingEnemyState;
import com.flizzet.main.GameManager;
import com.flizzet.player.Player;
import com.flizzet.settings.CurrentSettings;
import com.flizzet.sound.Sounds;
import com.flizzet.utils.AnimationUtils;

/**
 * Concrete bat enemy class.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class FlyingBatEnemy extends FlyingEnemy {

	private FlyingEnemyState state = FlyingEnemyState.CHASING_PLAYER;
	private FlyingBatEnemyCollision collision = new FlyingBatEnemyCollision(
			this);
	private EnemyScoreDealer scoreDealer = new EnemyScoreDealer(this);
	private Texture headImage;
	private ShapeRenderer shapeRenderer = new ShapeRenderer();
	private Animation<TextureRegion> rightWingAnim;
	private Animation<TextureRegion> leftWingAnim;
	private TextureRegion rightWingCurrentFrame;
	private TextureRegion leftWingCurrentFrame;

	private float stateTime = 0;
	private int side = 0; // 0 = left; 1 = right
	private float leftWingX, rightWingX;
	private float wingY;

	/** Default instantiable constructor */
	public FlyingBatEnemy() {

		headImage = GameManager.getInstance().assetManager
				.get("enemies/flyingBat/batHead.png", Texture.class);
		this.setHealthBar(new FlyingBatHealthBar(this));
		this.setAi(new FlyingBatEnemyAi(this));
		adjustBoundsToImage(headImage);

		/* Building animations */
		Texture wingSheetLeft = GameManager.getInstance().assetManager
				.get("enemies/flyingBat/batWingSheetLeft.png", Texture.class);
		Texture wingSheetRight = GameManager.getInstance().assetManager
				.get("enemies/flyingBat/batWingSheetRight.png", Texture.class);
		rightWingAnim = AnimationUtils.newAnimation(wingSheetRight, 12, 1, 50);
		rightWingAnim.setPlayMode(PlayMode.LOOP);
		leftWingAnim = AnimationUtils.newAnimation(wingSheetLeft, 12, 1, 50);
		leftWingAnim.setPlayMode(PlayMode.LOOP);

		/* Play sound */
		Sounds.play(Sounds.getInstance().flyFlapSound, 1.8f);
	}

	@Override
	public void update(float delta) {
		super.update(delta);

		/* Set collision bounds */
		this.setCollisionX(this.getX());
		this.setCollisionY(this.getY());
		this.setCollisionWidth(this.getWidth());
		this.setCollisionHeight(this.getHeight());

		/* Getting current frames */
		if (getCenterX() < Player.getInstance().getCenterX()) {
			side = 1;
		} else {
			side = 0;
		}
		stateTime += delta;
		rightWingCurrentFrame = rightWingAnim.getKeyFrame(stateTime);
		leftWingCurrentFrame = leftWingAnim.getKeyFrame(stateTime);

		/* Setting wing positions */
		leftWingX = bounds.x - 3;
		rightWingX = bounds.x + bounds.width + 3
				- rightWingAnim.getKeyFrame(0).getRegionWidth();
		wingY = bounds.y - 3;

		collision.update(delta);
		indicator.update(delta);
	}

	@Override
	public void render(SpriteBatch batch) {

		super.render(batch);

		/* Draw in a different order based on the direction */
		switch (side) {
			case 0:
				batch.draw(leftWingCurrentFrame, leftWingX, wingY);
				batch.draw(headImage, bounds.x, bounds.y, bounds.width,
						bounds.height);
				batch.draw(rightWingCurrentFrame, rightWingX, wingY);
				break;
			case 1:
				batch.draw(rightWingCurrentFrame, rightWingX, wingY);
				batch.draw(headImage, bounds.x, bounds.y, bounds.width,
						bounds.height, 0, 0, (int) bounds.width,
						(int) bounds.height, true, false);
				batch.draw(leftWingCurrentFrame, leftWingX, wingY);
		}

		/* Draw collisions */
		if (CurrentSettings.getInstance().showCollisions) {
			batch.end();
			shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
			shapeRenderer.begin(ShapeType.Line);
			shapeRenderer.setColor(Color.ORANGE);
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
		this.setState(FlyingEnemyState.DEAD);
		scoreDealer.newPoint();
		GameManager.getInstance().particleFunctions.addTextParticle(getCenterX(), getCenterY(), "kill!");
	}

	public FlyingEnemyState getState() {
		return this.state;
	}
	public void setState(FlyingEnemyState newState) {
		this.state = newState;
	}

}
