package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.Robot;
import org.usfirst.frc.team5472.robot.subsystems.LiftSubsystem;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;

public class DriveWithJoystickCommand extends Command {
	public static Joystick joystick;
	public static Button button1, button2, button3, button4, button5, button6, button7, button8,button11;
	public DriveWithJoystickCommand() {
		super("Joystick Drive");
		
	}
	
	@Override
	public void initialize(){
		Robot.driveSubsystem.stopMotors();
	}
	
	@Override
	public void execute(){
		Joystick j = Robot.oi.stick1;
		
		double y = j.getY();
		double twist = j.getTwist();
		
		double left = y + twist/2.0;
		double right = y - twist/2.0;
		
		Robot.driveSubsystem.set(left, right);
		
	
	}
	
	@Override
	public void end(){
		Robot.driveSubsystem.stopMotors();
	}
	
	@Override
	public void interrupted(){
		end();
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}

}
