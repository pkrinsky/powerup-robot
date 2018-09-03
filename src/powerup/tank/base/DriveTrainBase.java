package powerup.tank.base;

import powerup.tank.Robot;

public class DriveTrainBase {
	
	private double distance = 0;
	private int angle = 0;
	private int posX = 100;
	private int posY = 100;
	private int speed = 1;
	
	public double getAverageDistanceInInches() {
		Robot.log("DriveTrainBase:getAverageDistanceInInches:"+distance);
		return distance; 
	}
	
	public void zeroDistance() {
		distance = 0;
	}
	
	public void zeroAngle() {
		angle = 0;
	}
	
	public int getAngle() {
		return angle;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public void tankDrive(double left, double right) {
		Robot.log("DriveTrainBase:tankDrive left:"+left+" right:"+right);
		if (left == 0 && right == 0) {
			Robot.log("DriveTrainBase:tankDrive stop");
		} else if (left > 0 && right > 0 && left == right) {
			Robot.log("DriveTrainBase:tankDrive forward");
			distance += speed;
			if (angle == 0) {
				posX = posX + speed;
			} else if (angle == 180 || angle == -180) {
				posX = posX - speed;
			} else if (angle == 90) {
				posY = posY + speed;
			} else if (angle == -90) {
				posY = posY - speed;
			}
		} else if (left < 0 && right < 0 && left == right) {
			Robot.log("DriveTrainBase:tankDrive reverse");
			distance -= speed;	
		} else if (left >= 0 && left > right) {
			angle += 90;	
			Robot.log("DriveTrainBase:tankDrive right to:"+angle);
		} else if (right >= 0 && right > left) {
			angle -= 90;	
			Robot.log("DriveTrainBase:tankDrive left to:"+angle);
		}
		
		// keep angle [-179,180]
		if (angle < -179) angle += 360;
		if (angle > 180) angle -= 360;
		
		Robot.log("DriveTrainBase:tankDrive posX:"+posX+" posY:"+posY+" angle:"+angle);
		
	}	

}
