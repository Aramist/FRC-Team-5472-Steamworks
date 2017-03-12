package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

//NOT READY
public class AutoStationToSideCommand extends Command {

	private boolean finished;
	private boolean isBlue;
	private int angleMultiplier;

	public AutoStationToSideCommand() {
		// is it safe to be only using one encoder to
		// detect distance? it may be better to fall short rather than go too
		// far
		this.isBlue = DriverStation.getInstance().getAlliance() == Alliance.Blue;
		this.angleMultiplier = isBlue ? -1 : 1;
	}

	@Override
	public void end() {
		Robot.driveSubsystem.stop();
	}

	@Override
	public void execute() {
		Robot.driveSubsystem.driveWithHeading(-0.3, 0);
		while (Robot.driveSubsystem.getEncoder().getDistance() > -215)
			;
		// drive forward 215 cm

		Robot.driveSubsystem.drive(0.1, 0.1);// slow down
		Timer.delay(0.3);
		Robot.driveSubsystem.stop();// stop

		Robot.driveSubsystem.turnToHeading(angleMultiplier * -30);
		// turn right 30 degrees

		Robot.driveSubsystem.resetEncoder();

		Robot.driveSubsystem.driveWithHeading(-0.3, angleMultiplier * -30);
		while (Robot.driveSubsystem.getEncoder().getDistance() > -88.9)
			;

		// drive forward 88.9 cm to place gear

		Robot.driveSubsystem.drive(0.1, 0.1);
		Timer.delay(0.3);
		Robot.driveSubsystem.stop();
		Timer.delay(3.3); // time for pilot to pick up gear

		Robot.driveSubsystem.resetEncoder();

		Robot.driveSubsystem.driveWithHeading(0.3, -30 * angleMultiplier);
		while (Robot.driveSubsystem.getEncoder().getDistance() < 80)
			;
		// back up so won't run into
		// side
		Robot.driveSubsystem.drive(-0.1, -0.1);
		Robot.driveSubsystem.stop();
		Robot.driveSubsystem.turnToHeading(angleMultiplier * 180);
		// turn to face opposing alliance

		Robot.driveSubsystem.resetEncoder();

		Robot.driveSubsystem.driveWithHeading(0.3, angleMultiplier * 180);
		while (Robot.driveSubsystem.getEncoder().getDistance() < 91)
			;
		// drive forward to be parallel to the hopper

		Robot.driveSubsystem.drive(-0.1, -0.1);
		Robot.driveSubsystem.stop();

		Robot.driveSubsystem.turnToHeading(angleMultiplier * 270);
		// turn to face hopper

		Robot.driveSubsystem.resetEncoder();

		Robot.driveSubsystem.driveWithHeading(0.3, angleMultiplier * 270);
		while (Robot.driveSubsystem.getEncoder().getDistance() < 93)
			;
		// drive to slam into hopper

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
		finished = true;
	}

	// STARTING POSITION: line of loading station - I made it such that the
	// Robot should be on the inner
	// edge of the tape line (more in the center) than in the loading area.
	// Check if this is working
	@Override
	public void initialize() {
		// do I put anything here?
	}

	@Override
	public void interrupted() {
		end();
	}

	@Override
	protected boolean isFinished() {
		return finished;
	}

}
