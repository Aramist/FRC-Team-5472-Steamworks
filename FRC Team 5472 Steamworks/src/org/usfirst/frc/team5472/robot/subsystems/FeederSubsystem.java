package org.usfirst.frc.team5472.robot.subsystems;

import org.usfirst.frc.team5472.robot.RobotMap;
import org.usfirst.frc.team5472.robot.commands.FeedCommand;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

public class FeederSubsystem extends Subsystem{
	private VictorSP feederMotor;
	public FeederSubsystem() {
		super("Feeder");
//		Thread t = new Thread(() -> {
//			feederMotor = new VictorSP(4);
//			feederMotor.set(0.6);
//		} );
//		t.start();
		this.feederMotor = new VictorSP(RobotMap.feederMotor);
		
	}
	
	public void setFeeder(double d){
		feederMotor.set(d);
	}

	@Override
	protected void initDefaultCommand() {
		this.setDefaultCommand(new FeedCommand());
	}

}
