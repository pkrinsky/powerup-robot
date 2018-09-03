package powerup.tank.commands;

import powerup.tank.Robot;
import powerup.tank.base.CommandBase;

public class DriveStraight extends CommandBase {
	
	private double targetDistance;
	private double startDistance;
	private boolean success = false;
	
	public DriveStraight(double distance) {
		super();
		this.targetDistance = distance;
	}

	
	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.log("DriveStraight:initialize");
		success = false;
		startDistance = Robot.driveTrain.getAverageDistanceInInches();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double currentDistance = Robot.driveTrain.getAverageDistanceInInches() - startDistance;
		Robot.log("DriveStraight:currentDistance:"+currentDistance);
		
		if (currentDistance >= targetDistance) success = true;
		
		if (!success) {
			Robot.driveTrain.tankDrive(1, 1);
		} else {
			Robot.driveTrain.tankDrive(0, 0);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return success;
	}

	// Called once after isFinished returns true
	protected void end() {

	}



}
