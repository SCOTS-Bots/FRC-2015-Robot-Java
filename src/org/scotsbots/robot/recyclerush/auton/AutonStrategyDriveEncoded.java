package org.scotsbots.robot.recyclerush.auton;

import org.scotsbots.robot.AutonStrategy;
import org.scotsbots.robot.RobotOperation;

public class AutonStrategyDriveEncoded extends AutonStrategy
{
	@Override
	public void intialize()
	{
		
	}

	@Override
	public void update()
	{
		//TODO Fix this.
		while(RobotOperation.driveEncoded(123))
		{
			;
		}
		
	}

	@Override
	public String getName()
	{
		return "Drive Straight Encoded";
	}

	@Override
	public boolean isDefault()
	{
		return false;
	}
}
