package org.usfirst.frc.team5472.robot.subsystems;

import static org.usfirst.frc.team5472.robot.RobotMap.motorList;

import org.usfirst.frc.team5472.robot.MotorInterface;
import org.usfirst.frc.team5472.robot.RobotMap;
import org.usfirst.frc.team5472.robot.commands.LiftDefaultCommand;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LiftSubsystem extends Subsystem implements MotorInterface {
	private CANTalon liftMotor;

	private Solenoid liftSolenoid0;

	public LiftSubsystem() {
		super("Lift");
		System.out.println("Lift");
		updateMotors();

		this.liftSolenoid0 = new Solenoid(RobotMap.liftSolenoid0);

		new Thread(() -> {
			double current;
			while (DriverStation.getInstance().isEnabled()) {
				current = liftMotor.getOutputCurrent();
				SmartDashboard.putNumber("Lift Current", current);

				try {
					this.wait(200L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				if (liftSolenoid0.get())
					SmartDashboard.putString("LiftSolenoid", "LOCKED");
				else
					SmartDashboard.putString("LiftSolenoid", "UNWIND");// check

				if (current > 20)
					SmartDashboard.putString("LiftMonitor", "CURRENT TOO HIGH");
			}
		}).start();
		System.out.println("Lift");
	}

	@Override
	public void updateMotors() {
		liftMotor = (CANTalon) motorList[RobotMap.liftMotor];
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new LiftDefaultCommand());
	}

	public void setLift(boolean b) {
		liftSolenoid0.set(b);
	}

	public void setLift(double d) {
		liftMotor.set(d);
	}
}
