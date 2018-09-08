package powerup.tank.base;

import powerup.tank.Robot;
import powerup.tank.graphics.TankGraphics;

public class RobotRunner {
	
	public static void run(RobotBase robot, CommandGroupBase group) {
		run(robot,group,100);
	}
	
	public static void run(RobotBase robot, CommandGroupBase group, long delay) {
		
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
			if (command != null) {
				Robot.log("RobotRunner:got command "+command.getClass().getName()+" isFinished:"+command.isFinished());
				if (command.isFinished()) {
					currentCommand ++;
					runInit = true;
					command = group.getCommand(currentCommand);
					if (command != null)
						Robot.log("RobotRunner:got next command "+command.getClass().getName());
				}
			} else {
				Robot.log("RobotRunner:no commands to run");
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
				Thread.sleep(delay);
				Robot.log("--------------------------\n\n");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
		Robot.log("RobotRunner:done score:"+TankGraphics.getScore());
		
	}

}
