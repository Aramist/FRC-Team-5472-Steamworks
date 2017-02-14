package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class DriveWithJoystickCommand extends Command {
	public Joystick j;
	public DriveWithJoystickCommand() {
		super("Joystick Drive");
		requires(Robot.driveSubsystem);
		j = Robot.oi.stick1;
	}
	
	@Override
	public void initialize(){
		Robot.driveSubsystem.stopMotors();
	}
	
	@Override
	public void execute(){
		
		double y = j.getY();
		double twist = j.getX();
		
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
