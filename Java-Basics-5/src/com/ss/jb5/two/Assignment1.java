/**
 * 
 */
package com.ss.jb5.two;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Elliot
 *
 */

interface PerformOperation {
	String check(int i);
}

public class Assignment1 {

	/**
	 * @param args
	 * This program uses lambdas to:
	 * 		check if a number is odd or even
	 * 		check if a number is prime
	 * 		check if a number is a palindrome
	 */
	public static void main(String[] args) {
		
		// lambda to check if number is odd or even
		PerformOperation isOdd = (int i) -> {
			if (i % 2 == 0) {
				return "EVEN";
			}
			else return "ODD";
		};
		
		// lambda to check if number is prime
		PerformOperation isPrime = (int i ) -> {

			if (i <= 1) return "COMPOSITE";

			for (int j = 2; j < Math.sqrt(i); j++) {
				if (i % j == 0) {
					return "COMPOSITE";
				}
			}

			return "PRIME";
		};

		// lambda to check if number is a palindrome
		PerformOperation isPalindrome = (int i ) -> {
			
			String number = Integer.toString(i);
			
			for (int j = 0; j < number.length() / 2; j++) {
				if (number.charAt(j) == number.charAt(number.length() - (j+1))) {
					// continue
				}
				else {
					// not a palindrome
					return "NOT A PALINDROME";
				}
			}
			return "PALINDROME";
		};
		
	
		while (true) {
			try {
				Scanner sc = new Scanner(System.in);
				
				// read number of test cases
				int tests = sc.nextInt();
				
				int testCase = 0;
				int num = 0;
				
				for (int i = 0; i < tests; i++) {

					testCase = sc.nextInt();
					num = sc.nextInt();
					
					// pick the chosen test case
					switch (testCase) {
						case 1: 
							System.out.println(isOdd.check(num));
							break;
						case 2:
							System.out.println(isPrime.check(num));
							break;
						case 3:
							System.out.println(isPalindrome.check(num));
							break;
						default: 
							System.out.println("Incorrect test case entered, choose 1 2 or 3");
							break;
					}
				}

				sc.close();

			} catch(InputMismatchException e) {
				System.out.println("Only enter Integers");
				continue;
			}
			break;
		}
	}
}
