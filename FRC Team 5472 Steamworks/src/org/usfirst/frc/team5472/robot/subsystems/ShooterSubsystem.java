package org.usfirst.frc.team5472.robot.subsystems;

import org.usfirst.frc.team5472.robot.RobotMap;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
//to assure accuracy, two motors will be controlling the shooter
public class ShooterSubsystem extends Subsystem {
	
	private static VictorSP shooterMotor1;
	private static VictorSP shooterMotor2;
	private static VictorSP susanMotor;
	public ShooterSubsystem(){
		shooterMotor1 = new VictorSP(RobotMap.shooterMotor1);
		shooterMotor2 = new VictorSP(RobotMap.shooterMotor2);
		susanMotor = new VictorSP(RobotMap.susanMotor);
	}
	
	@Override
	protected void initDefaultCommand() {
		
	}

}
