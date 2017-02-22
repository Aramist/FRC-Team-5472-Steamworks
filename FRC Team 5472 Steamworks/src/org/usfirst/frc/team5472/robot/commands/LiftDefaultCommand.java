package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.Robot;
import org.usfirst.frc.team5472.robot.RobotMap;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class LiftDefaultCommand extends Command {
	private Joystick j;

	public LiftDefaultCommand() {
		requires(Robot.liftSubsystem);
	}

	@Override
	public void initialize() {
		j = Robot.oi.getJoystick();
		if (j.getRawButton(RobotMap.liftSolenoid0)) {
			Robot.liftSubsystem.switchLift();
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	public void execute() {
		double x = 0;
		if (j.getZ() < 0)
			x = 0.5;
		else
			x = 1.0;

		if (j.getRawButton(RobotMap.unwindButton))
			Robot.liftSubsystem.setLift(-1 * x);
		else if (j.getRawButton(RobotMap.liftButton))
			Robot.liftSubsystem.setLift(x);
		else
			Robot.liftSubsystem.setLift(0.0);
	}

	@Override
	public void end() {
		Robot.liftSubsystem.setLift(0);
	}

}
