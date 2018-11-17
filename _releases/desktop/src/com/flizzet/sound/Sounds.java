package com.flizzet.sound;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.flizzet.settings.CurrentSettings;

/**
 * Holds all sounds needed through the game.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class Sounds {
    
    private static Sounds INSTANCE = new Sounds();
    private Random random = new Random();
    
    /* Play */
    public Sound shootSound;
    public Sound lavaSound;
    public Sound enemyHitSound;
    public Sound shellSound;
    public Sound wallHitSound;
    public Sound playerHitSound;
    public Sound playerDeathSound;
    public Sound zombieGroanSound;
    public Sound playerLandSound;
    public Sound chestOpenSound;
    public Sound highScoreSound;
    public Sound nextStageSound;
    public Sound trapShootSound;
    public Sound sawSound;
    public Sound selectEnterSound;
    public Sound selectExitSound;
    public Sound selectExitEffectSound;
    public Sound selectSelectSound;
    public Sound flyFlapSound;
    public Sound reloadSound;
    
    /* Menu */
    public Sound clickSound;
    public Sound transitionCloseSound;
    public Sound transitionOpenSound;
    public Sound infoSound;
    public Sound notificationSound;
    public Sound buySound;
    public Sound swoosh1Sound;
    public Sound swoosh2Sound;
    
    private List<Sound> sounds = new ArrayList<Sound>();
    
    /** Returns an instance of the Sounds class */
    public static Sounds getInstance() {
	return INSTANCE;
    }
    /** Suppress default constructor for noninstantiability */
    private Sounds() {}
    
    /** Loads all sounds into memory */
    public void loadSounds() {
	/* Play */
	sounds.add(shootSound = Gdx.audio.newSound(Gdx.files.internal("sounds/gun/gunshot.ogg")));
	sounds.add(lavaSound = Gdx.audio.newSound(Gdx.files.internal("sounds/map/lava.ogg")));
	sounds.add(enemyHitSound = Gdx.audio.newSound(Gdx.files.internal("sounds/enemies/enemyhit.ogg")));
	sounds.add(shellSound = Gdx.audio.newSound(Gdx.files.internal("sounds/gun/shell.ogg")));
	sounds.add(wallHitSound = Gdx.audio.newSound(Gdx.files.internal("sounds/gun/wallhit.ogg")));
	sounds.add(playerHitSound = Gdx.audio.newSound(Gdx.files.internal("sounds/player/hit.ogg")));
	sounds.add(playerDeathSound = Gdx.audio.newSound(Gdx.files.internal("sounds/player/death.ogg")));
	sounds.add(zombieGroanSound = Gdx.audio.newSound(Gdx.files.internal("sounds/enemies/zombiegroan.ogg")));
	sounds.add(playerLandSound = Gdx.audio.newSound(Gdx.files.internal("sounds/player/land.ogg")));
	sounds.add(chestOpenSound = Gdx.audio.newSound(Gdx.files.internal("sounds/score/chest.ogg")));
	sounds.add(highScoreSound = Gdx.audio.newSound(Gdx.files.internal("sounds/score/highscore.ogg")));
	sounds.add(nextStageSound = Gdx.audio.newSound(Gdx.files.internal("sounds/score/nextstage.ogg")));
	sounds.add(trapShootSound = Gdx.audio.newSound(Gdx.files.internal("sounds/enemies/guntrapshoot.ogg")));
	sounds.add(sawSound = Gdx.audio.newSound(Gdx.files.internal("sounds/enemies/saw.ogg")));
	sounds.add(infoSound = Gdx.audio.newSound(Gdx.files.internal("sounds/menu/info.ogg")));
	sounds.add(selectEnterSound = Gdx.audio.newSound(Gdx.files.internal("sounds/enemyselect/enter.ogg")));
	sounds.add(selectExitSound = Gdx.audio.newSound(Gdx.files.internal("sounds/enemyselect/exit.ogg")));
	sounds.add(selectExitEffectSound = Gdx.audio.newSound(Gdx.files.internal("sounds/enemyselect/exiteffect.ogg")));
	sounds.add(selectSelectSound = Gdx.audio.newSound(Gdx.files.internal("sounds/enemyselect/select.ogg")));
	sounds.add(flyFlapSound = Gdx.audio.newSound(Gdx.files.internal("sounds/enemies/flyflap.ogg")));
	sounds.add(reloadSound = Gdx.audio.newSound(Gdx.files.internal("sounds/gun/reload.ogg")));
	
	/* Menu */
	sounds.add(clickSound = Gdx.audio.newSound(Gdx.files.internal("sounds/menu/click.ogg")));
	sounds.add(transitionCloseSound = Gdx.audio.newSound(Gdx.files.internal("sounds/menu/transitionclose.ogg")));
	sounds.add(transitionOpenSound = Gdx.audio.newSound(Gdx.files.internal("sounds/menu/transitionopen.ogg")));
	sounds.add(notificationSound = Gdx.audio.newSound(Gdx.files.internal("sounds/menu/notification.ogg")));
	sounds.add(buySound = Gdx.audio.newSound(Gdx.files.internal("sounds/menu/buy.ogg")));
	sounds.add(swoosh1Sound = Gdx.audio.newSound(Gdx.files.internal("sounds/menu/swoosh1.ogg")));
	sounds.add(swoosh2Sound = Gdx.audio.newSound(Gdx.files.internal("sounds/menu/swoosh2.ogg")));
    }
    
    /** Removes all sounds from memory */
    public void dispose() {
	for (Sound s : sounds) {
	    s.dispose();
	}
    }
    
    /** Finds a random swoosh sound */
    public Sound newSwoosh() {
	int chance = random.nextInt(2);
	switch (chance) {
	case 0:
	    return swoosh1Sound;
	case 1:
	    return swoosh2Sound;
	}
	
	return null;
    }
    
    /** Plays a sound multiplied by the game volume */
    public static void play(Sound sound, float volume) {
	if (CurrentSettings.getInstance().sound) {
	    sound.play(volume * CurrentSettings.getInstance().soundVolume);
	}
    }
    /** Plays a sound multiplied by the game volume with pitch */
    public static void play(Sound sound, float volume, float pitch) {
	if (CurrentSettings.getInstance().sound) {
	    long id = sound.play(volume * CurrentSettings.getInstance().soundVolume);
	    sound.setPitch(id, pitch);
	}
    }
    /** Plays a sound multiplied by the game volume with looping */
    public static void play(Sound sound, float volume, boolean looping) {
	if (CurrentSettings.getInstance().sound) {
	    long id = sound.play(volume * CurrentSettings.getInstance().soundVolume);
	    sound.setLooping(id, looping);
	}
    }

}
