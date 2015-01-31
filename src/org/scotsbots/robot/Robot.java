/*----------------------------------------------------------------------------*/

/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
/* Howell SCOTS Bots 2015 - Competition Season                                */
/*----------------------------------------------------------------------------*/

package org.scotsbots.robot;

import org.scotsbots.robot.hardware.RobotHardware;
import org.scotsbots.robot.hardware.RobotHardwarePracticebot;
import org.scotsbots.robot.operation.auton.AutonStrategy;
import org.scotsbots.robot.utils.Logger;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Main Class for our 2015 Robot
 * @author Domenic
 *
 */
public class Robot extends IterativeRobot 
{
	public static SendableChooser autoChooser;
	public static AutonStrategy selectedAuton = null;
	public static RobotHardware bot = null;
	
    public void robotInit() 
    {
    	Logger.riolog("S.C.O.T.S. Bots 2015 Robot intializing...");
    	autoChooser = new SendableChooser();
    	bot = new RobotHardwarePracticebot();   //This changes which bot it loads.
    	bot.initialize();
    	RobotOperation.initialize();
    	if(bot.usesCamera())
    	{
    		RobotVision.initialize();
    	}
    	bot.addAutons();
		SmartDashboard.putData("Autonomous Mode Chooser", autoChooser);
		Logger.riolog("S.C.O.T.S. Bots 2015 Robot intialized.");
    }
    
    public void autonomousInit()
    {
    	RobotOperation.reset();
    	selectedAuton = (AutonStrategy) autoChooser.getSelected();
    	selectedAuton.intialize();
    }
    
    public void autonomousPeriodic() 
    {
    	if(selectedAuton.step <= selectedAuton.amountSteps())
    	{
    		selectedAuton.update();
    	}
		RobotOperation.logSmartDashboard();
    }
    
    public void teleopInit()
    {
    	RobotOperation.reset();
    }
    
    public void teleopPeriodic() 
    {
    	if(bot.usesCamera())
    	{
    		RobotVision.stream();
    	}
		RobotOperation.logSmartDashboard();
		bot.teleop();
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
		SmartDashboard.putData("Autonomous Mode Chooser", autoChooser);
    }
}
