package com.flizzet.controlsettingsmenu;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.guicomponents.GuiComponent;
import com.flizzet.main.GameManager;

/**
 * Displays which control is currently selected.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
class ControlSelectionIndicator extends GuiComponent {

	private Texture indicatorImage;
	private ControlOption currentSelection;

	/** Default instantiable constructor */
	public ControlSelectionIndicator() {
		super(0, 0);

		this.indicatorImage = GameManager.getInstance().assetManager.get(
				"gui/settingsMenu/controls/selectionIndicator.png",
				Texture.class);
		this.adjustBoundsToImage();
	}

	@Override
	public void update(float delta) {
		bounds.x = currentSelection.getX() - 2;
		bounds.y += ((currentSelection.getY() - 2) - bounds.y) / 5;
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(indicatorImage, bounds.x, bounds.y, bounds.width,
				bounds.height);
	}

	@Override
	public void triggered() {
	}

	/** Adjusts dimensions to image width and height */
	private void adjustBoundsToImage() {
		this.setWidth(indicatorImage.getWidth() * 2);
		this.setHeight(indicatorImage.getHeight() * 2);
	}

	public void setSelection(ControlOption newSelection) {
		this.currentSelection = newSelection;
	}

}
