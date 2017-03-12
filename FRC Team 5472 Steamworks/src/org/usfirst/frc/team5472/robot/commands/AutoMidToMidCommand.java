package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class AutoMidToMidCommand extends Command {
	// STARTING POSITION: Directly facing middle lift

	private boolean cont;
	private boolean finished;

	public AutoMidToMidCommand(boolean cont) {
		requires(Robot.driveSubsystem);
		this.cont = cont;
	}

	@Override
	public void end() {
		Robot.driveSubsystem.stop();
		finished = true;
	}

	@Override
	public void execute() {

		Robot.driveSubsystem.driveWithVelocity(0.5, false);
		Timer.delay(4.0);
		// drive forward 175.26 cm
		// while (Robot.driveSubsystem.getLeftEncoder().getDistance() > -175.26)
		// ;
		Robot.driveSubsystem.drive(0.1, 0.1);// slow down
		Timer.delay(0.3);
		// Robot.driveSubsystem.drive(0, 0);// stop
		Robot.driveSubsystem.stop();
		// time for robot to fully stop and for pilot to pick up gear

		if (!cont) {
			finished = true;
			return;
		}

		// add shooting
		finished = true;
	}

	@Override
	public void initialize() {
		// do I put anything here?
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
