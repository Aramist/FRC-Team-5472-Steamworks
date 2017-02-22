package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.Robot;

import edu.wpi.first.wpilibj.Timer;

public class AutoMidToMid {
	// STARTING POSITION: Directly facing middle lift
	public AutoMidToMid() {
		while (Robot.driveSubsystem.getLeftEncoder().getDistance() > -175.26)
			Robot.driveSubsystem.drive(-0.3, -0.3);// drive forward 175.26 cm

		Robot.driveSubsystem.drive(0.1, 0.1);// slow down
		Timer.delay(0.3);
		Robot.driveSubsystem.drive(0, 0);// stop
		Timer.delay(3.3);// time for robot to fully stop and for pilot to pick
							// up gear

		// add shooting

	}

}
