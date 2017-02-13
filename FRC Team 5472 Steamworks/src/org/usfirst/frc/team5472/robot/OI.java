package org.usfirst.frc.team5472.robot;

import org.usfirst.frc.team5472.robot.commands.CloggedFeederCommand;
import org.usfirst.frc.team5472.robot.commands.FeedCommand;
import org.usfirst.frc.team5472.robot.commands.LiftNegativeCommand;
import org.usfirst.frc.team5472.robot.commands.LiftPositiveCommand;
import org.usfirst.frc.team5472.robot.commands.ShootCommand;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
	
	public Joystick stick1;
	
	private JoystickButton button1,button2,button3,button4,button5,button6,button7,button8,button11;

	public OI(){
		//TODO: Fill this in when controls are decided
		
		stick1 = new Joystick(0);
		//stick2 = new Joystick(1);
		//potentiometer at base will dictate speed of feeder
		button1 = new JoystickButton(stick1, 1);//shoot
		button2 = new JoystickButton(stick1, 2);//feeder
		button3 = new JoystickButton(stick1, 3);//lift
		button4 = new JoystickButton(stick1, 4);//unwind
		button5 = new JoystickButton(stick1, 5);//turn lazy susan to face shooter at boiler
		button6 = new JoystickButton(stick1, 6);// gear shift low to high
		button7 = new JoystickButton(stick1, 7);
		button8 = new JoystickButton(stick1, 8);
		button11 = new JoystickButton(stick1, 11);//feeder goes backward at full speed because that means something is jammed
		//they want the lower switch (by the Logitech logo) to be the mechanism that controls the speed of the feeder. Positive values only 
		//I was hoping to have button12 reset all of the monitored values on the dashboard
		button3.whileHeld(new LiftPositiveCommand());
		button4.whileHeld(new LiftNegativeCommand());
		button2.whileHeld(new FeedCommand());
		button5.whileHeld(new ShootCommand());
		button1.whileHeld(new ShootCommand());
		button11.whileHeld(new CloggedFeederCommand());
		
	}
}
