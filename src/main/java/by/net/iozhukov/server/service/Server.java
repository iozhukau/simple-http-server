package by.net.iozhukov.server.service;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * 
 * 
 * 
 * @author Ilya Zhukov (ilya.beetle@gmail.com)
 */
public class Server {

	private Thread service;
	private ServerSocket serverSocket;
	private Boolean continueWork;

	public Server(Integer port) {

		// TODO - Comments for Server constructor
		this.service = new Thread() {

			@Override
			public void run() {
				work();
			}

		};

		// TODO - Comments for Server constructor
		try {
			this.serverSocket = new ServerSocket(port.intValue());
		} catch (IOException e) {
			Logger.getLogger("console").log(Level.FATAL, "The server is already running or a socket is busy");
			System.exit(0);
		}
	}

	/**
	 * 
	 */
	public void launch() {

		continueWork = true;
		service.start();

		Logger.getLogger("console").log(Level.INFO, "Server started work");
	}

	/**
	 * 
	 */
	public void stop() {
		continueWork = false;

		synchronized (this) {
			try {
				this.wait(5000L);
			} catch (InterruptedException e) {
				Logger.getLogger("console").log(Level.ERROR, "The server did not work correctly");
				System.exit(0);
			}
			Logger.getLogger("console").log(Level.INFO, "Server end work");
			System.exit(0);
		}
	}

	/**
	 * 
	 */
	private void work() {
		while (true) {
			try {
				if (continueWork) {

					Socket socket = serverSocket.accept();
					new Thread(new RequestProcessor(socket)).start();

				} else {
					return;
				}
			} catch (SocketException e) {
				Logger.getLogger("console").log(Level.ERROR, "Error creating or accessing a Socket");
			} catch (IOException e) {
				Logger.getLogger("console").log(Level.ERROR, "I/O error occurred while waiting for connection");
			}
		}
	}

}
