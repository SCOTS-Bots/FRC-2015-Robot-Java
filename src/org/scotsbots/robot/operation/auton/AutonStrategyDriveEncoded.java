package org.scotsbots.robot.operation.auton;

import org.scotsbots.robot.RobotOperation;

import edu.wpi.first.wpilibj.Timer;

public class AutonStrategyDriveEncoded extends AutonStrategy
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
			Timer.delay(3);
			RobotOperation.driveEncoded(6);
			step = 2;
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

	@Override
	public int amountSteps()
	{
		return 1;
	}

}
