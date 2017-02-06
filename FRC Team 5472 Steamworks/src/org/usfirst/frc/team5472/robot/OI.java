package org.usfirst.frc.team5472.robot;

import org.usfirst.frc.team5472.robot.subsystems.LiftSubsystem;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
	
	public Joystick stick1;
	
	stick1 = new Joystick(1);
	button1 = new JoystickButton(stick1, 1);
			button2 = new JoystickButton(stick1, 2);
			button3 = new JoystickButton(stick1, 3);//lift
			button4 = new JoystickButton(stick1, 4);//unwind
			button5 = new JoystickButton(stick1, 5);
			button6 = new JoystickButton(stick1, 6);
			button7 = new JoystickButton(stick1, 7);
			button8 = new JoystickButton(stick1, 8);
			
			button11 = new JoystickButton(stick1, 11);//feeder goes backward at full speed because that means something is jammed
			//they want the lower switch (by the Logitech logo) to be the mechanism that controls the speed of the feeder. Positive values only 

	public OI(){
		//TODO: Fill this in when controls are decided
		
		stick1 = new Joystick(0);
		//stick2 = new Joystick(1);
		button3.whileHeld((new LiftSubsystem()).setLift(0.8));
		button4.whileHeld((new LiftSubsystem()).setLift(-0.4));
		
	}
}
