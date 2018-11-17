package com.flizzet.ingamegui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.camera.MainCamera;
import com.flizzet.guicomponents.GuiComponent;
import com.flizzet.main.GameManager;
import com.flizzet.player.Player;
import com.flizzet.utils.FontUtils;

/**
 * Draws the players score in the InGameGui.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 * @see also InGameGui
 */
public class ScoreCounter extends GuiComponent {

	private Texture coinIcon;
	private Texture platformIcon;
	private GlyphLayout layout = new GlyphLayout();
	private PauseButton pauseButton = new PauseButton();

	private float coinX, coinY;
	private float coinWidth, coinHeight;
	private float platformX, platformY;
	private float platformWidth, platformHeight;
	private static final int MARGIN = 10;

	private int iconScale = 2;

	/** Default instantiable constructor */
	public ScoreCounter() {
		super(0, 0);
		this.coinIcon = GameManager.getInstance().assetManager
				.get("gui/icons/coinIcon.png", Texture.class);
		this.platformIcon = GameManager.getInstance().assetManager
				.get("gui/icons/platformIconLower.png", Texture.class);
		this.coinWidth = coinIcon.getWidth() * iconScale;
		this.coinHeight = coinIcon.getWidth() * iconScale;
		this.platformWidth = platformIcon.getWidth() * iconScale;
		this.platformHeight = platformIcon.getHeight() * iconScale;
		this.pauseButton.setWidth(pauseButton.getWidth() * iconScale);
		this.pauseButton.setHeight(pauseButton.getHeight() * iconScale);
	}

	@Override
	public void update(float delta) {
		/* Coin icon positioning */
		coinX = MainCamera.getInstance().getWidth() - MARGIN - coinWidth;
		coinY = MainCamera.getInstance().getHeight() - MARGIN - coinHeight;

		/* Platform icon positioning */
		platformX = MARGIN;
		platformY = MainCamera.getInstance().getHeight() - MARGIN
				- platformHeight;

		/* Pause button positioning */
		pauseButton
				.setX(coinX + (coinWidth / 2) - (pauseButton.getWidth() / 2));
		pauseButton.setY(coinY - pauseButton.getHeight() - 2);
		pauseButton.update(delta);
	}

	@Override
	public void render(SpriteBatch batch) {
		/* Stabilize */
		float shakeXOffset = (MainCamera.getInstance().getCamera().position.x
				- MainCamera.getInstance().getTargetX());
		float shakeYOffset = (MainCamera.getInstance().getCamera().position.y
				- MainCamera.getInstance().getTargetY());
		float textSpacing = 3;

		/* Draw icons */
		batch.draw(coinIcon, coinX + shakeXOffset, coinY + shakeYOffset,
				coinWidth, coinHeight);
		batch.draw(platformIcon, platformX + shakeXOffset,
				platformY + shakeYOffset, platformWidth, platformHeight);

		/* Draw numbers */
		/* Platforms */
		BitmapFont font = FontUtils.UPHEAVAL_SCORECOUNTER_MEDIUM;
		font.setUseIntegerPositions(false);
		font.draw(batch,
				String.valueOf(Player.getInstance().getScore().getPlatforms()),
				platformX + platformWidth + textSpacing + shakeXOffset,
				platformY + (platformHeight / 2) + (font.getCapHeight() / 2)
						+ shakeYOffset);

		/* Coins */
		layout.setText(font,
				String.valueOf(Player.getInstance().getScore().getCoins()));
		font.draw(batch,
				String.valueOf(Player.getInstance().getScore().getCoins()),
				coinX - layout.width - textSpacing + shakeXOffset,
				coinY + (coinHeight / 2) + (font.getCapHeight() / 2)
						+ shakeYOffset);

		pauseButton.render(batch);
	}

	@Override
	public void triggered() {
	}

	public void setPlatformIcon(Texture newIcon) {
		this.platformIcon = newIcon;
	}

}
