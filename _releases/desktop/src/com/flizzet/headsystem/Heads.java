package com.flizzet.headsystem;

import java.util.ArrayList;

import com.flizzet.heads.*;
import com.flizzet.player.Player;
import com.flizzet.upgradesystem.AlreadyLoadedException;

/**
 * Handles purchasing and equipping heads.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class Heads {

    private static final Heads INSTANCE = new Heads();
    
    private ArrayList<Head> heads = new ArrayList<Head>();
    private Head currentlyEquipped;
    
    
    /** Returns an instance of the Heads class*/
    public static Heads getInstance() {
	return INSTANCE;
    }
    /** Private single use constructor */
    private Heads() {}
    
    /** Sets up the Head ArrayList 
     * @throws AlreadyLoadedException Heads class is already loaded */
    public void load() throws AlreadyLoadedException {
	if (!heads.isEmpty()) {
	    throw new AlreadyLoadedException("Heads class is already loaded");
	}
	
	heads.add(new MinerHead());		// 0
	heads.add(new KnightHead());		// 1
	heads.add(new PirateHead());		// 2
	heads.add(new PoliceHead());		// 3
	heads.add(new RacerHead());		// 4
	heads.add(new FirefighterHead());	// 5
	heads.add(new BigPumpHead());		// 6
	heads.add(new EskimoHead());		// 7
	heads.add(new ElvisHead());		// 8
	heads.add(new AngelHead());		// 9
	heads.add(new KatHead());		// 10
	heads.add(new KarateKingHead());	// 11
	heads.add(new BalloonHead());		// 12
	heads.add(new AstronautHead());		// 13
	heads.add(new NarioHead());		// 14
	heads.add(new NinjaHead());		// 15
	heads.add(new SkeletonHead());		// 16
	heads.add(new RobotHead());		// 17
	heads.add(new DinoHead());		// 18
	heads.add(new GoldHead());		// 19
	
    }
    
    /** Returns whether or not a specific head is bought.
     * @param <E> the head type 
     * @throws NullPointerException The upgradeType (E) is not in the Heads ArrayList */
    public <E> boolean isBought(Class<E> headType) {
	for (Head h : heads) {
	    if (headType.isInstance(h)) {
		if (h.isBought()) {
		    return true;
		} else {
		    return false;
		}
	    }
	}
	throw new NullPointerException("Head type " + headType + " is not in the Heads ArrayList");
    }
    
    /** Returns whether or not a specific head is equipped.
     * @param <E> the upgrade type
     * @throws NullPointerException The headType (E) is not in the Heads ArrayList */
    public <E> boolean isEquipped(Class<E> headType) {
	for (Head h : heads) {
	    if (headType.isInstance(h)) {
		if (h.isEquipped()) {
		    return true;
		} else {
		    return false;
		}
	    }
	}
	throw new NullPointerException("Head type " + headType + " is not in the Heads ArrayList");
    }

    /** Purchases the head defined
     * @param <E> the head type */
    public <E> void buy(Class<E> headType, boolean pay) {
	for (Head h : heads) {
	    if (headType.isInstance(h)) {
		if (!h.isBought()) {
		    if (pay) {
			Player.getInstance().getScore().removeFromCurrentCoins(h.getPrice());
			h.setBought(true);
		    } else {
			h.setBought(true);
		    }
		}
	    }
	}
    }
    
    /** Equips the head defined
     * @param <E> the head type */
    public <E> void equip(Class<E> headType) {
	for (Head h : heads) {
	    if (headType.isInstance(h)) {
		if (!h.isEquipped()) {
		    h.setEquipped(true);
		    currentlyEquipped = h;
		    Player.getInstance().getHead().setHead(h);
		}
	    } else {
		h.setEquipped(false);
	    }
	}
    }
    
    /** Returns the head defined 
     * @param <E> the head type
     * @throws NullPointerException The headType (E) is not in the Heads ArrayList */
    public <E> Head getHead(Class<E> headType) {
	for (Head h : heads) {
	    if (headType.isInstance(h)) {
		return h;
	    }
	}
	throw new NullPointerException("Head type " + headType + " is not in the Heads ArrayList");
    }
    
    /** Returns the currently equipped head */
    public Head getEquippedHead() {
	return currentlyEquipped;
    }
    
    /** Returns all loaded heads */
    public ArrayList<Head> getHeads() {
	return heads;
    }
    
}
