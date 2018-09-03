package powerup.tank.commands;

import powerup.tank.base.CommandGroupBase;
import powerup.tank.commands.Turn.Units;

public class DriveFromAtoB extends CommandGroupBase{
	
	public DriveFromAtoB() {
		addSequential(new DriveStraight(30));  
		addSequential(new Turn(90,Units.Degrees));
		addSequential(new DriveStraight(20));
	} 
	
}
