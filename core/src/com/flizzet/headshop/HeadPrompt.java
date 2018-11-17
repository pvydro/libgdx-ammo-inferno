package com.flizzet.headshop;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.camera.MainCamera;
import com.flizzet.guicomponents.EconomyComponent;
import com.flizzet.guicomponents.GuiComponent;
import com.flizzet.utils.FontUtils;

/**
 * Option for purchasing a head.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
class HeadPrompt extends GuiComponent {

	private IconView view;
	private HeadIcon headIcon;
	private PromptBuyButton buyButton;
	private PromptCancelButton cancelButton;
	private BitmapFont font = FontUtils.UPHEAVAL_UPGRADEMENU_SMALL;
	private EconomyComponent economy = new EconomyComponent();
	private String name = "";
	private String price = "500";
	private GlyphLayout priceLayout = new GlyphLayout(font, "500");
	private GlyphLayout nameLayout = new GlyphLayout(font, "");
	private float priceX, priceY;
	private float nameX, nameY;

	private static final int ICON_MULTIPLIER = 5;
	private static final int MARGIN = 5;

	private float iconX, iconY;
	private float iconWidth, iconHeight;

	/** Default instantiable constructor */
	public HeadPrompt(IconView view) {
		super(0, 0);
		this.view = view;
		this.buyButton = new PromptBuyButton();
		this.cancelButton = new PromptCancelButton(view);
	}

	@Override
	public void update(float delta) {

		iconWidth = headIcon.getImage().getWidth() * ICON_MULTIPLIER;
		iconHeight = headIcon.getImage().getHeight() * ICON_MULTIPLIER;

		iconX = bounds.x + MainCamera.getInstance().getCenterX()
				- (iconWidth / 2) + view.getXChange();
		iconY = bounds.y + MainCamera.getInstance().getCenterY();

		/* Setting name position */
		nameX = bounds.x + MainCamera.getInstance().getCenterX()
				- (nameLayout.width / 2);
		nameY = iconY - 5;

		/* Setting price position */
		priceX = bounds.x + MainCamera.getInstance().getCenterX()
				- (priceLayout.width / 2);
		priceY = nameY - 10;

		/* Setting button position */
		buyButton.setX(bounds.x + MainCamera.getInstance().getCenterX()
				- buyButton.getWidth() - MARGIN);
		buyButton.setY(
				priceY - priceLayout.height - buyButton.getHeight() - MARGIN);
		cancelButton.setX(
				bounds.x + MainCamera.getInstance().getCenterX() + MARGIN);
		cancelButton.setY(priceY - priceLayout.height - cancelButton.getHeight()
				- MARGIN);
		buyButton.update(delta);
		cancelButton.update(delta);

		/* Setting economy position */
		economy.setX(this.getX());
		economy.setY(this.getY());
		economy.update(delta);

	}

	@Override
	public void render(SpriteBatch batch) {

		font.setUseIntegerPositions(false);
		font.draw(batch, price, priceX, priceY);
		font.draw(batch, name, nameX, nameY);
		batch.draw(headIcon.getImage(), iconX, iconY, iconWidth, iconHeight);

		buyButton.render(batch);
		cancelButton.render(batch);
		economy.render(batch);

	}

	@Override
	public void triggered() {
	}

	public void setHeadIcon(HeadIcon newHeadIcon) {
		this.headIcon = newHeadIcon;
		this.price = "$" + String.valueOf(newHeadIcon.getHead().getPrice());
		this.name = newHeadIcon.getHead().getName();
		priceLayout = new GlyphLayout(font, price);
		nameLayout = new GlyphLayout(font, name);
	}

	public PromptBuyButton getBuyButton() {
		return this.buyButton;
	}

}
