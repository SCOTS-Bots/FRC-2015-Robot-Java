package org.scotsbots.robot.hardware;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.Talon;

public class RobotHardwarePracticebot extends RobotHardware
{
	public Talon rightMotors;
	public Talon leftMotors;
	
	public Talon winchMotor;
	
	public void initialize()
	{
		rightMotors = new Talon(1);
		leftMotors = new Talon(2);
		drivetrain = new RobotDrive(rightMotors, leftMotors);
		winchMotor = new Talon(0);
		
		leftDriveEncoder = new Encoder(7, 8, false, Encoder.EncodingType.k4X);
		rightDriveEncoder = new Encoder(9, 10, false, Encoder.EncodingType.k4X);	
		
		drivetrain.setInvertedMotor(MotorType.kRearLeft, true);
		drivetrain.setInvertedMotor(MotorType.kRearRight, true);
	}
}