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
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.TankModifier;

public class DriveSubsystem extends Subsystem {

	class DriveWithVelocityPIDInput implements PIDSource {
		@Override
		public PIDSourceType getPIDSourceType() {
			// TODO Auto-generated method stub
			return null;
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
			// TODO Auto-generated method stub

		}
	}

	protected AHRS navx = new AHRS(SPI.Port.kMXP);
	private VictorSP frontLeft;
	private VictorSP frontRight;
	private VictorSP backLeft;
	private VictorSP backRight;

	private Solenoid shiftGearSolenoid0;
	private Solenoid shiftGearSolenoid1;

	// PID Outputs for both sides of the tank drivetrain
	private PIDOutput anglePIDOutput;

	private PIDOutput driveOutput;
	// Encoders
	private Encoder leftEncoder;

	private Encoder rightEncoder;
	// Turning to an angle
	// PID Constants
	private double kP_angle = 0.03;
	private double kI_angle = 0.00;
	private double kD_angle = 0.01;
	private double kF_angle = 0.00;

	private PIDController anglePIDController;
	private double kP_drive = 0.06;
	private double kI_drive = 0.00;
	private double kD_drive = 0.02;
	private double kF_drive = 0.00;

	private PIDController drivePIDController;

	public DriveSubsystem() {
		super("Drive");

		// Initialize VictorSP objects belonging to drive train
		frontLeft = new VictorSP(RobotMap.frontLeftMotor);
		frontRight = new VictorSP(RobotMap.frontRightMotor);
		backLeft = new VictorSP(RobotMap.backLeftMotor);
		backRight = new VictorSP(RobotMap.backRightMotor);

		frontLeft.setInverted(true);
		backLeft.setInverted(true);

		leftEncoder = new Encoder(RobotMap.leftEncoderA, RobotMap.leftEncoderB, true);
		rightEncoder = new Encoder(RobotMap.rightEncoderA, RobotMap.rightEncoderB);

		anglePIDOutput = (double d) -> {
			double[] get = get();
			drive(get[0] - d, get[1] + d);
		};

		driveOutput = (double d) -> {
			drive(d, d);
		};

		// Initialize angle PIDController
		anglePIDController = new PIDController(kP_angle, kI_angle, kD_angle, kF_angle, navx, anglePIDOutput);
		anglePIDController.setContinuous(true);
		anglePIDController.setAbsoluteTolerance(10.0);

		drivePIDController = new PIDController(kP_drive, kI_drive, kD_drive, kF_drive, new DriveWithVelocityPIDInput(), driveOutput);

		leftEncoder.setDistancePerPulse(RobotMap.wheelDiameter * Math.PI);
		rightEncoder.setDistancePerPulse(RobotMap.wheelDiameter * Math.PI);

		shiftGearSolenoid0 = new Solenoid(RobotMap.shiftGearSolenoid0);
		shiftGearSolenoid1 = new Solenoid(RobotMap.shiftGearSolenoid1);

		// Y'alll don't know what a real English class is
		// \tAnna Darwish, 2017
		System.out.println("Initialized: Drive");
	}

	public void drive(double left, double right) {
		frontLeft.set(left);
		backLeft.set(left);
		frontRight.set(right);
		backRight.set(right);
	}

	public void driveWithVelocity(double velocity) {

	}

	public void followTrajectory(Waypoint[] waypoints) {
		Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 0.05, 1.7, 2.0, 60.0);
		Trajectory trajectory = Pathfinder.generate(waypoints, config);
		TankModifier modifier = new TankModifier(trajectory);
		modifier.modify(RobotMap.wheelBaseWidth);
		Trajectory left = modifier.getLeftTrajectory();
		Trajectory right = modifier.getRightTrajectory();

		EncoderFollower leftFollower = new EncoderFollower(left);
		EncoderFollower rightFollower = new EncoderFollower(right);

		for (int i = 0; i < left.segments.length; i++) {
			leftFollower.configureEncoder(0, 20, RobotMap.wheelDiameter);
			rightFollower.configureEncoder(0, 20, RobotMap.wheelDiameter);
			leftFollower.configurePIDVA(0.80, 0.00, 0.01, 1.00 / 6.00, 0.50);
			rightFollower.configurePIDVA(0.80, 0.00, 0.01, 1.00 / 6.00, 0.50);

			drive(leftFollower.calculate(leftEncoder.get()), rightFollower.calculate(rightEncoder.get()));
			Timer.delay(0.05);
		}
		stop();
	}

	public double[] get() {
		double[] ret = { frontLeft.get(), frontRight.get(), backLeft.get(), backRight.get() };
		return ret;
	}

	public Encoder getLeftEncoder() {
		return leftEncoder;
	}

	public Encoder getRightEncoder() {
		return rightEncoder;
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

	public void turnToHeading(double angle, double time) {
		turnToHeading(angle);
		Timer.delay(time);
		stop();
	}
}
