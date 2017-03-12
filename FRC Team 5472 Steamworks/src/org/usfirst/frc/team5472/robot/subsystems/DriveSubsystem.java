package org.usfirst.frc.team5472.robot.subsystems;

import org.usfirst.frc.team5472.robot.RobotMap;
import org.usfirst.frc.team5472.robot.commands.DriveWithJoystickCommand;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveSubsystem extends Subsystem {

	class DriveWithVelocityPIDInput implements PIDSource {
		private PIDSourceType type = null;

		@Override
		public PIDSourceType getPIDSourceType() {
			return type;
		}

		@Override
		public double pidGet() {
			double x = navx.getVelocityX();
			double y = navx.getVelocityY();
			double z = navx.getVelocityZ();
			return Math.sqrt(x * x + y * y + z * z);
		}

		@Override
		public void setPIDSourceType(PIDSourceType pidSource) {
			type = pidSource;
		}
	}

	public AHRS navx = new AHRS(SPI.Port.kMXP);

	private SpeedController frontLeft;
	private SpeedController frontRight;
	private SpeedController backLeft;
	private SpeedController backRight;

	private Solenoid shiftGearSolenoid0;
	private Solenoid shiftGearSolenoid1;

	private PIDOutput anglePIDOutput;
	private PIDOutput driveOutput;

	private Encoder encoder;

	// Turning to an angle
	// PID Constants
	private PIDController anglePIDController;
	private double kP_angle = 0.03;
	private double kI_angle = 0.00;
	private double kD_angle = 0.01;
	private double kF_angle = 0.00;

	private PIDController drivePIDController;
	private double kP_drive = 0.60;
	private double kI_drive = 0.00;
	private double kD_drive = 0.20;
	private double kF_drive = 0.00;

	public DriveSubsystem() {
		super("Drive");

		frontLeft = new VictorSP(RobotMap.frontLeftMotor);
		frontRight = new VictorSP(RobotMap.frontRightMotor);
		backLeft = new VictorSP(RobotMap.backLeftMotor);
		backRight = new VictorSP(RobotMap.backRightMotor);

		frontLeft.setInverted(true);
		backLeft.setInverted(true);
		frontRight.setInverted(false);
		backRight.setInverted(false);

		encoder = new Encoder(RobotMap.leftEncoderA, RobotMap.leftEncoderB, true);

		anglePIDOutput = (double d) -> {
			double[] get = get();
			drive(get[0] - d, get[1] + d);
		};

		driveOutput = (double d) -> {
			drive(d, d);
		};

		// Initialize angle PIDController
		anglePIDController = new PIDController(kP_angle, kI_angle, kD_angle, kF_angle, navx, anglePIDOutput);
		anglePIDController.setInputRange(-180, 180);
		anglePIDController.setContinuous(true);
		anglePIDController.setAbsoluteTolerance(10.0);

		drivePIDController = new PIDController(kP_drive, kI_drive, kD_drive, kF_drive, new DriveWithVelocityPIDInput(), driveOutput);

		encoder.setDistancePerPulse(RobotMap.wheelDiameter * Math.PI);

		shiftGearSolenoid0 = new Solenoid(RobotMap.shiftGearSolenoid0);
		shiftGearSolenoid1 = new Solenoid(RobotMap.shiftGearSolenoid1);

		// Y'alll don't know what a real English class is
		// \tAnna Darwish, 2017
	}

	public void drive(double left, double right) {
		frontLeft.set(left);
		backLeft.set(left);
		frontRight.set(right);
		backRight.set(right);
	}

	public void driveWithVelocity(double velocity, boolean forwards) {
		velocity = Math.abs(velocity);
		this.drivePIDController.setInputRange(-velocity, velocity);
		this.drivePIDController.setSetpoint(velocity * (forwards ? 1 : -1));
		this.drivePIDController.enable();
	}

	public double[] get() {
		double[] ret = { frontLeft.get(), frontRight.get(), backLeft.get(), backRight.get() };
		return ret;
	}

	public Encoder getEncoder() {
		return encoder;
	}

	public void resetEncoder() {
		encoder.reset();
	}

	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new DriveWithJoystickCommand());
	}

	public boolean isHighGear() {
		return !shiftGearSolenoid0.get();
	}

	public void setHighGear(boolean high) {
		if (isHighGear() && high)
			return;
		if (!isHighGear() && !high)
			return;
		switchSolenoid();
		this.stop();
		Timer.delay(.001);
	}

	public void stop() {
		frontLeft.set(0.0);
		frontRight.set(0.0);
		backLeft.set(0.0);
		backRight.set(0.0);
		anglePIDController.disable();
		drivePIDController.disable();
	}

	public void switchSolenoid() {
		shiftGearSolenoid0.set(!shiftGearSolenoid0.get());
		shiftGearSolenoid1.set(!shiftGearSolenoid1.get());
	}

	public void turnToHeading(double angle) {
		turnToHeading(angle, false);
	}

	public void turnToHeading(double angle, boolean autostop) {
		stop();
		anglePIDController.setSetpoint(angle);
		anglePIDController.enable();
		if (autostop)
			while (!anglePIDController.onTarget())
				Timer.delay(0.05);
	}

	public void driveWithHeading(double throttle, double angle) {
		drive(throttle, throttle);
		anglePIDController.setSetpoint(angle);
		anglePIDController.enable();
	}

	public void turnToHeading(double angle, double time) {
		turnToHeading(angle);
		Timer.delay(time);
		stop();
	}
}
