package com.flizzet.guicomponents;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.camera.MainCamera;
import com.flizzet.lighting.PromptLight;
import com.flizzet.main.GameManager;
import com.flizzet.particles.ScreenDarkener;
import com.flizzet.player.Player;
import com.flizzet.utils.FontUtils;

/**
 * Asks the user to rate the game and rewards them if they do.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class RatePrompt extends GuiComponent {

	/* Header */
	private BitmapFont headerFont = FontUtils.UPHEAVAL_175;
	private String header = "rate";
	private GlyphLayout headerLayout = new GlyphLayout(headerFont, header);
	private float headerX, headerY;

	/* Subtext */
	private BitmapFont subtextFont = FontUtils.UPHEAVAL_85;
	private String subtext = "rate for a gift?";
	private GlyphLayout subtextLayout = new GlyphLayout(subtextFont, subtext);
	private float subtextX, subtextY;

	/* Buttons */
	private RateAcceptButton acceptButton = new RateAcceptButton(this);
	private RateDeclineButton declineButton = new RateDeclineButton(this);

	/* Misc */
	private PromptLight light = new PromptLight(this);
	private ScreenDarkener screenDarkener = new ScreenDarkener(0.5f);
	private Texture bgImage;
	private Texture giftIcon;
	private float iconX, iconY;

	private final int STACK_MARGIN = 5;

	/** Default instantiable constructor */
	public RatePrompt() {
		super(0, 0);

		this.headerFont.setUseIntegerPositions(false);
		this.subtextFont.setUseIntegerPositions(false);

		this.bgImage = GameManager.getInstance().assetManager
				.get("gui/rateReward/promptBg.png");
		this.giftIcon = GameManager.getInstance().assetManager
				.get("gui/rateReward/giftIcon.png");
		adjustBoundsToImage();

		/* Set initial position for ease in */
		bounds.y = -400;
	}

	@Override
	public void update(float delta) {
		/* Position background */
		bounds.x = MainCamera.getInstance().getCenterX()
				- (bgImage.getWidth() / 2);
		bounds.y += (MainCamera.getInstance().getCenterY()
				- (bgImage.getHeight() / 2) - bounds.y) / 5;

		/* Position header */
		headerX = getCenterX() - (headerLayout.width / 2);
		headerY = bounds.y + bounds.height - (headerLayout.height)
				- STACK_MARGIN;

		/* Position subtext */
		subtextX = getCenterX() - (subtextLayout.width / 2);
		subtextY = headerY - headerLayout.height - (STACK_MARGIN * 3);

		/* Position buttons */
		float fullWidth = acceptButton.getWidth() + declineButton.getWidth()
				+ STACK_MARGIN;
		acceptButton
				.setX(getCenterX() + (fullWidth / 2) - acceptButton.getWidth());
		acceptButton.setY(bounds.y + (STACK_MARGIN * 4));
		acceptButton.update(delta);
		declineButton.setX(getCenterX() - (fullWidth / 2));
		declineButton.setY(bounds.y + (STACK_MARGIN * 4));
		declineButton.update(delta);

		/* Position gift icon */
		iconX = getCenterX() - (giftIcon.getWidth() / 2);
		iconY = getCenterY() - (giftIcon.getHeight() / 2);

		/* Light */
		light.update(delta);
	}

	@Override
	public void render(SpriteBatch batch) {
		/* Misc */
		screenDarkener.render(batch);
		light.render(batch);
		batch.draw(bgImage, bounds.x, bounds.y);
		/* Text */
		headerFont.draw(batch, header, headerX, headerY);
		subtextFont.draw(batch, subtext, subtextX, subtextY);
		/* Button */
		acceptButton.render(batch);
		declineButton.render(batch);
		/* Icon */
		batch.draw(giftIcon, iconX, iconY);
	}

	@Override
	public void triggered() {
	}

	/** Adjusts dimensions to image width and height */
	private void adjustBoundsToImage() {
		this.setWidth(bgImage.getWidth());
		this.setHeight(bgImage.getHeight());
	}

}

class RateAcceptButton extends ButtonComponent {

	private Texture shineImage = GameManager.getInstance().assetManager
			.get("gui/reviveMenu/buttonShine.png", Texture.class);
	private RatePrompt prompt;

	public RateAcceptButton(RatePrompt prompt) {
		super(0, 0);

		this.prompt = prompt;

		this.setImage(GameManager.getInstance().assetManager
				.get("gui/rateReward/acceptButton.png", Texture.class));
		this.setImageHovered(GameManager.getInstance().assetManager
				.get("gui/rateReward/acceptButton.png", Texture.class));
		this.setImagePushed(GameManager.getInstance().assetManager
				.get("gui/rateReward/acceptButtonPushed.png", Texture.class));
	}

	@Override
	public void render(SpriteBatch batch) {
		super.render(batch);
		if (isPushed()) {
			batch.draw(shineImage, bounds.x + 2, subtextY + 1 + 2,
					bounds.width - 4, bounds.height - 4);
		} else {
			batch.draw(shineImage, bounds.x, subtextY + 1, bounds.width,
					bounds.height);
		}
	}

	@Override
	public void triggered() {
		Gdx.net.openURI(
				"https://play.google.com/store/apps/details?id=com.flizzet.main");
		GameManager.getInstance().guiContainer.removeFromGui(prompt);
		GameNotification noti = new GameNotification("+$100");
		Player.getInstance().getScore().setCoins(
				Player.getInstance().getScore().getTotalCoins() + 100);
		GameManager.getInstance().guiContainer.addToGui(noti);
	}

}

class RateDeclineButton extends ButtonComponent {

	private Texture shineImage = GameManager.getInstance().assetManager
			.get("gui/rateReward/buttonShine.png", Texture.class);
	private RatePrompt prompt;

	public RateDeclineButton(RatePrompt prompt) {
		super(0, 0);

		this.prompt = prompt;

		this.setImage(GameManager.getInstance().assetManager
				.get("gui/rateReward/declineButton.png", Texture.class));
		this.setImageHovered(GameManager.getInstance().assetManager
				.get("gui/rateReward/declineButton.png", Texture.class));
		this.setImagePushed(GameManager.getInstance().assetManager
				.get("gui/rateReward/declineButtonPushed.png", Texture.class));
	}

	@Override
	public void render(SpriteBatch batch) {
		super.render(batch);
		if (isPushed()) {
			batch.draw(shineImage, bounds.x + 2, subtextY + 1 + 2,
					bounds.width - 4, bounds.height - 4);
		} else {
			batch.draw(shineImage, bounds.x, subtextY + 1, bounds.width,
					bounds.height);
		}
	}

	@Override
	public void triggered() {
		GameManager.getInstance().guiContainer.removeFromGui(prompt);
		GameNotification noti = new GameNotification("):");
		GameManager.getInstance().guiContainer.addToGui(noti);
	}

}
