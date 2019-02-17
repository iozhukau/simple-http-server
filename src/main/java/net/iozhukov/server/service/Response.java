package net.iozhukov.server.service;

import java.util.List;

/**
 * Class for creating Data Transfer Object necessary for server service
 * operation.<br>
 * Also, due to the operation of the multi-threaded application, it collects the
 * processing log internally to output it after the client responds.
 *
 * @author Ilya Zhukov (https://iozhukov.net)
 */
public class Response {

	private final StringBuilder log = new StringBuilder();
	private List<String> headers = null;
	private String HTTPMethod = "";
	private String statusCodeResponse = "";
	private String requestResource = "";
	private String mimeTypeFile = "";
	private String formatFile = "";
	private String response = "";
	private byte[] file = null;

	public Response() {
	}

	public List<String> getHeaders() {
		return headers;
	}

	public void setHeaders(List<String> headers) {
		this.headers = headers;
	}

	public String getHTTPMethod() {
		return HTTPMethod;
	}

	public void setHTTPMethod(String hTTPMethod) {
		HTTPMethod = hTTPMethod;
	}

	public String getRequestResource() {
		return requestResource;
	}

	public void setRequestResource(String requestResource) {
		this.requestResource = requestResource;
	}

	public String getFormatFile() {
		return formatFile;
	}

	public void setFormatFile(String formatFile) {
		this.formatFile = formatFile;
	}

	public String getMimeTypeFile() {
		return mimeTypeFile;
	}

	public void setMimeTypeFile(String mimeTypeFile) {
		this.mimeTypeFile = mimeTypeFile;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public String getStatusCodeResponse() {
		return statusCodeResponse;
	}

	public void setStatusCodeResponse(String statusCodeResponse) {
		this.statusCodeResponse = statusCodeResponse;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getLog() {
		return log.toString();
	}

	/**
	 * A method for adding an entry to the log of processing a particular query.<br>
	 * <b>After each message puts a newline character.</b>
	 *
	 * @param message
	 * 		- String with message
	 */
	public void addToLog(String message) {
		log.append(message).append("\n");
	}
}
