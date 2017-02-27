package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class AutoStationToSideCommand extends Command{
	public AutoStationToSideCommand() {
		// is it safe to be only using one encoder to
		// detect distance? it may be better to fall short rather than go too
		// far
		requires (Robot.driveSubsystem);
	}

	@Override
	public void end() {
		Robot.driveSubsystem.stop();
	}
	@Override
	public void execute(){
		while (Robot.driveSubsystem.getLeftEncoder().getDistance() > -215)
			Robot.driveSubsystem.drive(-0.3, -0.3);// drive forward 215 cm

		Robot.driveSubsystem.drive(0.1, 0.1);// slow down
		Timer.delay(0.3);
		Robot.driveSubsystem.stop();// stop

		Robot.driveSubsystem.turnToHeading(-30);// turn right 30 degrees

		Robot.driveSubsystem.getLeftEncoder().reset();
		Robot.driveSubsystem.getRightEncoder().reset();

		while (Robot.driveSubsystem.getLeftEncoder().getDistance() > -88.9)
			Robot.driveSubsystem.drive(-0.3, -0.3);// drive forward 88.9 cm to
		// place gear

		Robot.driveSubsystem.drive(0.1, 0.1);
		Timer.delay(0.3);
		Robot.driveSubsystem.stop();
		Timer.delay(3.3); // time for pilot to pick up gear

		Robot.driveSubsystem.getLeftEncoder().reset();
		Robot.driveSubsystem.getRightEncoder().reset();

		while (Robot.driveSubsystem.getLeftEncoder().getDistance() < 80)
			Robot.driveSubsystem.drive(0.3, 0.3);// back up so won't run into
		// side
		Robot.driveSubsystem.drive(-0.1, -0.1);
		Robot.driveSubsystem.stop();
		Robot.driveSubsystem.turnToHeading(180);// turn to face opposing
		// alliance

		Robot.driveSubsystem.getLeftEncoder().reset();
		Robot.driveSubsystem.getRightEncoder().reset();

		while (Robot.driveSubsystem.getLeftEncoder().getDistance() < 91)// drive
			// forward
			// to be
			// parallel
			// to
			// hopper

			Robot.driveSubsystem.drive(0.3, 0.3);

		Robot.driveSubsystem.drive(-0.1, -0.1);
		Robot.driveSubsystem.stop();

		Robot.driveSubsystem.turnToHeading(270);// turn to face hopper

		Robot.driveSubsystem.getLeftEncoder().reset();
		Robot.driveSubsystem.getRightEncoder().reset();

		while (Robot.driveSubsystem.getLeftEncoder().getDistance() < 93)// drive
			// to
			// slam
			// into
			// hopper

			Robot.driveSubsystem.drive(0.3, 0.3);

		Robot.driveSubsystem.drive(-0.1, -0.1);
		Robot.driveSubsystem.drive(0.0, 0.0);
		Robot.driveSubsystem.stop();
		// questionable to shoot into boiler from this range because of
		// potential
		// red/yellow card
		// might be safer to just stay past the base line and refrain from
		// shooting.
		// instead - maybe slam into hopper to empty it out in autonomous bc of
		// extra time
	}

	// STARTING POSITION: line of loading station - I made it such that the
	// Robot should be on the inner
	// edge of the tape line (more in the center) than in the loading area.
	// Check if this is working
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
