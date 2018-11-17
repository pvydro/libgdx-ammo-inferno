package com.flizzet.player;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.flizzet.camera.MainCamera;
import com.flizzet.ingamegui.InGameGui;
import com.flizzet.ingamegui.PlatformIndicator;
import com.flizzet.interfaces.Updatable;
import com.flizzet.main.GameManager;
import com.flizzet.map.Platform;
import com.flizzet.settings.ControlType;
import com.flizzet.settings.CurrentSettings;
import com.flizzet.upgrades.DoubleJumpUpgrade;
import com.flizzet.upgradesystem.Upgrades;

/**
 * Controls player entity.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class PlayerController implements Updatable {

	private Player player;

	private float xVelocity;
	private float topXVelocity;

	private float yVelocity;
	private float topYVelocity;
	private float jumpVel;

	private boolean pushStarted;
	private boolean dead;
	private boolean jumping;
	private int jumpCooldown = 0;
	private int totalJumps = 0;
	private boolean walking;

	private Platform targetPlatform;

	private boolean bouncing = false;
	private boolean bouncingDown = false;
	private boolean colliding = false;

	private boolean rightPushed = false;
	private boolean leftPushed = false;
	private boolean upPushed = false;

	private int groundParticleCooldown = 5;

	/** Default instantiable constructor */
	public PlayerController(Player player) {
		this.player = player;

		this.player.setX(GameManager.getInstance().getCamera().getCenterX()
				- (player.getWidth() / 2));

		this.topXVelocity = 3;
		this.topYVelocity = -5;
		this.jumpVel = 12;
	}

	@Override
	public void update(float delta) {

		/* Block controls if dead or dying */
		if (player.getState() == PlayerState.DYING) {
			yVelocity += (topYVelocity - yVelocity) / 20;
			slowDown(delta);
			player.setX(player.getX() + xVelocity);
			player.setY(player.getY() + yVelocity);
			return;
		}

		/* Glue this kid to the ground */
		if (targetPlatform != null && !jumping) {
			player.setY(targetPlatform.getY() + targetPlatform.getHeight());
		}

		/* Reset total jumps when necessary */
		if (targetPlatform != null
				&& player.getY() == targetPlatform.getY()
						+ targetPlatform.getHeight()
				&& !jumping && jumpCooldown <= 0) {
			totalJumps = 0;
		}

		/*
		 * * Horizontal movement
		 */
		/* Get accelerometer amount */
		float accelX = Gdx.input.getAccelerometerX();
		/* Checking key presses for computers */
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)
				|| Gdx.input.isKeyPressed(Input.Keys.D)) {
			rightPushed = true;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)
				|| Gdx.input.isKeyPressed(Input.Keys.A)) {
			leftPushed = true;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.UP)
				|| Gdx.input.isKeyPressed(Input.Keys.W)) {
			upPushed = true;
		}
		/* Tilt controls */
		if (CurrentSettings.getInstance().controls == ControlType.TILT) {
			if (accelX > .5f) {
				leftPushed = true;
				rightPushed = false;
			} else if (accelX < -.5f) {
				rightPushed = true;
				leftPushed = false;
			}

			if (Gdx.input.isTouched()) {
				upPushed = true;
			}
		}
		/* TouchpadJoystick controls */
		if (CurrentSettings.getInstance().controls == ControlType.CONSOLE) {
			if (InGameGui.getInstance().getTouchpad().getTouchpad()
					.getKnobPercentX() > 0) {
				rightPushed = true;
			} else if (InGameGui.getInstance().getTouchpad().getTouchpad()
					.getKnobPercentX() < 0) {
				leftPushed = true;
			}
		}

		/* Increasing xVelocity */
		if (pushStarted) {
			if (rightPushed && leftPushed) {
				slowDown(delta);
			} else if (rightPushed) {
				moveRight(delta);
			} else if (leftPushed) {
				moveLeft(delta);
			} else {
				slowDown(delta);
			}
		} else {
			if (rightPushed && leftPushed) {
				slowDown(delta);
			} else if (rightPushed) {
				moveRight(delta);
			} else if (leftPushed) {
				moveLeft(delta);
			} else {
				slowDown(delta);
			}
		}

		/*
		 * * Gravity / Verical Movement
		 */
		/* Falling if not colliding */
		if (!colliding) {
			yVelocity += (topYVelocity - yVelocity) / 20;
			jumping = true;
		}

		/*
		 * * Jumping
		 */
		/* Preventing jumping */
		if (jumpCooldown > 0)
			jumpCooldown--;
		/* Jumping if jump button is pressed */
		if (upPushed && colliding && !jumping && jumpCooldown <= 0 && Player
				.getInstance().getY() < MainCamera.getInstance().getHeight()) {
			yVelocity = 0;
			jump();
		}
		/* Double jumping */
		if (upPushed && totalJumps < 1
				&& Upgrades.getInstance().isEquipped(DoubleJumpUpgrade.class)) {
			if (yVelocity < 0) {
				jump();
			}
		}

		/*
		 * * Player State
		 */
		if (dead) {
			player.setState(PlayerState.DEAD);
		} else if (jumping) {
			player.setState(PlayerState.JUMPING);
		} else if (walking) {
			player.setState(PlayerState.WALKING);
		} else {
			player.setState(PlayerState.IDLE);
		}

		/* Updating speed */
		player.setX(player.getX() + xVelocity);
		player.setY(player.getY() + yVelocity);

		/* Ground particle cooldowns */
		if (groundParticleCooldown > 0) {
			groundParticleCooldown--;
		}

		/* Reset pushes */
		rightPushed = false;
		leftPushed = false;
		upPushed = false;

	}

	private void jump() {
		yVelocity += jumpVel;
		addGroundParticle();
		totalJumps++;
	}

	private void moveRight(float delta) {
		walking = true;
		player.setDirection(1);
		xVelocity += 30 * ((topXVelocity - xVelocity) / 2) * delta;
		addGroundParticle();
		if (CurrentSettings.getInstance().controls == ControlType.CONSOLE) {
			xVelocity += ((topXVelocity * InGameGui.getInstance().getTouchpad()
					.getTouchpad().getKnobPercentX()) - xVelocity) / 3;
		}
	}

	private void moveLeft(float delta) {
		walking = true;
		player.setDirection(0);
		xVelocity += 30 * ((-topXVelocity - xVelocity) / 2) * delta;
		addGroundParticle();
		if (CurrentSettings.getInstance().controls == ControlType.CONSOLE) {
			xVelocity += ((topXVelocity * InGameGui.getInstance().getTouchpad()
					.getTouchpad().getKnobPercentX()) - xVelocity) / 3;
		}
	}

	public void bounceDown() {
		if (!bouncingDown) {
			yVelocity = 0;
			bouncingDown = true;
		}
	}

	public void bounceLeft(float amt) {
		if (!bouncing) {
			xVelocity = 0;
			bouncing = true;
		}
		xVelocity -= amt;
	}

	public void bounceRight(float amt) {
		if (!bouncing) {
			xVelocity = 0;
			bouncing = true;
		}
		xVelocity += amt;
	}

	private void slowDown(float delta) {
		walking = false;
		xVelocity += 30 * ((0 - xVelocity) / 2) * delta;
	}

	/**
	 * When landing, the player will sink within the floor with his speed This
	 * function fixes the sink No plumber
	 */
	public void unsinkFromFloor(Platform platform) {
		bouncingDown = false;
		bouncing = false;
		setTargetPlatform(platform);
		float floorPoint = (platform.getY() + platform.getHeight());
		if (player.getY() < floorPoint) {
			float difference = floorPoint - player.getY();
			player.setY(player.getY() + difference);
			player.getHead().setY(player.getHead().getY() + difference);
		}

	}

	/** Adds a ground particle to the floor */
	public void addGroundParticle() {
		if (!jumping && groundParticleCooldown <= 0) {
			if (CurrentSettings.getInstance().groundParticles) {
				GameManager.getInstance().particleFunctions
						.addGroundParticle(player.getCenterX(), player.getY(),
								new Random().nextInt(CurrentSettings
										.getInstance().groundParticleAmount)
										+ 1);
			}
			groundParticleCooldown = CurrentSettings
					.getInstance().groundParticleTotal;
		}
	}

	/** Respawn */
	public void reset() {
		this.dead = false;
		this.jumping = false;
		this.walking = false;
		this.xVelocity = 0;
		this.yVelocity = 0;
		this.targetPlatform = null;
	}

	public float getYVel() {
		return this.yVelocity;
	}
	public float getXVel() {
		return this.xVelocity;
	}
	public boolean isColliding() {
		return this.colliding;
	}
	public boolean isJumping() {
		return this.jumping;
	}
	public Platform getTargetPlatform() {
		return this.targetPlatform;
	}

	public void setRightPushed(boolean isRightPushed) {
		this.rightPushed = isRightPushed;
	}
	public void setLeftPushed(boolean isLeftPushed) {
		this.leftPushed = isLeftPushed;
	}
	public void setUpPushed(boolean isUpPushed) {
		this.upPushed = isUpPushed;
	}
	public void setXVel(float newXVelocity) {
		this.xVelocity = newXVelocity;
	}
	public void setYVel(float newYVelocity) {
		this.yVelocity = newYVelocity;
	}
	public void setColliding(boolean isColliding) {
		this.colliding = isColliding;
	}
	public void setDead(boolean isDead) {
		this.dead = isDead;
	}
	public void setJumping(boolean isJumping) {
		this.jumping = isJumping;
	}
	public void setJumpCooldown(int newJumpCooldown) {
		this.jumpCooldown = newJumpCooldown;
	}
	public void setTargetPlatform(Platform newTargetPlatform) {
		this.targetPlatform = newTargetPlatform;
		if (!newTargetPlatform.isHit()) {
			newTargetPlatform.setHit(true);
			player.getScore().addToCurrentPlatforms(1);
			PlatformIndicator.getInstance()
					.setTargetPlatform(newTargetPlatform);
		}
	}

}
