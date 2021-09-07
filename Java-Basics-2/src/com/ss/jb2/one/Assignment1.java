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
	 * @param args takes in doubles
	 * Take multiple values from the command line
	 * and show the result of adding them
	 */
	public static void main(String[] args) {

		double sum = 0;
		
		// parse command line arguments and add to sum
		for (int i = 0; i < args.length; i++) {
			try {
				sum += Double.parseDouble(args[i]);
			}
			catch (NumberFormatException e) {
				System.out.println("Error: must enter valid numbers");
				return;
			}
		}

		System.out.println(sum);
	}

}
