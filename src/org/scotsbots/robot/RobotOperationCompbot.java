package org.scotsbots.robot;

import org.scotsbots.robot.hardware.RobotHardwareCompbot;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class RobotOperationCompbot extends RobotHardwareCompbot
{
	//TODO Create and modify arm position constants
	public static final int POSITION_0 = 0;
	public static final int POSITION_1 = 979;
	public static final int POSITION_2 = 2026;
	public static final int POSITION_3 = 3044;
	public static final int POSITION_4 = 3936;
	public static final int MAX_HEIGHT = 4250;
	
	public static int currentSetPosition = POSITION_0;
	
	/**
	 * Resets robot to pre-defined state.
	 */
	public static void reset()
	{
		liftEncoder.reset();
		setLiftPosition(POSITION_0);
		closeArms();
	}
	
	/**
	 * Assuming lift is at position 1, the robot is not moving, and is above the tote, close arms and retract arms, move to 0 position, lift arms to position 2. 
	 */
	public static void pickupTote()
	{
		//if arms extended, retract
		//if arms open, close arms
		//lower arms to position 1.
		setLiftPosition(POSITION_1);
		//raise to position two
	}
	
	/**
	 * Gets current postion of arm and raises one position.
	 */
	public static void raiseLiftPosition()
	{
		switch(currentSetPosition)
		{
			case POSITION_0: 
			{
				setLiftPosition(POSITION_1);
				currentSetPosition = POSITION_1;
				break;
			}
			case POSITION_1: 
			{
				setLiftPosition(POSITION_2);
				currentSetPosition = POSITION_2;
				break;
			}
			case POSITION_2: 
			{
				setLiftPosition(POSITION_3);
				currentSetPosition = POSITION_3;
				break;
			}
			case POSITION_3: 
			{
				setLiftPosition(POSITION_4);
				currentSetPosition = POSITION_4;
				break;
			}
			case POSITION_4:
			{
				break;
			}
		}
	}
	
	/**
	 * Gets current postion of arm and lowers one position.
	 */
	public static void lowerLiftPosition()
	{
		switch(currentSetPosition)
		{
			case POSITION_4: 
			{
				setLiftPosition(POSITION_3);
				currentSetPosition = POSITION_3;
				break;
			}
			case POSITION_3: 
			{
				setLiftPosition(POSITION_2);
				currentSetPosition = POSITION_2;
				break;
			}
			case POSITION_2: 
			{
				setLiftPosition(POSITION_1);
				currentSetPosition = POSITION_1;
				break;
			}
			case POSITION_1: 
			{
				setLiftPosition(POSITION_0);
				currentSetPosition = POSITION_0;
				break;
			}
			case POSITION_0:
			{
				break;
			}
		}	
	}
	
	public static void setLiftPosition(int encoderVal)
	{
		if(encoderVal > liftEncoder.get())
		{
			moveLift(-0.75);	
		}
		if(encoderVal < liftEncoder.get())
		{
			moveLift(0.75);
		}
	}
	
	/**
	 * Sets tote down at position 0.
	 */
	public static void setToteDown()
	{
		
	}
	
	public static void openArms()
	{
		armSolenoid.set(Value.kForward);
	}
	
	public static void closeArms()
	{
		armSolenoid.set(Value.kReverse);
	}
	
	/**
	 * Moves lift at speed.
	 * @param speed between 1 and -1
	 */
	public static void moveLift(double speed)
	{
		liftMotor.set(speed);
	}
	
	public static void moveArms(double speed)
	{
		armMotors.set(speed);
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
