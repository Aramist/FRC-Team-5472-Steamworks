package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.Robot;
import org.usfirst.frc.team5472.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.Timer;

public class AutoMidToMid {

	public AutoMidToMid() {
		// TODO Auto-generated constructor stub
		while (Robot.driveSubsystem.getLeftEncoder().getDistance() < 270)
		 Robot.driveSubsystem.set(0.3, 0.3);//drive forward 273.04746 cm
		
		
		Robot.driveSubsystem.set(-0.1,  -0.1);//slow down
		Timer.delay(0.3);
		Robot.driveSubsystem.set(0,0);//stop
		Timer.delay(3.3);//time for robot to fully stop and for pilot to pick up gear
		
		while (Robot.driveSubsystem.getLeftEncoder().getDistance() < 158.74)
		 Robot.driveSubsystem.set(-0.3,  -0.3);//backup halfway -114.3 cm
		
        //Note: errors may accumulate quickly - best to zero out encoders as frequently as possible
		
		Robot.driveSubsystem.set(-0.1,  -0.1);//slow down
		Timer.delay(0.3);
		Robot.driveSubsystem.set(0,0);//stop
		
		Robot.driveSubsystem.turnToHeading(43.75);//turn left
		
		Robot.driveSubsystem.getLeftEncoder().reset();
		Robot.driveSubsystem.getRightEncoder().reset();
		
		while (Robot.driveSubsystem.getLeftEncoder().getDistance() < 190.5)
		Robot.driveSubsystem.set(0.5, 0.5);//drive forward 190.5 cm past the baseline
		
		Robot.driveSubsystem.set(-0.1,  -0.1);//slow down
		Timer.delay(0.3);
		Robot.driveSubsystem.set(0,0);//stop
		
		//add code to shoot ten balls once shooter mechanism is finalized
		
		
		
		
		
		
		
		
	}

}
