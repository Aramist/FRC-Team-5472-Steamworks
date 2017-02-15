package org.usfirst.frc.team5472.robot.subsystems;

import org.usfirst.frc.team5472.robot.RobotMap;
import org.usfirst.frc.team5472.robot.commands.DriveWithJoystickCommand;

import com.ctre.CANTalon;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.TankModifier;

public class DriveSubsystem extends Subsystem {
	
	//Navx-MXP
	protected AHRS navx = new AHRS(SPI.Port.kMXP);
	
	//Drive motors
	private  VictorSP frontLeft;
	private VictorSP frontRight;
	private VictorSP backLeft;
	private VictorSP backRight;
	
	//Extra Motors
	private  VictorSP feederMotor;
	private  VictorSP susanMotor;
	private VictorSP shooterMotor1;
	private VictorSP shooterMotor2;
	private CANTalon liftMotor;//do I need to remove this if its present in the subsystem
	
	//PID Outputs for both sides of the tank drivetrain
	//private PIDOutput leftPIDOutput; //Currently unused
	//private PIDOutput rightPIDOutput; //Currently unused
	private PIDOutput anglePIDOutput;
	//private PIDOutput straightDriveOutput;
	
	//Encoders
	public Encoder leftEncoder;
	public Encoder rightEncoder;
	
	
	//Turning to an angle
	//PID Constants
	double kP_angle = 0.03;
	double kI_angle = 0.00;
	double kD_angle = 0.01;
	double kF_angle = 0.00;
	private PIDController anglePIDController;
	
	public DriveSubsystem() {
		super("Drive");
		
		//Initialize VictorSP objects belonging to drive train
		frontLeft = new VictorSP(RobotMap.frontLeftMotor);
		frontRight = new VictorSP(RobotMap.frontRightMotor);
		backLeft = new VictorSP(RobotMap.backLeftMotor);
		backRight = new VictorSP(RobotMap.backRightMotor);
		
		//Initialize other motors
		feederMotor = new VictorSP(RobotMap.feederMotor);
		susanMotor = new VictorSP(RobotMap.susanMotor);
		shooterMotor1 = new VictorSP(RobotMap.shooterMotor1);
		shooterMotor2 = new VictorSP(RobotMap.shooterMotor2);
		liftMotor = new CANTalon(RobotMap.liftMotor);//how to initialize
		leftEncoder = new Encoder(RobotMap.leftEncoderA, RobotMap.leftEncoderB, true);
		rightEncoder = new Encoder(RobotMap.rightEncoderA, RobotMap.rightEncoderB);
		
		/*
		//Initialize PIDOutput Interfaces
		leftPIDOutput = (double d) -> {
			frontLeft.set(d);
			backLeft.set(d);
		};
		
		rightPIDOutput = (double d) -> {
			frontRight.set(d);
			backRight.set(0);
		};
		*/
		
		anglePIDOutput = (double d) -> {
			frontRight.set(frontRight.get() + d);
			frontLeft.set(frontLeft.get() - d);
			backRight.set(backRight.get() + d);
			backLeft.set(backLeft.get() - d);
		};
		
		//straightDriveOutput = (double d) -> {
		//	set(d,d);
		//};
		
		//Initialize angle PIDController
		anglePIDController = new PIDController(kP_angle, kI_angle, kD_angle, kF_angle, navx, anglePIDOutput);
		anglePIDController.setContinuous(true);
		anglePIDController.setAbsoluteTolerance(10.0);
		
		leftEncoder.setDistancePerPulse(RobotMap.wheelDiameter * Math.PI );
		rightEncoder.setDistancePerPulse(RobotMap.wheelDiameter * Math.PI);
		//Y'alll don't know what a real English class is
		//\tAnna Darwish, 2017
	}
	
	public void drive(double frontLeft, double frontRight, double backLeft, double backRight){
		this.frontLeft.set(frontLeft);
		this.frontRight.set(frontRight);
		this.backLeft.set(backLeft);
		this.backRight.set(backRight);
	}
	
	public void stop(){
		drive(0, 0, 0, 0);
	}
	
	public void initDefaultCommand() {
		setDefaultCommand(new DriveWithJoystickCommand());
	}

	
	
	public void turnToHeading(double angle){
		turnToHeading(angle, false);
	}
	
	public void turnToHeading(double angle, boolean autostop){
		stopMotors();
		anglePIDController.setSetpoint(angle);
		anglePIDController.enable();
		if(autostop){
			while(!anglePIDController.onTarget()){
				Timer.delay(0.05);
			}
		}
	}
	
	public void turnToHeading(double angle, double time){
		turnToHeading(angle);
		Timer.delay(time);
		stopMotors();
	}
	
	public void set(double left, double right){
		frontLeft.set(left);
		backLeft.set(left);
		frontRight.set(right);
		backRight.set(right);
	}
	
	public void stopMotors(){
		frontLeft.set(0.0);
		frontRight.set(0.0);
		backLeft.set(0.0);
		backRight.set(0.0);
		anglePIDController.disable();
	}
	
	public Encoder getLeftEncoder()
	{
		 return leftEncoder;
	}
	public Encoder getRightEncoder()
	{
		 return rightEncoder;
	}
	
	public void driveWithVelocity(double velocity){
		
	}
	
	public void followTrajectory(Waypoint[] waypoints){
		Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 0.05, 1.7, 2.0, 60.0);
		Trajectory trajectory = Pathfinder.generate(waypoints, config);
		TankModifier modifier = new TankModifier(trajectory);
		modifier.modify(RobotMap.wheelBaseWidth);
		Trajectory left = modifier.getLeftTrajectory();
		Trajectory right = modifier.getRightTrajectory();
		
		EncoderFollower leftFollower = new EncoderFollower(left);
		EncoderFollower rightFollower = new EncoderFollower(right);
		
		for(int i = 0; i < left.segments.length; i++){
			leftFollower.configureEncoder(0, 20, RobotMap.wheelDiameter);
			rightFollower.configureEncoder(0, 20, RobotMap.wheelDiameter);
			//TODO: Put these values into RobotMap or a RobotSpecific config file
			leftFollower.configurePIDVA(0.80, 0.00, 0.01, 1.00/6.00 /*TODO: Calculate max velocity*/, 0.50);
			rightFollower.configurePIDVA(0.80, 0.00, 0.01, 1.00/6.00 /*TODO: Calculate max velocity*/, 0.50);
			
			set(leftFollower.calculate(leftEncoder.get()), rightFollower.calculate(rightEncoder.get()));
			Timer.delay(0.05);
		}
		stopMotors();
	}
	
	class DriveWithVelocityPIDInput implements PIDSource{
		public double pidGet(){
			double x = navx.getVelocityX();
			double y = navx.getVelocityY();
			double z = navx.getVelocityZ();
			return Math.sqrt(x*x + y*y + z*z);
		}

		@Override
		public void setPIDSourceType(PIDSourceType pidSource) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public PIDSourceType getPIDSourceType() {
			// TODO Auto-generated method stub
			return null;
		}
	}
}
