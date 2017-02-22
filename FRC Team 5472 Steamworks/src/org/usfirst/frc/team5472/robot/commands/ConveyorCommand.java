package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ConveyorCommand extends Command {

	public ConveyorCommand() {
		requires(Robot.shootSubsystem);
	}

	@Override
	public void end() {
		Robot.shootSubsystem.setConveyor(0.0);
	}

	@Override
	public void execute() {
		Robot.shootSubsystem.setConveyor(1.0);
	}

	@Override
	public void initialize() {
	}

	@Override
	public void interrupted() {
		end();
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

}
