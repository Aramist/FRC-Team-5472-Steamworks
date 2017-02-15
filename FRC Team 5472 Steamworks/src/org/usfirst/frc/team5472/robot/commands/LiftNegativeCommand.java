package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.Robot;
import org.usfirst.frc.team5472.robot.RobotMap;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class LiftNegativeCommand extends Command {
	private Joystick j;
	
	public LiftNegativeCommand(){
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
		if( j.getRawButton(RobotMap.unwindButton));
		Robot.liftSubsystem.setLift(-0.5);//unsure of how this value will respond
	}
	
	@Override
	public void end(){
		Robot.liftSubsystem.setLift(0);
	}

}
