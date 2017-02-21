package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.Robot;

import edu.wpi.first.wpilibj.Timer;

//when the boiler is on right and starting from inner line of overflow loading station
public class AutoMidToRight {
	public AutoMidToRight() {
		while (Robot.driveSubsystem.getLeftEncoder().getDistance() < 27.94)
			Robot.driveSubsystem.drive(0.3, 0.3);
		Robot.driveSubsystem.drive(-0.1,  -0.1);//slow down
		Timer.delay(0.3);

		Robot.driveSubsystem.drive(0,0);//stop
		Robot.driveSubsystem.turnToHeading(-90);//turn right 90 degrees

		Robot.driveSubsystem.getLeftEncoder().reset();
		Robot.driveSubsystem.getRightEncoder().reset();

		while (Robot.driveSubsystem.getLeftEncoder().getDistance() < 20.87)//drive parallel to airship to right side
			Robot.driveSubsystem.drive(0.3,  0.3);

		Robot.driveSubsystem.drive(-0.1,  -0.1);//slow down
		Timer.delay(0.3);

		Robot.driveSubsystem.drive(0,0);//stop

		Robot.driveSubsystem.turnToHeading(60);// turn to be facing lift

		Robot.driveSubsystem.getLeftEncoder().reset();
		Robot.driveSubsystem.getRightEncoder().reset();

		while (Robot.driveSubsystem.getLeftEncoder().getDistance() < 27.94)//drive forward to gear placement
			Robot.driveSubsystem.drive(0.3,  0.3);

		Robot.driveSubsystem.drive(-0.1,  -0.1);
		Timer.delay(0.3);
		Robot.driveSubsystem.drive(0.0, 0.0);
		Timer.delay(3.3); // time for pilot to pick up gear

		Robot.driveSubsystem.getLeftEncoder().reset();
		Robot.driveSubsystem.getRightEncoder().reset();


		while (Robot.driveSubsystem.getLeftEncoder().getDistance() > -35)
			Robot.driveSubsystem.drive(-0.3, -0.3);//back up so won't run into side, back up 80cm


		Robot.driveSubsystem.turnToHeading(0);

		Robot.driveSubsystem.getLeftEncoder().reset();
		Robot.driveSubsystem.getRightEncoder().reset();

		while (Robot.driveSubsystem.getLeftEncoder().getDistance() < 80)
			Robot.driveSubsystem.drive(0.3,  0.3);

		Robot.driveSubsystem.drive(-0.1, -0.1);
		Robot.driveSubsystem.drive(0.0,0.0);
		//add shooting

	}
}


