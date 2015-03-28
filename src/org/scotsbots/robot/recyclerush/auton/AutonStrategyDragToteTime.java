

package org.scotsbots.robot.recyclerush.auton;

import org.scotsbots.robot.Robot;
import org.scotsbots.robot.recyclerush.RobotHardwarePracticebot;
import org.scotsbots.robot.recyclerush.RobotOperationPracticebot;
import org.scotsbots.robot.AutonStrategy;

public class AutonStrategyDragToteTime extends AutonStrategy
{
public int time = 0;

public static final int DRAG_LEVEL = 200;
	
	@Override
	public void intialize()
	{
		RobotHardwarePracticebot.liftEncoder.reset();
	}

	@Override
	public void update()
	{
		time++;
		if(time == 1)
		{
			RobotOperationPracticebot.openArms();
		}
		
		if(time >= 100 && time <= 125)
		{
			Robot.bot.drivetrain.drive(-0.5, 0);
		}
		
		if(time == 150)
		{
			RobotOperationPracticebot.closeArms();
		}
		
		if(time >= 200 && time <= 270)
		{
			if (RobotHardwarePracticebot.liftEncoder.get() < DRAG_LEVEL)
			{
				RobotHardwarePracticebot.liftMotor.set(-0.75);
			}
			else
			{
				RobotHardwarePracticebot.liftMotor.set(0);
			}
		}
		if(time >= 300 && time <= 450)
		{
			Robot.bot.drivetrain.drive(0.5, 0);
		}
		if(time == 451)
		{
			RobotOperationPracticebot.openArms();
		}
	}

	@Override
	public String getName()
	{
		return "Drag Tote Timed";
	}
}
