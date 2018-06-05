package by.net.iozhukov.server;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.junit.Assert;
import org.junit.Test;

import by.net.iozhukov.server.service.Server;
import by.net.iozhukov.server.service.ServerTimer;

public class LauncherTest {

	@Test
	public void simpleTestMainPropertiesInLauncher() throws FileNotFoundException, IOException {

		// input =========================
		Properties config = new Properties();

		// test =========================
		config.load(new FileInputStream("configuration.properties"));

		// check =========================
		Assert.assertEquals(config.isEmpty(), false);
	}

	@Test
	public void midleTestLoadPropertiesInLauncher() throws FileNotFoundException, IOException {
		
		// input =========================
		Properties config = new Properties();

		// test =========================
		config.load(new FileInputStream("configuration.properties"));

		// check =========================
		Assert.assertEquals(config.containsKey("port"), true);
		Assert.assertEquals(config.containsKey("timerIsON"), true);
		Assert.assertEquals(config.containsKey("timerTime"), true);

	}
	
	@Test
	public void simpleTestCreatingTimerInLauncher() {
		
		// input =========================
		Integer port = 8080;
		Long time = 10000L;// 10s 

		Server server = new Server(port);

		// test =========================
		ServerTimer timer = new ServerTimer(server, time);

		// check =========================
		Assert.assertNotNull(timer);
	}
	
	@Test
	public void simpleTestCreatingServerInLauncher() {
		
		// input =========================
		Integer port = 8080;

		// test =========================
		Server server = new Server(port);

		// check =========================
		Assert.assertNotNull(server);
	}

}
