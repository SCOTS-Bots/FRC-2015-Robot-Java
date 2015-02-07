package org.scotsbots.robot.hardware;

import org.scotsbots.robot.RobotOperation;
import org.scotsbots.robot.RobotOperationCompbot;
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
	public static Victor armMotors;
	
	@Override
	public void initialize()
	{
		rightMotors = new Victor(0);
		leftMotors = new Victor(1);
		
		drivetrain = new RobotDrive(leftMotors, rightMotors);
		
		winchMotor = new Victor(2);
		armMotors = new Victor(3);
		
		gyro = new Gyro(0);
		accelerometer = new BuiltInAccelerometer();
		
		leftDriveEncoder = new Encoder(6,7, false, EncodingType.k4X);
		rightDriveEncoder = new Encoder(8,9, false, EncodingType.k4X);
	}

	@Override
	public void teleop()
	{
		RobotOperation.driveTank(1); //Change this when switching drive mode
		winchMotor.set(Gamepad.secondaryAttackJoystick.getLeftY() / 2); //currently at half power	
		armMotors.set(Gamepad.secondaryAttackJoystick.getRightY());
		
		if(Gamepad.secondaryAttackJoystick.getDPadUp())
		{
			RobotOperationCompbot.moveLift(1);
			//check encoder for upper limit
		}
		if(Gamepad.secondaryAttackJoystick.getDPadDown())
		{
			RobotOperationCompbot.moveLift(-1);
			//check encoder for lower limit
		}
		if(Gamepad.secondaryAttackJoystick.getRightY() > 0.1)
		{
			RobotOperationCompbot.moveArms(1);
		}
		if(Gamepad.secondaryAttackJoystick.getRightY() < -0.1)
		{
			RobotOperationCompbot.moveArms(-1);
		}
		if(Gamepad.secondaryAttackJoystick.getRB())
		{			
			RobotOperationCompbot.closeArms();
		}
		if(Gamepad.secondaryAttackJoystick.getLB())
		{
			RobotOperationCompbot.openArms();
		}
		
		//driver
		if(Gamepad.primaryRightAttackJoystick.getButton(4))
		{
			//set half speed
		}
		if(Gamepad.primaryRightAttackJoystick.getButton(5))
		{
			//set full speed
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
