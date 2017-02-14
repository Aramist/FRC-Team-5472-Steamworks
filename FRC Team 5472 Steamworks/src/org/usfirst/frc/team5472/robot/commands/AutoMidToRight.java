package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.Robot;
import org.usfirst.frc.team5472.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.Timer;
//when the boiler is on right and starting from inner line of overflow loading station
public class AutoMidToRight {
public AutoMidToRight() {
	while (DriveSubsystem.leftEncoder.getDistance() < 238)//halfway to airship
	{
		Robot.driveSubsystem.set(0.3, 0.3);
	}
	Robot.driveSubsystem.set(-0.1,  -0.1);//slow down
	Timer.delay(0.3);
	
	Robot.driveSubsystem.set(0,0);//stop
	Robot.driveSubsystem.turnToHeading(-90);//turn right 90 degrees
	
	DriveSubsystem.leftEncoder.reset();
	DriveSubsystem.rightEncoder.reset();
	
	while (DriveSubsystem.leftEncoder.getDistance() < 177.8)//drive parallel to airship to right side
	 Robot.driveSubsystem.set(0.3,  0.3);
	
	Robot.driveSubsystem.set(-0.1,  -0.1);//slow down
	Timer.delay(0.3);
	
	Robot.driveSubsystem.set(0,0);//stop
	
	Robot.driveSubsystem.turnToHeading(60);// turn to be facing lift
	
	DriveSubsystem.leftEncoder.reset();
	DriveSubsystem.rightEncoder.reset();
	
	while (DriveSubsystem.leftEncoder.getDistance() < 76.2)//drive forward to gear placement
		 Robot.driveSubsystem.set(0.3,  0.3);
	
	 Robot.driveSubsystem.set(-0.1,  -0.1);
	    Timer.delay(0.3);
	    Robot.driveSubsystem.set(0.0, 0.0);
		Timer.delay(3.3); // time for pilot to pick up gear
		
		DriveSubsystem.leftEncoder.reset();
		DriveSubsystem.rightEncoder.reset();
		
		
		while (DriveSubsystem.leftEncoder.getDistance() > -100)
		 Robot.driveSubsystem.set(-0.3, -0.3);//back up so won't run into side, back up 80cm
		

		Robot.driveSubsystem.turnToHeading(0);
		
		DriveSubsystem.leftEncoder.reset();
		DriveSubsystem.rightEncoder.reset();
		
		while (DriveSubsystem.leftEncoder.getDistance() < 80)
		 Robot.driveSubsystem.set(0.3,  0.3);
		
		Robot.driveSubsystem.set(-0.1, -0.1);
		Robot.driveSubsystem.set(0.0,0.0);
		//add shooting
	
	
	
 
} 
}


