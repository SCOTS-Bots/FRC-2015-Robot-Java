/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
/* Howell SCOTS Bots 2015 - Pre Season                                        */
/*----------------------------------------------------------------------------*/

package org.scotsbots.dummy_robot;

import edu.wpi.first.wpilibj.IterativeRobot;

public class RobotMain extends IterativeRobot 
{
    public void robotInit() 
    {
    	Logger.startLogging();
    	Logger.log("Robot starting up.");
    	RobotHardware.initialize();
    }

    public void autonomousPeriodic() 
    {

    }

    public void teleopPeriodic() 
    {
        Logger.log("Running Teleop.");
    }

    public void testPeriodic() 
    {
    
    }
    
    public void disabledInit() 
    {
    }
}
