package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.Robot;
import org.usfirst.frc.team5472.robot.subsystems.LiftSubsystem;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;

public class DriveWithJoystickCommand extends Command {
	public static Joystick joystick;
	public static Button button1, button2, button3, button4, button5, button6, button7, button8, button11;
	public DriveWithJoystickCommand() {
		super("Joystick Drive");
		joystick = new Joystick(1);
		button1 = new JoystickButton(joystick, 1);
				button2 = new JoystickButton(joystick, 2);
				button3 = new JoystickButton(joystick, 3);//lift
				button4 = new JoystickButton(joystick, 4);//unwind
				button5 = new JoystickButton(joystick, 5);
				button6 = new JoystickButton(joystick, 6);
				button7 = new JoystickButton(joystick, 7);
				button8 = new JoystickButton(joystick, 8);
				button11 = new JoystickButton(joystick, 11);//feeder goes backward at full speed because that means something is jammed
				//they want the lower switch (by the Logitech logo) to be the mechanism that controls the speed of the feeder. Positive values only 
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
		
		button3.whileHeld((new LiftSubsystem()).lift());
		
		button4.whileHeld((new LiftSubsystem()).unwind());
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
