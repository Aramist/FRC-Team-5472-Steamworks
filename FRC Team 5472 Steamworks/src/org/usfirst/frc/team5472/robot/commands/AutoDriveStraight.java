package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class AutoDriveStraight extends Command {

	private boolean finished;

	public AutoDriveStraight() {
		finished = false;
	}

	@Override
	public void execute() {
		Robot.driveSubsystem.drive(-0.4, -0.4);
		Timer.delay(3.0); // Joseph look here, autonomous time in seconds
		Robot.driveSubsystem.stop();
		finished = true;
	}

	@Override
	public void end() {
		Robot.driveSubsystem.stop();
	}

	@Override
	public void interrupted() {
		end();
	}

	@Override
	protected boolean isFinished() {
		return finished;
	}

}
