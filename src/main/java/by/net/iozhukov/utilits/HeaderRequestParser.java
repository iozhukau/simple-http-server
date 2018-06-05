package by.net.iozhukov.utilits;

import by.net.iozhukov.server.service.Response;

/**
 * 
 * 
 * 
 * @author Ilya Zhukov (ilya.beetle@gmail.com)
 */

public class HeaderRequestParser {

	public static void parse(Response interResponse) {
		
		String httpMethod = whatIsHTTPMethod(interResponse.getHeader()[0]);
		interResponse.setHTTPMethod(httpMethod);
		interResponse.addToLog("HTTP Method is: " + httpMethod);

		String requestResorce = whatIsRequestResource(interResponse.getHeader()[0]);
		interResponse.setRequestResource(requestResorce);
		interResponse.addToLog("Request resource is:" + requestResorce);

		String formatResource = whatIsFormatRequestResource(interResponse.getRequestResource());
		interResponse.setFormatFile(formatResource);
		interResponse.addToLog("Format a request the resource is: " + formatResource);

		String MIMEType = whatIsMIMETypeRequestResource(interResponse.getFormatFile());
		interResponse.setMimeTypeFile(MIMEType);
		interResponse.addToLog("MIME format a request the resource is: " + MIMEType);

	}

	private static String whatIsHTTPMethod(String string) {
		String result = "";
		int counder = 0;
		while (string.charAt(counder) != ' ') {
			result += string.charAt(counder);
			counder++;
		}
		return result;
	}

	private static String whatIsRequestResource(String string) {
		String result = "";
		int counter = 0;
		while (counter < string.length()) {
			if (string.charAt(counter) == '/') {
				counter++;
				while (string.charAt(counter) != ' ') {
					result += string.charAt(counter);
					counter++;
				}
				return result;
			}
			counter++;
		}
		return result;

	}

	private static String whatIsFormatRequestResource(String string) {
		String format = "";
		boolean pointIs = false;
		for (int i = string.length() - 1; i > 0; i--) {
			if (string.charAt(i) == '.') {
				pointIs = true;
				break;
			}
			format = string.charAt(i) + format;
		}
		if (pointIs) {
			return format;
		}
		return "";
	}

	private static String whatIsMIMETypeRequestResource(String string) {
		switch (string) {
		// text
		case "html": {
			return "text/html";
		}
		case "css": {
			return "text/css";
		}
		case "js": {
			return "text/javascript";
		}
		case "php": {
			return "text/php";
		}
		case "xml": {
			return "text/xml";
		}
		// images
		case "gif": {
			return "image/gif";
		}
		case "png": {
			return "image/png";
		}
		case "jpg": {
			return "image/jpeg";
		}
		case "jpeg": {
			return "image/jpeg";
		}
		case "bmp": {
			return "image/bmp";// не точно!!!!
		}
		case "ico": {
			return "image/vnd.microsoft.icon";
		}
		default: {
			return "/" + string;
		}
		}
	}

}
