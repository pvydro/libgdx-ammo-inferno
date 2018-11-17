package com.flizzet.headshop;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.camera.MainCamera;
import com.flizzet.guicomponents.GuiComponent;
import com.flizzet.headsystem.Head;
import com.flizzet.headsystem.Heads;
import com.flizzet.sound.Sounds;

/**
 * Displays icons for selection.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class IconView extends GuiComponent {

	private ArrayList<HeadIcon> headIcons = new ArrayList<HeadIcon>();
	private HeadPrompt prompt = new HeadPrompt(this);
	private HeadIndicator indicator = new HeadIndicator(this);
	private int state = 0; // 0 = Icon view; 1 = Prompt
	private float xChange = 0;
	private float mainYChange = -300;

	/** Default instantiable constructor */
	public IconView() {
		super(0, 0);
		buildIcons();
	}

	/** Sets up all icons for display */
	private void buildIcons() {

		/* Populate with heads */
		for (Head h : Heads.getInstance().getHeads()) {
			headIcons.add(new HeadIcon(h, this));
		}

		/* Position heads */
		int currentColumn = 0;
		int currentRow = 0;
		float baseX = MainCamera.getInstance().getCenterX()
				- ((headIcons.get(0).getWidth() + 2) * 2);
		float baseY = MainCamera.getInstance().getCenterY()
				+ ((headIcons.get(0).getHeight() + 2) * 2);
		for (HeadIcon h : headIcons) {

			/* Set position */
			h.setX(baseX + ((h.getWidth() + 2) * currentColumn));
			h.setY(baseY - ((h.getHeight() + 2) * currentRow));

			/* Next column, find row */
			currentColumn++;
			if (currentColumn == 4) {
				currentColumn = 0;
				currentRow++;
			}
		}

		/* Connect to prompt */
		prompt.setHeadIcon(getHeadIcon(0));

		/* Set dimensions */
		this.setWidth(headIcons.get(0).getWidth() * 4);
		this.setHeight(headIcons.get(0).getHeight() * 4);

	}

	@Override
	public void update(float delta) {

		/* Main positioning */
		mainYChange += (0 - mainYChange) / 3;
		mainYChange += (0 - bounds.y) / 10;
		bounds.y += mainYChange;

		/* Modifying xChange with state */
		xChange += (0 - xChange) / 3;
		if (state == 0) {
			xChange += (0 - bounds.x) / 10;
		} else {
			xChange += (-500 - bounds.x) / 10;
		}
		bounds.x += xChange;

		/* Icon manipulation */
		for (HeadIcon h : headIcons) {
			h.update(delta);
			h.setX(h.getX() + xChange);
		}

		/* Position prompt */
		prompt.setX(this.getX() + 500);
		prompt.setY(this.getY());
		prompt.update(delta);

		/* Move back from a prompt */
		if (Gdx.input.isKeyJustPressed(Keys.LEFT)) {
			closePrompt();
		}

		indicator.update(delta);

	}

	@Override
	public void render(SpriteBatch batch) {

		for (HeadIcon h : headIcons) {
			h.render(batch);
		}

		prompt.render(batch);
		indicator.render(batch);

	}

	/** Opens the side prompt for purchasing an upgrade */
	public void openPrompt(HeadIcon newHeadIcon) {
		Sounds.play(Sounds.getInstance().newSwoosh(), 1.0f);
		this.state = 1;
		prompt.setHeadIcon(newHeadIcon);
		prompt.getBuyButton().setHead(newHeadIcon.getHead());
	}

	/** Closes the side prompt */
	public void closePrompt() {
		Sounds.play(Sounds.getInstance().newSwoosh(), 1.0f);
		this.state = 0;
	}

	@Override
	public void triggered() {
	}

	public void setState(int newState) {
		this.state = newState;
	}

	public HeadIcon getHeadIcon(int index) {
		return headIcons.get(index);
	}
	public ArrayList<HeadIcon> getHeadIcon() {
		return this.headIcons;
	}
	public float getXChange() {
		return this.xChange;
	}
	public int getState() {
		return this.state;
	}

}
