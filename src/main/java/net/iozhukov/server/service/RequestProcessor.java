package net.iozhukov.server.service;

import net.iozhukov.utilits.HeaderRequestParser;
import net.iozhukov.utilits.ResourceReader;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

import static net.iozhukov.utilits.StringConstants.END_LINE;
import static net.iozhukov.utilits.StringConstants.STATUS_INTERNAL_SERVER_ERROR;

/**
 * The class implements the Runnable interface.<br>
 * The object of this class executes the request:<br>
 * <ul>
 * <li>Reading headers</li>
 * <li>Header parsing</li>
 * <li>Search for the requested resource</li>
 * <li>Sending a Resource</li>
 * </ul>
 *
 * @author Ilya Zhukov (https://iozhukov.net)
 */
public class RequestProcessor implements Runnable {

	private static final Logger logger = Logger.getLogger("console");

	private InputStream inputStream;
	private OutputStream outputStream;

	/**
	 * The constructor configures the input and output streams, from which the files
	 * will come and where they will go.
	 *
	 * @param socket
	 * 		- Port with which the handler interacts
	 */
	public RequestProcessor(Socket socket) {
		try {
			this.inputStream = socket.getInputStream();
			this.outputStream = socket.getOutputStream();
		} catch (IOException e) {
			logger.error("Error creating Input/Output streams in the socket, when processing the request");
		}
	}

	@Override
	public void run() {
		Response interResponse = readRequest();
		HeaderRequestParser.parse(interResponse);
		ResourceReader.handle(interResponse);
		sendResponse(interResponse);
	}

	/**
	 * Reads the header line by line in an array of rows, then saves it in DTO
	 * Response. At the same time writes the header to the response object's logger.
	 *
	 * <br>
	 * If there is an error reading the header, the status of the response is "500
	 * Internal Server Error".
	 *
	 * @return - The object of Response class
	 */
	private Response readRequest() {
		ArrayList<String> arrayHeaders = new ArrayList<>();
		Response interResponse = new Response();
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

		StringBuilder log = new StringBuilder();
		interResponse.addToLog("[REQUEST]:");

		while (true) {
			String arrayHeader = null;
			try {
				arrayHeader = reader.readLine();
			} catch (IOException e) {
				interResponse.setStatusCodeResponse(STATUS_INTERNAL_SERVER_ERROR);
				logger.error("Error reading input stream when reading data from request");
			}
			if (arrayHeader != null && !arrayHeader.trim().isEmpty()) {
				log.append(arrayHeader).append(END_LINE);
				arrayHeaders.add(arrayHeader);
			} else {
				break;
			}
		}

		interResponse.addToLog(log.toString());
		interResponse.setHeaders(arrayHeaders);
		return interResponse;
	}

	/**
	 * Forms a response header, attaches the response body, and sends it to the
	 * client.
	 *
	 * @param response
	 * 		- The object of Response class
	 */
	private void sendResponse(Response response) {
		byte[] content = response.getFile();
		StringBuilder responseBuilder = new StringBuilder();
		responseBuilder.append("HTTP/1.1 " + response.getStatusCodeResponse() + END_LINE);
		responseBuilder.append("Server: Simple HTTP Server" + END_LINE);
		responseBuilder.append("Content-Type: " + response.getMimeTypeFile() + END_LINE);
		responseBuilder.append("Content-Length: " + content.length + END_LINE);
		responseBuilder.append("Connection: close");

		response.addToLog("[RESPONSE]:");
		response.addToLog(responseBuilder.toString());

		try {
			outputStream.write(responseBuilder.toString().getBytes());
			outputStream.write((END_LINE + END_LINE).getBytes());
			outputStream.write(content);
			outputStream.flush();
			logger.info("The request processed {\n" + response.getLog() + "}\n");
		} catch (IOException e) {
			logger.error("Error writing in output stream, when processing the request");
		}
	}
}
