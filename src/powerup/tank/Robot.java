package powerup.tank;

import powerup.tank.base.RobotBase;
import powerup.tank.base.RobotRunner;
import powerup.tank.commands.DriveFromAtoB;
import powerup.tank.subsystems.DriveTrain;

public class Robot extends RobotBase {
	
	// subsystems
	public static DriveTrain driveTrain;
	
	public void robotInit() {
		driveTrain = new DriveTrain();
	}
	
	public static void main(String[] args) {
		Robot robot = new Robot();
		RobotRunner.run(robot, new DriveFromAtoB());
	}		

}
