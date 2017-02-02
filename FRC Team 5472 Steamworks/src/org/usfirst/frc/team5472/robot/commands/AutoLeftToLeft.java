package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.Robot;
import org.usfirst.frc.team5472.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.Timer;

public class AutoLeftToLeft {

	public AutoLeftToLeft() {
		while (DriveSubsystem.leftEncoder.getDistance() < 238)
			 Robot.driveSubsystem.set(0.3, 0.3);//drive forward 238.90732cm
			
			Robot.driveSubsystem.set(-0.1,  -0.1);//slow down
			Timer.delay(0.3);
			Robot.driveSubsystem.set(0,0);//stop
			
			Robot.driveSubsystem.turnToHeading(-30);//turn right 30 degrees
			
			DriveSubsystem.leftEncoder.reset();
			DriveSubsystem.rightEncoder.reset();
			
			while (DriveSubsystem.leftEncoder.getDistance() < 80)
			 Robot.driveSubsystem.set(0.3,  0.3);//drive forward 80cm
			
		
		    Robot.driveSubsystem.set(-0.1,  -0.1);
		    Timer.delay(0.3);
		    Robot.driveSubsystem.set(0.0, 0.0);
			Timer.delay(3.3); // time for pilot to pick up gear
			
			DriveSubsystem.leftEncoder.reset();
			DriveSubsystem.rightEncoder.reset();
			
			while (DriveSubsystem.leftEncoder.getDistance() > -80)
			 Robot.driveSubsystem.set(-0.3, -0.3);//back up so won't run into side, back up 80cm
			

			Robot.driveSubsystem.turnToHeading(0);
			
			DriveSubsystem.leftEncoder.reset();
			DriveSubsystem.rightEncoder.reset();
			
			while (DriveSubsystem.leftEncoder.getDistance() < 80)
			 Robot.driveSubsystem.set(0.3,  0.3);
			
			Robot.driveSubsystem.set(-0.1, -0.1);
			Robot.driveSubsystem.set(0.0,0.0);
		//questionable to shoot into boiler from this range because of potential red/yellow card
		//might be safer to just stay past the base line and refrain from shooting.
			//instead - maybe slam into hopper to empty it out in autonomous bc of extra time
		
		
	}

}
