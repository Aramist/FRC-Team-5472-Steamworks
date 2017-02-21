package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.Robot;

import edu.wpi.first.wpilibj.Timer;

public class AutoMidToMid {

	public AutoMidToMid() {
		while (Robot.driveSubsystem.getLeftEncoder().getDistance() < 55.88)
			Robot.driveSubsystem.drive(0.3, 0.3);//drive forward 55.88 cm


		Robot.driveSubsystem.drive(-0.1,  -0.1);//slow down
		Timer.delay(0.3);
		Robot.driveSubsystem.drive(0,0);//stop
		Timer.delay(3.3);//time for robot to fully stop and for pilot to pick up gear

		Robot.driveSubsystem.getLeftEncoder().reset();
		Robot.driveSubsystem.getRightEncoder().reset();

		while (Robot.driveSubsystem.getLeftEncoder().getDistance() < -27.94)
			Robot.driveSubsystem.drive(-0.3,  -0.3);//backup halfway -27.94 cm

		//Note: errors may accumulate quickly - best to zero out encoders as frequently as possible

		Robot.driveSubsystem.drive(-0.1,  -0.1);//slow down
		Timer.delay(0.3);
		Robot.driveSubsystem.drive(0,0);//stop

		Robot.driveSubsystem.turnToHeading(43.75);//turn left

		Robot.driveSubsystem.getLeftEncoder().reset();
		Robot.driveSubsystem.getRightEncoder().reset();

		while (Robot.driveSubsystem.getLeftEncoder().getDistance() < 80)
			Robot.driveSubsystem.drive(0.5, 0.5);//drive forward  past the baseline

		Robot.driveSubsystem.drive(-0.1,  -0.1);//slow down
		Timer.delay(0.3);
		Robot.driveSubsystem.drive(0,0);//stop

		//add code to shoot ten balls once shooter mechanism is finalized
		Robot.shootSubsystem.setShoot1(0.5);// will most likely have some constant value
		//double shoot; -> need to come up with a way to adjust speed based on driver input sensor
		Robot.shootSubsystem.setShoot2(1.0);

	}

}
