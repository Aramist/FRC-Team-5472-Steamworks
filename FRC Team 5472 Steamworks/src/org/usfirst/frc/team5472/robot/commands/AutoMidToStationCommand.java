package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class AutoMidToStationCommand extends Command {
	// when the boiler is on right and starting from inner line of overflow
	// loading station

	private boolean finished;
	private boolean isBlue;
	private int angleMultiplier;

	public AutoMidToStationCommand() {
		finished = false;
		isBlue = DriverStation.getInstance().getAlliance() == Alliance.Blue;
		angleMultiplier = isBlue ? -1 : 1;
	}

	@Override
	public void execute() {
		while (Robot.driveSubsystem.getEncoder().getDistance() > -26.5)
			Robot.driveSubsystem.drive(-0.3, -0.3);
		Robot.driveSubsystem.drive(0.1, 0.1);// slow down
		Timer.delay(0.3);

		Robot.driveSubsystem.drive(0, 0);// stop
		Robot.driveSubsystem.turnToHeading(angleMultiplier * 90);
		// turn left 90 degrees

		Robot.driveSubsystem.resetEncoder();

		while (Robot.driveSubsystem.getEncoder().getDistance() < -38.12)
			Robot.driveSubsystem.drive(-0.3, -0.3);
		Robot.driveSubsystem.drive(0.1, 0.1);// slow down
		Timer.delay(0.3);

		Robot.driveSubsystem.drive(0, 0);// stop
		Robot.driveSubsystem.turnToHeading(angleMultiplier * 0);

		Robot.driveSubsystem.resetEncoder();

		while (Robot.driveSubsystem.getEncoder().getDistance() > -117.94)
			Robot.driveSubsystem.drive(-0.3, -0.3);
		Robot.driveSubsystem.drive(0.1, 0.1);// slow down
		Timer.delay(0.3);

		Robot.driveSubsystem.drive(0, 0);// stop
		Robot.driveSubsystem.turnToHeading(angleMultiplier * -60);
		// turn right 60 degrees

		while (Robot.driveSubsystem.getEncoder().getDistance() > -55.88)
			Robot.driveSubsystem.drive(-0.3, -0.3);

		Robot.driveSubsystem.drive(0.1, 0.1);
		Timer.delay(0.3);
		Robot.driveSubsystem.drive(0.0, 0.0);
		Timer.delay(3.3); // time for pilot to pick up gear
		Robot.driveSubsystem.resetEncoder();

		while (Robot.driveSubsystem.getEncoder().getDistance() < 80)
			Robot.driveSubsystem.drive(0.3, 0.3);
		// back up so won't run into side
		Robot.driveSubsystem.drive(-0.1, -0.1);
		Robot.driveSubsystem.stop();
		Robot.driveSubsystem.turnToHeading(angleMultiplier * 180);
		// turn to face opposing alliance

		Robot.driveSubsystem.resetEncoder();

		while (Robot.driveSubsystem.getEncoder().getDistance() < 91)
			// drive forward to be parallel to hopper
			Robot.driveSubsystem.drive(0.3, 0.3);

		Robot.driveSubsystem.drive(-0.1, -0.1);
		Robot.driveSubsystem.stop();

		Robot.driveSubsystem.turnToHeading(angleMultiplier * 270);
		// turn to face the hoppper

		Robot.driveSubsystem.resetEncoder();

		while (Robot.driveSubsystem.getEncoder().getDistance() < 93)
			// drive to slam into hopper
			Robot.driveSubsystem.drive(0.3, 0.3);

		Robot.driveSubsystem.drive(-0.1, -0.1);
		Robot.driveSubsystem.drive(0.0, 0.0);
		Robot.driveSubsystem.stop();

	}

	@Override
	public void end() {
		Robot.driveSubsystem.stop();
	}

	@Override
	public void interrupted() {
		end();
	}

	@Override
	public boolean isFinished() {
		return finished;
	}
}
