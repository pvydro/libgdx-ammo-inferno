package com.flizzet.deathmenu;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.camera.MainCamera;
import com.flizzet.guicomponents.ButtonComponent;
import com.flizzet.guicomponents.GuiComponent;
import com.flizzet.main.GameManager;
import com.flizzet.menubuilders.ShopSelectionMenuBuilder;
import com.flizzet.utils.FontUtils;

/**
 * Selection menu for choosing the head, weapon, and upgrade shop.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class ShopSelectionMenu extends GuiComponent {

	private DeathMenu deathMenu;
	private ShopSelectionMenuBuilder builder = new ShopSelectionMenuBuilder();
	private BitmapFont fontHeader = FontUtils.UPHEAVAL_MENUHEADER_LARGE;
	private GlyphLayout headerLayout = new GlyphLayout(fontHeader, "shop");
	private BackButton backButton = new BackButton();

	private final int MARGIN = 10;
	private float headerX = MainCamera.getInstance().getCenterX()
			- (headerLayout.width / 2) + 400;
	private float headerY = MainCamera.getInstance().getHeight()
			- headerLayout.height - MARGIN;
	private float backButtonY = headerY - (fontHeader.getCapHeight() / 2)
			- (backButton.getHeight() / 2);

	/** Default instantiable constructor */
	public ShopSelectionMenu(DeathMenu deathMenu) {
		super(0, 0);

		this.deathMenu = deathMenu;
	}

	@Override
	public void update(float delta) {

		for (ButtonComponent b : builder.getButtons()) {
			b.setX(b.getX() + deathMenu.getXChange());
		}

		headerX += deathMenu.getXChange();

		backButton.setX(builder.getButtons().get(0).getX());
		backButton.setY(backButtonY);

	}

	@Override
	public void render(SpriteBatch batch) {

		fontHeader.setUseIntegerPositions(false);
		fontHeader.draw(batch, "shop", headerX, headerY);

	}

	/** Removes elements */
	public void disable() {
		for (ButtonComponent b : builder.getButtons()) {
			GameManager.getInstance().guiContainer.removeFromGui(b);
		}
		GameManager.getInstance().guiContainer.removeFromGui(backButton);
		builder.getButtons().clear();
	}

	/** Replaces elements */
	public void enable() {
		builder.buildMenu();
		headerX = MainCamera.getInstance().getCenterX()
				- (headerLayout.width / 2) + 400;
		GameManager.getInstance().guiContainer.addToGui(backButton);
	}

	@Override
	public void triggered() {
	}

	public ShopSelectionMenuBuilder getBuilder() {
		return this.builder;
	}

}
