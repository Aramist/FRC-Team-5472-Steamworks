package org.usfirst.frc.team5472.robot;

public class RobotMap {

	private static enum XBOX {
		A(1), B(2), X(3), Y(4), LSTICK(9), RSTICK(1), LB(5), RB(6), START(8), BACK(7), LYAXIS(1), LXAXIS(0), RYAXIS(
				5), RXAXIS(4), RT(3), LT(2);
		private int i;

		private XBOX(int i) {
			this.i = i;
		}

		public int id() {
			return this.i;
		}
	}

	// Robot Measurements
	public static final double wheelBaseWidth = 29.75 * 2.54 * 1e-2;
	public static final double wheelDiameter = 4.0 * 2.54; // Centimeters

	// DIO ports
	public static final int leftEncoderA = 0;
	public static final int leftEncoderB = 1;

	public static final int rightEncoderA = 2;
	public static final int rightEncoderB = 3;

	// PWM ports
	// Drive motors
	public static final int frontLeftMotor = 2;
	public static final int frontRightMotor = 0;
	public static final int backLeftMotor = 3;
	public static final int backRightMotor = 1;

	// Peripherals
	public static final int feederMotor = 5;

	public static final int shooterMotor1 = 3; // TALON
	public static final int shooterMotor2 = 2; // TALON
	public static final int susanMotor = 6; // this is for the lazy-suzan
	public static final int liftMotor = 4; // TALON

	public static final int agitatorMotor = 7; // No longer a Talon TODO: FIX
												// THIS
	public static final int conveyor = 4;

	// Pneumatics
	public static final int shiftGearSolenoidF = 0;
	public static final int shiftGearSolenoidR = 1;// there will be two
													// solenoids to
	// lift
	// the feeder - check if present still
	// or was for other bot
	public static final int liftSolenoid0 = 2;// there needs to be an additional
	// solenoid that permits the lift
	// mechanism to rotate backwards when
	// necessary
	public static final int flapSolenoid0 = 3;
	// button ten - bot lift
	// button nine - when solenoid is pressurized or depressurized (Justin
	// wasn't sure) ~ go backwards

	// Drive Controls
	public static final int feederButton = 1;
	public static final int emergencyFeedButton = 2;
	public static final int liftButton = 6;
	public static final int unwindButton = 4;
	public static final int shiftGearButton = 5;
	public static final int climberShiftButton = 3;

	// XBOX Controls
	public static final int hacksX = XBOX.RB.id();
	public static final int shootX = XBOX.LB.id();
	public static final int fireAxisX = XBOX.LYAXIS.id();
	public static final int conveyorX = XBOX.A.id();
	public static final int reverseFeedX = XBOX.B.id();
	public static final int susanManualLeftX = 180; // POV
	public static final int susanManualRightX = 0; // POV
}
