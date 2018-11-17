package com.flizzet.revivemenu;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.camera.MainCamera;
import com.flizzet.guicomponents.GuiComponent;
import com.flizzet.main.GameManager;
import com.flizzet.particles.ScreenDarkener;
import com.flizzet.player.Player;
import com.flizzet.settings.CurrentSettings;
import com.flizzet.utils.FontUtils;
import com.flizzet.utils.StopWatch;

/**
 * Menu to give the player the option to revive or not.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class ReviveMenu extends GuiComponent {

	/* Misc */
	private ScreenDarkener screenDarkener = new ScreenDarkener(0.5f);
	private Texture skullImage;
	private Random random = new Random();
	private StopWatch stopWatch = new StopWatch();

	/* Revive text */
	private String reviveText = "continue?";
	private BitmapFont reviveFont = FontUtils.UPHEAVAL_120;
	private GlyphLayout reviveLayout = new GlyphLayout(reviveFont, reviveText);
	private float textX, textY;

	/* Skull */
	private int jitterPositionAmt = 2;
	private float skullX, skullY;
	private float jitterX, jitterY;

	/* Revive button */
	private ReviveButton reviveButton = new ReviveButton(this);
	private PlayVideoButton videoButton = new PlayVideoButton();

	/** Default instantiable constructor */
	public ReviveMenu() {
		super(0, 0);

		skullImage = GameManager.getInstance().assetManager
				.get("gui/reviveMenu/skull.png");

		reviveFont.setUseIntegerPositions(false);

		stopWatch.start();
		stopWatch.startCountdown(3);
	}

	@Override
	public void update(float delta) {

		/* Position skull */
		skullX = MainCamera.getInstance().getCenterX()
				- ((skullImage.getWidth()) / 2);
		skullY = MainCamera.getInstance().getCenterY() + 50;

		float newXJitter = random.nextBoolean()
				? random.nextInt(jitterPositionAmt)
				: -random.nextInt(jitterPositionAmt);
		jitterX += (newXJitter - jitterX) / 4;
		float newYJitter = random.nextBoolean()
				? random.nextInt(jitterPositionAmt)
				: -random.nextInt(jitterPositionAmt);
		jitterY += (newYJitter - jitterY) / 4;

		/* Position revival text */
		reviveText = "continue? " + stopWatch.getElapsedCountSecs();
		reviveLayout.setText(reviveFont, reviveText);
		textX = MainCamera.getInstance().getCenterX()
				- (reviveLayout.width / 2);
		textY = MainCamera.getInstance().getCenterY() + 40;

		/* Countdown complete */
		if (stopWatch.isCountdownComplete()) {
			triggered();
		}
		/* Back button */
		if (Gdx.input.isKeyJustPressed(Keys.BACK)
				|| Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			triggered();
		}

		/* Position revive button */
		if (CurrentSettings.getInstance().adsPlayable) {
			reviveButton.setX(MainCamera.getInstance().getCenterX()
					- reviveButton.getWidth() - 2);
		} else {
			reviveButton.setX(MainCamera.getInstance().getCenterX()
					- (reviveButton.getWidth() / 2));
		}
		reviveButton.setY(MainCamera.getInstance().getCenterY()
				- (reviveButton.getHeight() / 2));
		reviveButton.update(delta);
		/* Position play video button */
		if (Player.getInstance().getScore().getTotalBills() < 15) {
			videoButton.setX(MainCamera.getInstance().getCenterX()
					- (videoButton.getWidth() / 2));
		} else {
			videoButton.setX(MainCamera.getInstance().getCenterX() + 2);
		}
		videoButton.setY(MainCamera.getInstance().getCenterY()
				- (videoButton.getHeight() / 2));
		videoButton.update(delta);

	}

	@Override
	public void render(SpriteBatch batch) {
		/* Draw screen darkener */
		screenDarkener.render(batch);

		/* Draw skull image */
		batch.draw(skullImage, skullX + jitterX, skullY + jitterY);

		/* Draw revive text */
		reviveFont.draw(batch, reviveText, textX, textY);

		/* Draw revive button */
		if (Player.getInstance().getScore().getTotalBills() >= 15)
			reviveButton.render(batch);
		if (CurrentSettings.getInstance().adsPlayable)
			videoButton.render(batch);
	}

	@Override
	public void triggered() {
		GameManager.getInstance().guiContainer.removeFromGui(this);
	}

	/** Stops the countdown currently in progress */
	public void stopCountdown() {
		stopWatch.stop();
	}

	public ReviveButton getReviveButton() {
		return this.reviveButton;
	}

}
