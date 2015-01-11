package org.scotsbots.dummy_robot.operation.auton;

import org.scotsbots.dummy_robot.Logger;

public class AutonStrategyTest extends AutonStrategy
{
	@Override
	public void intialize() 
	{
		Logger.riolog("Auton Strategy Initialized.");
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

}
