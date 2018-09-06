package powerup.client;

import powerup.engine.Util;
import powerup.field.Field;
import powerup.field.FieldObject;
import powerup.field.Robot;

@SuppressWarnings("unused")
public class MyRobot extends Robot {
	
	/* 
	 * Called periodically by the GameClient to get the next move
	 */
	
	public int getMove(Field field) {
		return moveManual(field);
		//return moveChallenge1(field);
		//return moveChallenge2(field);
		//return moveChallenge3(field);
	}
	
	
	/* 
	 * Manual movement using the keyboard
	 */

	private int moveManual(Field field) {
		return super.getMove(field);
	}	
	
	
	/* 
	 * Challenge #1
	 */

	private int moves = 0;
	
	private int moveChallenge1(Field field) {
		moves = moves + 1;
		Util.log("move:"+moves);
		return super.getMove(field);
	}
	
	
	
	/* 
	 * Challenge #2
	 */

	
	private int moveChallenge2(Field field) {
		int nextMove = Robot.STOP;
		
		moves = moves + 1;
		Util.log("move:"+moves);
		
		if (getCol() < 11)
			nextMove = Robot.EAST;
		else 
			nextMove = super.getMove(field); 
		
		return nextMove;
	}
	
	
	
	/* 
	 * Challenge #3
	 */	
	
	private int moveChallenge3(Field field) {
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
