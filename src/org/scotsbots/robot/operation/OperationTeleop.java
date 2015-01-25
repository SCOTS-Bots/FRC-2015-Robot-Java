package org.scotsbots.robot.operation;

import org.scotsbots.robot.Robot;
import org.scotsbots.robot.hardware.RobotHardwarePracticebot;
import org.scotsbots.robot.hardware.RobotHardwareWoodbot;
import org.scotsbots.robot.utils.Gamepad;

/** 
 * Carries out teleop mode.
 * @author Domenic
 *
 */
public class OperationTeleop
{
	public static int horizontalCameraVal = 0;
	
	public static void initialize()
	{
		horizontalCameraVal = 0;
	}
	
	public static void update()
	{    	
		RobotOperation.driveTank(); //Change this when switching drive mode
		
		if(Robot.bot instanceof RobotHardwarePracticebot)
		{
			RobotHardwarePracticebot hardware = (RobotHardwarePracticebot)Robot.bot;			
			hardware.winchMotor.set(Gamepad.secondary.getLeftY() / 2);
		}
		
		if(Robot.bot instanceof RobotHardwareWoodbot)
		{
			RobotHardwareWoodbot hardware = (RobotHardwareWoodbot)Robot.bot;

			if(Gamepad.secondary.getDPadLeft())
			{
				horizontalCameraVal--;
				hardware.horizontalCamera.setRaw(horizontalCameraVal);
			}
			
			else if(Gamepad.secondary.getDPadRight())
			{
				horizontalCameraVal++;
				hardware.horizontalCamera.setRaw(horizontalCameraVal);
			}
			hardware.solenoid.set(Gamepad.secondary.getA());
		}
	}
}
