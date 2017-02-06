package org.usfirst.frc.team5472.robot;

import org.usfirst.frc.team5472.robot.util.RobotIdentification;
import org.usfirst.frc.team5472.robot.util.RobotIdentification.RobotID;

public class RobotMap {
	
	static {
		RobotID r = RobotIdentification.getRobot();
		if(RobotIdentification.getRobot() != RobotID.ROBOT_1)
			modifyValues(r);
	}
	
	//Robot Measurements
	public static double wheelBaseWidth = 0.60; //Meters
	public static double wheelDiameter = 4.0 * 2.54; //Centimeters
	
	//DIO ports
	public static int leftEncoderA = 0;
	public static int leftEncoderB = 1;
	
	public static int rightEncoderA = 2;
	public static int rightEncoderB = 3;
	
	//PWM ports
	//Drive motors
	public static int frontLeftMotor = 0;
	public static int frontRightMotor = 1;
	public static int backLeftMotor = 2;
	public static int backRightMotor = 3;
	//Peripherals
	public static int feederMotor = 4;
	public static int susanMotor = 5; //this is for the lazy-suzan
	public static int liftMotor = 2;//Talon with a CAN bus for speed controller
	
	//Pneumatics
	public static int singleSolenoid0 = 0;
	public static int doubSolenoid0 = 1;//there will be two solenoids to lift the feeder
	
	//I2C Communications
	public static int raspiI2CAddress = 0;
	
	//Controls
	public static int feederButton = 3; //Temporary
	
	
	private static void modifyValues(RobotID r){
		switch(r){
			case ROBOT_2:
				break; //Implement in the future
			case ROBOT_3:
				break; //Implement when we have a robot 3
			default:
				break;
	
	
		}
	}
}
