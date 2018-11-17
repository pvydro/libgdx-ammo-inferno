package com.flizzet.upgradeshop;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.camera.MainCamera;
import com.flizzet.guicomponents.GuiComponent;
import com.flizzet.main.GameManager;
import com.flizzet.menubuilders.UpgradeMenuBuilder;
import com.flizzet.sound.Sounds;
import com.flizzet.upgrades.Upgrade;
import com.flizzet.upgradesystem.Upgrades;

/**
 * Scoll view for the upgrades menu.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class UpgradeScrollView extends GuiComponent {

	private Texture scrollWindowImage;
	private ArrayList<UpgradeScrollCell> cells = new ArrayList<UpgradeScrollCell>();
	private UpgradeBuyPrompt buyPrompt = new UpgradeBuyPrompt(this);;
	private UpgradeMenuBuilder menuBuilderPassed;
	private float scrollWindowX, scrollWindowY;
	private float cellX;
	private float initialMouseY;
	private float mouseChange;
	private float yChange = 0;
	private float xChange = 0;
	private float mainYChange = -300;
	private int state = 0; // 0 = Selecting upgrade; 1 = Buying upgrade
	private boolean pushed = false;

	/** Default instantiable constructor */
	public UpgradeScrollView(UpgradeMenuBuilder menuBuilder) {
		super(0, 0);

		this.menuBuilderPassed = menuBuilder;

		this.scrollWindowImage = GameManager.getInstance().assetManager
				.get("gui/upgradeShop/scrollWindow.png");
		this.adjustBoundsToImage();
		this.createCells();
	}

	@Override
	public void update(float delta) {

		/* Modifying xChange with state */
		xChange += (0 - xChange) / 3;
		if (state == 0) {
			xChange += (0 - bounds.x) / 10;
		} else {
			xChange += (-500 - bounds.x) / 10;
		}
		bounds.x += xChange;

		/* Main positioning */
		mainYChange += (0 - mainYChange) / 3;
		mainYChange += (0 - bounds.y) / 10;
		bounds.y += mainYChange;

		/* Position scroll window */
		scrollWindowX = bounds.x + (MainCamera.getInstance().getWidth() / 2)
				- (scrollWindowImage.getWidth() / 2);
		scrollWindowY = bounds.y + (MainCamera.getInstance().getHeight() / 2)
				- (scrollWindowImage.getHeight() / 2);

		/* Position cells */
		cellX = scrollWindowX + 8;

		/* Update all cells */
		int totalCells = 0;
		for (UpgradeScrollCell c : cells) {
			c.setX(cellX);
			/* Set y relative to window */
			c.setY(scrollWindowY + this.getHeight() - c.getHeight() - 8);
			/* Set y relative to total cells */
			c.setY(c.getY() - c.getHeight() * totalCells);
			/* Set y with scroll */
			c.setY(c.getY() - yChange);
			c.update(delta);
			totalCells++;
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

		/* Stopping at ceiling */
		// TODO Make ceiling and floor based on scrollview vs camera when more
		// are added
		if (cells.get(cells.size() - 1).getY() > scrollWindowY) {
			if (Math.signum(mouseChange) == -1) {
				yChange -= mouseChange / 5;
			}
		}
		/* Stopping at floor */
		if (cells.get(0).getY() + cells.get(0).getHeight() < scrollWindowY
				+ scrollWindowImage.getHeight()) {
			if (Math.signum(mouseChange) == 1) {
				yChange -= mouseChange / 5;
			}
		}

		/* Move back from a prompt */
		if (Gdx.input.isKeyJustPressed(Keys.LEFT)) {
			closePrompt();
		}

		buyPrompt.update(delta);

	}

	@Override
	public void render(SpriteBatch batch) {

		/* Render scroll window */
		batch.draw(scrollWindowImage, scrollWindowX, scrollWindowY);

		/* Render all cells */
		for (UpgradeScrollCell c : cells) {
			c.render(batch);
		}

		buyPrompt.render(batch);

	}

	@Override
	public void triggered() {
	}

	/** Builds all cells */
	private void createCells() {
		for (Upgrade u : Upgrades.getInstance().getUpgrades()) {
			cells.add(new UpgradeScrollCell(u, this));
		}
	}

	/** Sets bounds to image width and height */
	private void adjustBoundsToImage() {
		this.setWidth(scrollWindowImage.getWidth());
		this.setHeight(scrollWindowImage.getHeight());
	}

	/** Opens the side prompt for purchasing an upgrade */
	public void openPrompt(Upgrade upgrade) {
		Sounds.play(Sounds.getInstance().newSwoosh(), 1.0f);
		this.state = 1;
		buyPrompt.setUpgrade(upgrade);
	}

	/** Closes the side prompt */
	public void closePrompt() {
		Sounds.play(Sounds.getInstance().newSwoosh(), 1.0f);
		this.state = 0;
	}

	public float getWindowX() {
		return this.scrollWindowX;
	}
	public float getWindowY() {
		return this.scrollWindowY;
	}
	public float getWindowWidth() {
		return this.scrollWindowImage.getWidth();
	}
	public float getWindowHeight() {
		return this.scrollWindowImage.getHeight();
	}
	public int getState() {
		return this.state;
	}
	public UpgradeMenuBuilder getMenuBuilder() {
		return this.menuBuilderPassed;
	}

}
