package org.scotsbots.robot.recyclerush;

import org.scotsbots.robot.AutonStrategy;
import org.scotsbots.robot.RobotHardware;
import org.scotsbots.robot.RobotOperation;
import org.scotsbots.robot.recyclerush.auton.AutonStrategyDriveTimed;
import org.scotsbots.robot.recyclerush.auton.AutonStrategyNothing;
import org.scotsbots.robot.recyclerush.auton.AutonStrategyPickupCompTime;
import org.scotsbots.robot.utils.Gamepad;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Victor;

//SSID 4776
public class RobotHardwareCompbot extends RobotHardware
{
	public Victor rightMotors;
	public Victor leftMotors;
	
	public static Victor liftMotor;
	public static Victor armMotors;
	
	public static DoubleSolenoid armSolenoid;
	
	public static Encoder liftEncoder;
	
	public static Servo transmission;
	
	public static DigitalInput liftBottomLimit;
	public static DigitalInput backupLiftBottomLimit;
	public static DigitalInput liftTopLimit;
	public static DigitalInput armBottomLimit;
	
	public double liftSpeedRatio;
	public int liftGear;
	
	public double driverSpeedRatio;
	
	public int debounceComp;
	
	@Override
	public void initialize()

	{
		//PWM
		liftMotor = new Victor(0); //2);
		leftMotors = new Victor(1);
		rightMotors = new Victor(2); //0);
		armMotors = new Victor(3);
		transmission = new Servo(7);

		//CAN
		armSolenoid = new DoubleSolenoid(4,5);
		
		//DIO
		liftEncoder = new Encoder(0, 1, false, EncodingType.k4X);
		liftBottomLimit = new DigitalInput(2);
		liftTopLimit = new DigitalInput(3);
		backupLiftBottomLimit = new DigitalInput(4);
		
		switch1 = new DigitalInput(9);
		switch2 = new DigitalInput(8);
		
		//ANALOG
		gyro = new Gyro(0);
		
		//roboRio
		accelerometer = new BuiltInAccelerometer();
		
		//Stuff
		drivetrain = new RobotDrive(leftMotors, rightMotors);

		liftSpeedRatio = 1; //Half power default
		liftGear = 1;
		driverSpeedRatio = 1;
		debounceComp = 0;
		
		drivetrain.setInvertedMotor(MotorType.kRearLeft, true);
		drivetrain.setInvertedMotor(MotorType.kRearRight, true);
	}

	@Override
	public void teleop()
	{
		failsafe();
		RobotOperation.driveTank(1, driverSpeedRatio); //Change this when switching drive mode		
		RobotOperationCompbot.moveLift(Gamepad.secondaryAttackJoystick.getLeftY() * liftSpeedRatio);
		
		//add position checker (ex. after manually passing pos 1 sets current position to pos 1)
	
		//Arm extension
		//RobotOperationCompbot.moveArms(Gamepad.secondaryAttackJoystick.getRightY());
		
		if(Gamepad.secondaryAttackJoystick.getDPadRight())
		{
			liftSpeedRatio = 1;
		}
		
		if(Gamepad.secondaryAttackJoystick.getDPadLeft())
		{
			liftSpeedRatio = 0.5;
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
			if(debounceComp == 0)
			{
				RobotOperationCompbot.raiseLiftPosition();
			}
			debounceComp++;
			if(debounceComp == 5)
			{
				debounceComp = 0;
			}
		}
		
		if(Gamepad.secondaryAttackJoystick.getA())
		{
			if(debounceComp == 0)
			{
				RobotOperationCompbot.lowerLiftPosition();
			}
			debounceComp++;
			if(debounceComp == 5)
			{
				debounceComp = 0;
			}
		}
		
		//picks up tote
		if(Gamepad.secondaryAttackJoystick.getX())
		{
			RobotOperationCompbot.pickupTote();
		}
		
		//resets encoder
		if(Gamepad.secondaryAttackJoystick.getR3())
		{
			liftEncoder.reset();
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
		failsafe();
	}
	
	private void failsafe()
	{
		//failsafe
		if(!liftBottomLimit.get() || !backupLiftBottomLimit.get())
		{
			 liftEncoder.reset();
			 if(liftMotor.getSpeed() > 0)
			 {
				 liftMotor.set(0);
			 }
		}
		
		if(!liftTopLimit.get())
		{
			 liftEncoder.reset();
			 if(liftMotor.getSpeed() < 0)
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
	public void logSmartDashboard()
	{
		SmartDashboard.putNumber("Winch Encoder", liftEncoder.get());
		SmartDashboard.putBoolean("Lift bottom?", (!liftBottomLimit.get() || !backupLiftBottomLimit.get()));
		SmartDashboard.putBoolean("Lift top?", (!liftTopLimit.get()));
		SmartDashboard.putNumber("Lift Gear", liftGear);
		SmartDashboard.putNumber("Lift Speed Ratio", liftSpeedRatio);
		SmartDashboard.putNumber("Drive Speed Ratio", driverSpeedRatio);
		SmartDashboard.putNumber("Current Set Position", RobotOperationCompbot.currentSetPosition);
		
		super.logSmartDashboard();
	}
	
	public AutonStrategy getSwitchedAuton()
	{
		if(switch1.get() == true && switch2.get() == true) //a1
		{
			return new AutonStrategyNothing();
		}
		else if(switch1.get() == true && switch2.get() == false) //a2
		{
			return new AutonStrategyDriveTimed();
		}
		else if(switch1.get() == false && switch2.get() == true) //b1
		{
			return new AutonStrategyPickupCompTime();
		}
		return null;
	}

	@Override
	public String getName()
	{
		return "Competition Bot";
	}
}