package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.Robot;
import org.usfirst.frc.team5472.robot.RobotMap;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PIDShootCommand extends Command {
	private Joystick x;

	private double shooterSpeed;
	private double conveyorSpeed;

	private double agitatorSpeed;
	private double susanSpeed;

	public PIDShootCommand() {
		requires(Robot.shootSubsystem);

		shooterSpeed = 3165; // 3200 from boiler gear peg supposedly
		conveyorSpeed = 0.5;
		agitatorSpeed = 0.6;
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
			Robot.shootSubsystem.PIDSpool(shooterSpeed);
		else
			Robot.shootSubsystem.stop();

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
