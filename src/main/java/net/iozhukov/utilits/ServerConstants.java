package net.iozhukov.utilits;

/**
 * @author Ilya Zhukov (https://iozhukov.net)
 */
public final class ServerConstants {

	private ServerConstants() {
	}

	public static final String CONFIGURATION_PATH = "./resources/configuration.properties";
	public static final String RESOURCES_PATH = "./resources/";

	public static final String DEFAULT_PORT= "8080";
	public static final String DEFAULT_TIMER= "false";
	public static final String DEFAULT_TIMER_TIME= "1";


	public static final String PROPERTY_KEY_PORT= "port";
	public static final String PROPERTY_KEY_DEFAULT_TIMER= "timerIsON";
	public static final String PROPERTY_KEY_TIMER_TIME= "timerTime";



	public static final String END_LINE = "\r\n";
	public static final String HTML_FILE = "html";

	public static final String STATUS_OK = "200 OK";
	public static final String STATUS_BAD_REQUEST = "400 Bad Request";
	public static final String STATUS_RESOURCE_NOT_FOUND = "404 Not Found";
	public static final String STATUS_INTERNAL_SERVER_ERROR = "500 Internal Server Error";

}
