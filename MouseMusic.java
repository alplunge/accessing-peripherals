import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;

import org.jfugue.*;


public class MouseMusic implements Runnable {
	
	public Object lock = new Object();
	
	public MouseMusic() {
		
	}

	@Override
	public void run() {
		while(true) {
			synchronized (lock) {
				while(MouseChecker.m) {
					try {
						lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			Player player = new Player();
			//Read Screen Dimensions and Mouse Position
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			double width = screenSize.getWidth();
			double height = screenSize.getHeight();
			Point pointerLocation = MouseInfo.getPointerInfo().getLocation();
			int pitch = 127 - (int)(pointerLocation.y*127/height);
			int volume = (int)(pointerLocation.x*16383/width);
			player.play("X[Volume]=" + volume + " [" + pitch + "]");
		}
	}
}
