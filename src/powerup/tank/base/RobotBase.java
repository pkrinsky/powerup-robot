package powerup.tank.base;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RobotBase {
	
	public void robotInit() {
		// init subsystems
	}
	
	public void autonomousInit() {
		//driveTrain.zeroLeftEncoder();
		//driveTrain.zeroRightEncoder();
		//driveTrain.zeroGyroRotation();	
	}
	
	public void autonomousPeriodic() {
		//Scheduler.getInstance().run();
		//driveTrain.getGyroRotation();
	}
	
	public static void log(String s) {
		String date = new SimpleDateFormat("HH:mm:ss:S").format(new Date())+"   ";
		System.out.println(date.substring(0, 13)+" "+s);
	}	

}
