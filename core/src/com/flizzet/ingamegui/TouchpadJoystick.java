package com.flizzet.ingamegui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad.TouchpadStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.flizzet.guicomponents.GuiComponent;
import com.flizzet.main.GameManager;

/**
 * Touchpad joystick for smoother movement.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class TouchpadJoystick extends GuiComponent {

	private Touchpad touchpad;
	private TouchpadStyle touchpadStyle;
	private Skin touchpadSkin;
	private Drawable touchBackground;
	private Drawable touchKnob;

	/** Default instantiable constructor */
	public TouchpadJoystick() {
		super(0, 0);

		touchpadStyle = getTouchpadStyle();
		touchpad = new Touchpad(2, touchpadStyle);
	}

	@Override
	public void update(float delta) {
		touchpad.act(delta);
	}

	@Override
	public void render(SpriteBatch batch) {
		touchpad.draw(batch, 1.0f);
	}

	private TouchpadStyle getTouchpadStyle() {

		touchpadSkin = new Skin();
		touchpadSkin.add("touchBackground",
				GameManager.getInstance().assetManager.get(
						"gui/inGame/movement/console/touchpad/touchpadBackground.png"));
		touchpadSkin.add("touchKnob", GameManager.getInstance().assetManager
				.get("gui/inGame/movement/console/touchpad/touchpadKnob.png"));

		touchpadStyle = new TouchpadStyle();

		touchBackground = touchpadSkin.getDrawable("touchBackground");
		touchKnob = touchpadSkin.getDrawable("touchKnob");

		touchpadStyle.background = touchBackground;
		touchpadStyle.knob = touchKnob;

		return touchpadStyle;
	}

	@Override
	public void triggered() {
	}

	public Touchpad getTouchpad() {
		return this.touchpad;
	}

}
