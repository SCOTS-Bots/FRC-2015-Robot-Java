package org.scotsbots.robot.recyclerush.auton;

import org.scotsbots.robot.AutonStrategy;
import org.scotsbots.robot.Robot;
import org.scotsbots.robot.recyclerush.RobotHardwareCompbot;
import org.scotsbots.robot.recyclerush.RobotOperationCompbot;

public class AutonStrategyPickupCanCompTime extends AutonStrategy
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
		RobotHardwareCompbot.liftEncoder.reset();
	}

	@Override
	public void update()
	{
		time++;
		if(time >= 1 && time <= 25)
		{
			RobotOperationCompbot.openArms();
			
		}
		
		if(time >= 26 && time <= 176)
		{
			if (RobotHardwareCompbot.liftEncoder.get() < CAN_LIFT_POSITION)
			{
				RobotHardwareCompbot.liftMotor.set(-0.75);
			}
			else
			{
				RobotHardwareCompbot.liftMotor.set(0);
			}
		}
		
		if(time >= 176 && time <= 200)
		{
			Robot.bot.drivetrain.drive(-0.5, 0);
		}
		
		if(time >= 201 && time <= 226)
		{
			RobotOperationCompbot.closeArms();
		}
		
		if(time >= 227 && time <= 450)
		{
			if (RobotHardwareCompbot.liftEncoder.get() < CAN_LOAD_POSITION)
			{
				RobotHardwareCompbot.liftMotor.set(-0.75);
			}
			else
			{
				RobotHardwareCompbot.liftMotor.set(0);
			}
		}
		
		if(time >= 451 && time <= 481)
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
