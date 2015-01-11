package org.scotsbots.dummy_robot.operation;

import org.scotsbots.dummy_robot.operation.auton.AutonStrategy;
import org.scotsbots.dummy_robot.operation.auton.AutonStrategyTest;

/**
 * Carries out autonomous mode.
 * @author Domenic
 *
 */
public class OperationAutonomous
{
	public static AutonStrategy autonStrategyTest;
	
	/**
	 * Create and add all auton strategies here.
	 */
	public static void initializeAutons()
	{
		autonStrategyTest = new AutonStrategyTest();
		AutonStrategy.addAuton(autonStrategyTest);		
	}

}
