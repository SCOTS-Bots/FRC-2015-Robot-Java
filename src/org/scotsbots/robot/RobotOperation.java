package org.scotsbots.robot;

import org.scotsbots.robot.recyclerush.RobotHardwareCompbot;
import org.scotsbots.robot.recyclerush.RobotOperationCompbot;
import org.scotsbots.robot.utils.Gamepad;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.Timer;

/**
 * Static class for basic robot operation calls.
 * @author Domenic
 *
 */
public class RobotOperation 
{	
	public static PIDController encoderControl = new PIDController(.1, .001, 0, new PIDSource() 
	{
		public double pidGet() 
		{
			return getDistance();
		}
	}, new PIDOutput() 
	{
		public void pidWrite(double d) 
		{
			Robot.bot.drivetrain.tankDrive(-d * 0.5, -d * 0.5);
		}
	});
	
	/**
	 * Sets up Encoders and Gyro.
	 */
	public static void initialize()
	{	
		Robot.bot.gyro.initGyro();		
		
		encoderControl.setPercentTolerance(10);
		
		if(Robot.bot.leftDriveEncoder != null && Robot.bot.rightDriveEncoder != null)
		{
			Robot.bot.leftDriveEncoder.setDistancePerPulse(0.042);
			Robot.bot.rightDriveEncoder.setDistancePerPulse(0.042);
		}		
		if(Robot.bot.forwardDriveEncoder != null && Robot.bot.sideDriveEncoder != null)
		{
			Robot.bot.forwardDriveEncoder.setDistancePerPulse(0.042);
			Robot.bot.sideDriveEncoder.setDistancePerPulse(0.042);
		}
	}
	
	/**
	 * Drives tank drive.
	 * @param joystickSet 0 - Only gamepads, 1 - attacks and gamepad
	 */
	public static void driveTank(int joystickSet)
	{
		driveTank(joystickSet, 1);
	}
	
	/**
	 * Drives in tank, multiplies speed by speed ratio.
	 * @param joystickSet 0 - Only gamepads, 1 - attacks and gamepad
	 * @param speedRatio range of motor power
	 */
	public static void driveTank(int joystickSet, double speedRatio)
	{
		if(joystickSet == 0)
		{
			Robot.bot.drivetrain.tankDrive(Gamepad.primaryGamepad.getLeftY() * speedRatio, Gamepad.primaryGamepad.getRightY() * speedRatio, true);
		}
		if(joystickSet == 1)
		{
			Robot.bot.drivetrain.tankDrive(Gamepad.primaryLeftAttackJoystick.getY()* speedRatio, Gamepad.primaryRightAttackJoystick.getY()* speedRatio, true);
		}
		
        Timer.delay(0.005);	// wait 5ms to avoid hogging CPU cycles
	}
	
	/**
	 * Drives the robot in arcade using the left up/down and the right up/down
	 * @param joystickSet 0 - Only gamepads, 1 - attacks and gamepad
	 */
	public static void driveDoubleStickArcade(int joystickSet)
	{
		if(joystickSet == 0)
		{
			Robot.bot.drivetrain.arcadeDrive(Gamepad.primaryGamepad.getLeftY(), Gamepad.primaryGamepad.getRightX());
		}
		if(joystickSet == 1)
		{
			Robot.bot.drivetrain.arcadeDrive(Gamepad.primaryLeftAttackJoystick.getY(), Gamepad.primaryRightAttackJoystick.getX());
		}
		
        Timer.delay(0.005);	// wait 5ms to avoid hogging CPU cycles
	}
	
	/**
	 * Drives the robot in arcade using the left up/down and left left/right
	 * @param joystickSet 0 - Only gamepads, 1 - attacks and gamepad
	 */
	public static void driveSingleStickArcade(int joystickSet)
	{
		if(joystickSet == 0)
		{
			Robot.bot.drivetrain.arcadeDrive(Gamepad.primaryGamepad.getLeftY(), Gamepad.primaryGamepad.getLeftX()); 
		}
		if(joystickSet == 1)
		{
			Robot.bot.drivetrain.arcadeDrive(Gamepad.primaryLeftAttackJoystick.getY(), Gamepad.primaryLeftAttackJoystick.getX());
		}
		Timer.delay(0.005);	// wait 5ms to avoid hogging CPU cycles
	}
	
	public static void driveMecanum(int joystickSet)
	{
		driveMecanum(joystickSet, 1);
	}
	
	/**
	 * Drives mecanum using left left/right, left up/down, and right left/right.
	 * @param joystickSet 0 - Only gamepads, 1 - attacks and gamepad
	 */
	public static void driveMecanum(int joystickSet, double speedRatio)
	{
		if(joystickSet == 0)
		{
			Robot.bot.drivetrain.mecanumDrive_Cartesian(Gamepad.primaryGamepad.getLeftX() * speedRatio, Gamepad.primaryGamepad.getLeftY() * speedRatio, Gamepad.primaryGamepad.getRightX() * speedRatio, Robot.bot.gyro.getAngle());       
		}
		if(joystickSet == 1)
		{
			Robot.bot.drivetrain.mecanumDrive_Cartesian(Gamepad.primaryLeftAttackJoystick.getX() * speedRatio, Gamepad.primaryLeftAttackJoystick.getY() * speedRatio, Gamepad.primaryRightAttackJoystick.getX() * speedRatio, Robot.bot.gyro.getAngle());       
		}
		Timer.delay(0.005);	// wait 5ms to avoid hogging CPU cycles
	}
	
	static double time = 0;
	/**
	 * Drives straight using gyro. Checks if there from accelerometer.
	 * @param distance
	 */
	public static boolean driveTimed(double timeToTravel)
	{
		if(time/100 <= timeToTravel)
		{
			Robot.bot.drivetrain.drive(-1.0, 0);
		}
		else
		{
			return true;
		}
		time++;
		return false;
	}
		
	public static void turn(float angle)
	{
	    double targetHeading = Robot.bot.gyro.getAngle() + angle;
	    while (Math.abs(targetHeading - Robot.bot.gyro.getAngle()) > 0.5)
	    {
	        Robot.bot.drivetrain.arcadeDrive(0.0, (angle < 0.0)? 0.75: -0.75);
	    }
	}
	
	/**
	 * Drives using drive encoders if they exist.
	 * @param distance
	 * @return
	 */
	public static boolean driveEncoded(double distance)
	{		
		//Checks for dual or single drive encoders
		if((Robot.bot.leftDriveEncoder != null && Robot.bot.rightDriveEncoder != null) || (Robot.bot.forwardDriveEncoder != null && Robot.bot.sideDriveEncoder != null))
		{
			encoderControl.enable();
			encoderControl.setAbsoluteTolerance(0.01);
			encoderControl.setSetpoint(distance);
			if(encoderControl.onTarget())
			{
				return true;
			}
			else
			{
		        Timer.delay(0.005);	// wait 5ms to avoid hogging CPU cycles
			}
		}
		return false;
	}
	
	public static void reset()
	{
		if(Robot.bot.leftDriveEncoder != null && Robot.bot.rightDriveEncoder != null)
		{
			Robot.bot.leftDriveEncoder.reset();
			Robot.bot.rightDriveEncoder.reset();
			
			if(encoderControl != null)
			{
				encoderControl.reset();
			}
		}	
		if(Robot.bot.forwardDriveEncoder != null && Robot.bot.sideDriveEncoder != null)
		{
			Robot.bot.forwardDriveEncoder.reset();
			Robot.bot.sideDriveEncoder.reset();

			if(encoderControl != null)
			{
				encoderControl.reset();
			}
		}
		time = 0;
	}
	
	/**
	 * Gets distance of drive encoder(s).
	 * @returns negative 1 if there are no encoders initialized.
	 */
	public static double getDistance()
	{
		if(Robot.bot.leftDriveEncoder != null && Robot.bot.rightDriveEncoder != null)
		{
			return (Robot.bot.leftDriveEncoder.getDistance() + Robot.bot.rightDriveEncoder.getDistance())/2;
		}
		if(Robot.bot.forwardDriveEncoder != null && Robot.bot.sideDriveEncoder != null)
		{
			return Robot.bot.forwardDriveEncoder.getDistance();
		}
		return -1;
	}
}
