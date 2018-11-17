package com.flizzet.startmenu;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.camera.MainCamera;
import com.flizzet.guicomponents.GuiComponent;
import com.flizzet.menubuilders.StartMenuBuilder;
import com.flizzet.settings.CurrentSettings;
import com.flizzet.utils.FontUtils;

/**
 * Start Menu.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class StartMenu extends GuiComponent {

	private StartMenuBuilder menuBuilder = new StartMenuBuilder();

	 private String version = "beta " + CurrentSettings.getInstance().version;
	 private BitmapFont versionFont = FontUtils.UPHEAVAL_50;
	 private float versionX, versionY;

	/** Default instantiable constructor */
	public StartMenu() {
		super(0, 0);

		menuBuilder.buildMenu();
		versionFont.setUseIntegerPositions(false);
	}

	@Override
	public void update(float delta) {
		 versionX = 5;
		 versionY = MainCamera.getInstance().getHeight() - 5;
	}

	@Override
	public void render(SpriteBatch batch) {
		 versionFont.draw(batch, version, versionX, versionY);
	}

	@Override
	public void triggered() {

	}

}
