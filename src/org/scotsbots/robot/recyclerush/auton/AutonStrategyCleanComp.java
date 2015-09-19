package org.scotsbots.robot.recyclerush.auton;

import org.scotsbots.robot.AutonStrategy;
import org.scotsbots.robot.Robot;
import org.scotsbots.robot.recyclerush.RobotHardwareCompbot;
import org.scotsbots.robot.recyclerush.RobotOperationCompbot;

public class AutonStrategyCleanComp extends AutonStrategy
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
			RobotOperationCompbot.closeArms();
		}
		
		if(time >= 1 && time <= 155)
		{
			if (RobotHardwareCompbot.liftEncoder.get() < 1000)
			{
				RobotHardwareCompbot.liftMotor.set(-0.75);
			}
			else
			{
				RobotHardwareCompbot.liftMotor.set(0);
			}
		}
		if(time >= 156 && time <= 210)
		{
			Robot.bot.drivetrain.drive(0.5, 0);
		}
	}

	@Override
	public String getName()
	{
		return "Clean";
	}
}
