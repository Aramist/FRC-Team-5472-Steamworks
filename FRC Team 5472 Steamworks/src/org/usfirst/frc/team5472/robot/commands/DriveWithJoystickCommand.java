package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class DriveWithJoystickCommand extends Command {

	public DriveWithJoystickCommand() {
		super("Joystick Drive");
	}
	
	@Override
	public void initialize(){
		Robot.driveSubsystem.stopMotors();
	}
	
	@Override
	public void execute(){
		Joystick j = Robot.oi.stick1;
		
		double y = j.getY();
		double twist = j.getTwist();
		
		double left = y + twist/2.0;
		double right = y - twist/2.0;
		
		Robot.driveSubsystem.set(left, right);
	}
	
	@Override
	public void end(){
		Robot.driveSubsystem.stopMotors();
	}
	
	@Override
	public void interrupted(){
		end();
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}

}
