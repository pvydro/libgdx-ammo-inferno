package com.flizzet.controlsettingsmenu;

import com.flizzet.settings.ControlType;

/**
 * Concrete tilt control option.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
class TiltControlOption extends ControlOption {

	/** Default instantiable constructor */
	public TiltControlOption(ControlSelector selector) {
		super(selector, ControlType.TILT, "tilt");
	}

}
