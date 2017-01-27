package org.usfirst.frc.team5472.robot.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.Scanner;

public class RobotIdentification {
	
	public static enum RobotID{
		ROBOT_1, ROBOT_2, ROBOT_3;
	}
	
	public static RobotID getRobot(){
		Scanner s = null;
		try {
			s = new Scanner(new FileInputStream(Paths.get("id.txt").toFile()));
			int robotNumber = s.nextInt();
			switch(robotNumber){
			case 1:
				System.out.println("Using ROBOT_1");
				return RobotID.ROBOT_1;
			case 2:
				System.out.println("Using ROBOT_2");
				return RobotID.ROBOT_2;
			case 3:
				System.out.println("USING ROBOT_3");
				return RobotID.ROBOT_3;
			default:
				break;
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found error: " + e.getCause());
			System.out.println("Using ROBOT_1");
			return RobotID.ROBOT_1;
		} finally {
			if( s != null)
				s.close();
		}
		
		return RobotID.ROBOT_1;
	}
	
}
