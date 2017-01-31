package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.Robot;

import edu.wpi.first.wpilibj.Timer;

public class AutoLeftToLeft {

	public AutoLeftToLeft() {
		Robot.driveSubsystem.set(0.3, 0.3);//drive forward 238.90732 cm
		Timer.delay(3.0);
		
		Robot.driveSubsystem.set(-0.1,  -0.1);//slow down
		Timer.delay(0.3);
		Robot.driveSubsystem.set(0,0);//stop
		
		Robot.driveSubsystem.turnToHeading(-30);//turn right 30 degrees
		Robot.driveSubsystem.set(0.3,  0.3);//drive forward 80 cm
		Timer.delay(1.0);
	    Robot.driveSubsystem.set(-0.1,  -0.1);
	    Timer.delay(0.3);
	    Robot.driveSubsystem.set(0.0, 0.0);
		Timer.delay(3.3); // time for pilot to pick up gear
		
		Robot.driveSubsystem.set(-0.3, -0.3);//back up so won't run into side
		Timer.delay(2.0);
		
		Robot.driveSubsystem.turnToHeading(0);
		Robot.driveSubsystem.set(0.3,  0.3);
		Timer.delay(1.0);
		Robot.driveSubsystem.set(-0.1, -0.1);
		Robot.driveSubsystem.set(0.0,0.0);
		
		
	}

}
