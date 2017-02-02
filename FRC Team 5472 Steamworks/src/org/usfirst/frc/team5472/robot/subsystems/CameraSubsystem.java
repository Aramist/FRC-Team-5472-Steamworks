package org.usfirst.frc.team5472.robot.subsystems;

import org.usfirst.frc.team5472.robot.commands.AttemptGetCameraDataCommand;

import edu.wpi.first.wpilibj.command.Subsystem;

public class CameraSubsystem extends Subsystem {
		
	public CameraSubsystem() {
		super("Camera");
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new AttemptGetCameraDataCommand());
	}

}