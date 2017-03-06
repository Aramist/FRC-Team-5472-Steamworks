package org.usfirst.frc.team5472.robot;

import edu.wpi.first.wpilibj.SpeedController;

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

	public static SpeedController[] motorList = new SpeedController[13];

	// Robot Measurements
	public static final double wheelBaseWidth = 29.75 * 2.54 * 1e-2;
	public static final double wheelDiameter = 4.0 * 2.54; // Centimeters

	// DIO ports
	public static final int leftEncoderA = 0;
	public static final int leftEncoderB = 1;

	public static final int rightEncoderA = 2;
	public static final int rightEncoderB = 3;

	// Drive motors
	public static int frontLeftMotor = 0;
	public static int frontRightMotor = 0;
	public static int backLeftMotor = 0;
	public static int backRightMotor = 0;
	public static int feederMotor = 0;
	public static int agitatorMotor = 0;
	public static int conveyorMotor = 0;

	// Talons
	public static int susanMotor = 0;
	public static int liftMotor = 0;
	public static int shooterMotor = 0;

	// Pneumatics
	public static final int shiftGearSolenoid0 = 4;
	public static final int shiftGearSolenoid1 = 5;
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
		int fr = 0;
		int fl = 2;
		int br = 1;
		int bl = 3;

		int fe = 5;
		int ag = 7;
		int co = 4;

		int li = 4;
		int sh = 2;
		int su = 3;

		frontRightMotor = fr;
		frontLeftMotor = fl;
		backRightMotor = br;
		backLeftMotor = bl;
		feederMotor = fe;
		agitatorMotor = ag;
		conveyorMotor = co;
		liftMotor = li + 8;
		shooterMotor = sh + 8;
		susanMotor = su + 8;
	}
}
