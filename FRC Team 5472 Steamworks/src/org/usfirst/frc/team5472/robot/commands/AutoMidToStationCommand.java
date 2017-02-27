package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.Robot;

import edu.wpi.first.wpilibj.Timer;

public class AutoMidToStationCommand {
	// when the boiler is on right and starting from inner line of overflow
	// loading station

	public AutoMidToStationCommand() {
		while (Robot.driveSubsystem.getLeftEncoder().getDistance() > -26.5)
			Robot.driveSubsystem.drive(-0.3, -0.3);
		Robot.driveSubsystem.drive(0.1, 0.1);// slow down
		Timer.delay(0.3);

		Robot.driveSubsystem.drive(0, 0);// stop
		Robot.driveSubsystem.turnToHeading(90);// turn left 90 degrees

		Robot.driveSubsystem.getLeftEncoder().reset();
		Robot.driveSubsystem.getRightEncoder().reset();

		while (Robot.driveSubsystem.getLeftEncoder().getDistance() < -38.12)
			Robot.driveSubsystem.drive(-0.3, -0.3);
		Robot.driveSubsystem.drive(0.1, 0.1);// slow down
		Timer.delay(0.3);

		Robot.driveSubsystem.drive(0, 0);// stop
		Robot.driveSubsystem.turnToHeading(0);

		Robot.driveSubsystem.getLeftEncoder().reset();
		Robot.driveSubsystem.getRightEncoder().reset();

		while (Robot.driveSubsystem.getLeftEncoder().getDistance() > -117.94)
			Robot.driveSubsystem.drive(-0.3, -0.3);
		Robot.driveSubsystem.drive(0.1, 0.1);// slow down
		Timer.delay(0.3);

		Robot.driveSubsystem.drive(0, 0);// stop
		Robot.driveSubsystem.turnToHeading(-60);// turn right 60 degrees

		while (Robot.driveSubsystem.getLeftEncoder().getDistance() > -55.88)
			Robot.driveSubsystem.drive(-0.3, -0.3);

		Robot.driveSubsystem.drive(0.1, 0.1);
		Timer.delay(0.3);
		Robot.driveSubsystem.drive(0.0, 0.0);
		Timer.delay(3.3); // time for pilot to pick up gear
		Robot.driveSubsystem.getLeftEncoder().reset();
		Robot.driveSubsystem.getRightEncoder().reset();

		while (Robot.driveSubsystem.getLeftEncoder().getDistance() < 80)
			Robot.driveSubsystem.drive(0.3, 0.3);// back up so won't run into
		// side
		Robot.driveSubsystem.drive(-0.1, -0.1);
		Robot.driveSubsystem.stop();
		Robot.driveSubsystem.turnToHeading(180);// turn to face opposing
		// alliance

		Robot.driveSubsystem.getLeftEncoder().reset();
		Robot.driveSubsystem.getRightEncoder().reset();

		while (Robot.driveSubsystem.getLeftEncoder().getDistance() < 91)// drive
			// forward
			// to be
			// parallel
			// to
			// hopper

			Robot.driveSubsystem.drive(0.3, 0.3);

		Robot.driveSubsystem.drive(-0.1, -0.1);
		Robot.driveSubsystem.stop();

		Robot.driveSubsystem.turnToHeading(270);// turn to face hopper

		Robot.driveSubsystem.getLeftEncoder().reset();
		Robot.driveSubsystem.getRightEncoder().reset();

		while (Robot.driveSubsystem.getLeftEncoder().getDistance() < 93)// drive
			// to
			// slam
			// into
			// hopper

			Robot.driveSubsystem.drive(0.3, 0.3);

		Robot.driveSubsystem.drive(-0.1, -0.1);
		Robot.driveSubsystem.drive(0.0,  0.0);
		Robot.driveSubsystem.stop();

	}
}
