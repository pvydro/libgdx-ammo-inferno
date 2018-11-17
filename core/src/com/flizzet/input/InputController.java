package com.flizzet.input;

import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.flizzet.camera.MainCamera;
import com.flizzet.guicomponents.GuiComponent;
import com.flizzet.ingamegui.InGameGui;
import com.flizzet.ingamegui.TargetButton;
import com.flizzet.main.GameManager;
import com.flizzet.main.GameState;
import com.flizzet.main.PauseManager;
import com.flizzet.player.Player;
import com.flizzet.player.PlayerState;
import com.flizzet.settings.CurrentSettings;

/**
 * Listens for touch gestures.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class InputController implements GestureListener {

	/** Default instantiable constructor */
	public InputController() {
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {

		/* Make sure double tap jumping is enabled */
		if (!CurrentSettings.getInstance().doubleTapSelect) {
			return false;
		}
		/* Make sure the tap isnt within a movement button */
		for (GuiComponent b : InGameGui.getInstance().getButtons()) {
			if (b.getBounds().contains(MainCamera.getInstance().getMousePos().x,
					MainCamera.getInstance().getMousePos().y)) {
				return false;
			}
		}
		/* Double tap */
		if (count == 2) {
			if (Player.getInstance().getState() != PlayerState.DEAD
					&& Player.getInstance().getState() != PlayerState.DYING
					&& PauseManager.getInstance().getState() == GameState.PLAY
					&& GameManager.getInstance().stateManager
							.getCurrentState() == GameManager
									.getInstance().stateId_play) {

				TargetButton.getInstance().triggered();

			} else if (PauseManager.getInstance()
					.getState() == GameState.SELECT) {
				TargetButton.getInstance().triggered();
			}
			count = 0;
		}
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		return false;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		return false;
	}

	@Override
	public boolean panStop(float x, float y, int pointer, int button) {
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
			Vector2 pointer1, Vector2 pointer2) {
		return false;
	}

	@Override
	public void pinchStop() {

	}

}
