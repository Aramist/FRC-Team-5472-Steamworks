package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.Robot;
import org.usfirst.frc.team5472.robot.RobotMap;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class CloggedFeederCommand extends Command {
	
private Joystick j;
	
	public CloggedFeederCommand() {
		requires(Robot.feederSubsystem);
	}
	
	@Override
	public void initialize(){
		j = Robot.oi.getJoystick();
	}
	
	@Override
	public void execute(){
		
		if(j.getRawButton(RobotMap.emergencyFeedButton))
			Robot.feederSubsystem.setFeeder(-1.0);
		
		else
			Robot.feederSubsystem.stop();
		
	}
	
	@Override
	public void end(){
		Robot.feederSubsystem.stop();
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
