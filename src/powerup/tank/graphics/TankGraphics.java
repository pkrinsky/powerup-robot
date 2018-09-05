package powerup.tank.graphics;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class TankGraphics extends Canvas{
	
	private static final long serialVersionUID = -7506350633593738441L;
	
	public static final int HEIGHT = 800;
	public static final int WIDTH = 800;

	private BufferStrategy strategy;
	private BufferedImage robotImage;
	private BufferedImage dotImage;
	
	private BufferedImage getImage(String filename) {
		BufferedImage sourceImage = null;
		
		try {
			URL url = this.getClass().getClassLoader().getResource(filename);
			if (url == null) {
				throw new RuntimeException("Can't find filename: "+filename);
			}
			sourceImage = ImageIO.read(url);
			//System.out.println("read image "+filename+" width:"+sourceImage.getWidth()+" height:"+sourceImage.getHeight());
		} catch (IOException e) {
			throw new RuntimeException("Failed to load: "+filename);
		}
		
		return sourceImage;
	}
	


	
	public void setup() {
		//Robot.log("GraphicsController.setup");
		robotImage = getImage("robot.png");
		dotImage = getImage("dot.png");
		
		JFrame container = new JFrame("Tank");
		
		JPanel panel = (JPanel) container.getContentPane();
		panel.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		panel.setLayout(null);
		
		setBounds(0,0,WIDTH,HEIGHT);
		panel.add(this);
		
		setIgnoreRepaint(true);
		
		container.pack();
		container.setResizable(false);
		container.setVisible(true);
		
		container.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		

		requestFocus();

		createBufferStrategy(2);
		strategy = getBufferStrategy();
		
		//Robot.log("GraphicsController.setup complete");

	}
	
	


	public void drawField(int posX, int posY, int angle) {
		// init the graphics system to redraw the map
		Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0,HEIGHT,WIDTH,HEIGHT);
		g.setColor(Color.black);
		g.fillRect(0,0,WIDTH,HEIGHT);
		
		// draw dots
		int x = TankGraphics.WIDTH / 2;
		int y = TankGraphics.HEIGHT - 200;
		for (int i=0;i<4;i++) {
			y -= 50;
			g.drawImage(dotImage, x, y, null);	
		}
		
		for (int i=0;i<4;i++) {
			x += 50;
			g.drawImage(dotImage, x, y, null);	
		}
		
		for (int i=0;i<4;i++) {
			y -= 50;
			g.drawImage(dotImage, x, y, null);	
		}
		
		// rotate as needed around the center of the image
		double rotationRequired = Math.toRadians (angle);
		double centerX = robotImage.getWidth() / 2;
		double centerY = robotImage.getHeight() / 2;
		
		AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, centerX, centerY);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

		// Drawing the rotated image at the required drawing locations
		g.drawImage(op.filter(robotImage, null), posX, posY, null);
		
		// show the graphics
		g.dispose();
		strategy.show();
	}


}
