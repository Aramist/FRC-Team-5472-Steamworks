package org.usfirst.frc.team5472.robot.subsystems;

import org.usfirst.frc.team5472.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ShiftGearSubsystem extends Subsystem {

	 private Solenoid shiftGearSolenoid0;
	 
	 public ShiftGearSubsystem() {
		  super("ShiftGear");
		  this.shiftGearSolenoid0 = new Solenoid(RobotMap.shiftGearSolenoid0);
	 }

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
}
