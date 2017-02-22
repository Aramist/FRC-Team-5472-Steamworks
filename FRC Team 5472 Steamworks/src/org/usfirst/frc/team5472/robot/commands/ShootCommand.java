package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.Robot;
import org.usfirst.frc.team5472.robot.RobotMap;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class ShootCommand extends Command {
	private Joystick x;

	public ShootCommand() {
		requires(Robot.shootSubsystem);
	}

	@Override
	public void end() {
		Robot.shootSubsystem.setShoot1(0.0);
		Robot.shootSubsystem.setShoot2(0.0);
		Robot.shootSubsystem.setSusanMotor(0.0);
		Robot.shootSubsystem.setConveyor(0.0);
	}

	@Override
	public void execute() {
		double turn = 0.0;
		int angle = x.getPOV();
		if (angle == 90)
			turn = 0.3;
		else if (angle == 270)
			turn = -0.3;
		Robot.shootSubsystem.setSusanMotor(turn);

		if (Robot.oi.getXBOX().getRawButton(RobotMap.shootX)) {
			// double d = (Robot.oi.getXBOX().getRawAxis(RobotMap.fireAxisX) +
			// 1.0) / 2.0;
			Robot.shootSubsystem.setShoot1(0.575);
			// Robot.shootSubsystem.setShoot2(0.5);
		} else {
			Robot.shootSubsystem.setShoot1(0);
			// Robot.shootSubsystem.setShoot2(0);
		}

		if (Robot.oi.getXBOX().getRawButton(RobotMap.conveyorX)) {
			Robot.shootSubsystem.enableConveyor();
		} else {
			Robot.shootSubsystem.disableConveyor();
		}

	}

	@Override
	public void initialize() {
		x = Robot.oi.getXBOX();
	}

	@Override
	public void interrupted() {
		end();
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

}
