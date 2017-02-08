package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.Robot;


import edu.wpi.first.wpilibj.command.Command;

public class ShootCommand extends Command {

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
		
	}
	
	@Override
	public void execute(){
		if (Robot.oi.stick1.getRawButton(5))//alternate idea - if boiler is recognized by camera & contingency plan is to have them be able to manually turn it
		{
			//repeat this task until it is correctly oriented towards boiler
			 Robot.shootSubsystem.setSusanMotor(0.8);
		}
		if (Robot.oi.stick1.getRawButton(6))
		Robot.shootSubsystem.setShoot1(0.8);// will most likely have some constant value
		//double shoot; -> need to come up with a way to adjust speed based on driver input sensor
		Robot.shootSubsystem.setShoot2(0.8);//arbitrary value ~ need to change depending on angle to boiler
		
		
	}
	
	
	@Override
	public void end(){
		Robot.shootSubsystem.setShoot1(0.0);
		Robot.shootSubsystem.setShoot2(0.0);
		Robot.shootSubsystem.setSusanMotor(0.0);
	}

	}


