package powerup.client;

import powerup.engine.Util;
import powerup.field.Robot;
import powerup.server.GameClient;

public class MyClient extends GameClient {
	
	// slow the game down a little to make it easier to watch
	static int GAME_DELAY = 200;
	
	public static void main(String[] args) {
		GameClient client = new MyClient();
		String name = null;
		String debug = null;
		String serverAddress = null;
		String serverPort = null;
		
		if (args.length >= 1) {
			name = args[0];
		}
		if (args.length >= 2) {
			debug = args[1];
			Util.setDebugLevel(new Integer(debug));
		}
		if (args.length >= 3) {
			serverAddress = args[2];
		}
		if (args.length >= 4) {
			serverPort = args[3];
		}
			
		client.setup(serverAddress, serverPort, name);
		client.gameLoop(GAME_DELAY);
	}	

	@Override
	protected Robot newRobot() {
		Robot robot = new MyRobot();
		return robot;
	}

}
