package org.usfirst.frc.team5472.robot.subsystems;

import org.usfirst.frc.team5472.robot.RobotMap;
import org.usfirst.frc.team5472.robot.commands.ShootCommand;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
//to assure accuracy, two motors will be controlling the shooter
public class ShooterSubsystem extends Subsystem {

	private CANTalon shooterMotor1; //one spins forwards, and one spins backwards - won't know until bot is wired; big wheel spinning at constant speed
	private CANTalon shooterMotor2; //smaller wheel will depend on different values
	private VictorSP conveyor;
	private VictorSP susanMotor;
	private VictorSP agitatorMotor;

	public ShooterSubsystem(){
		super("Shoot");
		shooterMotor1 = new CANTalon(RobotMap.shooterMotor1);
		shooterMotor2 = new CANTalon(RobotMap.shooterMotor2);
		conveyor = new VictorSP(RobotMap.conveyor);
		susanMotor = new VictorSP(RobotMap.susanMotor);
		agitatorMotor = new VictorSP(RobotMap.agitatorMotor);
		System.out.println("Initialized: Shoot");
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new ShootCommand());
	}

	public void setConveyor(double d)
	{
		agitatorMotor.set(0.7);
		conveyor.set(d);
	}
	public void setShoot1(double d)
	{
		agitatorMotor.set(0.7);// check value
		shooterMotor1.set(d);
		conveyor.set(0.3);
	}
	public void setShoot2(double d)
	{
		agitatorMotor.set(0.7);
		shooterMotor2.set(d);
		conveyor.set(0.3);
	}

	public void setSusanMotor(double d)
	{
		agitatorMotor.set(0.7);
		susanMotor.set(d);
		conveyor.set(0.3);
	}
}
