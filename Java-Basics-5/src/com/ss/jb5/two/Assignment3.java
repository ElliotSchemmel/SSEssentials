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
public class Assignment3 {

	/**
	 * @param args
	 * This program takes in a list of integers and returns 
	 * a list where each integer is multiplied by 2
	 */
	public static void main(String[] args) {
		
		List<Integer> nums = new ArrayList<>(Arrays.asList(23, -3, 4345, 99, 92));
		
		Stream<Integer> doubledNums = doubling(nums);
		
		doubledNums.forEach(i -> System.out.print(i + " "));
	}
		
	public static Stream<Integer> doubling(List<Integer> list) {

		Stream<Integer> doubled = list.stream().map(i -> (i * 2));
		return doubled;
	}
}
