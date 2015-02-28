package org.scotsbots.robot.recyclerush.auton;

import org.scotsbots.robot.AutonStrategy;
import org.scotsbots.robot.Robot;
import org.scotsbots.robot.recyclerush.RobotOperationCompbot;

public class AutonStrategyPickupCompTime extends AutonStrategy
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
			RobotOperationCompbot.openArms();
		}
		
		if(time >= 100 && time <= 150)
		{
			Robot.bot.drivetrain.drive(-0.5, 0);
		}
		
		if(time == 175)
		{
			RobotOperationCompbot.closeArms();
		}
		
		if(time >= 200 && time <= 300)
		{
			Robot.bot.drivetrain.drive(0.5, 0);
		}
		if(time == 301)
		{
			RobotOperationCompbot.openArms();
		}
	}

	@Override
	public String getName()
	{
		return "Pickup Tote Timed";
	}

	@Override
	public boolean isDefault()
	{
		return false;
	}
}
