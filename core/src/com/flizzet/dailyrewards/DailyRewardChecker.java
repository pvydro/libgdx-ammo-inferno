package com.flizzet.dailyrewards;

import java.util.Calendar;

import com.flizzet.settings.CurrentSettings;

/**
 * Checks to see if a reward is available.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class DailyRewardChecker {

	/** Default instantiable constructor */
	public DailyRewardChecker() {
	}

	/**
	 * Checks to see if a reward is available by comparing dates
	 * 
	 * @param date1
	 *            - Current date
	 * @param date2
	 *            - Date being compared to
	 */
	public boolean checkForReward(int date2) {
		Calendar cal = Calendar.getInstance();
		int currentDay = cal.get(Calendar.DAY_OF_MONTH);

		/* Check if the player is playing on the same day */
		if (date2 == currentDay) {
			return false;
		}
		/* Check if the player played yesterday */
		if (date2 + 1 == currentDay) {
			return true;
		}
		if (date2 == 30 || date2 == 31) {
			if (currentDay == 1)
				return true;
		}

		/* If the player didn't play yesterday, or today, reset */
		CurrentSettings.getInstance().totalDays = 0;

		return true;
	}

	/** Returns a value of a reward */
	public int getReward() {
		return (10 + (50 * (CurrentSettings.getInstance().totalDays) / 10));
	}

}
