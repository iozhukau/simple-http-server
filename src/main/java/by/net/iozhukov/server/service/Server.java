package by.net.iozhukov.server.service;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import by.net.iozhukov.utilits.Watcher;

/**
 * 
 * @author Ilya Zhukov (ilya.beetle@gmail.com)
 */
public class Server {

	private Thread thread;
	private Watcher watcher;
	private ServerSocket serverSocket;

	private Set<Thread> setThreads = new HashSet<>();

	public Server(Integer port) {
		// TODO - Comments for Server constructor
		this.watcher = new Watcher(setThreads, this);

		// TODO - Comments for Server constructor
		this.thread = new Thread() {

			@Override
			public void run() {
				job();
			}

		};

		// TODO - Comments for Server constructor
		try {
			this.serverSocket = new ServerSocket(port.intValue());
		} catch (IOException e) {
			String message = "The server is already running or a socket is busy";
			Logger.getLogger("console").log(Level.FATAL, message);
			System.exit(0);
		}
	}

	/**
	 * 
	 */
	public void launch() {
		thread.start();
		watcher.start();

		String message = "Server started work";
		Logger.getLogger("console").log(Level.INFO, message);
	}

	/**
	 * 
	 */
	public synchronized void stop() {
		while (true) {
			if (setThreads.isEmpty()) {
				break;
			}
		}
		String message = "Server end work";
		Logger.getLogger("console").log(Level.INFO, message);
		System.exit(0);
	}

	/**
	 * 
	 */
	private void job() {
		while (true) {
			try {
				Socket socket = serverSocket.accept();

				Thread t = new Thread(new RequestProcessor(socket));
				setThreads.add(t);
				t.start();

			} catch (SocketException e) {
				String message = "1";
				Logger.getLogger("console").log(Level.ERROR, message);
			} catch (IOException e) {
				String message = "2";
				Logger.getLogger("console").log(Level.ERROR, message);
			}
		}
	}

}
