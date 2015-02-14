package org.scotsbots.robot.operation.auton;

import org.scotsbots.robot.utils.Logger;

public class AutonStrategyNothing extends AutonStrategy
{

	@Override
	public void intialize()
	{
		Logger.riolog("Doing nothing in autonomous.");
	}

	@Override
	public void update()
	{		
	}

	@Override
	public String getName()
	{
		return "Do Nothing";
	}

	@Override
	public boolean isDefault()
	{
		return true;
	}

	@Override
	public int amountSteps()
	{
		return 0;
	}
	
}