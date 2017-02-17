package org.usfirst.frc.team5472.robot;

public class RobotMap {
	
	private enum XBOX {
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
	
	
	//Robot Measurements
	public static double wheelDiameter = 4.0 * 2.54; //Centimeters
	
	//DIO ports
	public static int leftEncoderA = 0;
	public static int leftEncoderB = 1;
	
	public static int rightEncoderA = 2;
	public static int rightEncoderB = 3;

	//PWM ports
	//Drive motors
	public static int frontLeftMotor = 2;
	public static int frontRightMotor = 3;
	//public static int backLeftMotor = 2;
	//public static int backRightMotor = 3;
	
	//Peripherals
	public static int feederMotor = 0;
	
	public static int shooterMotor1 = 6;
	public static int shooterMotor2 = 5;//there are now two motors for the shooter for better accuracy
	public static int susanMotor = 1; //this is for the lazy-suzan
	public static int liftMotor = 2;//Talon with a CAN bus for speed controller
	
	public static int agitatorMotor = 3; //Talon ID
	public static int conveyor = 4;
	
	//Pneumatics
	public static int doubSolenoidF = 0;
	public static int doubSolenoidR = 1;//there will be two solenoids to lift the feeder - check if present still or was for other bot
	public static int liftSolenoid0 = 2;//there needs to be an additional solenoid that permits the lift mechanism to rotate backwards when necessary
	public static int shiftGearSolenoid0 = 3;
	//button ten - bot lift
	//button nine - when solenoid is pressurized or depressurized (Justin wasn't sure) ~ go backwards
	
	//I2C Communications
	public static int raspiI2CAddress = 0;
	
	//Drive Controls
	public static int feederButton = 1;
	public static int emergencyFeedButton = 2;
	public static int liftButton = 6;
	public static int unwindButton = 4;
	public static int shiftGearButton = 5;
	public static int climberShiftButton = 3;
	
	//XBOX Controls
	public static int hacksX = XBOX.RB.id();
	public static int shootX = XBOX.LB.id();
	public static int fireAxisX = XBOX.LYAXIS.id();
	public static int conveyorX = XBOX.A.id();
	public static int reverseFeedX = XBOX.B.id();
	public static int susanManualLeftX = 180; // POV
	public static int susanManualRightX = 0;  // POV
}
