package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class LazySusanCommand extends Command {

	private Joystick reference;
	
	public LazySusanCommand() {
		this.reference = Robot.oi.stick1;
	}
	
	@Override
	public void execute(){
		double turn = reference.getTwist() / 3.0;
		Robot.shootSubsystem.setSusanMotor(turn);
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
	
	public void end(){
		Robot.shootSubsystem.setSusanMotor(0D);
	}
	
	public void interrupted(){
		end();
	}
}
