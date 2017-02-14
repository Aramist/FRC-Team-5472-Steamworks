package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.Robot;
import org.usfirst.frc.team5472.robot.RobotMap;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class ShootCommand extends Command {
	private Joystick reference;

	public ShootCommand(){
		reference = Robot.oi.stick1;
	}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;

	}

	@Override
	public void execute(){
		if (Robot.oi.stick1.getRawButton(5))//alternate idea - if boiler is recognized by camera & contingency plan is to have them be able to manually turn it
		{
			double x = (reference.getRawButton(RobotMap.susanButton)) ? 0.3 : 0.0;//not sure how slowly it needs to be spin to allow the camera to register the boiler
			//repeat this task until it is correctly oriented towards boiler
			Robot.shootSubsystem.setSusanMotor(x);
		}
		if (Robot.oi.stick1.getRawButton(6))
		{
			double x = (reference.getRawButton(RobotMap.shootButton)) ? 0.4 : 0.0;	
			Robot.shootSubsystem.setShoot1(x);// will most likely have some constant value
			//double shoot; -> need to come up with a way to adjust speed based on driver input sensor
			Robot.shootSubsystem.setShoot2(2*x);//arbitrary value ~ need to change depending on angle to boiler
			//the smaller wheel will (hopefully) be in ratio to larger wheel, which is shoot1
		}

	}


	@Override
	public void end(){
		Robot.shootSubsystem.setShoot1(0.0);
		Robot.shootSubsystem.setShoot2(0.0);
		Robot.shootSubsystem.setSusanMotor(0.0);
	}

}

