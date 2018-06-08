package powerup.team000;

import powerup.field.Field;
import powerup.field.FieldObject;
import powerup.field.Robot;

public class MyRobot extends Robot {
	
	/* 
	 * Called periodically by the GameClient to get the next move
	 */
	public int getMove(Field field) {
		// default next move to STOP
		int nextMove = Robot.STOP;
		
		// controls which algorithm to use to control the robot
		int algorithm = 2;
		
		// after five seconds switch to manual 
		if (field.getGameSecs() < Field.GAME_SECS - 5) {
			algorithm = 1;
		}

		switch (algorithm) {
		case 1:
			// manually control the robot with the keyboard
			nextMove = super.getMove(field);
			break;
		case 2:
			// autonomous routine to move to the scale and shoot
			nextMove = moveToScaleAndShoot(field);
			break;
		}

		return nextMove;
	}	

	/* 
	 * Determine the next move using basic positional logic
	 */
	private int moveToScaleAndShoot(Field field) {
		// default next move to STOP
		int nextMove = Robot.STOP;
		
		// get the current location of the robot
		int currentCol = getCol();
		int currentRow = getRow();
		
		// find the blue scale and move to just below its location
		FieldObject blueScale = field.find("BS");
		int targetCol = blueScale.getCol();
		int targetRow = blueScale.getRow()+1;
		
		// compare the current row to the target to determine direction
		if (currentRow > targetRow)
			nextMove = Robot.NORTH;

		// compare the current column to the target to determine direction
		if (currentCol < targetCol)
			nextMove = Robot.EAST;
		
		//TODO need to implement SOUTH and WEST
		
		// if we have arrived and have a cube then shoot
		if (currentRow == targetRow 
				&& currentCol == targetCol
				&& hasCube()) {
			nextMove = Robot.SHOOT;
		}
		
		return nextMove;
	}

	
}
