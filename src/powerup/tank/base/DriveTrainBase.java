package powerup.tank.base;

import powerup.tank.Robot;
import powerup.tank.graphics.TankGraphics;

public class DriveTrainBase {
	
	private double distance = 0;
	private int angle = 0;
	private int posX = TankGraphics.WIDTH / 2;
	private int posY = TankGraphics.HEIGHT - 100;
	private int speed = 5;
	
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
	
	public int getPositionX() {
		return posX;
	}
	
	public int getPositionY() {
		return posY;
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
				posY = posY - speed;
			} else if (angle == 180 || angle == -180) {
				posY = posY + speed;
			} else if (angle == 90) {
				posX = posX + speed;
			} else if (angle == -90) {
				posX = posX - speed;
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
