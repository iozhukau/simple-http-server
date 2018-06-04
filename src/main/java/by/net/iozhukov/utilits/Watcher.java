package by.net.iozhukov.utilits;

import java.util.Set;

import by.net.iozhukov.server.service.Server;

public class Watcher extends Thread {

	private Set<Thread> set;
	private Server server;

	public Watcher(Set<Thread> set, Server server) {
		this.set = set;
		this.server = server;
	}

	@Override
	public void run() {
		synchronized (server.getClass()) {
			set.stream().filter(t -> t.isAlive() != true).map(t -> set.remove(t));
		}
	}
}
