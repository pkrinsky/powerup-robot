package powerup.tank.commands;

import powerup.tank.Robot;
import powerup.tank.base.CommandBase;

public class DriveStraight extends CommandBase {
	
	private double targetDistance;
	private double startDistance;
	private boolean success = false;
	
	public DriveStraight(double distance) {
		// save the target distance for later
		this.targetDistance = distance;
		Robot.log("DriveStraight:targetDistance:"+targetDistance);
	}

	
	// Called just before this Command runs the first time
	protected void initialize() {
		// save the starting point for later
		startDistance = Robot.driveTrain.getDistance();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		// calc distance traveled since this command started
		double currentDistance = Robot.driveTrain.getDistance() - startDistance;
		Robot.log("DriveStraight:currentDistance:"+currentDistance);
		
		// if we have gone far enough then stop
		if (currentDistance >= targetDistance) {
			Robot.driveTrain.tankDrive(0, 0);
			success = true;
		} else {
			Robot.driveTrain.tankDrive(1, 1);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return success;
	}

}
