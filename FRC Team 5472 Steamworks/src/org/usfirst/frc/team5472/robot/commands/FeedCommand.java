package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.Robot;
import org.usfirst.frc.team5472.robot.RobotMap;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class FeedCommand extends Command{
	
	private Joystick reference;
	
	public FeedCommand(){
		reference = Robot.oi.stick1;
	}
	
	public void execute(){
		double x = (reference.getRawButton(RobotMap.feederButton)) ? 0.6 : 0.0;
		Robot.feederSubsystem.setFeeder(x);
	}
	
	@Override
	protected boolean isFinished() {
		return true;
	}
	
	@Override
	public void end(){
		Robot.feederSubsystem.setFeeder(0.0);
	}
	
	@Override
	public void interrupted(){
		end();
	}
	
}
