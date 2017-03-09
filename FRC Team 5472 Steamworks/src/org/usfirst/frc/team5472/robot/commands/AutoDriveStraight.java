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
		Robot.driveSubsystem.drive(0.34, 0.34);
		Timer.delay(6.4); // Joseph look here, autonomous time in seconds
		Robot.driveSubsystem.stop();
		finished = true;
	}

	@Override
	protected boolean isFinished() {
		return finished;
	}

}
