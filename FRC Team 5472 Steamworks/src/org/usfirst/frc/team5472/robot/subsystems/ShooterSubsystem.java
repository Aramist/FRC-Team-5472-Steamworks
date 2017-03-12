package org.usfirst.frc.team5472.robot.subsystems;

import org.usfirst.frc.team5472.robot.RobotMap;
import org.usfirst.frc.team5472.robot.commands.PIDShootCommand;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

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

		shooterMotor.enableBrakeMode(true);
		shooterMotor.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		shooterMotor.changeControlMode(TalonControlMode.Speed);
		shooterMotor.reverseOutput(true);
		shooterMotor.reverseSensor(true);
		shooterMotor.configPeakOutputVoltage(3, -12.0);
		shooterMotor.setPID(0.2, // Proportional
				0.0, // Integral
				0.4, // Derivative
				0.0265, // Feed-forward (when rpm change should tune this)
				40, // IZone
				0, 0);

		// this.configureShooterPID();
	}

	// Spool with PIDs
	public void PIDSpool(double rpm) {
		shooterMotor.changeControlMode(TalonControlMode.Speed);
		shooterMotor.enable();
		shooterMotor.setSetpoint(rpm);
		shooterMotor.enableBrakeMode(false);
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new PIDShootCommand());
	}

	public void setConveyor(double d) {
		conveyorMotor.set(d);
	}

	public void setShooterMotor(double d) {
		shooterMotor.changeControlMode(TalonControlMode.PercentVbus);
		shooterMotor.set(d);
	}

	public void setSusanMotor(double d) {
		susanMotor.set(d);
	}

	public void setAgitatorMotor(double d) {
		agitatorMotor.set(d);
	}

	public void stop() {
		agitatorMotor.set(0.0);
		susanMotor.set(0.0);
		conveyorMotor.set(0.0);
		shooterMotor.disable();
		shooterMotor.set(0.0);
	}
}
