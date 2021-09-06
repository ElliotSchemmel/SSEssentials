/**
 * 
 */
package com.ss.jb4.three;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Elliot
 *
 */
public class Assignment3 {

	/**
	 * @param args
	 * In this program, one thread (the producer) that produces items (ints)
	 * and another thread (the consumer) that consumes the items.
	 * For communication purposes, both threads have access to a bounded buffer
	 * which is basically an array
	 */
	public static void main(String[] args) throws InterruptedException {
		
		final Buff buff = new Buff();
		
		// producer thread
		Thread tp = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					buff.produce();
				}catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		// consumer thread
		Thread tc = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					buff.consume();
				}catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		tp.start();
		tc.start();
		
		tp.join();
		tc.join();

	}
	
	public static class Buff {
		
		// size of Bounded Buffer
		int capacity = 3;

		// Bounded Buffer
		Queue<Integer> q = new LinkedList<>();
		
		public void produce() throws InterruptedException {
			int value = 0;
			while (true) {
				synchronized (this) {
					// wait while list is full
					while (q.size() == capacity) {
						wait();
					}
					
					// produce a value
					System.out.println("Produced " + value);
					
					// add value to list
					q.add(value++);
					
					// notify the consumer thread
					notify();
					
					// slows down program
					Thread.sleep(1000);
				}
			}
		}

		public void consume() throws InterruptedException {
			while (true) {
				synchronized (this) {
					// wait while list is empty
					while (q.size() == 0) {
						wait();
					}
					
					// consume a value
					int val = q.remove();
					System.out.println("Consumed " + val);
					
					// notify the consumer thread
					notify();

					// slows down program
					Thread.sleep(1000);
				}
			}
		}
		
	}

}
