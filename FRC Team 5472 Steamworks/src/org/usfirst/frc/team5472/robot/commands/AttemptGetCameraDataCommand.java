package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.RobotMap;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.command.Command;

public class AttemptGetCameraDataCommand extends Command {
	
	private I2C comms;
	
	private String returnedData;
	
	@Override
	public void initialize(){
		//Set up I2C communications with the Raspberry PI
		comms = new I2C(Port.kOnboard, RobotMap.raspiI2CAddress);
		returnedData = "";
		if(!comms.addressOnly()){
			System.out.println("Error initiating communications with RASPI over I2C, will retry in 15 seconds.");
			new Thread(() -> {
				try {
					this.wait(15000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				initialize();
			}).run();
		}
	}
	
	@Override
	public void execute(){
		byte[] length = new byte[1];
		comms.write(RobotMap.raspiI2CAddress, 1);
		comms.read(RobotMap.raspiI2CAddress, 1, length);
		int i = (int) length[0];
		byte[] data = new byte[i];
		comms.read(RobotMap.raspiI2CAddress, i, data);
		returnedData = new String(data);
		//TODO: Do something with the String
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}

}
