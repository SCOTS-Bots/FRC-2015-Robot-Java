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
	public static Jaguar rearLeftMotor;
	public static Jaguar frontLeftMotor;
	public static Jaguar rearRightMotor;
	public static Jaguar frontRightMotor;
	
	public static void initialize()
	{
		rearLeftMotor = new Jaguar(0);
		frontLeftMotor = new Jaguar(1);
		rearRightMotor = new Jaguar(2);
		frontRightMotor = new Jaguar(3);
		drivetrain = new RobotDrive(rearLeftMotor, frontLeftMotor, rearRightMotor, frontRightMotor);
	}
}
