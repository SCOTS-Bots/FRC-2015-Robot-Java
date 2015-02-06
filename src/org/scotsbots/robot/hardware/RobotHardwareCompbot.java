package org.scotsbots.robot.hardware;

import org.scotsbots.robot.RobotOperation;
import org.scotsbots.robot.operation.auton.AutonStrategy;
import org.scotsbots.robot.operation.auton.AutonStrategyDrive;
import org.scotsbots.robot.operation.auton.AutonStrategyDriveEncoded;
import org.scotsbots.robot.utils.Gamepad;
import org.scotsbots.robot.utils.Logger;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Victor;

//SSID 4776
public class RobotHardwareCompbot extends RobotHardware
{
	public static Victor rightMotors;
	public static Victor leftMotors;
	
	public static Victor winchMotor;
	public static Victor clampMotor;
	
	@Override
	public void initialize()
	{
		rightMotors = new Victor(3);
		leftMotors = new Victor(4);
		
		drivetrain = new RobotDrive(leftMotors, rightMotors);
		
		winchMotor = new Victor(5);
		clampMotor = new Victor(6);
		
		gyro = new Gyro(1);
		accelerometer = new BuiltInAccelerometer();
		
		leftDriveEncoder = new Encoder(6,7, false, EncodingType.k4X);
		rightDriveEncoder = new Encoder(8,9, false, EncodingType.k4X);
	}

	@Override
	public void teleop()
	{
		RobotOperation.driveTank(); //Change this when switching drive mode
		winchMotor.set(Gamepad.secondary.getLeftY() / 2); //currently at half power	
		clampMotor.set(Gamepad.secondary.getRightY());
	}

	@Override
	public void addAutons()
	{
		AutonStrategy.addAuton(new AutonStrategyDriveEncoded());		
	}

	@Override
	public String getName()
	{
		return "Competition Bot";
	}
}
