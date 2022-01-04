package com.gameofthree.client.domain.enitiy;

import java.util.concurrent.atomic.AtomicBoolean;

import org.springframework.messaging.simp.stomp.StompSession;

import com.gameofthree.client.domain.exception.GameOfThreeException;

public class StartingThread implements Runnable {

	Player player;
	StompSession session;
	private Thread worker;
	private AtomicBoolean running = new AtomicBoolean(false);
	private AtomicBoolean stopped = new AtomicBoolean(true);

	public StartingThread(Player player, StompSession session) {
		this.player = player;
		this.session = session;
	}

	@Override
	public void run() {
		try {
			this.player.sendStartingNumber("Enter the Starting Number", session);
		} catch (Exception e) {
			throw new GameOfThreeException(e);
		}
	}

	public void start() {
		worker = new Thread(this);
		worker.start();
	}

	public void stop() {
		running.set(false);
		worker.stop();
	}

	public void interrupt() {
		running.set(false);
		worker.interrupt();
	}

	boolean isRunning() {
		return running.get();
	}

	boolean isStopped() {
		return stopped.get();
	}

}
