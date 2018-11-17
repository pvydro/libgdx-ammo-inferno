package com.flizzet.utils;

import java.io.File;
import java.util.ArrayList;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureLoader.TextureParameter;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;

/**
 * Stores all the assets, tells manager when to load them.
 * 
 * @version 1.0
 */
public class Assets {
    
    //private ArrayList<FileHandle> assetDirs;
    
    private AssetManager manager;
    
    /** Default constructor */
    public Assets(AssetManager manager) {
	this.manager = manager;
    }
    
    /** Loads all assets from the asset directories */
    public void load() {
	
	if (Gdx.app.getType() == ApplicationType.Desktop) {
	    FileUtils.populate(FileUtils.search("/Users/Flizzet/Documents/javaWorkspace/SuperCaveJumper/android/assets/", new ArrayList<File>()));
	}
	
	FileHandle dirs = Gdx.files.internal("dirs.txt");
	String fileText = dirs.readString();
	
	String[] allFiles = fileText.split("\n");
	
	/* Filter everything to prevent half-pixels */
	TextureParameter param = new TextureParameter();
	param.minFilter = Texture.TextureFilter.Nearest;
	param.magFilter = Texture.TextureFilter.Nearest;
	
	for(String s : allFiles) {					// Iterate through all file references and load the important ones
	    
	    if(s.endsWith(".png")) {					// Found an image file; load it as a texture
		manager.load(s, Texture.class, param);
	    }
	    
	    if (s.endsWith(".ogg")) {
		manager.load(s, Sound.class);
	    }
	    
	}
	
    }

}
