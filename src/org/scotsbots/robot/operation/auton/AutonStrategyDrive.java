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
			RobotOperation.turn(90);
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
