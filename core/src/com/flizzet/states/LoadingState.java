package com.flizzet.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.ads.AdManager;
import com.flizzet.heads.MinerHead;
import com.flizzet.headsystem.Heads;
import com.flizzet.loadingmenu.LoadingMenu;
import com.flizzet.main.GameManager;
import com.flizzet.music.MusicHandler;
import com.flizzet.saving.Saves;
import com.flizzet.settings.CurrentSettings;
import com.flizzet.sound.Sounds;
import com.flizzet.upgradesystem.AlreadyLoadedException;
import com.flizzet.upgradesystem.Upgrades;
import com.flizzet.utils.FontUtils;
import com.flizzet.weapons.NoxWeapon;
import com.flizzet.weaponsystem.Weapons;

/**
 * Shows loading of all assets.
 *
 * @author Lucas Cirillo, Pedro Dutra (2017)
 * @version 1.0
 */
public class LoadingState extends GameState {

	private LoadingMenu loadingMenu;

	private boolean stateExiting = false;

	public LoadingState(int stateId, GameManager gameManager) {
		super(stateId, gameManager);
	}

	@Override
	public void create() {
	}

	/** Loads all assets */
	@Override
	public void entered() {

		gameManager.assets.load();
		FontUtils.loadFonts();

		loadingMenu = new LoadingMenu();
	}

	@Override
	public void update(float delta) {

		gameManager.assetManager.update();
		loadingMenu.update(delta);

		/* Single call */
		if (!stateExiting && gameManager.assetManager.update()) {
			/* Load upgrades, heads */
			try {
				Upgrades.getInstance().loadUpgrades();
				Heads.getInstance().load();
				Heads.getInstance().buy(MinerHead.class, false);
				Heads.getInstance().equip(MinerHead.class);
				Weapons.getInstance().load();
				Weapons.getInstance().equip(NoxWeapon.class);
			} catch (AlreadyLoadedException e) {
				e.printStackTrace();
			}

			/* Load save data */
			Saves.getInstance().load();
			/* Load sounds */
			Sounds.getInstance().loadSounds();
			/* Load music */
			MusicHandler.getInstance().loadMusic();

			/* Add this play to the totalPlays */
			CurrentSettings.getInstance().totalPlays++;

			/* Enter next state */
			gameManager.stateManager.enterState(gameManager.stateId_start);
			stateExiting = true;
		}

	}

	@Override
	public void render(SpriteBatch batch) {
		loadingMenu.render(batch);
	}

	@Override
	public void exited() {
		AdManager.getInstance().hideBannerAd();
		entityContainer.clear();
		particleContainer.clear();
		guiContainer.clear();
	}

	@Override
	public void dispose() {

	}

}
