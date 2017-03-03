
package org.usfirst.frc.team5472.robot;

import org.usfirst.frc.team5472.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team5472.robot.subsystems.FeederSubsystem;
import org.usfirst.frc.team5472.robot.subsystems.LiftSubsystem;
import org.usfirst.frc.team5472.robot.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

	private enum AutonomousOptions {
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

	private SendableChooser<Boolean> autonomousEnabled = new SendableChooser<Boolean>();

	private SendableChooser<AutonomousOptions> autonomousStarting = new SendableChooser<AutonomousOptions>();

	private SendableChooser<Boolean> afterGear = new SendableChooser<Boolean>();

	private Command autonomousCommand = null;

	@SuppressWarnings("unchecked")
	@Override
	public void autonomousInit() {
		boolean b = ((SendableChooser<Boolean>) SmartDashboard.getData("Is Autonomous Enabled?")).getSelected();
		AutonomousOptions starting = ((SendableChooser<AutonomousOptions>) SmartDashboard.getData("Starting Position")).getSelected();
		boolean after = ((SendableChooser<Boolean>) SmartDashboard.getData("After Placing a Gear:")).getSelected();

		System.out.println(b);
		System.out.println(starting);
		System.out.println(after);
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
		autonomousEnabled.addDefault("Run autonomous", new Boolean(true));
		autonomousEnabled.addObject("Do nothing", new Boolean(false));

		autonomousStarting.addDefault("Center", AutonomousOptions.CENTER);
		autonomousStarting.addObject("Left", AutonomousOptions.LEFT);
		autonomousStarting.addObject("Right", AutonomousOptions.RIGHT);

		afterGear.addDefault("Do nothing", new Boolean(false));
		afterGear.addObject("Shoot (WIP)", new Boolean(true));

		SmartDashboard.putData("Is Autonomous Enabled?", autonomousEnabled);
		SmartDashboard.putData("Starting Position", autonomousStarting);
		SmartDashboard.putData("After Placing a Gear:", afterGear);
		CameraServer.getInstance().startAutomaticCapture();
	}

	@Override
	public void teleopInit() {
		if (autonomousCommand != null)
			autonomousCommand.cancel();
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
