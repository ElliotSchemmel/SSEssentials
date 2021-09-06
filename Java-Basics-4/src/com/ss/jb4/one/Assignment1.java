/**
 * 
 */
package com.ss.jb4.one;

/**
 * @author Elliot
 *
 */
public class Assignment1 {

	/**
	 * @param args
	 * This program implements a Singleton with double checked locking
	 */
	public static void main(String[] args) {
		
		// create two DCLSingles
		DCLSingle single = DCLSingle.getInstance();
		DCLSingle dub = DCLSingle.getInstance();
		
		// show that it is the same object
		System.out.println(single);
		System.out.println(dub);
		
	}
}