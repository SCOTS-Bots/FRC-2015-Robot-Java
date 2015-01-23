package org.scotsbots.robot.hardware;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;

public abstract class RobotHardware
{	
	public RobotHardware bot;
	
	//universal hardware
	public RobotDrive drivetrain;
	public Encoder leftDriveEncoder;
	public Encoder rightDriveEncoder;
	
	public abstract void initialize();

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
