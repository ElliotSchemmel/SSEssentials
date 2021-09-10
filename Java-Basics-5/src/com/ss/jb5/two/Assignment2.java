/**
 * 
 */
package com.ss.jb5.two;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author Elliot
 *
 */
public class Assignment2 {

	/**
	 * @param args
	 * This program is given a list of non-negative integers 
	 * and returns an integer list of the rightmost digits
	 */
	public static void main(String[] args) {
		
		// create a List of Integers
		List<Integer> list = new ArrayList<>(Arrays.asList(23, 2345, 984, 29, 94, 4, 999, 39));
		
		// get the right most digits and store in Stream rightDigits
		Stream<Integer> rightDigits = getRightDigit(list);
		
		// print out all rightmost digits
		rightDigits.forEach((digit) -> System.out.print(digit + " "));
	}
	
	public static Stream<Integer> getRightDigit(List<Integer> list) {
		
		// use % 10 to convert all Integers to their right most digits
		Stream<Integer> rightDigits = list.stream().map(l -> l % 10);
		return rightDigits;	
	}

}
