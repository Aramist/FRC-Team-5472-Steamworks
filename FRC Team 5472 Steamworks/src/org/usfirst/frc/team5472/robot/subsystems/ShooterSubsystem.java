package org.usfirst.frc.team5472.robot.subsystems;

import org.usfirst.frc.team5472.robot.RobotMap;
import org.usfirst.frc.team5472.robot.commands.ShootCommand;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ShooterSubsystem extends Subsystem {

	private CANTalon shooterMotor;
	private SpeedController conveyorMotor;
	private SpeedController susanMotor;
	private SpeedController agitatorMotor;

	public ShooterSubsystem() {
		super("Shoot");

		shooterMotor = new CANTalon(RobotMap.shooterMotor);
		conveyorMotor = new VictorSP(RobotMap.conveyorMotor);
		susanMotor = new CANTalon(RobotMap.susanMotor);
		agitatorMotor = new VictorSP(RobotMap.agitatorMotor);

		shooterMotor.setInverted(false);
		conveyorMotor.setInverted(false);
		susanMotor.setInverted(false);
		agitatorMotor.setInverted(false);
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new ShootCommand());
	}

	public void setConveyor(double d) {
		conveyorMotor.set(d);
	}

	public void setShooterMotor(double d) {
		shooterMotor.set(d);
	}

	public void setSusanMotor(double d) {
		susanMotor.set(d);
	}

	public void setAgitatorMotor(double d) {
		agitatorMotor.set(d);
	}

	public void configureShooterPID() {
		shooterMotor.setPID(0.8, 0, 0.1);
		shooterMotor.setSetpoint(12000);
		shooterMotor.enable();
	}

	public void stop() {
		agitatorMotor.set(0.0);
		susanMotor.set(0.0);
		conveyorMotor.set(0.0);
		shooterMotor.set(0.0);
	}
}
