package org.scotsbots.dummy_robot.operation;

import org.scotsbots.dummy_robot.hardware.RobotHardwarePracticebot;
import org.scotsbots.dummy_robot.utils.Gamepad;
import org.scotsbots.robot.Robot;

/** 
 * Carries out teleop mode.
 * @author Domenic
 *
 */
public class OperationTeleop
{
	public static void initialize()
	{
		
	}
	
	public static void update()
	{    	
		RobotOperation.driveTank();
		
		if(Robot.bot instanceof RobotHardwarePracticebot)
		{
			RobotHardwarePracticebot hardware = (RobotHardwarePracticebot)Robot.bot;			
			hardware.winchMotor.set(Gamepad.secondary.getLeftY());
		}
	}
}
