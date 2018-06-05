package by.net.iozhukov.server.service;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * <h3>Simple HTTP Server.</h3><br>
 * An instance of this object listens to the port specified in the designer and
 * executes the work in a separate thread. If a request comes to him, it begins
 * processing in the new thread. <br>
 * <br>
 * <b>To start the work, it is necessary to call the method
 * {@link Server#launch()}</b><br>
 * 
 * 
 * @author Ilya Zhukov
 */
public class Server {

	/**
	 * The stream in which the server service is running
	 */
	private Thread service;

	/**
	 * A field that displays the current status of the server
	 */
	private Boolean serverAvailable;

	/**
	 * The port on which you can small talking with the server
	 */
	private ServerSocket serverSocket;

	/**
	 * Constructor for instantiating an object {@link Server} If the specified port
	 * is busy, the program crashes
	 * 
	 * @param port
	 *            - port number. Type Integer.
	 */
	public Server(Integer port) {
		this.serverAvailable = false;
		this.service = new Thread() {

			@Override
			public void run() {
				work();
			}
		};

		try {
			this.serverSocket = new ServerSocket(port.intValue());
		} catch (IOException e) {
			Logger.getLogger("console").log(Level.FATAL, "The server is already running or a socket is busy");
			System.exit(0);
		}
	}

	/**
	 * This method starts the server service, and changes the internal status to
	 * available.<br>
	 * If the method is called on a running instance of the server, a message will
	 * be sent to the log, and nothing will happen.
	 */
	public void launch() {
		if (serverAvailable) {
			Logger.getLogger("console").log(Level.WARN, "The server is already running");
			return;
		}

		service.start();
		serverAvailable = true;

		Logger.getLogger("console").log(Level.INFO, "Server started work");
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
			Logger.getLogger("console").log(Level.ERROR, "Error close server socket");
		}

		Logger.getLogger("console").log(Level.INFO, "Server end work");
		System.exit(0);// TODO - rebuild for closing threads

	}

	/**
	 * It's just a getter nothing more
	 */
	public boolean getServerAvailable() {
		return serverAvailable;
	}

	/**
	 * Listens for a connection to be made to this socket and accepts it. After it
	 * starts processing the request in a new thread.
	 */
	private void work() {
		while (true) {
			try {
				if (serverAvailable) {
					Socket socket = serverSocket.accept();
					new Thread(new RequestProcessor(socket)).start();
				} else {
					return;
				}
			} catch (SocketException e) {
				continue;
				//Logger.getLogger("console").log(Level.ERROR, "Error creating or accessing a Socket");
			} catch (IOException e) {
				Logger.getLogger("console").log(Level.ERROR, "I/O error occurred while waiting for connection");
			}
		}
	}

}
