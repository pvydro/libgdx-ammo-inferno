package com.flizzet.controlsettingsmenu;

import com.flizzet.settings.ControlType;

/**
 * Concrete console control option.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
class ConsoleControlOption extends ControlOption {

    /** Default instantiable constructor */
    public ConsoleControlOption(ControlSelector selector) {
	super(selector, ControlType.CONSOLE, "console");
    }

}
