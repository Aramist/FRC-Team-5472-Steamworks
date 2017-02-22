
package org.usfirst.frc.team5472.robot;

import org.usfirst.frc.team5472.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team5472.robot.subsystems.FeederSubsystem;
import org.usfirst.frc.team5472.robot.subsystems.LiftSubsystem;
import org.usfirst.frc.team5472.robot.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

public class Robot extends IterativeRobot {

	private enum AutonomousStarting {
		LEFT, RIGHT, CENTER;
	}

	public static final OI oi = new OI();
	public static final DriveSubsystem driveSubsystem = new DriveSubsystem();
	public static final FeederSubsystem feederSubsystem = new FeederSubsystem();
	public static final LiftSubsystem liftSubsystem = new LiftSubsystem();
	public static final ShooterSubsystem shootSubsystem = new ShooterSubsystem();

	public static void rumble(boolean on) {
		rumble(true, on);
	}

	public static void rumble(boolean heavy, boolean on) {
		if (on)
			oi.getXBOX().setRumble(!heavy ? RumbleType.kRightRumble : RumbleType.kLeftRumble, 1.0F);
		else {
			oi.getXBOX().setRumble(RumbleType.kLeftRumble, 0.0F);
			oi.getXBOX().setRumble(RumbleType.kRightRumble, 0.0F);
		}
	}

	SendableChooser<Boolean> autonomousEnabled = new SendableChooser<Boolean>();

	SendableChooser<AutonomousStarting> autonomousStarting = new SendableChooser<AutonomousStarting>();

	SendableChooser<Boolean> activateSafety = new SendableChooser<Boolean>();

	@Override
	public void autonomousInit() {
		// AutonomousStarting startingPosition =
		// autonomousStarting.getSelected();
		// boolean runningAutonomous =
		// autonomousEnabled.getSelected().booleanValue();
		// boolean safetyEnabled = activateSafety.getSelected().booleanValue();
		// TODO: Autonomous
		// reset encoder and have the motors drive forward until the getDistance
		// method returns the
		// determined value but first we must determine the equivalent distance
		// per "pulse" of the encoder
		// make an array for all the encoders & use .start() method before you
		// can get distance
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();

	}

	@Override
	public void robotInit() {

		// Configure the SendableChooser for whether an autonomous Command will
		// be run
		autonomousEnabled.addDefault("Enabled", new Boolean(true));
		autonomousEnabled.addObject("Disabled", new Boolean(false));
		// Configure the SendableChooser for autonomous starting position
		// selection
		autonomousStarting.addDefault("Center", AutonomousStarting.CENTER);
		autonomousStarting.addObject("Left", AutonomousStarting.LEFT);
		autonomousStarting.addObject("Right", AutonomousStarting.RIGHT);
		// Configure the SendableChooser for activation of "safety" mode
		activateSafety.addDefault("Disabled", new Boolean(false));
		activateSafety.addDefault("Enabled", new Boolean(true));

	}

	@Override
	public void teleopInit() {
		// TODO: Cancel Autonomous
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();

	}

	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
