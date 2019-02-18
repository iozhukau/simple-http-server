# Simple HTTP Server

_Console application for studying the operation of the HTTP protocol.
The server can on request return various static resources, such as: html pages, images, JavaScript scripts and CSS tables._

[**Project page**](https://iozhukov.net/project-simple-http-server.html "Simple HTTP Server")

## Technologies:
- **HTTP**
- **Java 8**
- **Properties**
- **Log4j**
- **Maven**
- **JUnit**

## How use this project
1. Download the finished [archive](https://bitbucket.org/iozhukov-projects/simple-http-server/downloads/replace "Downloads").//TODO new link
2. Unzip to the right place archive
3. Put index.html and other resources in the folder WWW
4. Run the file start.bat

## How build this project

1. Download the source code or clone the repository (stable branch is **master**).

2. Install project builder [**Maven**](https://maven.apache.org/ "Apache Maven Project") and **JDK 8**.

3. Run the command **mvn package** in the root of the project.

4. In the root of the project, the folder **"target"** appears, it will contain **"Simple HTTP Server.jar"**. This is the executable file.

5. **The application will not work without some things:**

	5.1 Folder **WWW** next to .jar file.
	
	5.2 Folder **config** next to .jar file with:
	
		- Configuration file **configuration.properties**, containing all properti(read the section "Properties")
		- Folder **errors** //todo add unfo
		
6. The application is launched by the command **java -jar "Simple HTTP Server.jar"**

## Properties

- *timerIsON* = [true|false] -- Switch for server timer.
- *timerTime* = [from 1 to 153722867280911] -- Time for timer, in minutes
- *port* = [8080] -- Port on which the requests will be expected

