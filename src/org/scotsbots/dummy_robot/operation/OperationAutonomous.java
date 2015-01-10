package org.scotsbots.dummy_robot.operation;

import java.util.ArrayList;
import java.util.List;

public class OperationAutonomous
{
	static List<OperationAutonomous>autons = new ArrayList<OperationAutonomous>();
	
	public static void initialize()
	{
		
	}
	
	public static void update()
	{
		
	}
	
	public static void addAuton(OperationAutonomous e)
	{
		autons.add(e);
	}
}
