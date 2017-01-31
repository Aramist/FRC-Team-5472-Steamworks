package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.Robot;

import edu.wpi.first.wpilibj.Timer;

public class AutoMidToMid {

	public AutoMidToMid() {
		// TODO Auto-generated constructor stub
		Robot.driveSubsystem.set(0.3, 0.3);//drive forward 273.04746 cm
		Timer.delay(3.0);
		
		Robot.driveSubsystem.set(-0.1,  -0.1);//slow down
		Timer.delay(0.3);
		Robot.driveSubsystem.set(0,0);//stop
		Timer.delay(3.3);//time for robot to fully stop and for pilot to pick up gear
		
		Robot.driveSubsystem.set(-0.3,  -0.3);
        Timer.delay(1.5);//backup halfway -114.3 cm
		
		Robot.driveSubsystem.set(-0.1,  -0.1);//slow down
		Timer.delay(0.3);
		Robot.driveSubsystem.set(0,0);//stop
		
		Robot.driveSubsystem.turnToHeading(43.75);//turn left
		Robot.driveSubsystem.set(0.5, 0.5);
		Timer.delay(2);//drive forward 75 inches past the baseline
		Robot.driveSubsystem.set(-0.1,  -0.1);//slow down
		Timer.delay(0.3);
		Robot.driveSubsystem.set(0,0);//stop
		
		//add code to shoot ten balls once shooter mechanism is finalized
		
		
		
		
		
		
		
		
	}

}
