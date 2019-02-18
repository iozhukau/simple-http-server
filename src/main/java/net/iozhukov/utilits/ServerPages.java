package net.iozhukov.utilits;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static net.iozhukov.utilits.ServerConstants.RESOURCES_PATH;

/**
 * Class storage html error pages.
 *
 * @author Ilya Zhukov (https://iozhukov.net)
 */
public class ServerPages {



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
		Path p400 = Paths.get(RESOURCES_PATH + "/errors/400.html");
		page400 = String.join("", Files.readAllLines(p400));
		Path p404 = Paths.get(RESOURCES_PATH + "./errors/404.html");
		page404 = String.join("", Files.readAllLines(p404));
		Path p500 = Paths.get(RESOURCES_PATH + "./errors/500.html");
		page500 = String.join("", Files.readAllLines(p500));
	}
}
