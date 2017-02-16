package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.Robot;

import edu.wpi.first.wpilibj.Timer;

public class AutoRightToRight {

	public AutoRightToRight() {
		
		while (Robot.driveSubsystem.getLeftEncoder().getDistance() < 55.88)
		 Robot.driveSubsystem.drive(0.3, 0.3);//drive forward 238.90732cm
		
		Robot.driveSubsystem.drive(-0.1,  -0.1);//slow down
		Timer.delay(0.3);
		Robot.driveSubsystem.drive(0,0);//stop
		
		Robot.driveSubsystem.turnToHeading(30);//turn left 30 degrees
		
		Robot.driveSubsystem.getLeftEncoder().reset();
		Robot.driveSubsystem.getRightEncoder().reset();
		
		while (Robot.driveSubsystem.getLeftEncoder().getDistance() < 27.94)
		 Robot.driveSubsystem.drive(0.3,  0.3);//drive forward 80cm
		
	
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
