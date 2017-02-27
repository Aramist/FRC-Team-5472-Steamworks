package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class AutoKeyToSideCommand extends Command{
	// STARTING POSITION: Inner line of key for boiler
	public AutoKeyToSideCommand() {
		requires(Robot.driveSubsystem);
	}
	@Override
	public void end() {
		Robot.driveSubsystem.stop();
	}

	@Override
	public void execute(){
		// Note: some of the commands may seem a tad "inverted" or backwards in
		// this - it's because
		// we treat the front of the robot as the feed, so we're backwards in
		// autonomous.
		while (Robot.driveSubsystem.getLeftEncoder().getDistance() > -215.9)
			Robot.driveSubsystem.drive(-0.3, -0.3);// drive forward 238.90732cm

		Robot.driveSubsystem.drive(0.1, 0.1);// slow down
		Timer.delay(0.3);
		Robot.driveSubsystem.drive(0, 0);// stop

		Robot.driveSubsystem.turnToHeading(30);// turn left 30 degrees

		Robot.driveSubsystem.getLeftEncoder().reset();
		Robot.driveSubsystem.getRightEncoder().reset();

		while (Robot.driveSubsystem.getLeftEncoder().getDistance() > -50.8)
			Robot.driveSubsystem.drive(-0.3, -0.3);// drive forward 80cm

		Robot.driveSubsystem.drive(0.1, 0.1);
		Timer.delay(0.3);
		Robot.driveSubsystem.drive(0.0, 0.0);
		// time for pilot to pick up gear
		Robot.driveSubsystem.stop();
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
