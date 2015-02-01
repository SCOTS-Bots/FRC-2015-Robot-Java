package org.scotsbots.robot;

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
 */
public class RobotOperation 
{	
	/* TODO uncomment after encoders
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
	*/
	
	/**
	 * Sets up Encoders and adds monitors to LiveWindow.
	 */
	public static void initialize()
	{	
		/* Encoder stuff
		if(Robot.bot instanceof RobotHardwarePracticebot)
		{
			Robot.bot.leftDriveEncoder.setDistancePerPulse(0.042);
			Robot.bot.rightDriveEncoder.setDistancePerPulse(0.042);
		}
		*/
		
		if(Robot.bot instanceof RobotHardwareWoodbot)
		{
			RobotHardwareWoodbot hardware = (RobotHardwareWoodbot)Robot.bot;
			
			LiveWindow.addActuator("Drive Train", "Front Left Motor", (Jaguar)hardware.frontLeftMotor);
			LiveWindow.addActuator("Drive Train", "Back Left Motor", (Jaguar)hardware.rearLeftMotor);
			LiveWindow.addActuator("Drive Train", "Front Right Motor", (Jaguar)hardware.frontRightMotor);
			LiveWindow.addActuator("Drive Train", "Back Right Motor", (Jaguar)hardware.rearRightMotor);
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
		
		Robot.bot.accelerometer.startLiveWindowMode();
		
		SmartDashboard.putNumber("Gyro Angle", Robot.bot.gyro.getAngle());
		SmartDashboard.putNumber("Accelerometer X", Robot.bot.accelerometer.getX());
		SmartDashboard.putNumber("Accelerometer Y", Robot.bot.accelerometer.getY());

	}
	
	/**
	 * Drives tank drive using left joystick and right joystick
	 */
	public static void driveTank()
	{
		Robot.bot.drivetrain.tankDrive(Gamepad.primary.getLeftY(), Gamepad.primary.getRightY(), true);
        Timer.delay(0.005);	// wait 5ms to avoid hogging CPU cycles
	}
	
	/**
	 * Drives the robot in arcade using the left up/down and the right up/down
	 */
	public static void driveDoubleStickArcade()
	{
		Robot.bot.drivetrain.arcadeDrive(Gamepad.primary.getLeftY(), Gamepad.primary.getRightX());
        Timer.delay(0.005);	// wait 5ms to avoid hogging CPU cycles
	}
	
	/**
	 * Drives the robot in arcade using the left up/down and left left/right
	 */
	public static void driveSingleStickArcade()
	{
		Robot.bot.drivetrain.arcadeDrive(Gamepad.primary.getLeftY(), Gamepad.primary.getLeftX());        
		Timer.delay(0.005);	// wait 5ms to avoid hogging CPU cycles
	}
	
	/**
	 * Drives mecanum using left left/right, left up/down, and right left/right.
	 */
	public static void driveMecanum()
	{
		Robot.bot.drivetrain.mecanumDrive_Cartesian(Gamepad.primary.getLeftX(), Gamepad.primary.getLeftY(), Gamepad.primary.getRightX(), 0);       
        Timer.delay(0.005);	// wait 5ms to avoid hogging CPU cycles
	}
	
	static double time = 0;
	/**
	 * Drives straight using gyro. Checks if there from accelerometer.
	 * @param distance
	 */
	public static boolean drive(double timeToTravel)
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
	
	public static void reset()
	{
		time = 0;
	}
	
	/*
	public static boolean drive(double distance)
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
		
		return false;
	}
	
	public static void reset()
	{
		Robot.bot.leftDriveEncoder.reset();
		Robot.bot.rightDriveEncoder.reset();
		if(encoderControl != null)
		{
			encoderControl.reset();
		}
	}
	
	public static double getDistance()
	{
		return (Robot.bot.leftDriveEncoder.getDistance() + Robot.bot.rightDriveEncoder.getDistance())/2;
	}
	*/
}