package org.scotsbots.robot.operation;

import org.scotsbots.robot.Robot;
import org.scotsbots.robot.hardware.RobotHardwarePracticebot;
import org.scotsbots.robot.utils.Gamepad;

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
