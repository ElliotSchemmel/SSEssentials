/**
 * 
 */
package com.ss.jb5.two;


/**
 * @author Elliot
 *
 */
public class Assignment6 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		SampleSingleton first = SampleSingleton.getInstance();
		SampleSingleton second = SampleSingleton.getInstance();

		// show that both singletons are actually the same object
		System.out.println(first);
		System.out.println(second);

	}

}