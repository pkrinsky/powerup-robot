package powerup.tank.base;

import powerup.tank.Robot;

public class DriveTrainBase {
	
	private double distance = 0;
	
	public double getAverageDistanceInInches() {
		Robot.log("DriveTrainBase:getAverageDistanceInInches:"+distance);
		return distance; 
	}
	
	public double getGyroRotation() {
		return 1.0;
	}
	
	public void tankDrive(double left, double right) {
		Robot.log("DriveTrainBase:tankDrive left:"+left+" right:"+right);
		distance += 10;
	}	

}
