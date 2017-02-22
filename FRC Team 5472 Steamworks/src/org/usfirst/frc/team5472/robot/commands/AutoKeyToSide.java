package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.Robot;

import edu.wpi.first.wpilibj.Timer;

public class AutoKeyToSide {
	// STARTING POSITION: Inner line of key for boiler
	public AutoKeyToSide() {
		// Note: some of the commands may seem a tad "inverted" or backwards in
		// this - it's because
		// we treat the front of the robot as the feed, so we're backwards in
		// autonomous.
		while (Robot.driveSubsystem.getLeftEncoder().getDistance() > -215.9)
			Robot.driveSubsystem.drive(-0.3, -0.3);// drive forward 238.90732cm

		Robot.driveSubsystem.drive(0.1, 0.1);// slow down
		Timer.delay(0.3);
		Robot.driveSubsystem.drive(0, 0);// stop

		Robot.driveSubsystem.turnToHeading(30);// turn left 30 degrees

		Robot.driveSubsystem.getLeftEncoder().reset();
		Robot.driveSubsystem.getRightEncoder().reset();

		while (Robot.driveSubsystem.getLeftEncoder().getDistance() > -50.8)
			Robot.driveSubsystem.drive(-0.3, -0.3);// drive forward 80cm

		Robot.driveSubsystem.drive(0.1, 0.1);
		Timer.delay(0.3);
		Robot.driveSubsystem.drive(0.0, 0.0);
		Timer.delay(3.3); // time for pilot to pick up gear

		// add shooting
	}

}
