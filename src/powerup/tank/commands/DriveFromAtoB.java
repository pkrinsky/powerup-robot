package powerup.tank.commands;

import powerup.tank.base.CommandGroupBase;

public class DriveFromAtoB extends CommandGroupBase{
	
	public DriveFromAtoB() {
		addSequential(new DriveStraight(3));  
		addSequential(new Turn(90));
		addSequential(new DriveStraight(2));
	} 
	
}
