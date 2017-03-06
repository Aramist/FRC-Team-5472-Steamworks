package org.usfirst.frc.team5472.robot.subsystems;//5472

import static org.usfirst.frc.team5472.robot.RobotMap.motorList;

import org.usfirst.frc.team5472.robot.MotorInterface;
import org.usfirst.frc.team5472.robot.RobotMap;
import org.usfirst.frc.team5472.robot.Updatable;
import org.usfirst.frc.team5472.robot.commands.FeedCommand;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class FeederSubsystem extends Subsystem implements MotorInterface, Updatable {
	private SpeedController feederMotor;

	private double feedValue = 0.6;

	public FeederSubsystem() {
		super("Feeder");
		System.out.println("Feeder");
		updateMotors();
		updateValues();
		System.out.println("Feeder");
	}

	@Override
	public void updateValues() {
		feedValue = SmartDashboard.getNumber("Feeder Speed", 0.6);
	}

	@Override
	public void updateMotors() {
		this.feederMotor = motorList[RobotMap.feederMotor];
		feederMotor.setInverted(true);
	}

	public void enableFeeder() {
		feederMotor.set(feedValue);
	}

	public void disableFeeder() {
		feederMotor.set(0.0);
	}

	public void reverseFeed() {
		feederMotor.set(-feedValue);
	}

	@Override
	protected void initDefaultCommand() {
		this.setDefaultCommand(new FeedCommand());
	}

}
