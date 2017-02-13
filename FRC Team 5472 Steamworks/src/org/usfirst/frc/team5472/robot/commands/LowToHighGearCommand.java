package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.Robot;
import org.usfirst.frc.team5472.robot.RobotMap;//is  this class necessary

import edu.wpi.first.wpilibj.Joystick;

public class LowToHighGearCommand {
	private Joystick reference;
	
	public LowToHighGearCommand(){
		reference = Robot.oi.stick1;
		
	}
	protected boolean isFinished() {
		return false;
	}
/*	
	@Override
	public void execute(){
		double x = (reference.getRawButton(RobotMap.shiftGearButton)) ? 0.8 : 0.0;
		Robot.liftSubsystem.setLift(x);
	}
	
	@Override
	public void end(){
		Robot.liftSubsystem.setLift(0); */
	}

