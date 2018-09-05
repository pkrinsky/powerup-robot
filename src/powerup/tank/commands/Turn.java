package powerup.tank.commands;

import powerup.tank.Robot;
import powerup.tank.base.CommandBase;

public class Turn extends CommandBase {
	
	private boolean success = false;
	private int targetTurn;
	private int startAngle;
	private int targetAngle;
	
	public Turn(int turn) {
		this.targetTurn = turn;
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.log("Turn:initialize");
		success = false;
		startAngle = Robot.driveTrain.getAngle();
		targetAngle = startAngle + targetTurn;
		Robot.log("Turn:targetAngle:"+targetAngle);
	}	

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		int currentAngle = Robot.driveTrain.getAngle();
		Robot.log("DriveStraight:currentAngle:"+currentAngle);
		
		if (currentAngle == targetAngle) success = true;
		
		if (!success) {
			if (currentAngle < targetAngle) {
				Robot.driveTrain.tankDrive(1, -1);	
			} else {
				Robot.driveTrain.tankDrive(-1, 1);
			}
		} else {
			Robot.driveTrain.tankDrive(0, 0);
		}
	}

	protected boolean isFinished() {
		return success;	
	}

	protected void end() {
		Robot.driveTrain.tankDrive(0, 0);
	}


}
