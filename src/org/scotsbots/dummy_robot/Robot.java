/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
/* Howell SCOTS Bots 2015 - Competition Season                                */
/*----------------------------------------------------------------------------*/

package org.scotsbots.dummy_robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class Robot extends IterativeRobot 
{
    public void robotInit() 
    {
    	Logger.riolog("S.C.O.T.S. Bots 2015 Robot starting up.");
    	RobotHardware.initialize();
    	//RobotVision.initialize();
    }

    public void autonomousPeriodic() 
    {

    }

    public void teleopPeriodic() 
    {
    	try
    	{
    		RobotHardware.drivetrain.tankDrive(-Gamepad.primary.joystick.getY(), Gamepad.primary.joystick.getRawAxis(3), true);
    	} 
    	catch(Exception e) 
    	{
    		Logger.riolog("Drivetrain threw an exception.", e);
    	}
    }

    public void testPeriodic() 
    {
    	LiveWindow.run();
    }
    
    public void disabledInit() 
    {
    }
}
