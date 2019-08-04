package com.iprogrammerr.algorithms_data_structures;

import org.junit.Test;

public class ThreadTest {

	@Test
	public void notifyingFromOther() {
		Thread first = new Thread(() -> {
			try {
				Thread currentThread = Thread.currentThread();
				System.out.println("Waiting from = " + currentThread.getId());
				synchronized (currentThread) {
					currentThread.wait();
				}
				System.out.println("Released!");
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		});
		Thread second = new Thread(() -> {
			try {
				Thread currentThread = Thread.currentThread();
				System.out.println("Waiting from = " + currentThread.getId());
				synchronized (currentThread) {
					currentThread.wait();
				}
				System.out.println("Released!");
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		});
		first.start();
		second.start();
		try {
			System.out.println("Will wake them up...");
			Thread.sleep(1000);
			System.out.println("Waking up!");
			synchronized (first) {
				first.notify();
			}
			synchronized (second) {
				second.notify();
			}
			Thread.sleep(500);
		} catch (Exception exception) {
			exception.printStackTrace();
		}

	}
}
