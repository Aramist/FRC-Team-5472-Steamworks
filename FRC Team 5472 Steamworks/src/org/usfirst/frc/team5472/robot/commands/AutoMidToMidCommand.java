package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class AutoMidToMidCommand extends Command{
	// STARTING POSITION: Directly facing middle lift


	public AutoMidToMidCommand() {
		requires(Robot.driveSubsystem);
	}
	@Override
	public void end() {
		Robot.driveSubsystem.stop();
	}

	@Override
	public void execute() {
		while (Robot.driveSubsystem.getLeftEncoder().getDistance() > -175.26)
			Robot.driveSubsystem.drive(-0.3, -0.3);// drive forward 175.26 cm

		Robot.driveSubsystem.drive(0.1, 0.1);// slow down
		Timer.delay(0.3);
		Robot.driveSubsystem.drive(0, 0);// stop
		Robot.driveSubsystem.stop();// time for robot to fully stop and for pilot to pick
		// up gear

		// add shooting

	}

	@Override
	public void initialize() {
		//do I put anything here?
	}

	@Override
	public void interrupted() {
		end();
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}



}
