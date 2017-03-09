package org.usfirst.frc.team5472.robot.subsystems;//5472

import org.usfirst.frc.team5472.robot.RobotMap;
import org.usfirst.frc.team5472.robot.commands.FeedCommand;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

public class FeederSubsystem extends Subsystem {
	private SpeedController feederMotor;

	private double feedValue = 0.6;

	public FeederSubsystem() {
		super("Feeder");

		this.feederMotor = new VictorSP(RobotMap.feederMotor);
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
