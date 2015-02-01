package org.scotsbots.robot.hardware;

import org.scotsbots.robot.RobotOperation;
import org.scotsbots.robot.operation.auton.AutonStrategy;
import org.scotsbots.robot.operation.auton.AutonStrategyTest;
import org.scotsbots.robot.utils.Gamepad;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.Servo;

//SSID 8888
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
		
		drivetrain.setInvertedMotor(MotorType.kFrontLeft, true);
		drivetrain.setInvertedMotor(MotorType.kFrontRight, true);

		halsensor = new DigitalInput(0);
		
		horizontalCamera = new Servo(8);
		verticalCamera = new Servo(9);
		
		solenoid = new DoubleSolenoid(0,1);
		
		gyro = new Gyro(1);
		accelerometer = new BuiltInAccelerometer();
	}

	@Override
	public void teleop()
	{	
		RobotOperation.driveDoubleStickArcade(); //Change this when switching drive mode

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
	
	@Override
	public boolean usesCamera()
	{
		return true;
	}

	@Override
	public String getName()
	{
		return "Woodbot";
	}
}
