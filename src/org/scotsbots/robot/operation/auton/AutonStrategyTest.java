package org.scotsbots.robot.operation.auton;

import org.scotsbots.robot.RobotOperation;
import org.scotsbots.robot.utils.Logger;

public class AutonStrategyTest extends AutonStrategy
{
	@Override
	public void intialize() 
	{
		Logger.riolog("Auton Strategy Initialized.");
		if(RobotOperation.drive(1) && step == 1)
		{
			step = 2;
			Logger.riolog("true");
		}
	}

	@Override
	public void update() 
	{
		
	}

	@Override
	public String getName() 
	{
		return "Test Auton";
	}

	@Override
	public boolean isDefault() 
	{
		return true;
	}

	public int amountSteps()
	{
		return 1;
	}

}
