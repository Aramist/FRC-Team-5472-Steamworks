package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.Robot;
import org.usfirst.frc.team5472.robot.RobotMap;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ShootCommand extends Command {
	private Joystick x;

	private double shooterSpeed;
	private double conveyorSpeed;

	private double agitatorSpeed;
	private double susanSpeed;

	public ShootCommand() {
		requires(Robot.shootSubsystem);

		shooterSpeed = -0.55;
		conveyorSpeed = 0.45;
		agitatorSpeed = 0.75;
		susanSpeed = 0.1;
	}

	@Override
	public void end() {
		Robot.shootSubsystem.stop();
	}

	@Override
	public void execute() {

		SmartDashboard.putNumber("Shooter Speed: ", shooterSpeed);

		double turn = 0.0;
		int angle = x.getPOV();
		SmartDashboard.putNumber("Xbox POV Angle", angle);

		if (angle == 90)
			turn = susanSpeed;
		else if (angle == 270)
			turn = -susanSpeed;
		Robot.shootSubsystem.setSusanMotor(turn);

		if (Robot.oi.getXBOX().getRawButton(RobotMap.shootX))
			Robot.shootSubsystem.setShooterMotor(shooterSpeed);
		else
			Robot.shootSubsystem.setShooterMotor(0.0);

		if (Robot.oi.getXBOX().getRawButton(RobotMap.conveyorX)) {
			Robot.shootSubsystem.setConveyor(conveyorSpeed);
			Robot.shootSubsystem.setAgitatorMotor(agitatorSpeed);
		} else if (Robot.oi.getXBOX().getRawButton(RobotMap.reverseFeedX)) {
			Robot.shootSubsystem.setConveyor(-conveyorSpeed);
			Robot.shootSubsystem.setAgitatorMotor(-agitatorSpeed);
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
