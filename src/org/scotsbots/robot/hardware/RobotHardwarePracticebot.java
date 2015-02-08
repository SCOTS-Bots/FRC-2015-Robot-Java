package org.scotsbots.robot.hardware;

import org.scotsbots.robot.RobotOperation;
import org.scotsbots.robot.operation.auton.AutonStrategy;
import org.scotsbots.robot.operation.auton.AutonStrategyDrive;
import org.scotsbots.robot.utils.Gamepad;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.Talon;

//SSID 9999
public class RobotHardwarePracticebot extends RobotHardware
{
	public Talon rightMotors;
	public Talon leftMotors;
	
	public Talon winchMotor;
	
	public Encoder leftDriveEncoder;
	public Encoder rightDriveEncoder;
	
	public void initialize()
	{
		rightMotors = new Talon(1);
		leftMotors = new Talon(2);
		drivetrain = new RobotDrive(rightMotors, leftMotors);
		winchMotor = new Talon(0);	
		
		drivetrain.setInvertedMotor(MotorType.kRearLeft, true);
		drivetrain.setInvertedMotor(MotorType.kRearRight, true);
		
		leftDriveEncoder = new Encoder(6,7, false, EncodingType.k4X);
		rightDriveEncoder = new Encoder(8,9, false, EncodingType.k4X);
	}

	@Override
	public void teleop()
	{
		RobotOperation.driveTank(1); //Change this when switching drive mode

		winchMotor.set(Gamepad.secondaryAttackJoystick.getLeftY() / 2); //currently at half power	
	}

	@Override
	public void addAutons()
	{
		AutonStrategy.addAuton(new AutonStrategyDrive());				
	}

	@Override
	public String getName()
	{
		return "Practice Bot";
	}
}