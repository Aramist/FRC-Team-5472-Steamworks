
package org.usfirst.frc.team5472.robot;

import org.usfirst.frc.team5472.robot.commands.AutoKeyToSideCommand;
import org.usfirst.frc.team5472.robot.commands.AutoMidToMidCommand;
import org.usfirst.frc.team5472.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team5472.robot.subsystems.FeederSubsystem;
import org.usfirst.frc.team5472.robot.subsystems.LiftSubsystem;
import org.usfirst.frc.team5472.robot.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

	public static final OI oi = new OI();
	public static DriveSubsystem driveSubsystem;
	public static FeederSubsystem feederSubsystem;
	public static LiftSubsystem liftSubsystem;
	public static ShooterSubsystem shootSubsystem;

	private SendableChooser<Boolean> autonomousEnabled = new SendableChooser<Boolean>();
	private SendableChooser<Boolean> shooterEnabled = new SendableChooser<Boolean>();

	private Command autonomousCommand = null;

	// @SuppressWarnings("unchecked")
	@Override
	public void autonomousInit() {
		if (autonomousEnabled.getSelected()) {
			int pos = DriverStation.getInstance().getLocation();
			boolean shoot = shooterEnabled.getSelected();
			if (pos == 2) {
				autonomousCommand = new AutoMidToMidCommand(shoot);
			} else
				autonomousCommand = new AutoKeyToSideCommand(shoot);
			autonomousCommand.start();
		}
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		double[] data = driveSubsystem.get();
		System.out.println(data.length);
		for (int i = 0; i < data.length; i++)
			SmartDashboard.putNumber("Motor: " + i, data[i]);
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
		driveSubsystem = new DriveSubsystem();
		feederSubsystem = new FeederSubsystem();
		liftSubsystem = new LiftSubsystem();
		shootSubsystem = new ShooterSubsystem();

		autonomousEnabled.addDefault("Run autonomous", new Boolean(true));
		autonomousEnabled.addObject("Do nothing", new Boolean(false));

		shooterEnabled.addDefault("Shoot", new Boolean(true));
		shooterEnabled.addObject("Just Drive", new Boolean(false));

		SmartDashboard.putData("autoEnabled", autonomousEnabled);
		SmartDashboard.putData("shootEnabled", shooterEnabled);

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
		SmartDashboard.putNumber("Left Encoder", driveSubsystem.getLeftEncoder().get());
		SmartDashboard.putNumber("Right Encoder", driveSubsystem.getRightEncoder().get());
		SmartDashboard.putNumber("Yaw Angle", driveSubsystem.navx.pidGet());
		double[] data = driveSubsystem.get();
		for (int i = 0; i < data.length; i++)
			SmartDashboard.putNumber("Motor " + i, data[i]);

	}

	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
