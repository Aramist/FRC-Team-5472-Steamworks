
package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class AutoKeyToSideCommand extends Command {
	// STARTING POSITION: Inner line of key for boiler

	private boolean finished;
	private boolean isBlue;
	private boolean shoot;
	private int angleMultiplier;

	public AutoKeyToSideCommand(boolean s) {
		requires(Robot.driveSubsystem);
		isBlue = DriverStation.getInstance().getAlliance() == Alliance.Blue;
		angleMultiplier = isBlue ? -1 : 1;
		shoot = s;
	}

	@Override
	public void end() {
		Robot.driveSubsystem.stop();
	}

	@Override
	public void execute() {
		// Note: some of the commands may seem a tad "inverted" or backwards in
		// this - it's because
		// we treat the front of the robot as the feed, so we're backwards in
		// autonomous.
		Robot.driveSubsystem.driveWithHeading(-0.3, 0);
		while (Robot.driveSubsystem.getEncoder().getDistance() > -215.9)
			;
		// drive forward 238.90732cm

		Robot.driveSubsystem.drive(0.1, 0.1);// slow down
		Timer.delay(0.3);
		Robot.driveSubsystem.drive(0, 0);// stop

		Robot.driveSubsystem.turnToHeading(angleMultiplier * 30);
		// turn left 30 degrees

		Robot.driveSubsystem.resetEncoder();

		Robot.driveSubsystem.driveWithHeading(-0.3, angleMultiplier * 30);
		while (Robot.driveSubsystem.getEncoder().getDistance() > -50.8)
			;

		Robot.driveSubsystem.drive(0.1, 0.1);
		Timer.delay(0.3);
		Robot.driveSubsystem.drive(0.0, 0.0);
		// time for pilot to pick up gear
		Robot.driveSubsystem.stop();
		// add shooting
		if (shoot) {
			Robot.shootSubsystem.setAgitatorMotor(0.5);
			Robot.shootSubsystem.setConveyor(0.5);
			Robot.shootSubsystem.setShooterMotor(-0.63);
			finished = true; // TODO: Do something
		}
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
