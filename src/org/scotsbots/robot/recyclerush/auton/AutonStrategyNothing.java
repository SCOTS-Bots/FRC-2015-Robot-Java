package org.scotsbots.robot.recyclerush.auton;

import org.scotsbots.robot.AutonStrategy;
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
}
