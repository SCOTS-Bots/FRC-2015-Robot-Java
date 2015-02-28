package org.scotsbots.robot.recyclerush.auton;

import org.scotsbots.robot.AutonStrategy;
import org.scotsbots.robot.RobotOperation;

public class AutonStrategyDriveTimed extends AutonStrategy
{
	@Override
	public void intialize()
	{
		
	}

	@Override
	public void update()
	{
		if(step == 1)
		{
			if(RobotOperation.driveTimed(1.75))
			{
				step = 2;
			}
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

	@Override
	public int amountSteps()
	{
		return 1;
	}

}
