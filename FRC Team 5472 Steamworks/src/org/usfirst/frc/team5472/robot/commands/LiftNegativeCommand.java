package org.usfirst.frc.team5472.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

public class LiftNegativeCommand extends Command {
	//private Joystick reference;
	
	public LiftNegativeCommand(){
		//reference = Robot.oi.stick1;
	}
	@Override
	protected boolean isFinished() {
		return false;
	}
	
	@Override
	public void execute(){
		//double x = (reference.getRawButton(RobotMap.unwindButton)) ? -0.4 : 0.0;
		//Robot.liftSubsystem.setLift(x);
	}
	
	@Override
	public void end(){
		//Robot.liftSubsystem.setLift(0);
	}

}
