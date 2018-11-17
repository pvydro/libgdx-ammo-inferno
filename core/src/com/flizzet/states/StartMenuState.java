package com.flizzet.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.ads.AdManager;
import com.flizzet.camera.MainCamera;
import com.flizzet.dailyrewards.DailyReward;
import com.flizzet.guicomponents.RatePrompt;
import com.flizzet.guicomponents.RewardPrompt;
import com.flizzet.main.GameManager;
import com.flizzet.saving.Saves;
import com.flizzet.settings.CurrentSettings;
import com.flizzet.startmenu.StartMenu;

/**
 * Holds menu elements.
 * 
 * @author Lucas Cirillo, Pedro Dutra (2017)
 * @version 1.01
 */
public class StartMenuState extends GameState {

	private StartMenu startMenu;

	public StartMenuState(int id, GameManager gameManager) {
		super(id, gameManager);
	}

	@Override
	public void create() {

	}

	@Override
	public void entered() {
		/* Set position for camera shakes */
		MainCamera.getInstance()
				.setTargetX(MainCamera.getInstance().getCamera().position.x);
		MainCamera.getInstance()
				.setTargetY(MainCamera.getInstance().getCamera().position.y);

		startMenu = new StartMenu();
		guiContainer.addToGui(startMenu);

		Saves.getInstance().loadDays();

		/*
		 * Check for a daily reward Otherwise if the player has played more than
		 * 4 times, ask them to rate for $$
		 */
		if (DailyReward.getInstance().getChecker()
				.checkForReward(CurrentSettings.getInstance().lastPlayedDay)) {
			CurrentSettings.getInstance().totalDays++;
			RewardPrompt prompt = new RewardPrompt();
			guiContainer.addToGui(prompt);
		} else {
			if (!CurrentSettings.getInstance().askedToRate) {
				if (CurrentSettings.getInstance().totalPlays > 4) {
					CurrentSettings.getInstance().askedToRate = true;
					RatePrompt prompt = new RatePrompt();
					guiContainer.addToGui(prompt);
				}
			}
		}

		CurrentSettings.getInstance().lastPlayedDay = DailyReward
				.getCurrentDay();
		Saves.getInstance().saveDays();
		Saves.getInstance().savePlays();

	}

	@Override
	public void update(float delta) {
	}

	@Override
	public void render(SpriteBatch batch) {
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
