package net.iozhukov.utilits;

import net.iozhukov.server.service.Response;
import net.iozhukov.utilits.HeaderRequestParser.Type;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static net.iozhukov.utilits.ServerConstants.*;

/**
 * The class determines which resource it is asking for, and returns it or the
 * error page.
 *
 * @author Ilya Zhukov (https://iozhukov.net)
 */
public class ResourceReader {

	private static final Logger logger = Logger.getLogger("console");
	private static final String WEB_RESOURCES = "./WWW/";

	public static void handle(Response response) {

		// If all bad, load page error 500;
		if (response.getStatusCodeResponse().equals(STATUS_INTERNAL_SERVER_ERROR)) {
			response.setFormatFile(HTML_FILE);
			response.setMimeTypeFile(Type.HTML);
			response.setFile(ServerPages.generateError500Page());
			response.addToLog("Added the error \"500\" page  in response");
			return;
		}

		// If method request is not GET, return the page error 400
		if (!response.getHTTPMethod().equals("GET")) {
			logger.error("Method is not GET in request: " + response.getRequestResource());
			response.setFormatFile(HTML_FILE);
			response.setMimeTypeFile(Type.HTML);
			response.setFile(ServerPages.generateError400Page());
			response.setStatusCodeResponse(STATUS_BAD_REQUEST);
			response.addToLog("Added the error \"400\" page  in response");
			return;
		}

		// If an empty query, return the main page;
		if (response.getRequestResource().equals("")) {
			response.setFormatFile(HTML_FILE);
			response.setMimeTypeFile(Type.HTML);
			response.setRequestResource("index");
			response.addToLog("Added the \"index\" page in response");
		}

		// Reading request resource;
		byte[] bufferResource = readResource(response);
		if (bufferResource != null) {
			response.setFile(bufferResource);
			response.setStatusCodeResponse(STATUS_OK);
			response.addToLog("Added the requested resource in response");
			if (response.getMimeTypeFile().isEmpty()) {
				response.setMimeTypeFile(Type.HTML);
			}
		} else {
			response.setFormatFile(HTML_FILE);
			response.setMimeTypeFile(Type.HTML);
			response.setFile(ServerPages.generateError404Page());
			response.setStatusCodeResponse(STATUS_RESOURCE_NOT_FOUND);
			response.addToLog("Added the error \"404\" page  in response");
		}
	}

	private static byte[] readResource(Response response) {
		File file = new File(WEB_RESOURCES + response.getRequestResource());
		if (file.exists()) {
			return readResource(file);
		} else {
			file = new File(WEB_RESOURCES + response.getRequestResource() + "." + HTML_FILE);
			if (file.exists()) {
				return readResource(file);
			} else {
				Logger.getLogger("console").log(Level.ERROR, "Request file not found: " + file.getPath());
				return null;
			}
		}
	}

	private static byte[] readResource(File file) {
		try {
			FileInputStream in = new FileInputStream(file);
			byte[] buffer = new byte[in.available()];
			in.read(buffer, 0, in.available());
			return buffer;
		} catch (FileNotFoundException e) {
			Logger.getLogger("console").log(Level.ERROR, "Request file not found: " + file.getPath());
			return null;
		} catch (IOException e) {
			Logger.getLogger("console").log(Level.ERROR, "Error reading a request file: " + file.getPath());
			return null;
		}
	}
}
