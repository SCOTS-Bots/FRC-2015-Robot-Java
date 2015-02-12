package org.scotsbots.robot.hardware;

import org.scotsbots.robot.RobotOperation;
import org.scotsbots.robot.RobotOperationCompbot;
import org.scotsbots.robot.operation.auton.AutonStrategy;
import org.scotsbots.robot.operation.auton.AutonStrategyDriveEncoded;
import org.scotsbots.robot.utils.Gamepad;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;

//ssid 4776
public class RobotHardwareMecCompbot extends RobotHardware
{
	public Victor rightFrontMotor;
	public Victor rightBackMotor;
	public Victor leftFrontMotor;
	public Victor leftBackMotor;
	
	public static Victor liftMotor;
	public static Victor armMotors;
	
	public static DoubleSolenoid armSolenoid;
	
	public static Encoder liftEncoder;
	
	public static Servo transmission;
	
	public static DigitalInput liftBottomLimit;
	public static DigitalInput armBottomLimit;
	public static DigitalInput armUpperLimit;
	
	public double liftSpeedRatio;
	public int liftGear;
	
	public double driverSpeedRatio;
	
	@Override
	public void initialize()
	{
		rightFrontMotor = new Victor(1);
		rightBackMotor = new Victor(2);
		leftFrontMotor = new Victor(8);
		leftBackMotor = new Victor(9);
		
		drivetrain = new RobotDrive(leftFrontMotor,leftBackMotor,rightFrontMotor,rightBackMotor);
		
		liftMotor = new Victor(2);
		armMotors = new Victor(3);
		
		armSolenoid = new DoubleSolenoid(0,1);
		
		liftEncoder = new Encoder(5, 6, false, EncodingType.k4X);
		//driveEncoder = new Encoder(8, 9, false, EncodingType.k4X);
		
		transmission = new Servo(7);
		
		liftBottomLimit = new DigitalInput(0);
		armBottomLimit = new DigitalInput(1);
		armUpperLimit = new DigitalInput(2);
		
		gyro = new Gyro(0);
		accelerometer = new BuiltInAccelerometer();
		
		liftSpeedRatio = 0.5; //Half power default
		liftGear = 1;
		driverSpeedRatio = 1;
	}

	@Override
	public void teleop()
	{
		RobotOperation.driveMecanum(1); //Change this when switching drive mode
		
		RobotOperationCompbot.moveLift(Gamepad.secondaryAttackJoystick.getLeftY() * liftSpeedRatio);
		RobotOperationCompbot.moveArms(Gamepad.secondaryAttackJoystick.getRightY());
		
		if(Gamepad.secondaryAttackJoystick.getDPadRight())
		{
			liftSpeedRatio = 0.5;
		}
		
		if(Gamepad.secondaryAttackJoystick.getDPadLeft())
		{
			liftSpeedRatio = 0.25;
		}
		
		if(Gamepad.secondaryAttackJoystick.getDPadUp())
		{
			liftGear = 2;
			transmission.set(1);
		}
		
		if(Gamepad.secondaryAttackJoystick.getDPadDown())
		{
			liftGear = 1;
			transmission.set(0);
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
			RobotOperationCompbot.raiseLiftPosition();
		}
		if(Gamepad.secondaryAttackJoystick.getA())
		{
			RobotOperationCompbot.lowerLiftPosition();
		}
		
		//driver
		if(Gamepad.primaryRightAttackJoystick.getButton(4))
		{
			driverSpeedRatio = 0.5;
		}
		if(Gamepad.primaryRightAttackJoystick.getButton(5))
		{
			driverSpeedRatio = 1;
		}
		
		//failsafe
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