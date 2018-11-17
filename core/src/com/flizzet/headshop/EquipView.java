package com.flizzet.headshop;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.camera.MainCamera;
import com.flizzet.guicomponents.GuiComponent;
import com.flizzet.headsystem.Head;
import com.flizzet.headsystem.Heads;
import com.flizzet.utils.FontUtils;

/**
 * Shows which head is equipped and allows changing.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class EquipView extends GuiComponent {

	private Heads heads = Heads.getInstance();
	private Head currentHead;
	private IconView view;
	private BitmapFont font = FontUtils.UPHEAVAL_UPGRADEMENU_SMALL;
	private String header = "equipped";
	private GlyphLayout layout = new GlyphLayout(font, header);
	private NextButton nextButton = new NextButton(this);
	private PreviousButton previousButton = new PreviousButton(this);

	private float headerX, headerY;
	private float headX, headY;
	private float headWidth, headHeight;
	private int currentHeadIndex = 0;

	private static final int ICON_MULTIPLIER = 3;
	private static final int MARGIN = 20;

	/**
	 * Default instantiable constructor
	 * 
	 * @param view
	 *            EquipView is dependent on a IconView for positioning
	 */
	public EquipView(IconView view) {
		super(0, 0);
		this.view = view;
		currentHeadIndex = Heads.getInstance().getHeads()
				.indexOf(Heads.getInstance().getEquippedHead());
	}

	@Override
	public void update(float delta) {
		currentHead = heads.getEquippedHead();

		this.setX(view.getX());
		this.setY(view.getY());

		/* Position and resize head */
		headWidth = currentHead.getIconImage().getWidth() * ICON_MULTIPLIER;
		headHeight = currentHead.getIconImage().getHeight() * ICON_MULTIPLIER;
		headX = bounds.x + MainCamera.getInstance().getCenterX()
				- (headWidth / 2);
		headY = bounds.y + MARGIN;

		/* Position buttons */
		nextButton.setX(headX + headWidth + 5);
		previousButton.setX(headX - previousButton.getWidth() - 5);
		float buttonY = headY + (headHeight / 2) - (nextButton.getHeight() / 2);
		nextButton.setY(buttonY);
		previousButton.setY(buttonY);
		nextButton.update(delta);
		previousButton.update(delta);

		/* Position text */
		headerX = bounds.x + MainCamera.getInstance().getCenterX()
				- (layout.width / 2);
		headerY = headY + headHeight + layout.height + 2;

	}

	@Override
	public void render(SpriteBatch batch) {

		batch.draw(currentHead.getIconImage(), headX, headY, headWidth,
				headHeight);
		nextButton.render(batch);
		previousButton.render(batch);

		font.setUseIntegerPositions(false);
		font.draw(batch, header, headerX, headerY);

	}

	@Override
	public void triggered() {
	}

	/** Equips the next head in rotation */
	public void nextHead() {
		currentHeadIndex++;
		/* Prevent from going out of index */
		if (currentHeadIndex > Heads.getInstance().getHeads().size() - 1) {
			currentHeadIndex = 0;
		}
		/* Check if not bought, if not bought then do the next one */
		if (!Heads.getInstance().getHeads().get(currentHeadIndex).isBought()) {
			nextHead();
			return;
		}
		/* Equip new head */
		Heads.getInstance().equip(Heads.getInstance().getHeads()
				.get(currentHeadIndex).getClass());
	}

	/** Equips the previous head in rotation */
	public void previousHead() {
		currentHeadIndex--;
		/* Prevent from going negative */
		if (currentHeadIndex < 0) {
			currentHeadIndex = Heads.getInstance().getHeads().size() - 1;
		}
		/* Check if not bought, if not bought then do the next one */
		if (!Heads.getInstance().getHeads().get(currentHeadIndex).isBought()) {
			previousHead();
			return;
		}
		/* Equip the new head */
		Heads.getInstance().equip(Heads.getInstance().getHeads()
				.get(currentHeadIndex).getClass());
	}

}
