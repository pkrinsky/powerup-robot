package powerup.tank.commands;

import powerup.tank.Robot;
import powerup.tank.base.CommandBase;

public class Turn extends CommandBase {
	
	double minimumSpeed;
	double amountTurn;
	double currAngle;
	double angleError;
	
	public static enum Units {
		Degrees, Radians, Rotations;
	}

	public Turn(double amountTurn, Units turnUnits) {// does not use vision
		this.amountTurn = amountTurn;
	}

	protected void execute() {
		currAngle = Robot.driveTrain.getGyroRotation();
		angleError = amountTurn - currAngle;
		angleError = angleError < -180 ? angleError + 360 : angleError;
		angleError = angleError > 180 ? angleError - 360 : angleError;
	}

	protected boolean isFinished() {
		return true;	
	}

	protected void end() {
		Robot.driveTrain.tankDrive(0, 0);
	}


}
