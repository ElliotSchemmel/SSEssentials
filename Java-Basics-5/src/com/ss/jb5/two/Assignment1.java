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
public class Assignment1 {

	/**
	 * @param args
	 * This program uses lambdas to:
	 * 		check if a number is odd or even
	 * 		check if a number is prime
	 * 		check if a number is a palindrome
	 */
	public static void main(String[] args) {
	
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
					switch (testCase) {
						case 1: 
							isOdd(num);
						case 2:
							isPrime(num);
						case 3:
							isPalindrome(num);
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
	
	public static int isOdd(int x) {
		return x;
	}

	public static int isPrime(int x) {
		return x;
	}

	public static int isPalindrome(int x) {
		return x;
	}
}
