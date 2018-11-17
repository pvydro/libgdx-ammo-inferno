package com.flizzet.ingamegui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.camera.MainCamera;
import com.flizzet.guicomponents.ButtonComponent;
import com.flizzet.main.GameManager;
import com.flizzet.player.Player;
import com.flizzet.settings.ControlType;
import com.flizzet.settings.CurrentSettings;

/**
 * Onscreen togglable jump button.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
class JumpButton extends ButtonComponent {

	private int currentPointer;
	private float alpha = 0;

	/** Default instantiable constructor */
	public JumpButton() {
		super(0, 0);

		switch (CurrentSettings.getInstance().controls) {
			case HORIZONTALARROWS:
				this.setImage(GameManager.getInstance().assetManager.get(
						"gui/inGame/movement/arrows/jumpBtn.png",
						Texture.class));
				this.setImageHovered(GameManager.getInstance().assetManager.get(
						"gui/inGame/movement/arrows/jumpBtn.png",
						Texture.class));
				this.setImagePushed(GameManager.getInstance().assetManager.get(
						"gui/inGame/movement/arrows/jumpBtnPushed.png",
						Texture.class));
				break;
			case CONSOLE:
				this.setImage(GameManager.getInstance().assetManager.get(
						"gui/inGame/movement/console/jumpBtn.png",
						Texture.class));
				this.setImageHovered(GameManager.getInstance().assetManager.get(
						"gui/inGame/movement/console/jumpBtn.png",
						Texture.class));
				this.setImagePushed(GameManager.getInstance().assetManager.get(
						"gui/inGame/movement/console/jumpBtnPushed.png",
						Texture.class));
				break;
			default:
				break;
		}

	}

	@Override
	public void render(SpriteBatch batch) {
		if (CurrentSettings.getInstance().controls == ControlType.CONSOLE) {
			Color tmp = batch.getColor();
			batch.setColor(tmp.r, tmp.g, tmp.b, alpha);
			super.render(batch);
			batch.setColor(tmp);
		} else {
			super.render(batch);
		}
	}

	@Override
	public void update(float delta) {
		state = BtnState.DEFAULT;

		/* Iterate through all possible touches for multitouch */
		for (int i = 0; i < 20; i++) {
			if (Gdx.input.isTouched(i)) {
				final float mouseX = MainCamera.getInstance().getMousePos(i).x;
				final float mouseY = MainCamera.getInstance().getMousePos(i).y;

				if (bounds.contains(mouseX, mouseY)) {
					currentPointer = i;
				} else {
					currentPointer = 19;
				}
			}
		}

		if (Gdx.input.isTouched(currentPointer)) {
			state = BtnState.PUSHED;
			triggered();

			alpha += (1 - alpha) / 2;
		} else {
			state = BtnState.DEFAULT;

			alpha += (0.5f - alpha) / 2;
		}

		subtextY = bounds.getY() - 1;
	}

	@Override
	public void triggered() {
		Player.getInstance().getController().setUpPushed(true);
	}

}