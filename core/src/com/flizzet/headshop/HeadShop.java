package com.flizzet.headshop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.camera.MainCamera;
import com.flizzet.guicomponents.GuiComponent;
import com.flizzet.guicomponents.TransitionComponent;
import com.flizzet.main.GameManager;
import com.flizzet.menubuilders.HeadMenuBuilder;
import com.flizzet.utils.FontUtils;

/**
 * Head shop.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class HeadShop extends GuiComponent {

	private HeadMenuBuilder menuBuilder = new HeadMenuBuilder();
	private BitmapFont headerFont = FontUtils.UPHEAVAL_UPGRADEMENU_LARGE_BORDERED;
	private GlyphLayout headerLayout = new GlyphLayout(headerFont, "heads");
	private BackButton backButton = new BackButton();
	private float headerX, headerY;
	private static final int MARGIN = 20;

	/** Default instantiable constructor */
	public HeadShop() {
		super(0, 0);

		menuBuilder.buildMenu();
	}

	@Override
	public void update(float delta) {
		/* Position header */
		float totalWidth = headerLayout.width + backButton.getWidth() + 10;
		headerX = menuBuilder.getIconView().getX()
				+ MainCamera.getInstance().getCenterX() + (totalWidth / 2)
				- headerLayout.width;
		headerY = MainCamera.getInstance().getHeight() - MARGIN;

		/* Set back button position */
		backButton.setX(menuBuilder.getIconView().getX()
				+ MainCamera.getInstance().getCenterX() - (totalWidth / 2));
		backButton.setY(headerY - (headerLayout.height / 2)
				- (backButton.getHeight() / 2));
		backButton.update(delta);

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
			if (menuBuilder.getIconView().getState() == 0) {
				GameManager.getInstance().stateManager
						.enterState(GameManager.getInstance().stateId_shop);
			} else {
				menuBuilder.getIconView().setState(0);
			}
		}
	}

	@Override
	public void render(SpriteBatch batch) {
		headerFont.setUseIntegerPositions(false);
		headerFont.draw(batch, "heads", headerX, headerY);
		backButton.render(batch);
	}

	@Override
	public void triggered() {

	}

}
