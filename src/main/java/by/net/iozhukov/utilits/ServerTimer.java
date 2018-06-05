package by.net.iozhukov.utilits;

import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import by.net.iozhukov.server.service.Server;

/**
 * 
 * 
 * 
 * @author Ilya Zhukov (ilya.beetle@gmail.com)
 */
public class ServerTimer implements Runnable {

	private static final Long SECONDS_PER_MINUTE = 60000L;

	private Server server;
	private Long time;

	/**
	 * 
	 * @param server
	 * @param time
	 */
	public ServerTimer(Server server, Long time) {
		this.server = server;
		this.time = time * SECONDS_PER_MINUTE;
	}

	@Override
	public void run() {
		Logger.getLogger("console").log(Level.INFO, "The timer has started");

		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				Logger.getLogger("console").log(Level.INFO, "Timer has finished");
				server.stop();
			}

		}, time - 5000L);// Minus timeout when finalize
	}
}
