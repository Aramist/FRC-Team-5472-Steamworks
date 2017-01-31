package org.usfirst.frc.team5472.robot.subsystems;


import org.usfirst.frc.team5472.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LiftSubsystem extends Subsystem {
	private static CANTalon liftMotor;
	

	public LiftSubsystem() {
		Thread t = new Thread(() -> {
		double current;
		liftMotor = new CANTalon(RobotMap.liftMotor);
		while (DriverStation.getInstance().isEnabled()) {
		 current = liftMotor.getOutputCurrent();	
	     SmartDashboard.putNumber("Lift Current", current);
	     Timer.delay(0.2);
	     if (current > 20)
	    	 SmartDashboard.putString("LiftMonitor", "CURRENT TOO HIGH");
		} 
		
	} );
		t.start();
	}
    public void lift()
    {
     liftMotor.set(0.8);
     
    }
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
}
