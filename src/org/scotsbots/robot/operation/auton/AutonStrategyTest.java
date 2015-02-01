package org.scotsbots.robot.operation.auton;

import org.scotsbots.robot.Robot;
import org.scotsbots.robot.RobotOperation;
import org.scotsbots.robot.utils.Logger;

import edu.wpi.first.wpilibj.Timer;

public class AutonStrategyTest extends AutonStrategy
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
			if(RobotOperation.drive(1))
			{
				Timer.delay(2);
				step = 2;
			}
		}
		if(step == 2)
		{
			RobotOperation.turn(90);
			step = 3;			
		}
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
		return 2;
	}

}
