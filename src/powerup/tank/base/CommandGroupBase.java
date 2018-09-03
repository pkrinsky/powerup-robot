package powerup.tank.base;

import java.util.ArrayList;
import java.util.List;

import powerup.tank.Robot;

public class CommandGroupBase {
	
	private List<CommandBase> commandList = new ArrayList<CommandBase>();
	
	public void addSequential(CommandBase command) {
		Robot.log("CommandGroup:addSequential "+command.getClass().getName());
		commandList.add(command);
	}
	
	public CommandBase getCommand(int i) {
		CommandBase c = null;
		if (i<commandList.size()) 
			c = commandList.get(i);
		
		return c;
		
	}

}
