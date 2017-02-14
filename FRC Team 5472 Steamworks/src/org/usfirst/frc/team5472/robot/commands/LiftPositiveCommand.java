package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.Robot;
import org.usfirst.frc.team5472.robot.RobotMap;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class LiftPositiveCommand extends Command {
	private Joystick reference;
	
	public LiftPositiveCommand(){
		reference = Robot.oi.stick1;
	}
	@Override
	protected boolean isFinished() {
		return false;
	}
	
	@Override
	public void execute(){
		double x = (reference.getRawButton(RobotMap.liftButton)) ? 0.8 : 0.0;
		Robot.liftSubsystem.setLift(x);
	}
	
	@Override
	public void end(){
		Robot.liftSubsystem.setLift(0);
	}

}
