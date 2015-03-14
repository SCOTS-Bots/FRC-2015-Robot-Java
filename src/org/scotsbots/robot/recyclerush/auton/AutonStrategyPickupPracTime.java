package org.scotsbots.robot.recyclerush.auton;

import org.scotsbots.robot.AutonStrategy;
import org.scotsbots.robot.Robot;
import org.scotsbots.robot.recyclerush.RobotOperationPracticebot;

public class AutonStrategyPickupPracTime extends AutonStrategy
{
	public int time = 0;
	
	@Override
	public void intialize()
	{
		
	}

	@Override
	public void update()
	{
		time++;
		if(time == 1)
		{
			RobotOperationPracticebot.openArms();
		}
		
		if(time >= 100 && time <= 150)
		{
			Robot.bot.drivetrain.drive(-0.5, 0);
		}
		
		if(time == 175)
		{
			RobotOperationPracticebot.closeArms();
		}
		
		if(time >= 200 && time <= 350)
		{
			Robot.bot.drivetrain.drive(0.5, 0);
		}
		if(time == 351)
		{
			RobotOperationPracticebot.openArms();
		}
	}

	@Override
	public String getName()
	{
		return "Pickup Tote Timed";
	}
}
