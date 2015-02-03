package org.scotsbots.robot.operation.auton;

import org.scotsbots.robot.RobotOperation;

import edu.wpi.first.wpilibj.Timer;

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
			Timer.delay(3);
			RobotOperation.drive(3);
			step = 2;
		}
	}

	@Override
	public String getName()
	{
		return "Drive Straight";
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
