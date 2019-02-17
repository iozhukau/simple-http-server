package net.iozhukov.server;

import net.iozhukov.server.service.Server;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LauncherTest {

	@Test
	public void simpleTestMainPropertiesInLauncher() throws IOException {

		// input =========================
		Properties config = new Properties();

		// test ==========================
		config.load(new FileInputStream("configuration.properties"));

		// check =========================
		Assert.assertFalse(config.isEmpty());
	}

	@Test
	public void middleTestLoadPropertiesInLauncher() throws IOException {
		
		// input =========================
		Properties config = new Properties();

		// test ==========================
		config.load(new FileInputStream("configuration.properties"));

		// check =========================
		Assert.assertTrue(config.containsKey("port"));
		Assert.assertTrue(config.containsKey("timerIsON"));
		Assert.assertTrue(config.containsKey("timerTime"));

	}
	
	@Test
	public void simpleTestCreatingServerInLauncher() {
		
		// input =========================
		Integer port = 8080;

		// test ==========================
		Server server = new Server(port);

		// check =========================
		Assert.assertNotNull(server);
	}
}
