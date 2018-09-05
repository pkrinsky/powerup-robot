package powerup.tank.commands;

import powerup.tank.Robot;
import powerup.tank.base.CommandBase;

public class Turn extends CommandBase {
	
	private boolean success = false;
	private int targetAngle;
	
	public Turn(int angle) {
		// save the target angle for later
		this.targetAngle = angle;
		Robot.log("Turn:targetAngle:"+targetAngle);
	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		int currentAngle = Robot.driveTrain.getAngle();
		Robot.log("Turn:currentAngle:"+currentAngle);
		
		// if we have turned far enough then stop
		if (currentAngle == targetAngle) {
			Robot.driveTrain.tankDrive(0, 0);
			success = true;
		} else {
			// compare the current to the target angle to see which way to turn
			if (currentAngle < targetAngle) {
				Robot.driveTrain.tankDrive(1, -1);	
			} else {
				Robot.driveTrain.tankDrive(-1, 1);
			}
		}
	}

	protected boolean isFinished() {
		return success;	
	}

}
