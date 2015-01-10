/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
/* Howell SCOTS Bots 2015 - Competition Season                                */
/*----------------------------------------------------------------------------*/

package org.scotsbots.dummy_robot;

import org.scotsbots.dummy_robot.operation.OperationTeleop;
import org.scotsbots.dummy_robot.operation.RobotOperation;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * Main Class for our 2015 Robot
 * @author Domenic
 *
 */
public class Robot extends IterativeRobot 
{
    public void robotInit() 
    {
    	Logger.riolog("S.C.O.T.S. Bots 2015 Robot starting up.");
    	RobotHardware.initialize();
    	RobotOperation.initialize();
    	//RobotVision.initialize();
    }
    
    public void autonomousInit()
    {
    	RobotOperation.reset();
    }
    
    public void autonomousPeriodic() 
    {

    }
    
    public void teleopInit()
    {
    	RobotOperation.reset();
    	OperationTeleop.initialize();
    }
    
    public void teleopPeriodic() 
    {
		RobotOperation.logSmartDashboard();
		OperationTeleop.update();
    }

    public void testInit()
    {
    	RobotOperation.reset();
    }
    
    public void testPeriodic() 
    {
    	LiveWindow.run();
    	RobotOperation.logSmartDashboard();
    }
    
    public void disabledInit() 
    {
    }
}
