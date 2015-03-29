package org.scotsbots.robot.recyclerush.auton;

import org.scotsbots.robot.AutonStrategy;
import org.scotsbots.robot.Robot;
import org.scotsbots.robot.RobotOperation;

public class AutonStrategyDriveTimed extends AutonStrategy
{
	public int time = 0;
	
	@Override
	public void intialize()
	{
		
	}

	@Override
	public void update()
	{
		time++;
		if(time >= 0 && time <= 100)
		{
			Robot.bot.drivetrain.drive(-0.5, 0);
		}
	}

	@Override
	public String getName()
	{
		return "Drive Straight Timed";
	}
}
