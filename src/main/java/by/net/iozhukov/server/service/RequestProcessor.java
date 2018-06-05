package by.net.iozhukov.server.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import by.net.iozhukov.utilits.HeaderRequestParser;
import by.net.iozhukov.utilits.ResourceReader;

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
 * @author Ilya Zhukov
 */
public class RequestProcessor implements Runnable {

	// Fields
	private InputStream inputStream;
	private OutputStream outputStream;

	/**
	 * The designer configures the input and output streams, from which the files
	 * will come and where they will go.
	 * 
	 * @param socket
	 *            - Port with which the handler interacts
	 */
	public RequestProcessor(Socket socket) {
		try {
			this.inputStream = socket.getInputStream();
			this.outputStream = socket.getOutputStream();
		} catch (IOException e) {
			Logger.getLogger("console").log(Level.ERROR,
					"Error creating Input/Output streams in the socket, when processing the request");
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
	 * Response. At the same time writes the header to the response object's log.
	 * 
	 * <br>
	 * If there is an error reading the header, the status of the response is "500
	 * Internal Server Error".
	 * 
	 * @return - The object of Response class
	 */
	private Response readRequest() {
		Response interResponse = new Response();
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		int i = 0;

		StringBuilder log = new StringBuilder();
		interResponse.addToLog("\n======= Header request =========");

		String[] arrayHeader = new String[50];

		while (true) {
			try {
				arrayHeader[i] = reader.readLine();
				log.append(arrayHeader[i] + "\n");

			} catch (IOException e) {
				Logger.getLogger("console").log(Level.ERROR,
						"Error reading input stream when reading data from request");
				interResponse.setSatusCodeResponse("500");
			}

			if (arrayHeader[i] == null || arrayHeader[i].trim().length() == 0) {
				break;
			}
			i++;
		}

		interResponse.addToLog(log.toString());
		interResponse.setHeader(arrayHeader);

		return interResponse;
	}

	/**
	 * Forms a response header, attaches the response body, and sends it to the
	 * client.
	 * 
	 * @param interResponse
	 *            - The object of Response class
	 */
	private void sendResponse(Response interResponse) {
		byte[] resourse = interResponse.getFile();
		String mimeType = interResponse.getMimeTypeFile();

		String response = "";
		response += "HTTP/1.1 " + interResponse.getSatusCodeResponse() + "\r\n";
		response += "Server: Simple HTTP Server\r\n";
		response += "Content-Type: " + mimeType + "\r\n";
		response += "Content-Length: " + resourse.length + "\r\n";
		response += "Connection: close\r\n\r\n";

		interResponse.addToLog("\n======= Header response =========");
		interResponse.addToLog(response);

		try {
			outputStream.write(response.getBytes());
			outputStream.write(resourse);
			outputStream.flush();
			Logger.getLogger("console").log(Level.INFO, "The request processed {\n" + interResponse.getLog() + "\n}");

		} catch (IOException e) {
			Logger.getLogger("console").log(Level.ERROR, "Error writing in output stream, when processing the request");
		}
	}

}
