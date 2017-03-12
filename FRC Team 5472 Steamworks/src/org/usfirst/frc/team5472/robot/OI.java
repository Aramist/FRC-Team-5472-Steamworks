package org.usfirst.frc.team5472.robot;//5472

import edu.wpi.first.wpilibj.Joystick;

public class OI {

	private Joystick stick;
	private Joystick xbox;

	public Joystick getJoystick() {
		return this.stick;
	}

	public Joystick getXBOX() {
		return xbox;
	}

	public OI() {
		// Joysticks
		stick = new Joystick(0);
		xbox = new Joystick(1);
	}

}