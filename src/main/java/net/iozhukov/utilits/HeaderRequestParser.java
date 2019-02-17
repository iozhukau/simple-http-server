package net.iozhukov.utilits;

import net.iozhukov.server.service.Response;

import static net.iozhukov.utilits.HeaderRequestParser.Type.*;
import static net.iozhukov.utilits.StringConstants.STATUS_BAD_REQUEST;

/**
 * The class parses the data from the request header and writes them to the
 * appropriate fields the object of Response class
 *
 * @author Ilya Zhukov (https://iozhukov.net)
 */
public class HeaderRequestParser {

	public static void parse(Response response) {
		if (response.getHeaders() == null || response.getHeaders().isEmpty()) {
			response.setStatusCodeResponse(STATUS_BAD_REQUEST);
		} else {
			String httpMethod = whatIsHTTPMethod(response.getHeaders().get(0));
			response.setHTTPMethod(httpMethod);
			response.addToLog("HTTP Method is: " + httpMethod);

			String requestResource = whatIsRequestResource(response.getHeaders().get(0));
			response.setRequestResource(requestResource);
			response.addToLog("Request resource is:" + requestResource);

			String formatResource = whatIsFormatRequestResource(response.getRequestResource());
			response.setFormatFile(formatResource);
			response.addToLog("Format a request the resource is: " + formatResource);

			String MIMEType = whatIsMIMETypeRequestResource(response.getFormatFile());
			response.setMimeTypeFile(MIMEType);
			response.addToLog("MIME format a request the resource is: " + MIMEType);
		}
	}

	private static String whatIsHTTPMethod(String string) {
		return string.split(" ")[0];
	}

	private static String whatIsRequestResource(String string) {
		return string.split(" ")[1].substring(1);
	}

	private static String whatIsFormatRequestResource(String string) {
		int inx = string.indexOf(".");
		return inx > 0 ? string.substring(inx) : "";
	}

	private static String whatIsMIMETypeRequestResource(String string) {
		switch (string) {
			// text
			case "html": {
				return HTML;
			}
			case "css": {
				return CSS;
			}
			case "js": {
				return JS;
			}
			case "php": {
				return PHP;
			}
			case "xml": {
				return XML;
			}
			// images
			case "gif": {
				return GIF;
			}
			case "png": {
				return PNG;
			}
			case "jpg": {
				return JPEG;
			}
			case "jpeg": {
				return JPEG;
			}
			case "bmp": {
				return BMP;
			}
			case "ico": {
				return ICO;
			}
			default: {
				return "";
			}
		}
	}

	public final class Type {

		public static final String HTML = "text/html";

		public static final String CSS = "text/css";

		public static final String JS = "text/javascript";

		public static final String PHP = "text/php";

		public static final String XML = "text/xml";

		public static final String GIF = "image/gif";

		public static final String PNG = "image/png";

		public static final String JPEG = "image/jpeg";

		public static final String BMP = "image/bmp";// TODO: not verified

		public static final String ICO = "image/vnd.microsoft.icon";

	}
}
