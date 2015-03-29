package org.scotsbots.robot.recyclerush.auton;

import org.scotsbots.robot.AutonStrategy;
import org.scotsbots.robot.Robot;
import org.scotsbots.robot.recyclerush.RobotHardwarePracticebot;
import org.scotsbots.robot.recyclerush.RobotOperationPracticebot;

public class AutonStrategyPickupCanPracTime extends AutonStrategy
{
	// Initial state assumed to be open arms around can arms may be at lowest level
	//
	//1. Set lift to proper can lift height
	//2. Close arms
	//3. Move forward to wall (x inches)
	//4. Set lift to proper can load height
	//
	private static final int CAN_LIFT_POSITION = 600;
	private static final int CAN_LOAD_POSITION = 2850;
	public int time = 0;
	
	@Override
	public void intialize()
	{
		RobotHardwarePracticebot.liftEncoder.reset();
	}

	@Override
	public void update()
	{
		time++;
		if(time >= 1 && time <= 150)
		{
			if (RobotHardwarePracticebot.liftEncoder.get() < CAN_LIFT_POSITION)
			{
				RobotHardwarePracticebot.liftMotor.set(-0.75);
			}
			else
			{
				RobotHardwarePracticebot.liftMotor.set(0);
			}
		}
		
		if(time >= 151 && time <= 175)
		{
			RobotOperationPracticebot.closeArms();
		}
		
		if(time >= 176 && time <= 400)
		{
			if (RobotHardwarePracticebot.liftEncoder.get() < CAN_LOAD_POSITION)
			{
				RobotHardwarePracticebot.liftMotor.set(-0.75);
			}
			else
			{
				RobotHardwarePracticebot.liftMotor.set(0);
			}
		}
		
		if(time >= 401 && time <= 430)
		{
			Robot.bot.drivetrain.drive(-0.5, 0);
		}
	}

	@Override
	public String getName()
	{
		return "Setup Can";
	}
}
