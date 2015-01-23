package org.scotsbots.robot.hardware;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Victor;

public class RobotHardwareCompbot extends RobotHardware
{
	public static Victor rightMotors;
	public static Victor leftMotors;
	
	@Override
	public void initialize()
	{
		rightMotors = new Victor(1);
		leftMotors = new Victor(2);
		
		drivetrain = new RobotDrive(rightMotors, leftMotors);
		
		leftDriveEncoder = new Encoder(7, 8, false, Encoder.EncodingType.k4X);
		rightDriveEncoder = new Encoder(9, 10, false, Encoder.EncodingType.k4X);	
	}
}
