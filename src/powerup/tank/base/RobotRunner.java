package powerup.tank.base;

import powerup.tank.Robot;
import powerup.tank.graphics.TankGraphics;

public class RobotRunner {
	
	public static void run(RobotBase robot, CommandGroupBase group) {
		
		boolean running = true;
		int currentCommand = 0;
		boolean runInit = true;
		
		Robot.log("RobotRunner:robotInit");
		robot.robotInit();
		
		TankGraphics graphics = new TankGraphics();
		graphics.setup();
		
		while (running && TankGraphics.getTime() < 30) {
			
			CommandBase command = group.getCommand(currentCommand);
			
			// check to see if this command is finished
			if (command != null && command.isFinished()) {
				currentCommand ++;
				runInit = true;
				command = group.getCommand(currentCommand);
			}
			
			if (command == null) {
				running = false;
			} else {
				if (runInit) {
					command.initialize();
					runInit = false;
				}
				Robot.log("RobotRunner:execute "+command.getClass().getName());
				command.execute();	
			}
			
			graphics.drawField(Robot.driveTrain.getPositionX(), Robot.driveTrain.getPositionY(), Robot.driveTrain.getAngle());
			
			
			// add a little delay
			try {
				Thread.sleep(100);
				Robot.log("--------------------------\n\n");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
		Robot.log("RobotRunner:done score:"+TankGraphics.getScore());
		
	}

}
