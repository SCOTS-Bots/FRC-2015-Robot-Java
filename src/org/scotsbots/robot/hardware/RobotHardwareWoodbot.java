package org.scotsbots.robot.hardware;

import org.scotsbots.robot.RobotOperation;
import org.scotsbots.robot.operation.auton.AutonStrategy;
import org.scotsbots.robot.operation.auton.AutonStrategyTest;
import org.scotsbots.robot.utils.Gamepad;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Solenoid;

public class RobotHardwareWoodbot extends RobotHardware
{
	public Jaguar rearLeftMotor;
	public Jaguar frontLeftMotor;
	public Jaguar rearRightMotor;
	public Jaguar frontRightMotor;
	
	public DigitalInput halsensor;
	
	public Servo horizontalCamera;
	public Servo verticalCamera;
	
	public DoubleSolenoid solenoid;
	
	public void initialize() 
	{
		rearLeftMotor = new Jaguar(0);
		frontLeftMotor = new Jaguar(1);
		rearRightMotor = new Jaguar(2);
		frontRightMotor = new Jaguar(3);
		drivetrain = new RobotDrive(rearLeftMotor, frontLeftMotor,rearRightMotor, frontRightMotor);
		
		leftDriveEncoder = new Encoder(7, 8, false, Encoder.EncodingType.k4X);
		rightDriveEncoder = new Encoder(9, 10, false, Encoder.EncodingType.k4X);	
		
		drivetrain.setInvertedMotor(MotorType.kFrontLeft, true);
		drivetrain.setInvertedMotor(MotorType.kFrontRight, true);

		halsensor = new DigitalInput(0);
		
		horizontalCamera = new Servo(8);
		verticalCamera = new Servo(9);
		
		solenoid = new DoubleSolenoid(0,1);
	}

	@Override
	public void teleop()
	{	
		RobotOperation.driveTank(); //Change this when switching drive mode

		if(Gamepad.secondary.getA())
		{
			solenoid.set(Value.kForward);
		}
		else
		{
			solenoid.set(Value.kReverse);
		}
	}

	@Override
	public void addAutons()
	{
		AutonStrategy.addAuton(new AutonStrategyTest());		
	}
}
