package com.flizzet.upgradesystem;

import java.util.ArrayList;

import com.flizzet.player.Player;
import com.flizzet.upgrades.BandaidUpgrade;
import com.flizzet.upgrades.DinkUpgrade;
import com.flizzet.upgrades.DoubleJumpUpgrade;
import com.flizzet.upgrades.EarlyBirdUpgrade;
import com.flizzet.upgrades.ExcavationistUpgrade;
import com.flizzet.upgrades.ExtraLifeUpgrade;
import com.flizzet.upgrades.LavaBootsUpgrade;
import com.flizzet.upgrades.LuckyDogUpgrade;
import com.flizzet.upgrades.StickySolesUpgrade;
import com.flizzet.upgrades.StyrofoamDartsUpgrade;
import com.flizzet.upgrades.SwiftUpgrade;
import com.flizzet.upgrades.TrapJammerUpgrade;
import com.flizzet.upgrades.Upgrade;
import com.flizzet.upgrades.ZombieLooterUpgrade;

/**
 * Main Upgrades class.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class Upgrades {
    
    private static final Upgrades INSTANCE = new Upgrades();
    
    private ArrayList<Upgrade> upgrades = new ArrayList<Upgrade>();
    private Upgrade[] currentlyEquipped = new Upgrade[3];
    private int currentSlot = 1;
    
    /** Returns an instance of the Upgrades class */
    public static Upgrades getInstance() {
	return INSTANCE;
    }
    /** Private single use constructor */
    private Upgrades() {}
    
    /** Sets up the Upgrade ArrayList 
     * @throws AlreadyLoadedException Upgrades class is already loaded. */
    public void loadUpgrades() throws AlreadyLoadedException {
	if (!upgrades.isEmpty()) {
	    throw new AlreadyLoadedException("Upgrades class is already loaded.");
	}

	upgrades.add(new StickySolesUpgrade()); 	// 0
	upgrades.add(new StyrofoamDartsUpgrade()); 	// 1
	upgrades.add(new EarlyBirdUpgrade());		// 2
	upgrades.add(new LuckyDogUpgrade());		// 3
	upgrades.add(new DoubleJumpUpgrade());		// 4
	upgrades.add(new ZombieLooterUpgrade());	// 5
	upgrades.add(new ExcavationistUpgrade());	// 6
	upgrades.add(new DinkUpgrade());		// 7
	upgrades.add(new ExtraLifeUpgrade());		// 8
	upgrades.add(new TrapJammerUpgrade());		// 9
	upgrades.add(new BandaidUpgrade());		// 10
	upgrades.add(new SwiftUpgrade());		// 11
	upgrades.add(new LavaBootsUpgrade());		// 12
    }
    
    /** Returns whether or not a specific upgrade is bought.
     * @param <E> the upgrade type 
     * @throws NullPointerException The upgradeType (E) is not in the Upgrades ArrayList */
    public <E> boolean isBought(Class<E> upgradeType) {
	for (Upgrade u : upgrades) {
	    if (upgradeType.isInstance(u)) {
		if (u.isBought()) {
		    return true;
		} else {
		    return false;
		}
	    }
	}
	throw new NullPointerException("Upgrade type " + upgradeType + " is not in the Upgrades ArrayList");
    }
    
    /** Returns whether or not a specific upgrade is equipped.
     * @param <E> the upgrade type
     * @throws NullPointerException The upgradeType (E) is not in the Upgrades ArrayList */
    public <E> boolean isEquipped(Class<E> upgradeType) {
	for (Upgrade u : upgrades) {
	    if (upgradeType.isInstance(u)) {
		if (u.isEquipped()) {
		    return true;
		} else {
		    return false;
		}
	    }
	}
	throw new NullPointerException("Upgrade type " + upgradeType + " is not in the Upgrades ArrayList");
    }
    
    /** Purchases the upgrade defined.
     * @param <E> the upgrade type */
    public <E> void buy(Class<E> upgradeType, boolean pay) {
	for (Upgrade u : upgrades) {
	    if (upgradeType.isInstance(u)) {
		if (!u.isBought()) {
		    if (pay) {
			Player.getInstance().getScore().removeFromCurrentCoins(u.getPrice());
			u.setBought(true);
		    } else {
			u.setBought(true);
		    }
		    
		}
	    }
	}
    }
    
    /** Equips the upgrade defined.
     * @param <E> The upgrade type
     * @param slot A number between 1 and 3, defines the index of the upgrade */
    public <E> void equip(Class<E> upgradeType) {
	for (Upgrade u : upgrades) {
	    if (upgradeType.isInstance(u)) {
		if (!u.isEquipped()) {
		    u.setEquipped(true); 
		    addToEquipped(u);
		}
	    }
	}
    }
    
    /** Unequips the upgrade defined.
     * @param <E> the upgrade type */
    public <E> void unequip(Class<E> upgradeType) {
	for (Upgrade u : upgrades) {
	    if (upgradeType.isInstance(u)) {
		if (u.isEquipped()) {
		    u.setEquipped(false);
		}
	    }
	}
    }
    
    /** Returns the upgrade defined
     * @param <E> the upgrade type
     * @throws NullPointerException The upgradeType (E) is not in the Upgrades ArrayList */
    public <E> Upgrade getUpgrade(Class<E> upgradeType) {
	for (Upgrade u : upgrades) {
	    if (upgradeType.isInstance(u)) {
		return u;
	    }
	}
	throw new NullPointerException("Upgrade type " + upgradeType + " is not in the Upgrades ArrayList");
    }
    
    /** Adds a new upgrade to currently equipped */
    private void addToEquipped(Upgrade upgrade) {
	if (currentlyEquipped[currentSlot - 1] != null) {
	    currentlyEquipped[currentSlot - 1].setEquipped(false);
	}
	currentlyEquipped[currentSlot - 1] = upgrade;
    }
    
    /** Returns all loaded upgrades */
    public ArrayList<Upgrade> getUpgrades() {
	return this.upgrades;
    }
    
    /** Returns all equipped upgrades */
    public Upgrade[] getEquippedUpgrades() {
	return this.currentlyEquipped;
    }
    
    public void setCurrentSlot(int newCurrentSlot)	{ this.currentSlot = newCurrentSlot; }
    public int getCurrentSlot()				{ return this.currentSlot; }
}
