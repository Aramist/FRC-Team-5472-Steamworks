package org.usfirst.frc.team5472.robot.subsystems;

import org.usfirst.frc.team5472.robot.RobotMap;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
//to assure accuracy, two motors will be controlling the shooter
public class ShooterSubsystem extends Subsystem {
	
	private static VictorSP shooterMotor1;//one spins forwards, and one spins backwards - won't know until bot is wired; big wheel spinning at constant speed
	private static VictorSP shooterMotor2;//smaller wheel will depend on different values
	private static VictorSP susanMotor;
	public ShooterSubsystem(){
		shooterMotor1 = new VictorSP(RobotMap.shooterMotor1);
		shooterMotor2 = new VictorSP(RobotMap.shooterMotor2);
		susanMotor = new VictorSP(RobotMap.susanMotor);
	}
	
	@Override
	protected void initDefaultCommand() {
		
	}
	
	public void setShoot1(double d)
	{
     shooterMotor1.set(d);		
	}
	public void setSusanMotor(double d)
	{
     susanMotor.set(d);		
	}
	public void setShoot2(double d)
	{
     shooterMotor2.set(d);		
	}
	
	
	
	

}
