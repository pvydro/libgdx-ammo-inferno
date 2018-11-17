package com.flizzet.controlsettingsmenu;

import com.flizzet.settings.ControlType;

/**
 * Concrete arrows control option.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
class ArrowsControlOption extends ControlOption {

    /** Default instantiable constructor */
    public ArrowsControlOption(ControlSelector selector) {
	super(selector, ControlType.HORIZONTALARROWS, "arrow keys");
    }

}
