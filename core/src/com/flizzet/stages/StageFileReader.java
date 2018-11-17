package com.flizzet.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.flizzet.batenemy.FlyingBatEnemySpawner;
import com.flizzet.enemies.MainEnemySpawner;
import com.flizzet.eyeenemy.FlyingEyeEnemySpawner;
import com.flizzet.flyzombieenemy.FlyingZombieEnemySpawner;
import com.flizzet.map.MapManager;

/**
 * Reads stage files and applies them.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class StageFileReader {

	private String[] stageDirs;

	/** Single use constructor */
	public StageFileReader() {

		stageDirs = new String[]{"stages/stage1.stg", "stages/stage2.stg",
				"stages/stage3.stg", "stages/stage4.stg", "stages/stage5.stg",
				"stages/stage6.stg", "stages/stage7.stg", "stages/stage8.stg",
				"stages/stage9.stg", "stages/stage10.stg"};
	}

    /** Uses a stage number to fetch a stage file and load it in */
    public void setStage(int currentStage) {
		try {
		    this.setStage(Gdx.files.internal(stageDirs[currentStage - 1]));		// Use the current stage - 2 for the order in the array
		} catch (IndexOutOfBoundsException e) {
		    e.printStackTrace();
		    System.err.println("Past maximum stages. Out of bounds in StageFileReader.");
		}
    }
    
    /** Reads a file and sets all spawner parameters and MapManager parameters
     * @see MapManager */
    public void setStage(FileHandle fileHandle) {	
		String fileText = fileHandle.readString();
		String wordsArray[] = fileText.split("\\r?\\n");	// Split the document per line
	
		/* Go through each line and pull the corresponding parameter and apply it to the game */
		int currentLine = 0;
		for (String s : wordsArray) {
		    currentLine++;
		    switch (currentLine) {
		    case 1:					// Eye enemy enabled 				BOOLEAN
			if (s.split(" : ")[1].equals("true"))	MainEnemySpawner.getInstance().setEyes(true);
			else					MainEnemySpawner.getInstance().setEyes(false);
			break;
		    case 2:					// Eye spawn cooldown 				INTEGER
			FlyingEyeEnemySpawner.getInstance().setCooldown(Integer.valueOf(s.split(" : ")[1]));
			break;
		    case 3:					// Bat enemy enabled 				BOOLEAN
			if (s.split(" : ")[1].equals("true"))	MainEnemySpawner.getInstance().setBats(true);
			else					MainEnemySpawner.getInstance().setBats(false);
			break;
		    case 4:					// Bat spawn cooldown				INTEGER
			FlyingBatEnemySpawner.getInstance().setCooldown(Integer.valueOf(s.split(" : ")[1]));
			break;
		    case 5:					// Flying zombie enemy enabled		BOOLEAN
			if (s.split(" : ")[1].equals("true"))	MainEnemySpawner.getInstance().setFlyingZombies(true);
			else					MainEnemySpawner.getInstance().setFlyingZombies(false);
			break;
		    case 6:					// Flying zombie enemy cooldown		INTEGER
			FlyingZombieEnemySpawner.getInstance().setCooldown(Integer.valueOf(s.split(" : ")[1]));
			break;
		    case 7:					// Zombie enemy enabled				BOOLEAN
			if (s.split(" : ")[1].equals("true"))	MainEnemySpawner.getInstance().setZombies(true);
			else					MainEnemySpawner.getInstance().setZombies(false);
			break;
		    case 8:					// Zombie difficulty				INTEGER
			MainEnemySpawner.getInstance().setZombieDifficulty(Integer.valueOf(s.split(" : ")[1]));
			break;
		    case 9:					// Spikes enemy enabled				BOOLEAN
			if (s.split(" : ")[1].equals("true"))	MainEnemySpawner.getInstance().setSpikes(true);
			else					MainEnemySpawner.getInstance().setSpikes(false);
			break;
		    case 10:					// Saw enemy enabled			BOOLEAN
			if (s.split(" : ")[1].equals("true"))	MainEnemySpawner.getInstance().setSaws(true);
			else					MainEnemySpawner.getInstance().setSaws(false);
			break;
		    case 11:					// Trap enemy enabled			BOOLEAN
			if (s.split(" : ")[1].equals("true"))	MainEnemySpawner.getInstance().setTraps(true);
			else					MainEnemySpawner.getInstance().setTraps(false);
			break;
		    }
		}
    }

}
