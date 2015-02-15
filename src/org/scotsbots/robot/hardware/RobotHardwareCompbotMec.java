package org.scotsbots.robot.hardware;

import org.scotsbots.robot.RobotOperation;
import org.scotsbots.robot.RobotOperationCompbot;
import org.scotsbots.robot.operation.auton.AutonStrategy;
import org.scotsbots.robot.operation.auton.AutonStrategyDriveEncoded;
import org.scotsbots.robot.operation.auton.AutonStrategyNothing;
import org.scotsbots.robot.utils.Gamepad;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;

//SSID 4776
public class RobotHardwareCompbotMec extends RobotHardware
{
	public Talon rightRearMotors;
	public Talon rightFrontMotors;
	public Talon leftFrontMotors;
	public Talon leftRearMotors;
		
	public double driverSpeedRatio;
	
	@Override
	public void initialize()
	{
		//PWM
		rightRearMotors = new Talon(1);
		rightFrontMotors = new Talon(0);
		leftFrontMotors = new Talon(2);
		leftRearMotors = new Talon(3);
		

		//ANALOG
		gyro = new Gyro(0);
		
		//roboRio
		accelerometer = new BuiltInAccelerometer();
		
		//Stuff
		drivetrain = new RobotDrive(leftRearMotors, leftFrontMotors,rightRearMotors,rightFrontMotors);
		
		//drivetrain.setInvertedMotor(MotorType.kRearLeft, true);
		//drivetrain.setInvertedMotor(MotorType.kRearRight, true);
	}

	@Override
	public void teleop()
	{		
		RobotOperation.driveMecanum(0, driverSpeedRatio); //Change this when switching drive mode		
	}

	@Override
	public void addAutons()
	{
		AutonStrategy.addAuton(new AutonStrategyNothing());
		AutonStrategy.addAuton(new AutonStrategyDriveEncoded());		
	}

	@Override
	public String getName()
	{
		return "Competition Bot - Mec";
	}
}
