/**
 * 
 */
package com.ss.jb5.one;

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Elliot
 *
 */
public class CommaSepInts {

	/**
	 * @param args
	 * This program takes in a comma separated list of ints
	 * and inserts and 'e' or 'o' before them depending on 
	 * if they are even or odd
	 */
	public static void main(String[] args) {
		System.out.println(getInts());

	}
	
	public static String getInts() {

		// ask user for ints and store in nums
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter list of integers");
		String input = sc.nextLine();
		String[] inputStr = input.split(" ");
		
		


		
		List<Integer> nums = new LinkedList<>();
		
		
		try {
			while (sc.hasNextInt()) {
				nums.add(sc.nextInt());
			}
		} catch(InputMismatchException e) {
			e.printStackTrace();
		}
		
		for (Integer num : nums) {
			System.out.println(num);
		}
		
		sc.close();
		
		return "this";
		
//		String separated = 
		
	}
	
	

}
