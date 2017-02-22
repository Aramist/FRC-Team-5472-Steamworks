package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class LiftShiftCommand extends Command {
	private Joystick j;

	public LiftShiftCommand() {
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
		Robot.LiftSubsystem.setLift();
	}

}
