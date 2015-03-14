package org.scotsbots.robot.recyclerush;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Timer;

public class RobotOperationPracticebot extends RobotHardwarePracticebot
{
	//TODO Create and modify arm position constants
	public static final int POSITION_0 = 0;
	public static final int POSITION_1 = 787;
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
		//setLiftPosition(POSITION_0);
		/*while((!liftBottomLimit.get() || !backupLiftBottomLimit.get()))// && (0 < liftEncoder.get()))
		{
			moveLift(0.75);	
		}*/
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
		
		setLiftPosition(POSITION_0);
		closeArms();
		setLiftPosition(POSITION_1);
	}
	
	/**
	 * Gets current position of arm and raises one position.
	 */
	public static void raiseLiftPosition()
	{
		if(liftEncoder.get() > POSITION_4)
		{
			;
		}
		else if(liftEncoder.get() > POSITION_3)
		{
			setLiftPosition(POSITION_4);
			//currentSetPosition = POSITION_4;
		}
		else if(liftEncoder.get() > POSITION_2)
		{
			setLiftPosition(POSITION_3);
			//currentSetPosition = POSITION_3;
		}
		else if(liftEncoder.get() > POSITION_1)
		{
			setLiftPosition(POSITION_2);
			//currentSetPosition = POSITION_2;
		}
		else if(liftEncoder.get() > POSITION_0)
		{
			setLiftPosition(POSITION_1);
			//currentSetPosition = POSITION_1;
		}
	}
	
	/**
	 * Gets current position of arm and lowers one position.
	 */
	public static void lowerLiftPosition()
	{
		if(liftEncoder.get() < POSITION_0)
		{
			;
		}
		else if(liftEncoder.get() < POSITION_1)
		{
			setLiftPosition(POSITION_0);
			//currentSetPosition = POSITION_0;
		}
		else if(liftEncoder.get() < POSITION_2)
		{
			setLiftPosition(POSITION_1);
			//currentSetPosition = POSITION_1;
		}
		else if(liftEncoder.get() < POSITION_3)
		{
			setLiftPosition(POSITION_2);
			//currentSetPosition = POSITION_2;
		}
		else if(liftEncoder.get() < POSITION_4)
		{
			setLiftPosition(POSITION_3);
			//currentSetPosition = POSITION_3;
		}
	}
	
	public static void setLiftPosition(int encoderVal)
	{
		while(encoderVal > liftEncoder.get())
		{
			moveLift(-0.75);	
		}
		while(encoderVal < liftEncoder.get())
		{
			moveLift(0.75);
		}
		currentSetPosition = encoderVal;
	}
	
	/**
	 * Sets tote down at position 0.
	 */
	public static void setToteDown()
	{
		setLiftPosition(POSITION_1);
		Timer.delay(2);
		openArms();
	}
	
	public static void openArms()
	{
		armSolenoid.set(Value.kReverse);
	}
	
	public static void closeArms()
	{
		armSolenoid.set(Value.kForward);
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
}
