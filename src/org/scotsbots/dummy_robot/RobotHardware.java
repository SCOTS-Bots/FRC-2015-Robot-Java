package org.scotsbots.dummy_robot;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.RobotDrive;

public class RobotHardware 
{
	//Declare all robot hardware. I.E. Victor, solenoid, relays, ect.
	
	/*               Front              
		+--------------------------------+
		|                                |
		|                                |
		|                                |
		|                                |
		|                                |
		|                                |
		+---------+            +---------+
		|         |            |         |
		|         |            |         |
		| Jaguar  |            | Jaguar  |
		|  Left   |            |  Right  |
		|         |            |         |
		|         |            |         |
		+---------+            +---------+
		|                                |
		|                                |
		|                                |
		|                                |
		|                                |
		|                                |
		+--------------------------------+
-	*/
	
	public static RobotDrive drivetrain;
	public static Jaguar leftDriveMotor;
	public static Jaguar rightDriveMotor;
	
	public static void initialize()
	{
		leftDriveMotor = new Jaguar(1);
		rightDriveMotor = new Jaguar(2);
		drivetrain = new RobotDrive(leftDriveMotor.getChannel(), rightDriveMotor.getChannel());
	}
}
