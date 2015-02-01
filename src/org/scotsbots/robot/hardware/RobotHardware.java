package org.scotsbots.robot.hardware;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.RobotDrive;

/**
 * Base class for creating a robot.
 * @author Domenic
 *
 */
public abstract class RobotHardware
{	
	public RobotHardware bot;
	
	//universal hardware
	public RobotDrive drivetrain;
	public Gyro gyro;
	public BuiltInAccelerometer accelerometer;
	
	public abstract void initialize();
	public abstract void teleop();
	public abstract void addAutons();
	public abstract String getName();

	public boolean usesCamera()
	{
		return false;
	}

	public RobotHardware()
	{
		bot = this;
	}
	
	public RobotHardware getBot()
	{
		return bot;
	}
	
	public RobotHardware setBot(RobotHardware r)
	{
		return bot = r;
	}	
}
