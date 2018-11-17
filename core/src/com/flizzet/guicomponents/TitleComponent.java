package com.flizzet.guicomponents;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.main.GameManager;

/**
 * Title component to appear on the start menu.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 * @see also StartMenu
 */
public class TitleComponent extends GuiComponent {

	private Texture iconImage;
	private Texture titleImage;
	private float titleX, titleY;

	private float jitterX, jitterY;
	private int jitterPositionAmt = 2;
	private Random random = new Random();

	private final int STACK_MARGIN = 5;

	/** Default instantiable constructor */
	public TitleComponent() {
		super(0, 0);

		this.iconImage = GameManager.getInstance().assetManager
				.get("gui/startMenu/title/fullOutlined.png");
		this.titleImage = GameManager.getInstance().assetManager
				.get("gui/startMenu/title/titleFull.png");
		this.setWidth(180);
		this.setHeight(180);
	}

	@Override
	public void update(float delta) {
		/* Position title */
		titleX = this.getCenterX() - (titleImage.getWidth() / 2);
		titleY = this.getY() - STACK_MARGIN - (titleImage.getHeight());

		/* Jitters */
		float newXJitter = random.nextBoolean()
				? random.nextInt(jitterPositionAmt)
				: -random.nextInt(jitterPositionAmt);
		jitterX += (newXJitter - jitterX) / 4;
		float newYJitter = random.nextBoolean()
				? random.nextInt(jitterPositionAmt)
				: -random.nextInt(jitterPositionAmt);
		jitterY += (newYJitter - jitterY) / 4;
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(iconImage, bounds.x + jitterX, bounds.y + jitterY,
				bounds.width, bounds.height);
		batch.draw(titleImage, titleX - jitterX, titleY - jitterY);
	}

	@Override
	public void triggered() {
	}

}
