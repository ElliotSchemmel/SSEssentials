/**
 * 
 */
package com.ss.jb1.two;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Random;

/**
 * @author Elliot
 *
 */
public class Assignment2 {

	/**
	 * Create a program that asks a user to guess a number
	 * Based on user input respond accordingly
	 * @param args
	 */
	public static void main(String[] args) {

		// generate random number
		Random rand = new Random();
		int randNum = rand.nextInt(100) + 1;
		
		int count = 1;
		
		// ask user for guess and store input in guess
		System.out.println("Guess a number between 1-100");
		Scanner input = new Scanner(System.in);
		
		while (true) {

			// get input and catch if improper input entered
			try {
				int guess = input.nextInt();
	
				
				/* compare guess to randNum
				 * if guess is within 10 + or - of number return 
				 * with correct answer
				 * else ask user to guess again
				*/
				
				// check if guess is in bounds
				if (guess > 100 || guess < 1) {
					System.out.println("Guess a number between 1-100");
					continue;
				}
				
				if (guess >= randNum - 10 && guess <= randNum + 10) {
					System.out.println("The correct answer is " + randNum);
					break;
				}
				
				count++;
				if (count > 5) {
					System.out.println("Sorry, the answer was " + randNum);
					break;
				}
				
				System.out.println("Try another number");
			}
			catch (InputMismatchException e) {
				System.out.println("Invalid input type, enter an Integer between 1-100");
				input.nextLine();
			}

		}
		
		input.close();
	}

}
