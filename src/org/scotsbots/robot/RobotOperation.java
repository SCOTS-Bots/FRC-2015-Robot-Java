package org.scotsbots.robot;

import org.scotsbots.robot.hardware.RobotHardwareCompbot;
import org.scotsbots.robot.hardware.RobotHardwarePracticebot;
import org.scotsbots.robot.hardware.RobotHardwareWoodbot;
import org.scotsbots.robot.utils.Gamepad;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Static class for basic robot operation calls.
 * @author Domenic
 *
 * TODO: Use distanceFrontToBack encoder to measure distance traveled instead of left and right encoders
 */
public class RobotOperation 
{	
	public static PIDController encoderControl = new PIDController(4, 0, 0, new PIDSource() 
	{
		public double pidGet() 
		{
			return getDistance();
		}
	}, new PIDOutput() 
	{
		public void pidWrite(double d) 
		{
			Robot.bot.drivetrain.tankDrive(d, d);
		}
	});
	
	
	/**
	 * Sets up Encoders and adds monitors to LiveWindow.
	 */
	public static void initialize()
	{	
		Robot.bot.gyro.initGyro();		
		
		if(Robot.bot.leftDriveEncoder != null && Robot.bot.rightDriveEncoder != null)
		{
			Robot.bot.leftDriveEncoder.setDistancePerPulse(0.042);
			Robot.bot.rightDriveEncoder.setDistancePerPulse(0.042);
		}		
		if(Robot.bot.driveEncoder != null)
		{
			Robot.bot.driveEncoder.setDistancePerPulse(0.042);
		}
		
		if(Robot.bot instanceof RobotHardwareWoodbot)
		{
			RobotHardwareWoodbot hardware = (RobotHardwareWoodbot)Robot.bot;
			
			LiveWindow.addActuator("Drive Train", "Front Left Motor", (Jaguar)hardware.frontLeftMotor);
			LiveWindow.addActuator("Drive Train", "Back Left Motor", (Jaguar)hardware.rearLeftMotor);
			//LiveWindow.addActuator("Drive Train", "Front Right Motor", (Jaguar)hardware.frontRightMotor);
			//LiveWindow.addActuator("Drive Train", "Back Right Motor", (Jaguar)hardware.rearRightMotor);
		}
		
		if(Robot.bot instanceof RobotHardwarePracticebot)
		{
			RobotHardwarePracticebot hardware = (RobotHardwarePracticebot)Robot.bot;
			
			LiveWindow.addActuator("Drive Train", "Front Left Motor", (Talon)hardware.leftMotors);
			LiveWindow.addActuator("Drive Train", "Front Left Motor", (Talon)hardware.rightMotors);
		}
	}
	
	/**
	 * Displays operation info on smart dashboard.
	 */
	public static void logSmartDashboard()
	{		
		SmartDashboard.putString("Current Robot", Robot.bot.getName());
		
		if(Robot.bot instanceof RobotHardwareWoodbot)
		{
			RobotHardwareWoodbot hardware = (RobotHardwareWoodbot)Robot.bot;				
			SmartDashboard.putBoolean("Magnet?", !hardware.halsensor.get());
		}
		
		if(Robot.bot instanceof RobotHardwareCompbot)
		{
			RobotHardwareCompbot hardware = (RobotHardwareCompbot)Robot.bot;
			SmartDashboard.putNumber("Winch Encoder", hardware.liftEncoder.get());
			SmartDashboard.putBoolean("Lift bottom?", (!hardware.liftBottomLimit.get() || !hardware.backupLiftBottomLimit.get()));
			SmartDashboard.putBoolean("Lift top?", (!hardware.liftTopLimit.get()));
			SmartDashboard.putNumber("Lift Gear", hardware.liftGear);
			SmartDashboard.putNumber("Lift Speed Ratio", hardware.liftSpeedRatio);
			SmartDashboard.putNumber("Drive Speed Ratio", hardware.driverSpeedRatio);
			SmartDashboard.putNumber("Current Set Position", RobotOperationCompbot.currentSetPosition);
			SmartDashboard.putNumber("Left to Right Distance (inches)", hardware.distanceLeftToRight.getDistance());
			SmartDashboard.putNumber("Front to Back Distance (inches)", hardware.distanceFrontToBack.getDistance());
		}
		
		Robot.bot.accelerometer.startLiveWindowMode();
		
		SmartDashboard.putNumber("Gyro Angle", Robot.bot.gyro.getAngle());
		SmartDashboard.putNumber("Accelerometer X", Robot.bot.accelerometer.getX());
		SmartDashboard.putNumber("Accelerometer Y", Robot.bot.accelerometer.getY());

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
	    while (Math.abs(targetHeading - Robot. bot.gyro.getAngle()) > 0.5)
	    {
	        Robot.bot.drivetrain.arcadeDrive(0.0, (angle < 0.0)? -0.5: 0.5);
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
		if((Robot.bot.leftDriveEncoder != null && Robot.bot.rightDriveEncoder != null) || Robot.bot.driveEncoder != null)
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
		if(Robot.bot instanceof RobotHardwareCompbot)
		{
			RobotOperationCompbot.reset();
		}
		
		Robot.bot.gyro.initGyro();

		if(Robot.bot.leftDriveEncoder != null && Robot.bot.rightDriveEncoder != null)
		{
			Robot.bot.leftDriveEncoder.reset();
			Robot.bot.rightDriveEncoder.reset();
			
			if(encoderControl != null)
			{
				encoderControl.reset();
			}
		}	
		if(Robot.bot.driveEncoder != null)
		{
			Robot.bot.driveEncoder.reset();
			
			if(encoderControl != null)
			{
				encoderControl.reset();
			}
		}
		time = 0;
	}
	
	/**
	 * Gets distance of drive encoder(s).
	 * @return
	 */
	public static double getDistance()
	{
		if(Robot.bot.leftDriveEncoder != null && Robot.bot.rightDriveEncoder != null)
		{
			return (Robot.bot.leftDriveEncoder.getDistance() + Robot.bot.rightDriveEncoder.getDistance())/2;
		}
		if(Robot.bot.driveEncoder != null)
		{
			return Robot.bot.driveEncoder.getDistance();
		}
		return -1;
	}
}
