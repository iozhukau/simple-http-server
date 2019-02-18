# Simple HTTP Server

_Console application for studying operations of the HTTP protocol._

_The server can return various static resources on request e.g: html pages, images, JavaScript scripts and CSS tables._

[**Project page**](https://blog.iozhukov.net/2017/12/project-simple-http-server.html "Simple HTTP Server")

### Technologies:
- **HTTP**
- **Java 8**
- **Properties**
- **Log4j**
- **Maven**
- **JUnit**

### How to use this application
1. Download the [archive](https://bitbucket.org/iozhukov-projects/simple-http-server/downloads/simple-http-server.zip "Downloads") with application.
2. Unzip archive.
3. Put index.html and other resources in the folder "WWW".
4. Run the file "start.cmd".

### How to build this project

1. Download the source code or clone the repository (stable branch is **master**).

2. Install project builder [**Maven**](https://maven.apache.org/ "Apache Maven Project") and **JDK 8**.

3. Run the command **mvn package** in the root of the project.

4. In the root of the project, the folder **"target"** appears, it will contain **"Simple HTTP Server.jar"**. This is the executable file.

5. **The application will not work without some things:**

	5.1 Folder **WWW** next to .jar file.
	
	5.2 Folder **resources** next to .jar file with:
	
		- Configuration file **configuration.properties**, containing all properti(read the section "Properties")
		- Folder **errors** whit html page for error. (400.html, 404.html, 500.html)
		
6. The application is launched by the command **java -jar "Simple HTTP Server.jar"**

### Properties

			timerIsON = [true|false] -- Switch for server timer.
			timerTime = [from 1 to 153722867280911] -- Time for timer, in minutes
			port = [8080] -- Port on which the requests will be expected

