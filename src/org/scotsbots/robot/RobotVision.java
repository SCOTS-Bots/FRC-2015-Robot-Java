package org.scotsbots.robot;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.DrawMode;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ShapeMode;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.vision.AxisCamera;

public class RobotVision
{
	int session;
    static Image frame;
    static AxisCamera camera;
    
	public static void initialize()
	{
		frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
        camera = new AxisCamera("10.47.76.100");
	}
	
	public static void stream()
	{	        
        camera.getImage(frame);
		NIVision.Rect rect = new NIVision.Rect(10, 10, 100, 100);
        NIVision.imaqDrawShapeOnImage(frame, frame, rect, DrawMode.DRAW_VALUE, ShapeMode.SHAPE_OVAL, 0.0f);
        CameraServer.getInstance().setImage(frame);
        Timer.delay(0.005);		// wait for a motor update time
	}
}
