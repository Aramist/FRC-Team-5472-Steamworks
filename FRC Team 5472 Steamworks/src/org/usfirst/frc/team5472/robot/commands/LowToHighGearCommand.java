package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.Robot;
import org.usfirst.frc.team5472.robot.RobotMap;//is  this class necessary

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class LowToHighGearCommand extends Command{
	private Joystick j;
	
	public LowToHighGearCommand(){
		requires(Robot.shiftGearSubsystem);
		
		
	}
	
	@Override
	public void initialize(){
		j = Robot.oi.getJoystick();
	}
	protected boolean isFinished() {
		return false;
	}
	@Override
	public void execute(){
		double x = (j.getRawButton(RobotMap.shiftGearButton)) ? 0.8 : 0.0;
	
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

