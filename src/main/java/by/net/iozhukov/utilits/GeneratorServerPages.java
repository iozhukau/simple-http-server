package by.net.iozhukov.utilits;

/**
 * Class storage html error pages.
 *
 * @author Ilya Zhukov
 */
public class GeneratorServerPages {
	
	public static byte[] generateError400Page() {
		String page = "<!DOCTYPE html>\n" + 
				"<html>\n" + 
				"<head>\n" + 
				"	<meta charset=\"utf-8\">\n" + 
				"	<link href=\"https://fonts.googleapis.com/css?family=Risque\" rel=\"stylesheet\">\n" + 
				"	<title>400 Bad Request</title>\n" + 
				"	<style>\n" + 
				"	a{\n" + 
				"		cursor: pointer;\n" + 
				"	}\n" + 
				"	body{\n" + 
				"		background-image:linear-gradient(#48D1CC, 480px, #98FB98);\n" + 
				"		background-size: 1px 9000px;\n" + 
				"	}\n" + 
				"	p{\n" + 
				"		font-family: 'Risque', cursive;\n" + 
				"	}\n" + 
				"	.cool-header{\n" + 
				"		text-shadow: 5px 5px 5px RGBA(0, 0, 0, 0.7);\n" + 
				"	}\n" + 
				"	.content-page{\n" + 
				"		margin-left: auto;\n" + 
				"		margin-right: auto;\n" + 
				"\n" + 
				"		padding: 20px;\n" + 
				"		text-align:center;\n" + 
				"		background-color:RGBA(256, 256, 256, 0.5);\n" + 
				"\n" + 
				"		min-height:400px;\n" + 
				"		min-width:400px;\n" + 
				"		max-height:600px;\n" + 
				"		max-width:600px;\n" + 
				"\n" + 
				"		border: 1px solid black;\n" + 
				"		border-radius: 20px;\n" + 
				"	}\n" + 
				"	.picture-error{\n" + 
				"		height: 120px\n" + 
				"	}\n" + 
				"	</style>\n" + 
				"</head>\n" + 
				"<body>\n" + 
				"	<div  style=\"margin-top:5em\" class=\"content-page\">\n" + 
				"		<div style=\"margin-top:25%\">" +
				"			<h1 class=\"cool-header\">400 Bad Request</h1>\n" + 
				"			<p>Invalid request.</p>\n" +
				"		</div>"+
				"	</div>\n" + 
				"</body>\n" + 
				"</html>";

		byte[] buffer = page.getBytes();
		return buffer;
	}
	
	public static byte[] generateError404Page() {
		String page = "<!DOCTYPE html>\n" + 
				"<html>\n" + 
				"<head>\n" + 
				"	<meta charset=\"utf-8\">\n" + 
				"	<link href=\"https://fonts.googleapis.com/css?family=Risque\" rel=\"stylesheet\">\n" + 
				"	<title>404 Not Found</title>\n" + 
				"	<style>\n" + 
				"	a{\n" + 
				"		cursor: pointer;\n" + 
				"	}\n" + 
				"	body{\n" + 
				"		background-image:linear-gradient(#48D1CC, 480px, #98FB98);\n" + 
				"		background-size: 1px 9000px;\n" + 
				"	}\n" + 
				"	p{\n" + 
				"		font-family: 'Risque', cursive;\n" + 
				"	}\n" + 
				"	.cool-header{\n" + 
				"		text-shadow: 5px 5px 5px RGBA(0, 0, 0, 0.7);\n" + 
				"	}\n" + 
				"	.content-page{\n" + 
				"		margin-left: auto;\n" + 
				"		margin-right: auto;\n" + 
				"\n" + 
				"		padding: 20px;\n" + 
				"		text-align:center;\n" + 
				"		background-color:RGBA(256, 256, 256, 0.5);\n" + 
				"\n" + 
				"		min-height:400px;\n" + 
				"		min-width:400px;\n" + 
				"		max-height:600px;\n" + 
				"		max-width:600px;\n" + 
				"\n" + 
				"		border: 1px solid black;\n" + 
				"		border-radius: 20px;\n" + 
				"	}\n" + 
				"	.picture-error{\n" + 
				"		height: 120px\n" + 
				"	}\n" + 
				"	</style>\n" + 
				"</head>\n" + 
				"<body>\n" + 
				"	<div style=\"margin-top:5em\" class=\"content-page\">\n" + 
				"		<div style=\"margin-top:25%\">" +
				"			<h1 class=\"cool-header\">404 Not Found</h1>\n" + 
				"			<p>The requested resource is not found.</p>\n" +
				"		</div>"+		
				"	</div>\n" + 
				"</body>\n" + 
				"</html>\n" + 
				"";

		byte[] buffer = page.getBytes();
		return buffer;
	}



	public static byte[] generateError500Page() {
		String page = "<!DOCTYPE html>\n" + 
				"<html>\n" + 
				"<head>\n" + 
				"	<meta charset=\"utf-8\">\n" + 
				"	<link href=\"https://fonts.googleapis.com/css?family=Risque\" rel=\"stylesheet\">\n" + 
				"	<title>500 Internal Server Error</title>\n" + 
				"	<style>\n" + 
				"	a{\n" + 
				"		cursor: pointer;\n" + 
				"	}\n" + 
				"	body{\n" + 
				"		background-image:linear-gradient(#48D1CC, 480px, #98FB98);\n" + 
				"		background-size: 1px 9000px;\n" + 
				"	}\n" + 
				"	p{\n" + 
				"		font-family: 'Risque', cursive;\n" + 
				"	}\n" + 
				"	.cool-header{\n" + 
				"		text-shadow: 5px 5px 5px RGBA(0, 0, 0, 0.7);\n" + 
				"	}\n" + 
				"	.content-page{\n" + 
				"		margin-left: auto;\n" + 
				"	    margin-right: auto;\n" + 
				"\n" + 
				"		padding: 20px;\n" + 
				"		text-align:center;\n" + 
				"		background-color:RGBA(256, 256, 256, 0.5);\n" + 
				"\n" + 
				"		min-height:400px;\n" + 
				"		min-width:400px;\n" + 
				"		max-height:600px;\n" + 
				"		max-width:600px;\n" + 
				"\n" + 
				"		border: 1px solid black;\n" + 
				"		border-radius: 20px;\n" + 
				"	}\n" + 
				"	.picture-error{\n" + 
				"		height: 120px\n" + 
				"	}\n" + 
				"	</style>\n" + 
				"</head>\n" + 
				"<body>\n" + 
				"	<div  style=\"margin-top:5em\" class=\"content-page\">\n" + 
				"		<div style=\"margin-top:25%\">" +
				"			<h1 class=\"cool-header\">500 Internal Server Error</h1>\n" + 
				"			<p>Internal server error.</p>\n" +
				"		</div>"+
				"	</div>\n" + 
				"</body>\n" + 
				"</html>\n" + 
				"";

		byte[] buffer = page.getBytes();
		return buffer;
	}


}
