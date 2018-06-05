package by.net.iozhukov.server.service;

/**
 * 
 * 
 * 
 * @author Ilya Zhukov (ilya.beetle@gmail.com)
 */
public class Response {

	// Fields
	private String[] header = null;
	private String HTTPMethod = "";
	private String satusCodeResponse = "";
	private String requestResource = "";
	private String mimeTypeFile = "";
	private String formatFile = "";
	private String response = "";
	private byte[] file = null;

	private StringBuilder log = new StringBuilder();

	// Constructor
	public Response() {

	}

	// Getters and Setters
	public String[] getHeader() {
		return header;
	}

	public void setHeader(String[] header) {
		this.header = header;
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

	public void setRequestResource(String requestResorce) {
		this.requestResource = requestResorce;
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

	public String getSatusCodeResponse() {
		return satusCodeResponse;
	}

	public void setSatusCodeResponse(String satusCodeResponse) {
		this.satusCodeResponse = satusCodeResponse;
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

	public void addToLog(String message) {
		log.append(message+"\n");
	}

}
