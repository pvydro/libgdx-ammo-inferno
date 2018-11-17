package com.flizzet.dailyrewards;

import java.util.Calendar;

/**
 * Does checks for daily rewards and gives rewards.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class DailyReward {
    
    private static final DailyReward INSTANCE = new DailyReward();

    private DailyRewardChecker checker = new DailyRewardChecker();
    
    /** Returns an instance of the DailyReward class */
    public static DailyReward getInstance() {
	return INSTANCE;
    }
    /** Suppress default constructor for noninstantiability */
    private DailyReward() {}

    public DailyRewardChecker getChecker()	{ return this.checker; }
    
    public static int getCurrentDay() {
	Calendar cal = Calendar.getInstance();
	return cal.get(Calendar.DAY_OF_MONTH);
    }

}
