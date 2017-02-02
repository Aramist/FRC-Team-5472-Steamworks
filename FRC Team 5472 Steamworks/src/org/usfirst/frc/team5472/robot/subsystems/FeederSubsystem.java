package org.usfirst.frc.team5472.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;

public class FeederSubsystem {
	private static VictorSP feederMotor;
	public FeederSubsystem() {
		Thread t = new Thread(() -> {
			feederMotor = new VictorSP(4);
			feederMotor.set(0.6);
		} );
		t.start();
		// TODO Auto-generated constructor stub
	}

}
