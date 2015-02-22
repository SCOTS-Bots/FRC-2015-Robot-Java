package org.scotsbots.robot.recyclerush.auton;

import org.scotsbots.robot.AutonStrategy;
import org.scotsbots.robot.RobotOperation;

public class AutonStrategyDrive extends AutonStrategy
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
			
			RobotOperation.turnTimedMillis(2000);
			step = 2;
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
		return true;
	}

	@Override
	public int amountSteps()
	{
		return 1;
	}

}
