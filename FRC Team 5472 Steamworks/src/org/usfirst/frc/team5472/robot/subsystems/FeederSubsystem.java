package org.usfirst.frc.team5472.robot.subsystems;//5472

import org.usfirst.frc.team5472.robot.RobotMap;
import org.usfirst.frc.team5472.robot.commands.FeedCommand;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

public class FeederSubsystem extends Subsystem{
	private VictorSP feederMotor;
	
	public FeederSubsystem() {
		super("Feeder");
		this.feederMotor = new VictorSP(RobotMap.feederMotor);
		System.out.println("Initialized: Feed");
	}
	
	public void setFeeder(double d){
		feederMotor.set(d);
	}
	
	public void stop(){
		feederMotor.set(0.0);
	}

	@Override
	protected void initDefaultCommand() {
		this.setDefaultCommand(new FeedCommand());
	}

}
