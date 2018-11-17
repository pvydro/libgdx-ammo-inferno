package com.flizzet.utils;

/**
 * Stopwatch utility with a countdown.
 * 
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class StopWatch {

    private long startTime = 0;
    private long stopTime = 0;
    private long count;
    private boolean running = false;

    public void start() {

	this.startTime = System.currentTimeMillis();
	this.running = true;

    }

    /* Create countdown */
    public void startCountdown(int time) {

	this.startTime = System.currentTimeMillis();
	count = time;
	this.running = true;

    }

    /* Stop the current function of the stop watch */
    public void stop() {

	this.stopTime = System.currentTimeMillis();
	this.running = false;

    }

    /* Return the elapsed time in milliseconds */
    public long getElapsedMilli() {

	long elapsed;
	if (running) {
	    elapsed = System.currentTimeMillis() - startTime;
	} else {
	    elapsed = (stopTime - startTime);
	}
	return elapsed;

    }

    /* Return the elapsed time in seconds */
    public long getElapsedSecs() {

	long elapsed;
	if (running) {
	    elapsed = ((System.currentTimeMillis() - startTime) / 1000);
	} else {
	    elapsed = ((stopTime - startTime) / 1000);
	}
	return elapsed;
    }

    /* Return whether or not the countdown is complete */
    public boolean isCountdownComplete() {

	long elapsed;
	if (running) {
	    elapsed = ((System.currentTimeMillis() - startTime) / 1000);
	} else {
	    elapsed = ((stopTime - startTime) / 1000);
	}
	if (elapsed >= count)
	    return true;
	return false;

    }

    /* Return the elapsed time in countdown seconds */
    public long getElapsedCountSecs() {

	long elapsed;
	long totalCount;
	if (running) {
	    elapsed = (System.currentTimeMillis() - startTime) / 1000;
	} else {
	    elapsed = ((stopTime - startTime) / 1000);
	}
	totalCount = count - elapsed;

	return totalCount;

    }

    /* Stop the stopwatch and reset all its values */
    public void reset() {

	stop();
	running = false;
	startTime = System.currentTimeMillis();
	stopTime = 0;
	count = 0;

    }

    public boolean isRunning() {

	return running;

    }
}