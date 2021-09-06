/**
 * 
 */
package com.ss.jb4.two;

/**
 * @author Elliot
 *
 */
public class Assignment2 {

	/**
	 * @param args
	 * This program creates a deadlock between two threads
	 */
	public static void main(String[] args) {
		
		// locks used to sync the threads and deadlock them
		Object lock1 = new Object();
		Object lock2 = new Object();

		Runnable t1 = new Runnable() {

			@Override
			public void run() {
				try {
					synchronized (lock1) {
						Thread.sleep(100);
						synchronized (lock2) {
							System.out.println("T1 is running");
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};

		Runnable t2 = new Runnable() {

			@Override
			public void run() {
				try {
					synchronized (lock2) {
						Thread.sleep(100);
						synchronized (lock1) {
							System.out.println("T2 is running");
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		
		new Thread(t1).start();
		new Thread(t2).start();
		
		System.out.println("Deadlock");

	}
}
