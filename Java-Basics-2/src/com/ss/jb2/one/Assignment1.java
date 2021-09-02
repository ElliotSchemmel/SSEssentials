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

		float sum = 0;
		
		for (int i = 0; i < args.length; i++) {
			try {
				sum += Float.parseFloat(args[i]);
			}
			catch (NumberFormatException e) {
				System.out.println("Error: must enter valid numbers");
				return;
			}
		}

		System.out.println(sum);
	}

}
