package by.net.iozhukov.utilits;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import by.net.iozhukov.server.service.Server;

public class WatcherTest {

	@Test
	public void simpleTestConstructorWatcher() {

		// input ========================

		Set<Thread> setThreads = new HashSet<>();
		Thread t1 = new Thread();
		Thread t2 = new Thread();
		Thread t3 = new Thread();
		setThreads.add(t1);
		setThreads.add(t2);
		setThreads.add(t3);
		Server server = new Server(8080);

		// test =========================

		Watcher wather = new Watcher(setThreads, server);

		// check ========================

		Assert.assertNotNull(wather);
	}

	@Test
	public void simpleTestRunWatcher() throws InterruptedException {
		/*
		// input ========================

		Set<Thread> setThreads = new HashSet<>();
		Watcher wather = new Watcher(setThreads, new Server(8080));
		
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					Long time = System.currentTimeMillis();
					boolean condition = time < time + 3000;
					if (condition) {
						return;
					}
				}
			}
		});
		Thread t2 = new Thread();
		Thread t3 = new Thread();
		setThreads.add(t1);
		setThreads.add(t2);
		setThreads.add(t3);
		
		// test =========================
		
		wather.start();

		// check ========================
		Assert.assertEquals(setThreads.isEmpty(), false);
		Thread.sleep(5000);
		Assert.assertEquals(setThreads.isEmpty(), true);
		*/
		fail("Not yet implemented");
	}

	@Test
	public void hardTestRunWatcher() {
		/*
		// input ========================

		Set<Thread> setThreads = new HashSet<>();
		Watcher wather = new Watcher(setThreads, new Server(8080));

		// test =========================
		int counter = 0;
		while (counter < 100) {
			counter++;
		}

		// check ========================
		*/
		fail("Not yet implemented");
	}

}
