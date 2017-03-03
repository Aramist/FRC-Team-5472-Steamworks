package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ShiftGearCommand extends Command {

	private boolean finished = false;

	public ShiftGearCommand() {

	}

	@Override
	public void initialize() {
		Robot.liftSubsystem.switchLift();
		finished = true;
	}

	@Override
	protected boolean isFinished() {
		return finished;
	}

	@Override
	public void execute() {
	}

	@Override
	public void interrupted() {
		end();
	}

}