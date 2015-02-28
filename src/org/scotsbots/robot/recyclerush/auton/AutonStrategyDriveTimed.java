package org.scotsbots.robot.recyclerush.auton;

import org.scotsbots.robot.AutonStrategy;
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
			RobotOperation.driveTimed(1);
		}
	}

	@Override
	public String getName()
	{
		return "Drive Straight Timed";
	}

	@Override
	public boolean isDefault()
	{
		return false;
	}
}
