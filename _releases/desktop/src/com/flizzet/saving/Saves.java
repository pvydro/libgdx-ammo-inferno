package com.flizzet.saving;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.flizzet.headsystem.Head;
import com.flizzet.headsystem.Heads;
import com.flizzet.player.Player;
import com.flizzet.settings.ControlType;
import com.flizzet.settings.CurrentSettings;
import com.flizzet.upgrades.Upgrade;
import com.flizzet.upgradesystem.Upgrades;
import com.flizzet.weapons.Weapon;
import com.flizzet.weaponsystem.Weapons;

/**
 * Handles saving and loading.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class Saves {
    
    public static final Saves INSTANCE = new Saves();
    public static Preferences prefs = Gdx.app.getPreferences("svflizzetscj002");
    
    /** Private single use constructor */
    private Saves() {}
    
    /** Returns an instance of the Saves class */
    public static Saves getInstance() {
	return INSTANCE;
    }
    
    /** Saves to the save file */
    public void save() {
	
	saveEconomy();
	saveUpgrades();
	saveHeads();
	saveWeapons();
	saveControls();
	saveEffects();
	saveParticles();
	saveGeneral();
	saveDays();
	savePlays();
	
	prefs.flush();
	
    }
    
    /** Loads from the save file */
    public void load() {
	
	/* Assign default variables if it's the first time playing
	 * Otherwise, load everything */
	CurrentSettings.getInstance().playedBefore = prefs.getBoolean("playedBefore");
	if (!CurrentSettings.getInstance().playedBefore) {
	    CurrentSettings.getInstance().controls = ControlType.CONSOLE;
	    CurrentSettings.getInstance().blood = false;
	    CurrentSettings.getInstance().textParticles = true;
	    CurrentSettings.getInstance().groundParticles = false;
	    CurrentSettings.getInstance().bulletHits = false;
	    CurrentSettings.getInstance().platformLightParticles = true;
	    CurrentSettings.getInstance().lavaParticles = false;
	    CurrentSettings.getInstance().enemyHits = true;
	    CurrentSettings.getInstance().enemyDeath = true;
	    CurrentSettings.getInstance().playerLand = true;
	    CurrentSettings.getInstance().softLight = true;
	    CurrentSettings.getInstance().screenFlash = true;
	    CurrentSettings.getInstance().screenShake = true;
	    CurrentSettings.getInstance().bugs = true;
	} else {
	    loadEconomy();
	    loadUpgrades();
	    loadHeads();
	    loadWeapons();
	    loadControls();
	    loadEffects();
	    loadParticles();
	    loadGeneral();
	    loadDays();
	    loadPlays();
	}
	
    }
    
    /** Loads economy elements from the save file */
    public void loadEconomy() {
	/* Economy */
	Player.getInstance().getScore().setCoins(prefs.getInteger("coins"));
	Player.getInstance().getScore().setBills(prefs.getInteger("bills"));
	Player.getInstance().getScore().setPlatforms(prefs.getInteger("platforms"));
	Player.getInstance().getScore().setHighScore(prefs.getInteger("highscore"));
    }

    /** Loads upgrade elements from the save file */
    public void loadUpgrades() {
	for (Upgrade u : Upgrades.getInstance().getUpgrades()) {
	    if (prefs.getBoolean(u.getName() + " bought")) {
		Upgrades.getInstance().buy(u.getClass(), false);
	    }
	    if (prefs.getBoolean(u.getName() + " equipped")) {
		Upgrades.getInstance().equip(u.getClass());
		Upgrades.getInstance().setCurrentSlot(Upgrades.getInstance().getCurrentSlot() + 1);
	    }
	}
    }
    
    /** Loads head elements from the save file */
    public void loadHeads() {
	for (Head h : Heads.getInstance().getHeads()) {
	    if (prefs.getBoolean(h.getName() + " bought")) {
		Heads.getInstance().buy(h.getClass(), false);
	    }
	    
	    if (prefs.getBoolean(h.getName() + " equipped")) {
		Heads.getInstance().equip(h.getClass());
	    }
	}
    }
    
    /** Loads weapon elements from the save file */
    public void loadWeapons() {
	for (Weapon w : Weapons.getInstance().getWeapons()) {
	    w.setTotalKills(prefs.getInteger(w.getName() + " totalKills"));
	    
	    if (prefs.getBoolean(w.getName() + " gold")) {
		Weapons.getInstance().setGold(w.getClass());
	    }
	    if (prefs.getBoolean(w.getName() + " bought")) {
		Weapons.getInstance().buy(w.getClass(), false);
	    }
	    if (prefs.getBoolean(w.getName() + " equipped")) {
		Weapons.getInstance().equip(w.getClass());
	    }
	}
    }
    
    /** Loads control setting from the save file */
    public void loadControls() {
	CurrentSettings.getInstance().controls = ControlType.values()[prefs.getInteger("controlType")];
    }
    
    /** Loads effect settings from the save file */
    public void loadEffects() {
	CurrentSettings.getInstance().collideParticles = prefs.getBoolean("collideParticles");
	CurrentSettings.getInstance().softLight = prefs.getBoolean("softLight");
	CurrentSettings.getInstance().screenFlash = prefs.getBoolean("screenFlash");
	CurrentSettings.getInstance().screenShake = prefs.getBoolean("screenShake");
	CurrentSettings.getInstance().bugs = prefs.getBoolean("bugs");
    }
    
    /** Loads particle settings from the save file */
    public void loadParticles() {
	CurrentSettings.getInstance().blood = prefs.getBoolean("bloodParticles");
	CurrentSettings.getInstance().textParticles = prefs.getBoolean("textParticles");
	CurrentSettings.getInstance().groundParticles = prefs.getBoolean("walkingParticles");
	CurrentSettings.getInstance().bulletHits = prefs.getBoolean("bulletHitParticles");
	CurrentSettings.getInstance().platformLightParticles = prefs.getBoolean("lightParticles");
	CurrentSettings.getInstance().lavaParticles = prefs.getBoolean("lavaParticles");
	CurrentSettings.getInstance().enemyHits = prefs.getBoolean("enemyHitParticles");
	CurrentSettings.getInstance().enemyDeath = prefs.getBoolean("enemyDeathParticles");
	CurrentSettings.getInstance().playerLand = prefs.getBoolean("landParticles");
    }
    
    /** Loads general settings from the save file */
    public void loadGeneral() {
	CurrentSettings.getInstance().music = prefs.getBoolean("music");
	CurrentSettings.getInstance().sound = prefs.getBoolean("sound");
	CurrentSettings.getInstance().doubleTapSelect = prefs.getBoolean("doubleTap");
    }
    
    /** Loads total days in a row data from the save file */
    public void loadDays() {
	CurrentSettings.getInstance().lastPlayedDay = prefs.getInteger("lastPlayedDay");
	CurrentSettings.getInstance().totalDays = prefs.getInteger("totalDays");
    }
    
    /** Loads total plays from the save file */
    public void loadPlays() {
	CurrentSettings.getInstance().totalPlays = prefs.getInteger("totalPlays");
	CurrentSettings.getInstance().askedToRate = prefs.getBoolean("askedToRate");
    }
    
    
    
    
    /** Saves the economy elements to the save file */
    public void saveEconomy() {
	prefs.putInteger("coins", Player.getInstance().getScore().getTotalCoins());
	prefs.putInteger("bills", Player.getInstance().getScore().getTotalBills());
	prefs.putInteger("platforms", Player.getInstance().getScore().getTotalPlatforms());
	prefs.putInteger("highscore", Player.getInstance().getScore().getHighScore());
	
	prefs.putBoolean("playedBefore", true);
	
	prefs.flush();
    }
    
    /** Saves the upgrade elements to the save file */
    public void saveUpgrades() {
	for (Upgrade u : Upgrades.getInstance().getUpgrades()) {
	    prefs.putBoolean(u.getName() + " bought", u.isBought());
	    prefs.putBoolean(u.getName() + " equipped", u.isEquipped());
	}
	prefs.flush();
    }
    
    /** Saves the head elements to the save file */
    public void saveHeads() {
	for (Head h : Heads.getInstance().getHeads()) {
	    prefs.putBoolean(h.getName() + " bought", h.isBought());
	    prefs.putBoolean(h.getName() + " equipped", h.isEquipped());
	}
	prefs.flush();
    }
    
    /** Saves the weapon elements to the save file */
    public void saveWeapons() {
	for (Weapon w : Weapons.getInstance().getWeapons()) {
	    prefs.putBoolean(w.getName() + " bought", w.isBought());
	    prefs.putBoolean(w.getName() + " equipped", w.isEquipped());
	    prefs.putBoolean(w.getName() + " gold", w.isGold());
	    prefs.putInteger(w.getName() + " totalKills", w.getTotalKills());
	}
	prefs.flush();
    }
    
    /** Saves controls settings to the save file */
    public void saveControls() {
	prefs.putInteger("controlType", CurrentSettings.getInstance().controls.ordinal());
	prefs.flush();
    }
    
    /** Saves effects settings to the save file */
    public void saveEffects() {
	prefs.putBoolean("collideParticles", CurrentSettings.getInstance().collideParticles);
	prefs.putBoolean("softLight", CurrentSettings.getInstance().softLight);
	prefs.putBoolean("screenFlash", CurrentSettings.getInstance().screenFlash);
	prefs.putBoolean("screenShake", CurrentSettings.getInstance().screenShake);
	prefs.putBoolean("bugs", CurrentSettings.getInstance().bugs);
    }
    
    /** Saves particle settings to the save file */
    public void saveParticles() {
	prefs.putBoolean("bloodParticles", CurrentSettings.getInstance().blood);
	prefs.putBoolean("textParticles", CurrentSettings.getInstance().textParticles);
	prefs.putBoolean("walkingParticles", CurrentSettings.getInstance().groundParticles);
	prefs.putBoolean("bulletHitParticles", CurrentSettings.getInstance().bulletHits);
	prefs.putBoolean("lightParticles", CurrentSettings.getInstance().platformLightParticles);
	prefs.putBoolean("lavaParticles", CurrentSettings.getInstance().lavaParticles);
	prefs.putBoolean("enemyHitParticles", CurrentSettings.getInstance().enemyHits);
	prefs.putBoolean("enemyDeathParticles", CurrentSettings.getInstance().enemyDeath);
	prefs.putBoolean("landParticles", CurrentSettings.getInstance().playerLand);
	prefs.flush();
    }
    
    /** Saves general settings to the save file */
    public void saveGeneral() {
	prefs.putBoolean("music", CurrentSettings.getInstance().music);
	prefs.putBoolean("sound", CurrentSettings.getInstance().sound);
	prefs.putBoolean("doubleTap", CurrentSettings.getInstance().doubleTapSelect);
	prefs.flush();
    }
    
    /** Saves days played data to the save file */
    public void saveDays() {
	prefs.putInteger("lastPlayedDay", CurrentSettings.getInstance().lastPlayedDay);
	prefs.putInteger("totalDays", CurrentSettings.getInstance().totalDays);
	prefs.flush();
    }
    
    /** Saves total plays to the save file */
    public void savePlays() {
	prefs.putInteger("totalPlays", CurrentSettings.getInstance().totalPlays);
	prefs.putBoolean("askedToRate", CurrentSettings.getInstance().askedToRate);
	prefs.flush();
    }
    
    public Preferences get(Preferences prefs)	{ return prefs; }
    
}
