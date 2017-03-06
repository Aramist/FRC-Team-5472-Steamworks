
package org.usfirst.frc.team5472.robot;

import static org.usfirst.frc.team5472.robot.RobotMap.motorList;

import org.usfirst.frc.team5472.robot.commands.AutoDriveStraight;
import org.usfirst.frc.team5472.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team5472.robot.subsystems.FeederSubsystem;
import org.usfirst.frc.team5472.robot.subsystems.LiftSubsystem;
import org.usfirst.frc.team5472.robot.subsystems.ShooterSubsystem;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.VictorSP;
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

	private Command autonomousCommand = null;

	// @SuppressWarnings("unchecked")
	@Override
	public void autonomousInit() {
		if (autonomousEnabled.getSelected()) {
			autonomousCommand = new AutoDriveStraight();
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
		RobotMap.updatePWM();
		System.out.println("robotinit");
		motorList[0] = new VictorSP(0);
		motorList[1] = new VictorSP(1);
		motorList[2] = new VictorSP(2);
		motorList[3] = new VictorSP(3);
		motorList[4] = new VictorSP(4);
		motorList[5] = new VictorSP(5);
		motorList[6] = new VictorSP(6);
		motorList[7] = new VictorSP(7);
		motorList[8] = new VictorSP(8);
		motorList[9] = new VictorSP(9);
		motorList[10] = new CANTalon(2);
		motorList[11] = new CANTalon(5);
		motorList[12] = new CANTalon(4);

		driveSubsystem = new DriveSubsystem();
		feederSubsystem = new FeederSubsystem();
		liftSubsystem = new LiftSubsystem();
		shootSubsystem = new ShooterSubsystem();

		System.out.println("RobotInit");
		autonomousEnabled.addDefault("Run autonomous", new Boolean(true));
		autonomousEnabled.addObject("Do nothing", new Boolean(false));

		SmartDashboard.putData("autoEnabled", autonomousEnabled);
		CameraServer.getInstance().startAutomaticCapture();
		System.out.println(driveSubsystem.get().length);
	}

	@Override
	public void teleopInit() {
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("data", driveSubsystem.getLeftEncoder().get());
		SmartDashboard.putNumber("more data", driveSubsystem.getRightEncoder().get());
		SmartDashboard.putNumber("even more data", driveSubsystem.navx.pidGet());
		double[] data = driveSubsystem.get();
		for (int i = 0; i < data.length; i++)
			SmartDashboard.putNumber("Motor: " + i, data[i]);

	}

	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
