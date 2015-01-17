package org.scotsbots.dummy_robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.Talon;

public class RobotHardware
{
	// Declare all robot hardware. I.E. Victor, solenoid, relays, ect.

	//four motor control
	/*
	public static RobotDrive drivetrain;

	public static Talon rearLeftMotor;
	public static Talon frontLeftMotor;
	public static Talon rearRightMotor;
	public static Talon frontRightMotor;
	*/
	public static Encoder leftDriveEncoder;
	public static Encoder rightDriveEncoder;
	
	public static Talon rightMotors;
	public static Talon leftMotors;
	public static RobotDrive drivetrain;
	
	public static void initialize() 
	{
		/*
		rearLeftMotor = new Talon(0);
		frontLeftMotor = new Talon(1);
		rearRightMotor = new Talon(2);
		frontRightMotor = new Talon(3);
		drivetrain = new RobotDrive(rearLeftMotor, frontLeftMotor,rearRightMotor, frontRightMotor);
		*/
		leftDriveEncoder = new Encoder(7, 8, false, Encoder.EncodingType.k4X);
		rightDriveEncoder = new Encoder(9, 10, false, Encoder.EncodingType.k4X);	
		
		//drivetrain.setInvertedMotor(MotorType.kRearRight, true);
		
		rightMotors = new Talon(1);
		leftMotors = new Talon(2);
		drivetrain = new RobotDrive(leftMotors, rightMotors);

	}
}
