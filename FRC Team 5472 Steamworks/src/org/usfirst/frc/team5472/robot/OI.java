package org.usfirst.frc.team5472.robot;//5472

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {

	private Joystick stick;
	private Joystick xbox;

	public Joystick getJoystick(){
		return this.stick;
	}
	
	public Joystick getXBOX(){
		return xbox;
	}
	
	//XBOX Joystick Buttons
	private JoystickButton hacks;
	private JoystickButton conveyor;
	
	//Joystick Buttons
	private JoystickButton climberShift;
	private JoystickButton shiftGear;
	
	public OI(){
		stick = new Joystick(0);
		xbox = new Joystick(1);
		
		hacks = new JoystickButton(xbox, RobotMap.hacksX);
		
	}
}
