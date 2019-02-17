package net.iozhukov.server.service;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.Timer;
import java.util.TimerTask;

/**
 * A class extends class Thread. Needed for creating an off-timer instance for
 * the server passed in the constructor. <br>
 * Runs on the object of this class using the {@link ServerTimer # start ()}
 * method
 *
 * @author Ilya Zhukov (https://iozhukov.net)
 */
public class ServerTimer extends Thread {

	private static final Long SECONDS_PER_MINUTE = 60000L;

	private final Server server;
	private final Long time;

	/**
	 * The constructor will prepare the settings for the timer.
	 *
	 * @param server
	 * 		- Server which must be stopped after a specified time
	 * @param time
	 * 		- Time through which the specified server will be stopped.
	 */
	public ServerTimer(Server server, Long time) {
		this.server = server;
		this.time = time * SECONDS_PER_MINUTE;
	}

	@Override
	public void run() {
		Logger.getLogger("console").log(Level.INFO, "The timer has started");
		Timer timer = new Timer();
		timer.schedule(new SimpleTimerTask(server), time);
	}

	private class SimpleTimerTask extends TimerTask {

		private final Server server;

		SimpleTimerTask(Server server) {
			this.server = server;
		}

		@Override
		public void run() {
			Logger.getLogger("console").log(Level.INFO, "Timer has finished");
			server.stop();
		}
	}
}
