package com.flizzet.weaponshop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.camera.MainCamera;
import com.flizzet.guicomponents.GuiComponent;
import com.flizzet.guicomponents.TransitionComponent;
import com.flizzet.main.GameManager;
import com.flizzet.menubuilders.WeaponShopBuilder;
import com.flizzet.utils.FontUtils;

/**
 * Weapon shop.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class WeaponShop extends GuiComponent {

	private WeaponShopBuilder menuBuilder = new WeaponShopBuilder();

	private BackButton backButton = new BackButton();
	private BitmapFont headerFont = FontUtils.UPHEAVAL_UPGRADEMENU_LARGE_BORDERED;
	private String header = "weapons";
	private GlyphLayout headerLayout = new GlyphLayout(headerFont, header);

	private float headerX, headerY;

	private static final int MARGIN = 30;

	/** Default instantiable constructor */
	public WeaponShop() {
		super(0, 0);

		menuBuilder.buildMenu();
	}

	@Override
	public void update(float delta) {

		float totalWidth = backButton.getWidth() + headerLayout.width + 10;

		/* Back button positioning */
		backButton
				.setX(MainCamera.getInstance().getCenterX() - (totalWidth / 2));
		backButton.setY(MainCamera.getInstance().getHeight() - MARGIN
				- backButton.getHeight());
		backButton.update(delta);

		/* Header position */
		headerX = MainCamera.getInstance().getCenterX() + (totalWidth / 2)
				- headerLayout.width;
		headerY = backButton.getY() + (backButton.getHeight())
				- (headerLayout.height / 2);

		/* Back button */
		if (Gdx.input.isKeyJustPressed(Keys.BACK)
				|| Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			/* Suppress if states are transitioning */
			for (GuiComponent g : GameManager.getInstance().guiContainer
					.getGuiComponents()) {
				if (g instanceof TransitionComponent) {
					return;
				}
			}
			GameManager.getInstance().stateManager
					.enterState(GameManager.getInstance().stateId_shop);
		}
	}

	@Override
	public void render(SpriteBatch batch) {

		headerFont.draw(batch, header, headerX, headerY);
		backButton.render(batch);
	}

	@Override
	public void triggered() {
	}

}
