package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.Robot;
import org.usfirst.frc.team5472.robot.RobotMap;

import edu.wpi.first.wpilibj.Joystick;

public class LiftCommand {
private Joystick reference;
	
	public LiftCommand(){
		reference = Robot.oi.stick1;
	}
	
	public void execute(){
		double x = (reference.getRawButton(RobotMap.liftButton)) ? 0.8 : 0.0;
		Robot.liftSubsystem.setLift(x);
	}
	
	


}
