package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.Robot;
import org.usfirst.frc.team5472.robot.RobotMap;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class LiftDefaultCommand extends Command {
	private Joystick j;
	
	public LiftDefaultCommand(){
		requires(Robot.feederSubsystem);
		
	}
	
	
	@Override
	public void initialize(){
		j = Robot.oi.getJoystick();
	}
	protected boolean isFinished() {
		return false;
	}
	
	@Override
	public void execute(){
		if( j.getRawButton(RobotMap.unwindButton))
			Robot.liftSubsystem.setLift(-0.5);//unsure of how this value will respond
		else if(j.getRawButton(RobotMap.liftButton))
			Robot.liftSubsystem.setLift(0.6);
		else
			Robot.liftSubsystem.setLift(0.0);
	}
	
	@Override
	public void end(){
		Robot.liftSubsystem.setLift(0);
	}

}
