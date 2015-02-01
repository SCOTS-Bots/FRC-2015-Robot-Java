package org.scotsbots.robot.hardware;

import org.scotsbots.robot.RobotOperation;
import org.scotsbots.robot.operation.auton.AutonStrategy;
import org.scotsbots.robot.operation.auton.AutonStrategyTest;
import org.scotsbots.robot.utils.Gamepad;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Victor;

//SSID Not yet configured
public class RobotHardwareCompbot extends RobotHardware
{
	public static Victor rightMotors;
	public static Victor leftMotors;
	
	public static Victor winchMotor;
	public static Victor clampMotor;
	public static BuiltInAccelerometer accelerometer;
	
	@Override
	public void initialize()
	{
		rightMotors = new Victor(1);
		leftMotors = new Victor(2);
		
		drivetrain = new RobotDrive(rightMotors, leftMotors);
		
		winchMotor = new Victor(3);
		clampMotor = new Victor(4);
		
		gyro = new Gyro(1);
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
		AutonStrategy.addAuton(new AutonStrategyTest());		
	}

	@Override
	public String getName()
	{
		return "Competition Bot";
	}
}
