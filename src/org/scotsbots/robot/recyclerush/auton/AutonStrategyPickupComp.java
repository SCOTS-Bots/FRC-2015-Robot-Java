package org.scotsbots.robot.recyclerush.auton;

import org.scotsbots.robot.AutonStrategy;
import org.scotsbots.robot.RobotOperation;
import org.scotsbots.robot.recyclerush.RobotOperationCompbot;

import edu.wpi.first.wpilibj.Timer;

public class AutonStrategyPickupComp extends AutonStrategy
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
			//TODO This defeats the purpose of the step system, but whatever.
			RobotOperationCompbot.openArms();
			Timer.delay(1);
			while(RobotOperation.driveEncoded(12))
			{
				step = 2;
			}	
		}
		
		if(step == 2)
		{
			Timer.delay(2);
			RobotOperationCompbot.setLiftPosition(RobotOperationCompbot.POSITION_0);
			Timer.delay(1);
			RobotOperationCompbot.closeArms();
			Timer.delay(1);
			RobotOperationCompbot.setLiftPosition(RobotOperationCompbot.POSITION_1);
			Timer.delay(1);
			while(RobotOperation.driveEncoded(123))
			{
				step = 3;
			}
		}
	}

	@Override
	public String getName()
	{
		return "Pickup Tote";
	}

	@Override
	public boolean isDefault()
	{
		return false;
	}

	@Override
	public int amountSteps()
	{
		return 2;
	}

}
