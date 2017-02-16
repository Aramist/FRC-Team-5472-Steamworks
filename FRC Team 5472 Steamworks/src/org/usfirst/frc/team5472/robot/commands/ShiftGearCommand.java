package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.Robot;
import org.usfirst.frc.team5472.robot.RobotMap;//is  this class necessary

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Command;

public class ShiftGearCommand extends Command{
	private Joystick j;
	
	
	public ShiftGearCommand(){
		requires(Robot.driveSubsystem);
		
		
		
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
		if(j.getRawButton(RobotMap.shiftGearButton))
		  Robot.driveSubsystem.switchSolenoid();
	
	}
	
	@Override
	public void interrupted(){
		end();
	}
	
	
	}

