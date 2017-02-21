package org.usfirst.frc.team5472.robot;//5472

import org.usfirst.frc.team5472.robot.commands.CloggedFeederCommand;
import org.usfirst.frc.team5472.robot.commands.ConveyorCommand;
import org.usfirst.frc.team5472.robot.commands.FeedCommand;

import org.usfirst.frc.team5472.robot.commands.ShiftGearCommand;
import org.usfirst.frc.team5472.robot.commands.ShootCommand;

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

	private JoystickButton shiftGear;

	private JoystickButton feedOut;
	private JoystickButton feedIn;
	
	public OI(){
		//Joysticks
		stick = new Joystick(0);
		xbox = new Joystick(1);

		//XBOX Buttons
		hacks = new JoystickButton(xbox, RobotMap.hacksX);
		conveyor = new JoystickButton(xbox, RobotMap.conveyorX);

		//Joystick Buttons

		shiftGear = new JoystickButton(stick, RobotMap.shiftGearButton);

		feedOut = new JoystickButton(stick, RobotMap.feederButton);
		feedIn = new JoystickButton(stick,RobotMap.emergencyFeedButton);

		//Initialize XBOX Buttons
		hacks.whileHeld(new ShootCommand());
		conveyor.whileHeld(new ConveyorCommand());

		//Initialize Joystick Buttons

		shiftGear.whenPressed(new ShiftGearCommand());
		feedOut.whileHeld(new CloggedFeederCommand());
		feedIn.whileHeld(new FeedCommand());
		System.out.println("Initialized: OI");
	}

}