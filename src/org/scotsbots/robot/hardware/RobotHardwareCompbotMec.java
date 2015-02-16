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
	//needs to change tank to mec
	public Victor rightMotors;
	public Victor leftMotors;
	
	//public static Victor liftMotor;
	//public static Victor armMotors;
	
	//public static DoubleSolenoid armSolenoid;
	
	//public static Encoder liftEncoder;
	
	//public static Servo transmission;
	
	//public static DigitalInput liftBottomLimit;
	//public static DigitalInput armBottomLimit;
	
	//public double liftSpeedRatio;
	//public int liftGear;
	
	public double driverSpeedRatio;
	
	@Override
	public void initialize()
	{
		//PWM
		//liftMotor = new Victor(0); 
		leftMotors = new Victor(1);
		rightMotors = new Victor(2); 
		//armMotors = new Victor(3);

		//CAN
		//armSolenoid = new DoubleSolenoid(4,5);
		
		//DIO
		//liftBottomLimit = new DigitalInput(2);
		
		//DIO 3
		//liftEncoder = new Encoder(0, 1, false, EncodingType.k4X);
		//transmission = new Servo(7);
		driveEncoder = new Encoder(8, 9, false, EncodingType.k4X);

		
		//ANALOG
		gyro = new Gyro(0);
		
		//roboRio
		accelerometer = new BuiltInAccelerometer();
		
		//Stuff
		drivetrain = new RobotDrive(leftMotors, rightMotors);

		//liftSpeedRatio = 1; //Half power default
		//liftGear = 1;
		driverSpeedRatio = 1;
		
		drivetrain.setInvertedMotor(MotorType.kRearLeft, true);
		drivetrain.setInvertedMotor(MotorType.kRearRight, true);
	}

	@Override
	public void teleop()
	{
		int debounce = 0;
		
		RobotOperation.driveTank(1, driverSpeedRatio); //Change this when switching drive mode		
		//RobotOperationCompbot.moveLift(Gamepad.secondaryAttackJoystick.getLeftY() * liftSpeedRatio);
	
		//Arm extension
		//RobotOperationCompbot.moveArms(Gamepad.secondaryAttackJoystick.getRightY());
		
		if(Gamepad.secondaryAttackJoystick.getDPadRight())
		{
			//liftSpeedRatio = 1;
		}
		
		if(Gamepad.secondaryAttackJoystick.getDPadLeft())
		{
			//liftSpeedRatio = 0.5;
		}
		
		if(Gamepad.secondaryAttackJoystick.getDPadUp())
		{
			//liftGear = 2;
			//transmission.set(1);
		}
		
		if(Gamepad.secondaryAttackJoystick.getDPadDown())
		{
			//liftGear = 1;
			//transmission.set(0);
		}

		if(Gamepad.secondaryAttackJoystick.getRB())
		{			
			RobotOperationCompbot.closeArms();
		}
		if(Gamepad.secondaryAttackJoystick.getLB())
		{
			RobotOperationCompbot.openArms();
		}
		
		if(Gamepad.secondaryAttackJoystick.getY())
		{
			if(debounce == 1)
			{
				RobotOperationCompbot.raiseLiftPosition();
			}
			debounce++;
			if(debounce == 1000)
			{
				debounce = 0;
			}
		}
		if(Gamepad.secondaryAttackJoystick.getA())
		{
			if(debounce == 1)
			{
				RobotOperationCompbot.lowerLiftPosition();
			}
			debounce++;
			if(debounce == 1000)
			{
				debounce = 0;
			}
		}
		
		//driver
		if(Gamepad.primaryRightAttackJoystick.getButton(4))
		{
			driverSpeedRatio = 0.75;
		}
		if(Gamepad.primaryRightAttackJoystick.getButton(5))
		{
			driverSpeedRatio = 1;
		}
		
		//failsafe
		/*
		  if(!liftBottomLimit.get())
		 
		{
			 liftEncoder.reset();
			 if(liftMotor.getSpeed() > 0)
			 {
				 liftMotor.set(0);
			 }
		}
		
		if(liftEncoder.get() == RobotOperationCompbot.MAX_HEIGHT)
		{
			liftMotor.set(0);
		}
		*/
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
		return "Mec Bot";
	}
}
