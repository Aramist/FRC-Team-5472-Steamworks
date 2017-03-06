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
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	public void execute() {

		if (j.getRawButton(RobotMap.climberShiftButton)) {
			Robot.liftSubsystem.setLift(true);
		} else {
			Robot.liftSubsystem.setLift(false);
		}

		if (j.getRawButton(RobotMap.unwindButton))
			Robot.liftSubsystem.setLift(-1);
		else if (j.getRawButton(RobotMap.liftButton))
			Robot.liftSubsystem.setLift(1);
		else
			Robot.liftSubsystem.setLift(0.0);
	}

	@Override
	public void end() {
		Robot.liftSubsystem.setLift(0);
	}

}
