package org.scotsbots.dummy_robot.hardware;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;

public class RobotHardwareWoodbot extends RobotHardware
{
	public Jaguar rearLeftMotor;
	public Jaguar frontLeftMotor;
	public Jaguar rearRightMotor;
	public Jaguar frontRightMotor;
	
	public DigitalInput halsensor;
	
	public void initialize() 
	{
		rearLeftMotor = new Jaguar(0);
		frontLeftMotor = new Jaguar(1);
		rearRightMotor = new Jaguar(2);
		frontRightMotor = new Jaguar(3);
		drivetrain = new RobotDrive(rearLeftMotor, frontLeftMotor,rearRightMotor, frontRightMotor);
		
		leftDriveEncoder = new Encoder(7, 8, false, Encoder.EncodingType.k4X);
		rightDriveEncoder = new Encoder(9, 10, false, Encoder.EncodingType.k4X);	
		
		drivetrain.setInvertedMotor(MotorType.kRearRight, true);		
		
		halsensor = new DigitalInput(0);
	}
}
