package com.flizzet.guicomponents;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.camera.MainCamera;
import com.flizzet.dailyrewards.DailyReward;
import com.flizzet.lighting.PromptLight;
import com.flizzet.main.GameManager;
import com.flizzet.particles.ScreenDarkener;
import com.flizzet.player.Player;
import com.flizzet.settings.CurrentSettings;
import com.flizzet.utils.FontUtils;

/**
 * Displays the users daily reward.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class RewardPrompt extends GuiComponent {

	/* Background */
	private Texture bgImage;
	private Texture rewardIcon;
	private float rewardIconX, rewardIconY;
	private PromptLight light = new PromptLight(this);

	/* Header */
	private BitmapFont headerFont = FontUtils.UPHEAVAL_150;
	private String header = "daily reward";
	private GlyphLayout headerLayout = new GlyphLayout(headerFont, header);
	private float headerX, headerY;

	/* You've played */
	private BitmapFont playedFont = FontUtils.UPHEAVAL_85;
	private String played = "you've played";
	private GlyphLayout playedLayout = new GlyphLayout(playedFont, played);
	private float playedX, playedY;

	/* Total days */
	private BitmapFont totalDaysFont = FontUtils.UPHEAVAL_175;
	private String totalDays = String
			.valueOf(CurrentSettings.getInstance().totalDays)
			+ (CurrentSettings.getInstance().totalDays > 1 ? " days" : " day");
	private GlyphLayout totalDaysLayout = new GlyphLayout(totalDaysFont,
			totalDays);
	private float totalDaysX, totalDaysY;

	/* In a row */
	private String inARow = "in a row";
	private GlyphLayout inARowLayout = new GlyphLayout(playedFont, inARow);
	private float inARowX, inARowY;

	/* Score */
	private BitmapFont scoreFont = FontUtils.UPHEAVAL_175;
	private String score = "+"
			+ DailyReward.getInstance().getChecker().getReward();
	private GlyphLayout scoreLayout = new GlyphLayout(scoreFont, score);
	private float scoreX, scoreY;

	/* Overlays */
	private ScreenDarkener screenDarkener = new ScreenDarkener(0.8f);
	private ShineOverlay shineOverlay = new ShineOverlay();

	/* Button */
	private RewardAcceptButton acceptButton = new RewardAcceptButton(this);

	private final int STACK_MARGIN = 5;

	/** Default instantiable constructor */
	public RewardPrompt() {
		super(0, 0);

		this.headerFont.setUseIntegerPositions(false);
		this.playedFont.setUseIntegerPositions(false);
		this.totalDaysFont.setUseIntegerPositions(false);
		this.scoreFont.setUseIntegerPositions(false);
		this.bgImage = GameManager.getInstance().assetManager
				.get("gui/dailyReward/promptBg.png");
		this.rewardIcon = GameManager.getInstance().assetManager
				.get("gui/dailyReward/rewardIcon.png");
		this.adjustBoundsToImage();

		/* Give the reward to the player */
		Player.getInstance().getScore()
				.setCoins(Player.getInstance().getScore().getTotalCoins()
						+ DailyReward.getInstance().getChecker().getReward());

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
		headerX = this.getCenterX() - (headerLayout.width / 2);
		headerY = this.getY() + this.getHeight() - (headerLayout.height)
				- STACK_MARGIN;
		/* Position "you've played" */
		playedX = this.getCenterX() - (playedLayout.width / 2);
		playedY = headerY - headerLayout.height - (STACK_MARGIN * 3);
		/* Position total days */
		totalDaysX = this.getCenterX() - (totalDaysLayout.width / 2);
		totalDaysY = playedY - playedLayout.height - STACK_MARGIN;
		/* Position "in a row" */
		inARowX = this.getCenterX() - (inARowLayout.width / 2);
		inARowY = totalDaysY - totalDaysLayout.height - STACK_MARGIN;
		/* Position score */
		float fullWidth = rewardIcon.getWidth() + STACK_MARGIN
				+ scoreLayout.width;
		scoreX = this.getCenterX() + (fullWidth / 2) - scoreLayout.width;
		scoreY = this.getCenterY() - (scoreLayout.height / 2);
		rewardIconX = this.getCenterX() - (fullWidth / 2);
		rewardIconY = scoreY - (scoreLayout.height / 2)
				- (rewardIcon.getHeight() / 2);

		/* Accept button */
		acceptButton.setX(this.getCenterX() - (acceptButton.getWidth() / 2));
		acceptButton.setY(this.getY() - 2);
		acceptButton.update(delta);

		/* Light */
		light.update(delta);

		/* Back button */
		if (Gdx.input.isKeyPressed(Keys.BACK)
				|| Gdx.input.isKeyPressed(Keys.ESCAPE)) {
			GameManager.getInstance().guiContainer.removeFromGui(this);
		}
	}

	@Override
	public void render(SpriteBatch batch) {

		/* Overlay screen */
		screenDarkener.render(batch);
		shineOverlay.render(batch);

		/* Light */
		light.render(batch);

		/* Draw prompt image */
		batch.draw(bgImage, bounds.x, bounds.y);

		/* Draw header */
		headerFont.draw(batch, header, headerX, headerY);
		/* Draw "you've played" */
		playedFont.draw(batch, played, playedX, playedY);
		/* Draw total days */
		totalDaysFont.draw(batch, totalDays, totalDaysX, totalDaysY);
		/* Draw "in a row" */
		playedFont.draw(batch, inARow, inARowX, inARowY);
		/* Draw score */
		scoreFont.draw(batch, score, scoreX, scoreY);

		/* Draw reward icon */
		batch.draw(rewardIcon, rewardIconX, rewardIconY);

		/* Draw accept button */
		acceptButton.render(batch);

	}

	@Override
	public void triggered() {
	}

	/** Sets dimensions to image width and height */
	private void adjustBoundsToImage() {
		this.setWidth(bgImage.getWidth());
		this.setHeight(bgImage.getHeight());
	}

}

class RewardAcceptButton extends ButtonComponent {

	private Texture shineImage = GameManager.getInstance().assetManager
			.get("gui/dailyReward/buttonShine.png", Texture.class);
	private RewardPrompt prompt;

	public RewardAcceptButton(RewardPrompt prompt) {
		super(0, 0);

		this.prompt = prompt;

		this.setImage(GameManager.getInstance().assetManager
				.get("gui/dailyReward/acceptButton.png", Texture.class));
		this.setImageHovered(GameManager.getInstance().assetManager
				.get("gui/dailyReward/acceptButton.png", Texture.class));
		this.setImagePushed(GameManager.getInstance().assetManager
				.get("gui/dailyReward/acceptButtonPushed.png", Texture.class));
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
	}

}
