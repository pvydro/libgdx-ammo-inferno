package com.flizzet.revivemenu;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.guicomponents.GuiComponent;
import com.flizzet.menubuilders.RevivedMenuBuilder;

/**
 * Displays after playing an advertisement to give the user a second before
 * reviving.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class RevivedMenu extends GuiComponent {

	private static final RevivedMenu INSTANCE = new RevivedMenu();

	private RevivedMenuBuilder menuBuilder = new RevivedMenuBuilder();

	/** Returns an instance of the RevivedMenu class */
	public static RevivedMenu getInstance() {
		return INSTANCE;
	}
	/** Default instantiable constructor */
	public RevivedMenu() {
		super(0, 0);
	}

	@Override
	public void update(float delta) {
	}

	@Override
	public void render(SpriteBatch batch) {
	}

	/** Adds to screen */
	public void enable() {
		menuBuilder.buildMenu();
	}

	public void disable() {
		menuBuilder.disable();
	}

	@Override
	public void triggered() {
	}

}
