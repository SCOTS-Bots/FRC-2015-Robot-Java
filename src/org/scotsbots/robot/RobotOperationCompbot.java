package org.scotsbots.robot;

public class RobotOperationCompbot
{
	//TODO Create arm position constants
	
	/**
	 * Resets robot to pre-defined state.
	 */
	public static void reset()
	{
		//reset lift encoder
		//set arms closed
		//set arms to position TODO 0 or 1.
	}
	
	/**
	 * Assuming lift is at position 1, the robot is not moving, and is above the tote, close arms and retract arms, move to 0 position, lift arms to position 2. 
	 */
	public static void pickupTote()
	{
		//if arms extended, retract
		//if arms open, close arms
		//lower arms to position 1.
		//raise to position two
	}
	
	/**
	 * Gets current postion of arm and raises one position.
	 */
	public static void raiseLiftPosition()
	{
		
	}
	
	/**
	 * Gets current postion of arm and lowers one position.
	 */
	public static void lowerLifPosition()
	{
		//if going to lowest postion, use hall effect sensor to stop and reset encoder.
	}
	
	/**
	 * Sets tote down at position 0.
	 */
	public static void setToteDown()
	{
		
	}
	
	/**
	 * Open or close arm solenoid.
	 */
	public static void toggleArms()
	{
		
	}
	
	/**
	 * Moves lift at speed.
	 * @param speed between 1 and -1
	 */
	public static void moveLift(int speed)
	{
		
	}
	
	/**
	 * Extends arms to hall effect sensor.
	 */
	public static void extendArms()
	{
		
	}
	
	/**
	 * Retracts arm to hall effect sensor
	 */
	public static void retractArms()
	{
		
	}
}
