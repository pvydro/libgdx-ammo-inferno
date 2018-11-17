package com.flizzet.upgradesystem;

/**
 * Called when an ArrayList being filled by a load function has already been loaded.
 * </br> This is used to prevent duplicates in an ArrayList without using a Set.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class AlreadyLoadedException extends Exception {

    private static final long serialVersionUID = -3446447219261693222L;
    
    /** Default instantiable constructor */
    public AlreadyLoadedException() {}
    
    public AlreadyLoadedException(String message) {
	super(message);
    }
    
    public AlreadyLoadedException(Throwable cause) {
	super(cause);
    }
    
    public AlreadyLoadedException(String message, Throwable cause) {
	super(message, cause);
    }
    
}
