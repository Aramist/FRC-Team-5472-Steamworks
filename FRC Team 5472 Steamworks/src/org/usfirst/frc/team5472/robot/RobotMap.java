package org.usfirst.frc.team5472.robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RobotMap {

	private static enum XBOX {
		A(1), B(2), X(3), Y(4), LSTICK(9), RSTICK(1), LB(5), RB(6), START(8), BACK(7), LYAXIS(1), LXAXIS(0), RYAXIS(5), RXAXIS(4), RT(3), LT(2);
		private int i;

		private XBOX(int i) {
			this.i = i;
		}

		public int id() {
			return this.i;
		}
	}

	public static SpeedController[] motorList = new SpeedController[12];

	static {
		motorList[0] = new VictorSP(0);
		motorList[1] = new VictorSP(1);
		motorList[2] = new VictorSP(2);
		motorList[3] = new VictorSP(3);
		motorList[4] = new VictorSP(4);
		motorList[5] = new VictorSP(5);
		motorList[6] = new VictorSP(6);
		motorList[7] = new VictorSP(7);
		motorList[8] = new VictorSP(8);
		motorList[9] = new VictorSP(9);
		motorList[10] = new CANTalon(0);
		motorList[11] = new CANTalon(1);
		motorList[12] = new CANTalon(2);
	}

	// Robot Measurements
	public static final double wheelBaseWidth = 29.75 * 2.54 * 1e-2;
	public static final double wheelDiameter = 4.0 * 2.54; // Centimeters

	// DIO ports
	public static final int leftEncoderA = 0;
	public static final int leftEncoderB = 1;

	public static final int rightEncoderA = 2;
	public static final int rightEncoderB = 3;

	// Drive motors
	public static final int frontLeftMotorDefault = 2;
	public static final int frontRightMotorDefault = 0;
	public static final int backLeftMotorDefault = 3;
	public static final int backRightMotorDefault = 1;
	public static final int feederMotorDefault = 5;
	public static final int agitatorMotorDefault = 7;
	public static final int conveyorMotorDefault = 4;

	// Talons
	public static final int susanMotorDefault = 5;
	public static final int liftMotorDefault = 4;
	public static final int hoodMotorDefault = 3;
	public static final int shooterMotorDefault = 2;

	// Drive motors
	public static int frontLeftMotor = frontLeftMotorDefault;
	public static int frontRightMotor = frontRightMotorDefault;
	public static int backLeftMotor = backLeftMotorDefault;
	public static int backRightMotor = backRightMotorDefault;
	public static int feederMotor = feederMotorDefault;
	public static int agitatorMotor = agitatorMotorDefault;
	public static int conveyorMotor = conveyorMotorDefault;

	// Talons
	public static int susanMotor = susanMotorDefault;
	public static int liftMotor = liftMotorDefault;
	public static int hoodMotor = hoodMotorDefault;
	public static int shooterMotor = shooterMotorDefault;

	// Pneumatics
	public static final int shiftGearSolenoid0 = 0;
	public static final int shiftGearSolenoid1 = 1;
	public static final int liftSolenoid0 = 2;
	public static final int flapSolenoid0 = 3;
	// button ten - bot lift
	// button nine - when solenoid is pressurized or depressurized (Justin
	// wasn't sure) ~ go backwards

	// Drive Controls
	public static final int feederButton = 1;
	public static final int emergencyFeedButton = 5;
	public static final int liftButton = 6;
	public static final int unwindButton = 4;
	public static final int shiftGearButton = 2;
	public static final int climberShiftButton = 3;

	// XBOX Controls
	public static final int hacksX = XBOX.RB.id();
	public static final int shootX = XBOX.LB.id();
	public static final int fireAxisX = XBOX.LYAXIS.id();
	public static final int conveyorX = XBOX.A.id();
	public static final int reverseFeedX = XBOX.B.id();
	public static final int susanManualLeftX = 180; // POV
	public static final int susanManualRightX = 0; // POV

	public static double agitatorSpeed = 0.7;
	public static double conveyorSpeed = 0.45;
	public static double shooterFlywheelSpeed = 0.67;

	public static void updatePWM() {
		int fr = (int) SmartDashboard.getNumber("PWM: Front Right Drive", frontRightMotorDefault);
		int fl = (int) SmartDashboard.getNumber("PWM: Front Left Drive", frontLeftMotorDefault);
		int br = (int) SmartDashboard.getNumber("PWM: Back Right Drive", backRightMotorDefault);
		int bl = (int) SmartDashboard.getNumber("PWM: Back Left Drive", backLeftMotorDefault);

		int fe = (int) SmartDashboard.getNumber("PWM: Feeder", feederMotorDefault);
		int su = (int) SmartDashboard.getNumber("PWM: Turret Talon", susanMotorDefault);
		int ag = (int) SmartDashboard.getNumber("PWM: Agitator", agitatorMotorDefault);
		int co = (int) SmartDashboard.getNumber("PWM: Conveyor", conveyorMotorDefault);

		int li = (int) SmartDashboard.getNumber("Talon: Lift", liftMotorDefault);
		int ho = (int) SmartDashboard.getNumber("Talon: Hood", hoodMotorDefault);
		int sh = (int) SmartDashboard.getNumber("Talon: Shooter", shooterMotorDefault);

		frontRightMotor = fr;
		frontLeftMotor = fl;
		backRightMotor = br;
		backLeftMotor = bl;
		feederMotor = fe;
		susanMotor = su;
		agitatorMotor = ag;
		conveyorMotor = co;
		liftMotor = li + 8;
		hoodMotor = ho + 8;
		shooterMotor = sh + 8;
	}
}
