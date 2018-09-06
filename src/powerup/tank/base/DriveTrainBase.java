package powerup.tank.base;

import powerup.tank.Robot;
import powerup.tank.graphics.TankGraphics;

public class DriveTrainBase {

	public static final int MAX_SPEED = 1;
	
	private double distance = 0;
	private int angle = 0;
	private int posX = TankGraphics.WIDTH / 2;
	private int posY = TankGraphics.HEIGHT - 100;
	private int speed = 0;
	
	public double getDistance() {
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

		// calculate speed and angle based on inputs
		if (left == 0 && right == 0) {
			// NEUTRAL: slow down until we reach zero
			if (speed > 0) speed--;
			if (speed < 0) speed++;
			Robot.log("DriveTrainBase:tankDrive neutral");
		} else if (left > 0 && right > 0 && left == right) {
			// FORWARD: increase speed
			if (speed < MAX_SPEED) speed++;
			Robot.log("DriveTrainBase:tankDrive forward");
		} else if (left < 0 && right < 0 && left == right) {
			// REVERSE: increase speed
			if (speed > -MAX_SPEED) speed--;
			Robot.log("DriveTrainBase:tankDrive reverse");
		} else if (left >= 0 && left > right) {
			// RIGHT TURN
			if (speed == 0) {
				angle += 90;	
				Robot.log("DriveTrainBase:tankDrive right to:"+angle);
			} else {
				if (speed > 0) speed--;
				if (speed < 0) speed++;
				Robot.log("DriveTrainBase:tankDrive speed too fast to turn right");
			}
		} else if (right >= 0 && right > left) {
			if (speed == 0) {
				angle -= 90;	
				Robot.log("DriveTrainBase:tankDrive left to:"+angle);
			} else {
				if (speed > 0) speed--;
				if (speed < 0) speed++;
				Robot.log("DriveTrainBase:tankDrive speed too fast to turn left");
			}
			
		}

		Robot.log("DriveTrainBase:tankDrive speed:"+speed);

		// keep angle [-179,180]
		if (angle < -179) angle += 360;
		if (angle > 180) angle -= 360;

		// update position
		if (angle == 0) {
			posY = posY - speed;
		} else if (angle == 180 || angle == -180) {
			posY = posY + speed;
		} else if (angle == 90) {
			posX = posX + speed;
		} else if (angle == -90) {
			posX = posX - speed;
		}

		// update distance
		distance += Math.abs(speed);


		
		Robot.log("DriveTrainBase:tankDrive posX:"+posX+" posY:"+posY+" angle:"+angle+" dist:"+distance);
		
	}	

}
