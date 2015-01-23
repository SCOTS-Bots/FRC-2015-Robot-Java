package org.scotsbots.robot.operation;

import org.scotsbots.robot.Robot;
import org.scotsbots.robot.hardware.RobotHardware;
import org.scotsbots.robot.hardware.RobotHardwarePracticebot;
import org.scotsbots.robot.hardware.RobotHardwareWoodbot;
import org.scotsbots.robot.utils.Gamepad;
import org.scotsbots.robot.utils.Logger;
import org.scotsbots.robot.utils.MathUtils;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
		if(Robot.bot instanceof RobotHardwarePracticebot)
		{
			Robot.bot.leftDriveEncoder.setDistancePerPulse(0.042);
			Robot.bot.rightDriveEncoder.setDistancePerPulse(0.042);
		}
		
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
			
			LiveWindow.addSensor("Drive Train", "Left Encoder", hardware.leftDriveEncoder);
			LiveWindow.addSensor("Drive Train", "Right Encoder", hardware.rightDriveEncoder);
		}
	}
	
	/**
	 * Displays operation info on smart dashboard.
	 */
	public static void logSmartDashboard()
	{
		SmartDashboard.putNumber("Left Distance", Robot.bot.leftDriveEncoder.getDistance());
		SmartDashboard.putNumber("Right Distance", Robot.bot.rightDriveEncoder.getDistance());
		SmartDashboard.putNumber("Left Speed", Robot.bot.leftDriveEncoder.getRate());
		SmartDashboard.putNumber("Right Speed", Robot.bot.rightDriveEncoder.getRate());
		
		if(Robot.bot instanceof RobotHardwareWoodbot)
		{
			RobotHardwareWoodbot hardware = (RobotHardwareWoodbot)Robot.bot;				
			SmartDashboard.putBoolean("Magnet?", !hardware.halsensor.get());
		}
		
	}
	
	public static void driveTank()
	{
		Robot.bot.drivetrain.tankDrive(Gamepad.primary.getLeftY(), Gamepad.primary.getRightY(), false);
        Timer.delay(0.005);	// wait 5ms to avoid hogging CPU cycles
	}
	
	public static void driveSingleStickArcade()
	{
		Robot.bot.drivetrain.arcadeDrive(Gamepad.primary.getLeftY(), Gamepad.primary.getRightY());
        Timer.delay(0.005);	// wait 5ms to avoid hogging CPU cycles
	}
	
	public static void driveDoubleStickArcade()
	{
		Robot.bot.drivetrain.arcadeDrive(Gamepad.primary.getLeftY(), Gamepad.primary.getLeftX());        
		Timer.delay(0.005);	// wait 5ms to avoid hogging CPU cycles
	}
	
	public static void driveMecanum()
	{
		Robot.bot.drivetrain.mecanumDrive_Cartesian(Gamepad.primary.getLeftX(), Gamepad.primary.getLeftY(), Gamepad.primary.getRightX(), 0);       
        Timer.delay(0.005);	// wait 5ms to avoid hogging CPU cycles
	}
	
	/**
	 * Drives straight using encoders. Returns true if arrived at setpoint.
	 * @param distance
	 */
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
	
	/**
	 *	Resets encoders and PIDController.
	 */
	public static void reset()
	{
		Robot.bot.leftDriveEncoder.reset();
		Robot.bot.rightDriveEncoder.reset();
		if(encoderControl != null)
		{
			encoderControl.reset();
		}
	}
	
	/**
	 * Gets average distance between the two encoders.
	 * @return
	 */
	public static double getDistance()
	{
		return (Robot.bot.leftDriveEncoder.getDistance() + Robot.bot.rightDriveEncoder.getDistance())/2;
	}
}
