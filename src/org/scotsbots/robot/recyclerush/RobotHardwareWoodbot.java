package org.scotsbots.robot.recyclerush;

import org.scotsbots.robot.Robot;
import org.scotsbots.robot.RobotHardware;
import org.scotsbots.robot.RobotOperation;
import org.scotsbots.robot.utils.Gamepad;
import org.scotsbots.robot.utils.Logger;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Servo;

//SSID 8888
public class RobotHardwareWoodbot extends RobotHardware
{
	public Jaguar rearLeftMotor;
	public Jaguar frontLeftMotor;
	
	public DigitalInput halsensor;
	
	public Servo horizontalCamera;
	public Servo verticalCamera;
	
	public DoubleSolenoid solenoid;
	
	public Jaguar liftMotor;
	public Jaguar liftMotor2;
	public Encoder liftEncoder;
	
	public void initialize() 
	{
		rearLeftMotor = new Jaguar(0);
		frontLeftMotor = new Jaguar(1);
		liftMotor = new Jaguar(2);
		liftMotor2 = new Jaguar(3);
		liftEncoder = new Encoder(6, 7, false, EncodingType.k4X);
		
		drivetrain = new RobotDrive(rearLeftMotor, frontLeftMotor);	
		
		drivetrain.setInvertedMotor(MotorType.kFrontLeft, true);
		drivetrain.setInvertedMotor(MotorType.kFrontRight, true);

		halsensor = new DigitalInput(0);
		
		horizontalCamera = new Servo(8);
		verticalCamera = new Servo(9);
		
		solenoid = new DoubleSolenoid(0,1);
		
		gyro = new Gyro(1);
		accelerometer = new BuiltInAccelerometer();
		
		liftEncoder.reset();
		
		RobotHardwareWoodbot hardware = (RobotHardwareWoodbot)Robot.bot;
		
		LiveWindow.addActuator("Drive Train", "Front Left Motor", (Jaguar)hardware.frontLeftMotor);
		LiveWindow.addActuator("Drive Train", "Back Left Motor", (Jaguar)hardware.rearLeftMotor);
		//LiveWindow.addActuator("Drive Train", "Front Right Motor", (Jaguar)hardware.frontRightMotor);
		//LiveWindow.addActuator("Drive Train", "Back Right Motor", (Jaguar)hardware.rearRightMotor);
	}

	@Override
	public void teleop()
	{	
		boolean lifting = false;
		
		RobotOperation.driveDoubleStickArcade(0); //Change this when switching drive mode

		if(Gamepad.secondaryGamepad.getA())
		{
			solenoid.set(Value.kForward);
		}
		else
		{
			solenoid.set(Value.kReverse);
		}
		
		if(Gamepad.secondaryGamepad.getB())
		{
			lifting = !lifting;
		}

		if(lifting && Math.abs(liftEncoder.getRaw()) < 2)
		{
			liftMotor.set(0.5);
			liftMotor2.set(0.5);
			
		}
		if(lifting && Math.abs(liftEncoder.getRaw()) > 2)
		{
			liftMotor.set(0);
			liftMotor2.set(0);
			lifting = false;
		}
		if(!lifting)
		{
			liftMotor.set(0);
			liftMotor2.set(0);
		}
		Logger.riolog("" + liftEncoder.getRaw());
	}

	@Override
	public void logSmartDashboard()
	{
		RobotHardwareWoodbot hardware = (RobotHardwareWoodbot)Robot.bot;				
		SmartDashboard.putBoolean("Magnet?", !hardware.halsensor.get());
		super.logSmartDashboard();
	}
	
	@Override
	public void addAutons()
	{
	}
	
	@Override
	public boolean usesCamera()
	{
		return true;
	}

	@Override
	public String getName()
	{
		return "Woodbot";
	}
}
