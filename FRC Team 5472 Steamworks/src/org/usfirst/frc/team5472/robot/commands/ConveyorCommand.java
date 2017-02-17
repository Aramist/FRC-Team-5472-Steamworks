package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class ConveyorCommand extends Command{
private Joystick j;
	public ConveyorCommand() {
		requires(Robot.shootSubsystem);
		// TODO Auto-generated constructor stub
	}
    
	@Override
	public void initialize(){
		 j = Robot.oi.getJoystick();
	}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void execute()
	{
		Robot.shootSubsystem.setConveyor(1.0);
	}
	@Override
	public void end(){
		Robot.shootSubsystem.setConveyor(0.0);
	}
	@Override
	public void interrupted(){
		end();
	}


}
