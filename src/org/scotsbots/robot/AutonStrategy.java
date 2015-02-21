package org.scotsbots.robot;

import java.util.ArrayList;

import org.scotsbots.robot.utils.Logger;

/**
 * An abstract class for creating autonomous strategies. Make sure to add the auton strat to the list.
 * @author Domenic
 */
public abstract class AutonStrategy 
{
	public int step = 1;
	
	static ArrayList<AutonStrategy>autons = new ArrayList<AutonStrategy>();
	
	public static void addAuton(AutonStrategy e)
	{
		autons.add(e);
		if(e.isDefault())
		{
			Robot.autoChooser.addDefault(e.getName(), e);
		}
		else
		{
			Robot.autoChooser.addObject(e.getName(), e);
		}
		Logger.riolog("Auton Strategy: " + e + " added.");
	}
	
	public static void removeAuton(AutonStrategy e)
	{
		autons.remove(e);
		Logger.riolog("Auton Strategy: " + e + " removed.");
	}
	
	public static ArrayList<AutonStrategy> getAutons()
	{
		return autons;
	}
	
	public abstract void intialize();
	public abstract void update(); 
	public abstract String getName();
	public abstract boolean isDefault();
	public abstract int amountSteps();
}
