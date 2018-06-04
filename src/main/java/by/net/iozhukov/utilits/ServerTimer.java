package by.net.iozhukov.utilits;

import java.util.Timer;
import java.util.TimerTask;

import by.net.iozhukov.server.service.Server;

/**
 * 
 * @author Ilya Zhukov (ilya.beetle@gmail.com)
 */
public class ServerTimer implements Runnable {

	private Server server;
	private Long time;

	public ServerTimer(Server server, Long time) {
		this.server = server;
		this.time = time;
	}

	@Override
	public void run() {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				server.stop();
			}
			
		}, time);
	}
}
