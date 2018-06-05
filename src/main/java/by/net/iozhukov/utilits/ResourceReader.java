package by.net.iozhukov.utilits;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import by.net.iozhukov.server.service.Response;

/**
 * The class determines which resource it is asking for, and returns it or the
 * error page.
 * 
 * @author Ilya Zhukov
 */
public class ResourceReader {

	public static void handle(Response interResponse) {
		byte[] bufferResource = null;

		// If all bad, load page error 500;
		if (interResponse.getSatusCodeResponse().equals("500")) {
			bufferResource = GeneratorServerPages.generateError500Page();
			interResponse.setSatusCodeResponse("500 Internal Server Error");
			interResponse.setFile(GeneratorServerPages.generateError500Page());

			interResponse.addToLog("Added the error \"500\" page  in response");
			return;
		}

		// If method request is not GET, return the page error 400
		if (!interResponse.getHTTPMethod().equals("GET")) {
			Logger.getLogger("console").log(Level.ERROR,
					"Method is not GET in request: " + interResponse.getRequestResource());

			interResponse.setFile(GeneratorServerPages.generateError400Page());
			interResponse.setMimeTypeFile("text/html");
			interResponse.setSatusCodeResponse("400 Bad Request");

			interResponse.addToLog("Added the error \"400\" page  in response");
			return;
		}

		// If an empty query, return the main page;
		if (interResponse.getRequestResource().equals("")) {

			interResponse.setFormatFile("html");
			interResponse.setMimeTypeFile("text/html");
			interResponse.setRequestResource("index.html");
			interResponse.setSatusCodeResponse("200 OK");

			interResponse.addToLog("Added the \"index\" page in response");
		}

		// Reading request resource;
		bufferResource = readResource(interResponse.getRequestResource());
		if (bufferResource != null) {
			interResponse.setFile(bufferResource);
			interResponse.setSatusCodeResponse("200 OK");

			interResponse.addToLog("Added the requested resource in response");
			return;
		}

		// If read not success, load in response page error 404;
		bufferResource = GeneratorServerPages.generateError404Page();
		if (bufferResource != null) {
			interResponse.setSatusCodeResponse("404 Not Found");
			interResponse.setMimeTypeFile("text/html");
			interResponse.setFile(bufferResource);

			interResponse.addToLog("Added the error \"404\" page  in response");
			return;
		}
	}

	private static byte[] readResource(String requestResourse) {
		File file = new File("./WWW/" + requestResourse);
		FileInputStream in = null;
		byte[] buffer = null;
		try {
			in = new FileInputStream(file);
			buffer = new byte[in.available()];
			in.read(buffer, 0, in.available());
		} catch (FileNotFoundException e) {
			Logger.getLogger("console").log(Level.ERROR, "Request file not found: " + requestResourse);
		} catch (IOException e) {
			Logger.getLogger("console").log(Level.ERROR, "Error reading a request file: " + requestResourse);
		}
		return buffer;

	}

}
