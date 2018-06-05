package by.net.iozhukov.server;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import by.net.iozhukov.server.service.Server;
import by.net.iozhukov.server.service.ServerTimer;

/**
 * @author Ilya Zhukov
 */
public class Launcher {

	private static final Properties config = new Properties();

	public static void main(String[] args) {
		Logger.getLogger("console").log(Level.INFO, "Started server initialization");

		loadConfiguration();

		Integer port = Integer.parseInt(config.getProperty("port", "8080")); // default value port 8080
		Logger.getLogger("console").log(Level.INFO, "Port: " + port);

		Server server = new Server(port);
		server.launch();

		Boolean timerON = Boolean.parseBoolean(config.getProperty("timerIsON", "false")); // default value disable timer
		Logger.getLogger("console").log(Level.INFO, "Time is ON: " + timerON);
		if (timerON) {
			Long time = Long.parseLong(config.getProperty("timerTime", "1")); // default value one minute
			Logger.getLogger("console").log(Level.INFO, "Set timer on: " + time + "m");

			ServerTimer timer = new ServerTimer(server, time);
			timer.start();
		}


	}

	/**
	 * Method for loading properties from a file "configuration.properties" and are
	 * handle the exceptions if there are such. This file must be at the root of the
	 * program.
	 */
	private static void loadConfiguration() {
		Logger.getLogger("console").log(Level.INFO, "Reading Configuration");

		try {
			config.load(new FileInputStream("./configuration.properties"));
		} catch (FileNotFoundException e) {
			Logger.getLogger("console").log(Level.WARN,
					"The configuration file was not found! The default settings will be used (timer = OFF, socket = 8080)");
		} catch (IOException e) {
			Logger.getLogger("console").log(Level.ERROR,
					"Error reading configuration file! The default settings will be used (timer = OFF, socket = 8080)");
		}

		Logger.getLogger("console").log(Level.INFO, "Configuration initialize");
	}

}
