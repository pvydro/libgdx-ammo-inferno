package com.flizzet.effectssettingsmenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.camera.MainCamera;
import com.flizzet.guicomponents.CheckboxComponent;
import com.flizzet.guicomponents.GuiComponent;
import com.flizzet.guicomponents.SeparatorComponent;
import com.flizzet.guicomponents.TransitionComponent;
import com.flizzet.main.GameManager;
import com.flizzet.menubuilders.EffectsSettingsMenuBuilder;
import com.flizzet.utils.FontUtils;

/**
 * Effects settings menu.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class EffectsSettingsMenu extends GuiComponent {

	private BitmapFont headerFont = FontUtils.UPHEAVAL_UPGRADEMENU_LARGE_BORDERED;
	private String header = "effects";
	private GlyphLayout headerLayout = new GlyphLayout(headerFont, header);
	private BackButton backButton = new BackButton();

	private float headerX, headerY;
	private float initialMouseY;
	private float mouseChange;
	private float yChange = 0;
	private boolean pushed = false;

	private static final int MARGIN = 20;

	private EffectsSettingsMenuBuilder menuBuilder = new EffectsSettingsMenuBuilder();

	/** Default instantiable constructor */
	public EffectsSettingsMenu() {
		super(0, 0);

		menuBuilder.buildMenu();
	}

	@Override
	public void update(float delta) {
		float fullWidth = backButton.getWidth() + headerLayout.width + 10;

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

		/* Update menubuilder for initial scroll pos */
		menuBuilder.update(delta);

		/* Apply scroll to elements */
		for (CheckboxComponent c : menuBuilder.getEffectCheckboxes()) {
			c.setY(c.getY() - yChange);
		}
		for (CheckboxComponent c : menuBuilder.getParticleCheckboxes()) {
			c.setY(c.getY() - yChange);
		}

		for (SeparatorComponent s : menuBuilder.getSeparators()) {
			s.setY(s.getY() - yChange);
		}

		/* Handling a scroll */
		initialMouseY += (MainCamera.getInstance().getMousePos().y
				- initialMouseY) / 5;
		if (Gdx.input.isTouched()) {
			if (!pushed) {
				pushed = true;
				initialMouseY = MainCamera.getInstance().getMousePos().y;
			}

			mouseChange = initialMouseY
					- MainCamera.getInstance().getMousePos().y;
		} else {
			pushed = false;
		}
		mouseChange += (0 - mouseChange) / 7;
		yChange += mouseChange / 5;

		/* Stopping at floor */
		if (menuBuilder.getSeparators().get(0)
				.getY() < MainCamera.getInstance().getHeight()
						- menuBuilder.MARGIN) {
			if (Math.signum(mouseChange) == 1) {
				yChange -= mouseChange / 5;
			}
		}
		if (menuBuilder.getSeparators().get(2).getY() > 10) {
			if (Math.signum(mouseChange) == -1) {
				yChange -= mouseChange / 5;
			}
		}

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
					.enterState(GameManager.getInstance().stateId_settings);
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
