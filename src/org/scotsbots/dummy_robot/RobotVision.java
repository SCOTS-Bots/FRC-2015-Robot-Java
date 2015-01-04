package org.scotsbots.dummy_robot;

public class RobotVision
{
	//This is the old 2014 code that no longer works for 2015. Will edit later. -Domenic
	/*
	private static Timer timer = new Timer();
	private static final String BEAGELIP = "192.168.1.103";

	public static void initialize()
	{
		timer.start();
		System.out.println("Robot Vision Intialize");
		Thread q = new Thread(new Runnable()
		{
			public void run()
			{
				while (true)
				{
					try
					{
						Thread.sleep(30);
						retrieve();
					} catch (Exception e)
					{
						// System.out.println("Exception in thread: " + e);
					}
				}
			}
		});
		q.start();
	}

	static SocketConnection http = null;
	static InputStream data = null;

	public static void retrieve()
	{
		boolean connectionFailure = true;
		try
		{
			http = (SocketConnection) Connector.open("socket://" + BEAGELIP);
			connectionFailure = false;
			data = http.openInputStream();
			int p = 1;
			int length = 0;
			int failTime = 0;
			while (p >= 0 && length < 100 && failTime < 300)
			{ // this is on the robot.
				if (data.available() > 0)
				{
					p = data.read();
					length++;
					failTime = 0;
				} else
				{
					try
					{
						Thread.sleep(20);
					} catch (Exception e)
					{

					}
					failTime += 20;
				}
			}
			System.out.println("RobotVision message received:\n\t" + length
					+ "/100 , " + failTime + "/300ms");
			data.close();
			http.close();

			// SmartDashboard.putNumber("vision DATABASE SIZE",database.length());

		} catch (Exception e)
		{
			 System.out.println("Exception in RobotVision.retrieve() (networking):");
			 System.out.println("\t" + e);
			 System.out.println("\t" + e.getMessage());
		}
		try
		{
			data.close();
		} catch (Exception e)
		{
			// System.out.println("Error Closing Data: " + e);
		}
		try
		{

			http.close();
		} catch (Exception e)
		{
			// System.out.println("Error Closing HTTP: " + e);
		}
		if (connectionFailure)
		{
			// System.out.println("Connect Failure, gcing");
			double t = timer.get();
			System.gc();
			// System.out.println("GC took " + (timer.get() - t) + " seconds");
			try
			{
				Thread.sleep(30000);
			} catch (Exception e)
			{
			}
		}
	}
	*/
}
