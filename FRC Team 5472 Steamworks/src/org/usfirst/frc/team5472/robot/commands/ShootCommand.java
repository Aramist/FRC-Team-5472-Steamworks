package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.Robot;
import org.usfirst.frc.team5472.robot.RobotMap;
import org.usfirst.frc.team5472.robot.Updatable;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ShootCommand extends Command implements Updatable {
	private Joystick x;

	private double shooterSpeed;
	private double conveyorSpeed;
	private double agitatorSpeed;
	private double susanSpeed;
	private double hoodSpeed;

	public ShootCommand() {
		requires(Robot.shootSubsystem);
		updateValues();
	}

	@Override
	public void updateValues() {
		shooterSpeed = SmartDashboard.getNumber("Shooter Speed", 0.67);
		conveyorSpeed = SmartDashboard.getNumber("Conveyor Speed", 0.6);
		agitatorSpeed = SmartDashboard.getNumber("Agitator Speed", 0.7);
		hoodSpeed = SmartDashboard.getNumber("Hood Speed", 0.1);
		susanSpeed = SmartDashboard.getNumber("Turret Speed", 0.3);
	}

	@Override
	public void end() {
		Robot.shootSubsystem.setShooterMotor(0.0);
		Robot.shootSubsystem.setSusanMotor(0.0);
		Robot.shootSubsystem.setConveyor(0.0);
		Robot.shootSubsystem.setHoodMotor(0.0);
	}

	@Override
	public void execute() {
		double hood = 0.0;
		double turn = 0.0;
		int angle = x.getPOV();
		SmartDashboard.putNumber("Xbox POV Angle", angle);

		if (angle == 0)
			turn = hoodSpeed;
		else if (angle == 180)
			turn = -hoodSpeed;

		if (angle == 90)
			turn = susanSpeed;
		else if (angle == 270)
			turn = -susanSpeed;

		Robot.shootSubsystem.setHoodMotor(hood);
		Robot.shootSubsystem.setSusanMotor(turn);

		if (Robot.oi.getXBOX().getRawButton(RobotMap.shootX))
			Robot.shootSubsystem.setShooterMotor(shooterSpeed);
		else
			Robot.shootSubsystem.setShooterMotor(0.0);

		if (Robot.oi.getXBOX().getRawButton(RobotMap.conveyorX)) {
			Robot.shootSubsystem.setConveyor(conveyorSpeed);
			Robot.shootSubsystem.setAgitatorMotor(agitatorSpeed);
		} else {
			Robot.shootSubsystem.setConveyor(0.0);
			Robot.shootSubsystem.setAgitatorMotor(0.0);
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
