/**
 * 
 */
package com.ss.jb2.one;

/**
 * @author Elliot
 *
 */
public class Assignment1 {

	/**
	 * @param args
	 * Take multiple values from the command line
	 * and show the result of adding them
	 */
	public static void main(String[] args) {

		int sum = 0;
		
		for (int i = 0; i < args.length; i++) {
			sum += Integer.parseInt(args[i]);
		}

		System.out.println(sum);
		
	}

}
