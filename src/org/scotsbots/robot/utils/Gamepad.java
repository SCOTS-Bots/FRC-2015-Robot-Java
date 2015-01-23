package org.scotsbots.robot.utils;

import edu.wpi.first.wpilibj.Joystick;

/**
 * Originally adapted from Adambots code.
 * 
 * @author Howell Robotics
 *
 */
public class Gamepad
{
	public Joystick joystick;

	/**
	 * Primary Driver Controller Port Number.
	 */
	private static final int DRIVER_PRIMARY = 0;

	/**
	 * Secondary Driver Controller Port Number.
	 */
	private static final int DRIVER_SECONDARY = 1;

	/**
	 * XBOX 360 South Face Button
	 */
	private static final int BUTTON_A = 1;

	/**
	 * XBOX 360 East Face Button
	 */
	private static final int BUTTON_B = 2;

	/**
	 * XBOX 360 West Face Button
	 */
	private static final int BUTTON_X = 3;

	/**
	 * XBOX 360 North Face Button
	 */
	private static final int BUTTON_Y = 4;
	/**
	 * XBOX 360 Left Bumper (Top)
	 */
	private static final int BUTTON_LB = 5;

	/**
	 * XBOX 360 Right Bumper (Top)
	 */
	private static final int BUTTON_RB = 6;

	/**
	 * XBOX 360 Back Button
	 */
	private static final int BUTTON_BACK = 7;

	/**
	 * XBOX 360 Start Button
	 */
	private static final int BUTTON_START = 8;

	/**
	 * XBOX 360 Left Horizontal Axis (Left=-1, Right=1)
	 */
	private static final int AXIS_LEFT_X = 0;

	/**
	 * XBOX 360 Left Vertical Axis (Up=-1, Down=1)
	 */
	private static final int AXIS_LEFT_Y = 1;

	/**
	 * XBOX 360 Trigger Axis (right - left)
	 */
	private static final int AXIS_TRIGGERS = 3;

	/**
	 * XBOX 360 Right Horizontal Axis (Left=-1, Right=1)
	 */
	private static final int AXIS_RIGHT_X = 2;

	/**
	 * XBOX 360 Right Vertical Axis (Up=-1, Down=1)
	 */
	private static final int AXIS_RIGHT_Y = 3;

	/**
	 * XBOX 360 Horizontal D-PAD
	 */
	private static final int AXIS_DPAD_HORIZONTAL = 6;

	private Gamepad(int port)
	{
		joystick = new Joystick(port);
	}

	public static Gamepad primary = new Gamepad(0);
	public static Gamepad secondary = new Gamepad(1);

	/**
	 * Returns the value of the trigger with a deadzone.
	 * 
	 * @return
	 */
	public static double createDeadzone(double triggerValue)
	{
		return Math.abs(triggerValue) < 0.15 ? 0 : triggerValue;
	}

	public double getTriggers()
	{
		return createDeadzone(joystick.getRawAxis(AXIS_TRIGGERS) * 2) / 2;
	}

	/**
	 * Corresponds to HORIZONTAL input on the LEFT joystick.
	 *
	 * @return The X coordinate of the left joystick (-1 is LEFT, 1 is RIGHT)
	 */
	public double getLeftX()
	{
		return createDeadzone(joystick.getRawAxis(AXIS_LEFT_X));
	}

	/**
	 * Corresponds to VERTICAL input on the LEFT joystick.
	 *
	 * @return The Y coordinate of the LEFT joystick (-1 is UP, 1 is DOWN)
	 */
	public double getLeftY()
	{
		return createDeadzone(joystick.getRawAxis(AXIS_LEFT_Y));
	}

	/**
	 * Corresponds to HORIZONTAL input on the RIGHT joystick
	 *
	 * @return The X coordinate of the RIGHT joystick (-1 is LEFT, 1 is RIGHT)
	 */
	public double getRightX()
	{
		return createDeadzone(joystick.getRawAxis(AXIS_RIGHT_X));
	}

	/**
	 * Corresponds to VERTICAL input on the RIGHT joystick
	 *
	 * @return The Y coordinate of the RIGHT joystick (-1 is UP, 1 is DOWN)
	 */
	public double getRightY()
	{
		return createDeadzone(joystick.getRawAxis(AXIS_RIGHT_Y));
	}

	public boolean getDPadLeft()
	{
		return joystick.getRawAxis(AXIS_DPAD_HORIZONTAL) < -0.5;
	}

	public boolean getDPadRight()
	{
		return joystick.getRawAxis(AXIS_DPAD_HORIZONTAL) > 0.5;
	}

	/**
	 *
	 * @return Is the left bumper pressed? [top one]
	 */
	public boolean getLB()
	{
		return joystick.getRawButton(BUTTON_LB);
	}

	/**
	 *
	 * @return Is the right bumper pressed? [top one]
	 */
	public boolean getRB()
	{
		return joystick.getRawButton(BUTTON_RB);
	}

	public boolean getA()
	{
		return joystick.getRawButton(BUTTON_A);
	}

	public boolean getB()
	{
		return joystick.getRawButton(BUTTON_B);
	}

	public boolean getX()
	{
		return joystick.getRawButton(BUTTON_X);
	}

	public boolean getY()
	{
		return joystick.getRawButton(BUTTON_Y);
	}

	public boolean getStart()
	{
		return joystick.getRawButton(BUTTON_START);
	}

	public boolean getBack()
	{
		return joystick.getRawButton(BUTTON_BACK);
	}
}