package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.Robot;
import org.usfirst.frc.team5472.robot.RobotMap;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class ShootCommand extends Command {
private Joystick j;
	
	public ShootCommand(){
		requires(Robot.shootSubsystem);
		
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
		double turn = 0.0;
		int angle = j.getPOV();
		if(angle == 90)
			turn = 0.3;
		else if(angle == 270)
			turn = -0.3;
		Robot.shootSubsystem.setSusanMotor(turn);
		if (j.getRawButton(RobotMap.shootButton))
		{
			double x = (j.getRawButton(RobotMap.shootButton)) ? 0.4 : 0.0;	
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
		Robot.shootSubsystem.setConveyor(0.0);
	}
	
	@Override
	public void interrupted(){
		end();
	}

}


