package com.flizzet.controlsettingsmenu;

import com.flizzet.settings.ControlType;

/**
 * Concrete control option for the joystick control.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
class JoystickControlOption extends ControlOption {

    /** Default instantiable constructor */
    public JoystickControlOption(ControlSelector selector) {
	super(selector, ControlType.JOYSTICK, "joystick");
    }

}
