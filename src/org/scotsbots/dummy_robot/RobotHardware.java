package org.scotsbots.dummy_robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.RobotDrive;

public class RobotHardware
{
	// Declare all robot hardware. I.E. Victor, solenoid, relays, ect.

	public static RobotDrive drivetrain;
	public static Jaguar rearLeftMotor;
	public static Jaguar frontLeftMotor;
	public static Jaguar rearRightMotor;
	public static Jaguar frontRightMotor;

	public static Encoder leftDriveEncoder;
	public static Encoder rightDriveEncoder;

	public static void initialize() 
	{
		rearLeftMotor = new Jaguar(3);
		frontLeftMotor = new Jaguar(1);
		rearRightMotor = new Jaguar(2);
		frontRightMotor = new Jaguar(4);
		drivetrain = new RobotDrive(rearLeftMotor, frontLeftMotor,rearRightMotor, frontRightMotor);

		leftDriveEncoder = new Encoder(6, 7, false, Encoder.EncodingType.k4X);
		rightDriveEncoder = new Encoder(8, 9, false, Encoder.EncodingType.k4X);		
	}
}
