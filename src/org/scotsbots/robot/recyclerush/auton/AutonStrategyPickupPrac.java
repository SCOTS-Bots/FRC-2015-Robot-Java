package org.scotsbots.robot.recyclerush.auton;

import org.scotsbots.robot.AutonStrategy;
import org.scotsbots.robot.RobotOperation;
import org.scotsbots.robot.recyclerush.RobotOperationPracticebot;

import edu.wpi.first.wpilibj.Timer;

public class AutonStrategyPickupPrac extends AutonStrategy
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
			RobotOperationPracticebot.openArms();
			Timer.delay(1);
			while(RobotOperation.driveEncoded(12))
			{
				step = 2;
				break;
			}	
		}
		
		if(step == 2)
		{
			Timer.delay(2);
			RobotOperationPracticebot.setLiftPosition(RobotOperationPracticebot.POSITION_0);
			Timer.delay(1);
			RobotOperationPracticebot.closeArms();
			Timer.delay(1);
			RobotOperationPracticebot.setLiftPosition(RobotOperationPracticebot.POSITION_1);
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
