package net.iozhukov.utilits;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Class storage html error pages.
 *
 * @author Ilya Zhukov (https://iozhukov.net)
 */
public class ServerPages {

	private static final Logger logger = Logger.getLogger("console");

	private static String page400;
	private static String page404;
	private static String page500;

	public static byte[] generateError400Page() {
		if (page400 == null) {
			throw new RuntimeException("Error page 400, not load");
		}
		return page400.getBytes();
	}

	public static byte[] generateError404Page() {
		if (page404 == null) {
			throw new RuntimeException("Error page 404, not load");
		}
		return page404.getBytes();
	}

	public static byte[] generateError500Page() {
		if (page500 == null) {
			throw new RuntimeException("Error page 500, not load");
		}
		return page500.getBytes();
	}

	public static void loadResources() throws IOException {
		Path p400 = Paths.get("./config/errors/400.html");
		page400 = String.join("", Files.readAllLines(p400));
		Path p404 = Paths.get("./config/errors/404.html");
		page404 = String.join("", Files.readAllLines(p404));
		Path p500 = Paths.get("./config/errors/500.html");
		page500 = String.join("", Files.readAllLines(p500));
	}
}
