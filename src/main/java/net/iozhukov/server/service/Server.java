package net.iozhukov.server.service;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/**
 * <h3>Simple HTTP Server.</h3><br>
 * An instance of this object listens to the port specified in the designer and
 * executes the work in a separate thread. If a request comes to him, it begins
 * processing in the new thread. <br>
 * <br>
 * <b>To start the work, it is necessary to call the method
 * {@link Server#launch()}</b><br>
 *
 * @author Ilya Zhukov (https://iozhukov.net)
 */
public class Server {

	private static final Logger logger = Logger.getLogger("console");

	/**
	 * The thread in which the server service is running
	 */
	private final Thread service;

	/**
	 * A field that displays the current status of the server
	 */
	private boolean serverAvailable;

	/**
	 * The socket on which you can small talking with the server
	 */
	private ServerSocket serverSocket;

	private Long timerTime;

	/**
	 * Constructor for instantiating an object {@link Server} If the specified port
	 * is busy, the program crashes
	 *
	 * @param port
	 * 		- port number. Type Integer.
	 */
	public Server(int port) {
		this.serverAvailable = false;
		this.service = new Thread() { // for example method reference and lambda
			@Override
			public void run() {
				work();
			}
		};

		try {
			this.serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			logger.fatal("The server is already running or a socket is busy");
			System.exit(0);
		}
	}

	/**
	 * Constructor for instantiating an object {@link Server} If the specified port
	 * is busy, the program crashes
	 *
	 * @param port
	 * 		- port number. Type Integer.
	 * @param timerTime
	 * 		- If you need to start the server for a specific period, set the time in minutes here.
	 */
	public Server(Integer port, Long timerTime) {
		this.serverAvailable = false;
		this.timerTime = timerTime;
		this.service = new Thread(this::work);

		try {
			this.serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			logger.fatal("The server is already running or a socket is busy");
			System.exit(0);
		}
	}

	/**
	 * This method starts the server service, and changes the internal status to
	 * available.<br>
	 * If the method is called on a running instance of the server, a message will
	 * be sent to the logger, and nothing will happen.
	 */
	public void launch() {
		if (serverAvailable) {
			logger.log(Level.WARN, "The server is already running");
			return;
		}
		serverAvailable = true;
		service.start();
		if (timerTime != null) {
			ServerTimer timer = new ServerTimer(this, timerTime);
			timer.start();
		}
		logger.info("Server started work");
	}

	/**
	 * Listens for a connection to be made to this socket and accepts it. After it
	 * starts processing the request in a new thread.
	 */
	private void work() {
		while (serverAvailable) {
			try {
				Socket socket = serverSocket.accept();
				new Thread(new RequestProcessor(socket)).start();
			} catch (SocketException e) {
				logger.error("Error creating or accessing a Socket:" + e.getMessage());
			} catch (IOException e) {
				logger.error("I/O error occurred while waiting for connection");
			}
		}
	}

	/**
	 * This method changes the server's internal status to inaccessible, close
	 * server port and exits the application
	 */
	public void stop() {
		serverAvailable = false;
		try {
			serverSocket.close();
		} catch (IOException e) {
			logger.error("Error close server socket");
		}
		logger.info("Server end work");
	}

	/**
	 * It's just a getter nothing more
	 *
	 * @return - current server status
	 */
	public boolean getServerAvailable() {
		return serverAvailable;
	}

}
