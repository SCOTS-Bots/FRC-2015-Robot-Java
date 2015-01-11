package org.scotsbots.dummy_robot.operation;

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
	}
}
