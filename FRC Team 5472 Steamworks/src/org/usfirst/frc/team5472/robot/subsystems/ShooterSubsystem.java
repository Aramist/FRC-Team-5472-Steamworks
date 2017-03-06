package org.usfirst.frc.team5472.robot.subsystems;

import static org.usfirst.frc.team5472.robot.RobotMap.motorList;

import org.usfirst.frc.team5472.robot.MotorInterface;
import org.usfirst.frc.team5472.robot.RobotMap;
import org.usfirst.frc.team5472.robot.commands.ShootCommand;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ShooterSubsystem extends Subsystem implements MotorInterface {

	private CANTalon shooterMotor;
	private SpeedController conveyorMotor;
	private SpeedController susanMotor;
	private SpeedController agitatorMotor;

	private void print(int... i) {
		for (int j : i)
			System.out.print("" + j + "  ");
	}

	public ShooterSubsystem() {
		super("Shoot");
		System.out.println("Shooter");
		updateMotors();
		System.out.println("shooter");
	}

	@Override
	public void updateMotors() {
		shooterMotor = (CANTalon) motorList[RobotMap.shooterMotor];
		conveyorMotor = motorList[RobotMap.conveyorMotor];
		susanMotor = motorList[RobotMap.susanMotor];
		agitatorMotor = motorList[RobotMap.agitatorMotor];

		print(RobotMap.shooterMotor, RobotMap.conveyorMotor, RobotMap.susanMotor, RobotMap.agitatorMotor);

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
