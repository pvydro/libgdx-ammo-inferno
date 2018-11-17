package com.flizzet.weaponsystem;

import java.util.ArrayList;

import com.flizzet.player.Player;
import com.flizzet.upgradesystem.AlreadyLoadedException;
import com.flizzet.weapons.ApertureWeapon;
import com.flizzet.weapons.AshWeapon;
import com.flizzet.weapons.PrismWeapon;
import com.flizzet.weapons.EclipseWeapon;
import com.flizzet.weapons.EonWeapon;
import com.flizzet.weapons.MantaWeapon;
import com.flizzet.weapons.MonarchWeapon;
import com.flizzet.weapons.NoxWeapon;
import com.flizzet.weapons.RiftWeapon;
import com.flizzet.weapons.VixWeapon;
import com.flizzet.weapons.Weapon;

/**
 * Handles weapon purchasing and equipping.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class Weapons {
    
    private static final Weapons INSTANCE = new Weapons();
    
    private ArrayList<Weapon> weapons = new ArrayList<Weapon>();
    private Weapon currentlyEquipped;

    /** Returns an instance of the Weapons class */
    public static Weapons getInstance() {
	return INSTANCE;
    }
    /** Private single use constructor */
    public Weapons() {}
    
    /** Sets up the Weapon ArrayList 
     * @throws AlreadyLoaadedException Weapons class is already loaded */
    public void load() throws AlreadyLoadedException {
	if (!weapons.isEmpty()) {
	    throw new AlreadyLoadedException("Weapons class is already loaded");
	}
	
	weapons.add(new NoxWeapon());		// 0
	weapons.add(new PrismWeapon());		// 1
	weapons.add(new AshWeapon());		// 2
	weapons.add(new MonarchWeapon());	// 3
	weapons.add(new EonWeapon());		// 4
	weapons.add(new EclipseWeapon());	// 5
	weapons.add(new RiftWeapon());		// 6
	weapons.add(new ApertureWeapon());	// 7
	weapons.add(new VixWeapon());		// 8
	weapons.add(new MantaWeapon());		// 9
	
    }
    
    /** Returns whether or not a specific weapon is bought.
     * @param <E> the weapon type
     * @throws NullPointerException The weaponType (E) is not in the Weapons ArrayList. */
    public <E> boolean isBought(Class<E> weaponType) {
	for (Weapon w : weapons) {
	    if (weaponType.isInstance(w)) {
		if (w.isBought()) {
		    return true;
		} else {
		    return false;
		}
	    }
	}
	throw new NullPointerException("The weaponType " + weaponType + " is not in the Weapons ArrayList");
    }
    
    /** Returns whether or not a specific weapon is equipped.
     * @param <E> the weapon type
     * @throws NullPointerException The weaponType (E) is not in the Weapons ArrayList. */
    public <E> boolean isEquipped(Class<E> weaponType) {
	for (Weapon w : weapons) {
	    if (weaponType.isInstance(w)) {
		if (w.isEquipped()) {
		    return true;
		} else {
		    return false;
		}
	    }
	}
	throw new NullPointerException("The weaponType " + weaponType + " is not in the Weapons ArrayList");
    }
    
    /** Purchases the weapon defined
     * @param <E> the weapon type */
    public <E> void buy(Class<E> weaponType, boolean pay) {
	for (Weapon w : weapons) {
	    if (weaponType.isInstance(w)) {
		if (!w.isBought()) {
		    if (pay) {
			Player.getInstance().getScore().removeFromCurrentCoins(w.getPrice());
			w.setBought(true);
		    } else {
			w.setBought(true);
		    }
		}
	    }
	}
    }
    
    /** Equips the weapon defined based on the class type
     * @param <E> the weapon type */
    public <E> void equip(Class<E> weaponType) {
	for (Weapon w : weapons) {
	    if (weaponType.isInstance(w)) {
		if (!w.isEquipped()) {
		    w.setEquipped(true);
		    currentlyEquipped = w;
		    Player.getInstance().setWeapon(w);
		}
	    } else {
		w.setEquipped(false);
	    }
	}
    }
    
    /** Sets the weapon defined to gold or not gold
     * @param <E> the weapon type */
    public <E> void setGold(Class<E> weaponType) {
	for (Weapon w : weapons) {
	    //if (weaponType.isInstance(w)) {
		w.setGold(!w.isGold());
		System.out.println("Hitterstons!");
	    //}
	}
    }
    
    /** Returns the weapon defined 
     * @param <E> the weapon type
     * @throws NullPointerException The weaponType (E) is not in the Weapons ArrayList */
    public <E> Weapon getWeapon(Class<E> weaponType) {
	for (Weapon w : weapons) {
	    if (weaponType.isInstance(w)) {
		return w;
	    }
	}
	throw new NullPointerException("Weapon type " + weaponType + " is not in the Weapons ArrayList");
    }
    public Weapon getWeapon(int weaponIndex) {
	return weapons.get(weaponIndex);
    }
    
    /** Returns the currently equipped weapon */
    public Weapon getEquippedWeapon() {
	return currentlyEquipped;
    }
    
    /** Returns all loaded heads */
    public ArrayList<Weapon> getWeapons() {
	return weapons;
    }

}
