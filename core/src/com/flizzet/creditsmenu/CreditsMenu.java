package com.flizzet.creditsmenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.camera.MainCamera;
import com.flizzet.guicomponents.GuiComponent;
import com.flizzet.guicomponents.TransitionComponent;
import com.flizzet.main.GameManager;
import com.flizzet.menubuilders.CreditsMenuBuilder;
import com.flizzet.utils.FontUtils;

/**
 * Credits menu.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class CreditsMenu extends GuiComponent {

	private CreditsMenuBuilder menuBuilder = new CreditsMenuBuilder();

	private BitmapFont headerFont = FontUtils.UPHEAVAL_UPGRADEMENU_LARGE_BORDERED;
	private String header = "credits";
	private GlyphLayout headerLayout = new GlyphLayout(headerFont, header);
	private float headerX, headerY;

	private BackButton backButton = new BackButton();
	private final int MARGIN = 20;

	/** Default instantiable constructor */
	public CreditsMenu() {
		super(0, 0);

		menuBuilder.buildMenu();
	}

	@Override
	public void update(float delta) {
		float fullWidth = headerLayout.width + backButton.getWidth() + 10;

		/* Position header */
		headerX = MainCamera.getInstance().getCenterX() + (fullWidth / 2)
				- headerLayout.width;
		headerY = MainCamera.getInstance().getHeight() - MARGIN;

		/* Position back button */
		backButton
				.setX(MainCamera.getInstance().getCenterX() - (fullWidth / 2));
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
			GameManager.getInstance().stateManager.enterState(
					GameManager.getInstance().stateId_generalSettings);
		}
	}

	@Override
	public void render(SpriteBatch batch) {
		backButton.render(batch);
		headerFont.draw(batch, header, headerX, headerY);
	}

	@Override
	public void triggered() {
	}

}
