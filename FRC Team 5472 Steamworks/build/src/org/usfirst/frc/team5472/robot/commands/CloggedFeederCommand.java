package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class CloggedFeederCommand extends Command {

	public CloggedFeederCommand() {
		requires(Robot.feederSubsystem);
	}

	@Override
	public void initialize() {
	}

	@Override
	public void execute() {
		Robot.feederSubsystem.reverseFeed();
	}

	@Override
	public void end() {
		Robot.feederSubsystem.disableFeeder();
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
