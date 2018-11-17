package com.flizzet.music;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.flizzet.settings.CurrentSettings;

/**
 * Controls music loading and playback.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class MusicHandler {

	private static final MusicHandler INSTANCE = new MusicHandler();

	private Music menuMusic;

	/** Returns an instance of the MusicHandler class */
	public static MusicHandler getInstance() {
		return INSTANCE;
	}
	/** Suppress default constructor for noninstantiability */
	private MusicHandler() {
	}

	/** Loads {@link Music} for later use */
	public void loadMusic() {
		menuMusic = Gdx.audio
				.newMusic(Gdx.files.internal("sounds/music/menumusic.ogg"));
	}

	/** Plays a {@link Music} */
	public void play(Music music, boolean looping) {
		if (!music.isPlaying() && CurrentSettings.getInstance().music) {
			music.setVolume(0.5f);
			music.setLooping(looping);
			music.play();
		}
	}

	/** Stops a {@link Music} */
	public void stop(Music music) {
		if (music.isPlaying())
			music.stop();
	}

	public Music getMenuMusic() {
		return this.menuMusic;
	}

}
