package org.scotsbots.robot.utils;

import java.util.Calendar;

import edu.wpi.first.wpilibj.DriverStation;

public class Logger 
{
	//This is the old 2014 code that no longer works for 2015. Will edit later. -Domenic
	/*
	public static boolean shouldLog = false;
	public static DriverStationLCD lcd = DriverStationLCD.getInstance();
	private static int currentLine = 1;
	
	public static void log(String contents, Exception e)
	{
		log(contents);
		log(e.getMessage());
	}
	
	
	 * Logs to lcd and console with time stamp.
	 * @param name - name of file.
	 
	public static void log(String contents)
	{
		if(shouldLog)
		{
			System.out.println("[Log] [" + Calendar.MINUTE + "mi " + Calendar.SECOND + "sec " + Calendar.MILLISECOND + "ms] " + contents);
			switch(currentLine)
			{
				case 1:
					lcd.println(DriverStationLCD.Line.kUser1, 1, contents);
				case 2:
					lcd.println(DriverStationLCD.Line.kUser2, 1, contents);
				case 3:
					lcd.println(DriverStationLCD.Line.kUser3, 1, contents);
				case 4: 
					lcd.println(DriverStationLCD.Line.kUser4, 1, contents);
				case 5:
					lcd.println(DriverStationLCD.Line.kUser5, 1, contents);
				case 6:
					lcd.println(DriverStationLCD.Line.kUser6, 1, contents);
				default: 
					lcd.println(DriverStationLCD.Line.kUser1, 1, contents);
			}
			
			currentLine++;
			if(currentLine == 7)
			{
				currentLine = 1;
			}
		}
		else
		{
			System.out.println("Not logging.");
		}
	}
	
	
	 * Clears the lcd and gets everything ready for logging.
	 
	public static void startLogging()
	{
		lcd.clear();
		shouldLog = true;
	}
	*/
	
	public static void riolog(String contents, boolean isErr)
	{
		if(isErr)
			System.err.println("[ERRLog] [" + Calendar.MINUTE + "mi " + Calendar.SECOND + "sec " + Calendar.MILLISECOND + "ms] " + contents);
		else
			System.out.println("[Log] [" + Calendar.MINUTE + "mi " + Calendar.SECOND + "sec " + Calendar.MILLISECOND + "ms] " + contents);
	}
	
	public static void riolog(String contents)
	{
		riolog(contents, false);
	}
	
	public static void riolog(String contents, Exception e)
	{
		riolog(contents, true);
		e.printStackTrace();
	}
}
