# Simple HTTP Server

*Console application for studying the operation of the HTTP protocol.*

*The server can on request return various static resources, such as: html pages, images, JavaScript scripts and CSS tables.*

[**Project page**](http://blog.ibeetle.info/2017/12/project-simple-http-server.html "Simple HTTP Server")

## Technologies:

- **HTTP**

- **Java SE, Java IO**

- **Properties**

- **Log4j**

- **Maven**

- **JUnit**

## How use this project
1. Download the finished [archive](https://www.dropbox.com/s/7omiko3x337glh2/simple-http-server.zip?dl=0 "simple-http-server.zip").
 
2. Unzip to the right place archive
 
3. Put index.html and other resources in the folder WWW

4. Run the file start.bat


## How build this project

1. Download the source code or clone the repository (stable branch is **master**).

2. Install project builder [**Maven**](https://maven.apache.org/ "Apache Maven Project") and **JDK 8** and younger.

3. Run the command **mvn package** in the root of the project.

4. In the root of the project, the folder **"target"** appears, it will contain **"Simple HTTP Server.jar"**. This is the executable file.

5. **The application will not work without some things:**

	5.1 Folder **WWW** next to .jar file. 
	
	5.2 Configuration file **configuration.properties**, containing: 
	
			*timerIsON* = [true|false] -- Switch for server timer
			*timerTime* = [from 1 to 153722867280911] -- Time for timer, in minutes
			*port* = [8080] -- Port on which the requests will be expected
	
6. The application is launched by the command **java -jar "Simple HTTP Server.jar"**
