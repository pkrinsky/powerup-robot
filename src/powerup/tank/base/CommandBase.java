package powerup.tank.base;

import powerup.tank.Robot;

public class CommandBase {
	
	protected void initialize() {
		Robot.log("CommandBase:initialize "+this.getClass().getName());
	}
	
	protected void execute() {
		Robot.log("CommandBase:execute "+this.getClass().getName());
	}
	
	protected boolean isFinished() {
		return true;
	}
}
