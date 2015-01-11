package org.scotsbots.dummy_robot.operation;

import org.scotsbots.dummy_robot.RobotHardware;
import org.scotsbots.dummy_robot.utils.Gamepad;
import org.scotsbots.dummy_robot.utils.MathUtils;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
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
			RobotHardware.drivetrain.tankDrive(d, d);
		}
	});

	/**
	 * Sets up Encoders and adds monitors to LiveWindow.
	 */
	public static void initialize()
	{
		RobotHardware.leftDriveEncoder.setDistancePerPulse(0.042);
		RobotHardware.rightDriveEncoder.setDistancePerPulse(0.042);
		
		LiveWindow.addActuator("Drive Train", "Front Left Motor", (Jaguar)RobotHardware.frontLeftMotor);
		LiveWindow.addActuator("Drive Train", "Back Left Motor", (Jaguar)RobotHardware.rearLeftMotor);
		LiveWindow.addActuator("Drive Train", "Front Right Motor", (Jaguar)RobotHardware.frontRightMotor);
		LiveWindow.addActuator("Drive Train", "Back Right Motor", (Jaguar)RobotHardware.rearRightMotor);

		LiveWindow.addSensor("Drive Train", "Left Encoder", RobotHardware.leftDriveEncoder);
		LiveWindow.addSensor("Drive Train", "Right Encoder", RobotHardware.rightDriveEncoder);
	}
	
	/**
	 * Displays operation info on smart dashboard.
	 */
	public static void logSmartDashboard()
	{
		SmartDashboard.putNumber("Left Distance", RobotHardware.leftDriveEncoder.getDistance());
		SmartDashboard.putNumber("Right Distance", RobotHardware.rightDriveEncoder.getDistance());
		SmartDashboard.putNumber("Left Speed", RobotHardware.leftDriveEncoder.getRate());
		SmartDashboard.putNumber("Right Distance", RobotHardware.rightDriveEncoder.getRate());
	}
	
	public static void driveTank()
	{
		RobotHardware.drivetrain.tankDrive(-Gamepad.primary.getLeftY(), Gamepad.primary.getRightY(), true);
        Timer.delay(0.005);	// wait 5ms to avoid hogging CPU cycles
	}
	
	public static void driveSingleStickArcade()
	{
		RobotHardware.drivetrain.arcadeDrive(Gamepad.primary.getLeftY(), Gamepad.primary.getRightY());
        Timer.delay(0.005);	// wait 5ms to avoid hogging CPU cycles
	}
	
	public static void driveDoubleStickArcade()
	{
		RobotHardware.drivetrain.arcadeDrive(Gamepad.primary.getLeftY(), Gamepad.primary.getLeftX());        
		Timer.delay(0.005);	// wait 5ms to avoid hogging CPU cycles
	}
	
	public static void driveMecanum()
	{
		RobotHardware.drivetrain.mecanumDrive_Cartesian(Gamepad.primary.getLeftX(), Gamepad.primary.getLeftY(), Gamepad.primary.getRightX(), 0);       
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
		RobotHardware.leftDriveEncoder.reset();
		RobotHardware.rightDriveEncoder.reset();
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
		return (RobotHardware.leftDriveEncoder.getDistance() + RobotHardware.rightDriveEncoder.getDistance())/2;
	}
}
