package net.iozhukov.server;

import net.iozhukov.server.service.Server;
import net.iozhukov.utilits.ServerPages;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Ilya Zhukov (https://iozhukov.net)
 */
public class Launcher {

	private static final Properties config = new Properties();
	private static final Logger logger = Logger.getLogger("console");

	public static void main(String[] args) {
		Server server = createServer();
		server.launch();
	}

	private static Server createServer() {
		logger.log(Level.INFO, "Started server initialization");
		loadConfiguration();
		loadErrorPages();

		int port = Integer.parseInt(config.getProperty("port", "8080"));
		logger.log(Level.INFO, "Port: " + port);

		boolean timerOn = Boolean.parseBoolean(config.getProperty("timerIsON", "false"));
		logger.log(Level.INFO, "Time is ON: " + timerOn);

		Long timerTime = Long.parseLong(config.getProperty("timerTime", "1"));
		logger.log(Level.INFO, "Set timer on: " + timerTime + "m");

		return timerOn ? new Server(port, timerTime) : new Server(port);
	}

	/**
	 * Method for loading properties from a file "configuration.properties" and are
	 * handle the exceptions if there are such. This file must be at the root of the
	 * program.
	 */
	private static void loadConfiguration() {
		logger.log(Level.INFO, "Reading Configuration");
		try {
			config.load(new FileInputStream("./config/configuration.properties"));
		} catch (FileNotFoundException e) {
			logger.log(Level.WARN,
					"The configuration file was not found! The default settings will be used (timer = OFF, socket = 8080)");
		} catch (IOException e) {
			logger.log(Level.ERROR,
					"Error reading configuration file! The default settings will be used (timer = OFF, socket = 8080)");
		}
		logger.log(Level.INFO, "Configuration initialize");
	}

	private static void loadErrorPages() {
		try {
			ServerPages.loadResources();
		} catch (Exception e) {
			logger.fatal("Error load resources:" + e.getMessage());
			System.exit(0);
		}
	}
}
