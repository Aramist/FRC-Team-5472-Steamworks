package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.Robot;

import edu.wpi.first.wpilibj.Timer;

public class AutoMidToLeft {
	// when the boiler is on right and starting from inner line of overflow
	// loading station

	public AutoMidToLeft() {
		while (Robot.driveSubsystem.getLeftEncoder().getDistance() < 27.94)
			Robot.driveSubsystem.drive(0.3, 0.3);
		Robot.driveSubsystem.drive(-0.1, -0.1);// slow down
		Timer.delay(0.3);

		Robot.driveSubsystem.drive(0, 0);// stop
		Robot.driveSubsystem.turnToHeading(90);// turn left 90 degrees

		Robot.driveSubsystem.getLeftEncoder().reset();
		Robot.driveSubsystem.getRightEncoder().reset();

		while (Robot.driveSubsystem.getLeftEncoder().getDistance() < 17.67)
			Robot.driveSubsystem.drive(0.3, 0.3);
		Robot.driveSubsystem.drive(-0.1, -0.1);// slow down
		Timer.delay(0.3);

		Robot.driveSubsystem.drive(0, 0);// stop
		Robot.driveSubsystem.turnToHeading(0);

		Robot.driveSubsystem.getLeftEncoder().reset();
		Robot.driveSubsystem.getRightEncoder().reset();

		while (Robot.driveSubsystem.getLeftEncoder().getDistance() < 28.16)
			Robot.driveSubsystem.drive(0.3, 0.3);
		Robot.driveSubsystem.drive(-0.1, -0.1);// slow down
		Timer.delay(0.3);

		Robot.driveSubsystem.drive(0, 0);// stop
		Robot.driveSubsystem.turnToHeading(-60);// turn right 60 degrees

		while (Robot.driveSubsystem.getLeftEncoder().getDistance() < 10.56)
			Robot.driveSubsystem.drive(0.3, 0.3);

		Robot.driveSubsystem.drive(-0.1, -0.1);
		Timer.delay(0.3);
		Robot.driveSubsystem.drive(0.0, 0.0);
		Timer.delay(3.3); // time for pilot to pick up gear
		Robot.driveSubsystem.getLeftEncoder().reset();
		Robot.driveSubsystem.getRightEncoder().reset();

		while (Robot.driveSubsystem.getLeftEncoder().getDistance() > -100)
			Robot.driveSubsystem.drive(-0.3, -0.3);// back up so won't run into
													// side

		Robot.driveSubsystem.turnToHeading(0);

		Robot.driveSubsystem.getLeftEncoder().reset();
		Robot.driveSubsystem.getRightEncoder().reset();

		while (Robot.driveSubsystem.getLeftEncoder().getDistance() < 80)
			Robot.driveSubsystem.drive(0.3, 0.3);

		Robot.driveSubsystem.drive(-0.1, -0.1);
		Robot.driveSubsystem.drive(0.0, 0.0);
		// //questionable to shoot into boiler from this range because of
		// potential
		// red/yellow card
		// //might be safer to just stay past the base line and refrain from
		// shooting.
		// //instead - maybe slam into hopper to empty it out in autonomous bc
		// of
		// extra time
	}
}
