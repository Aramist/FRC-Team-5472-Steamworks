package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class LiftNegativeCommand extends Command {

	@Override
	protected boolean isFinished() {
		return false;
	}
	
	@Override
	public void execute(){
		Robot.liftSubsystem.setLift(-0.4);
	}
	
	@Override
	public void end(){
		Robot.liftSubsystem.setLift(0);
	}

}
