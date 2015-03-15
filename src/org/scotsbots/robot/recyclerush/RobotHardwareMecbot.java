package org.scotsbots.robot.recyclerush;

import org.scotsbots.robot.AutonStrategy;
import org.scotsbots.robot.RobotHardware;
import org.scotsbots.robot.RobotOperation;
import org.scotsbots.robot.recyclerush.auton.AutonStrategyNothing;

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.Talon;

public class RobotHardwareMecbot extends RobotHardware
{
	public Talon rightFront;
	public Talon rightBack;
	public Talon leftFront;
	public Talon leftBack;
	
	@Override
	public void initialize()
	{
		rightFront = new Talon(0);
		rightBack = new Talon(1);
		leftFront = new Talon(3);
		leftBack = new Talon(2);
		drivetrain = new RobotDrive(leftBack, leftFront, rightBack, rightFront);		
		
		gyro = new Gyro(0);
		
		drivetrain.setInvertedMotor(MotorType.kFrontRight, true);
		drivetrain.setInvertedMotor(MotorType.kRearRight, true);
	}

	@Override
	public void teleop()
	{
		RobotOperation.driveMecanum(1);
	}

	@Override
	public AutonStrategy getSwitchedAuton()
	{
		return new AutonStrategyNothing();
	}

	@Override
	public String getName()
	{
		return "Mecbot";
	}

}
