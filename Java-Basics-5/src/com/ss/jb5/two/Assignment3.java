/**
 * 
 */
package com.ss.jb5.two;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
		
		// list of ints 
		List<Integer> nums = new ArrayList<>(Arrays.asList(23, -3, 4345, 99, 92));
		
		// double the ints
		List<Integer> doubledNums = doubling(nums);
		
		// print out doubled ints
		doubledNums.forEach(i -> System.out.print(i + " "));
	}
		
	public static List<Integer> doubling(List<Integer> list) {

		// use stream to map doubled ints to new list
		List<Integer> doubled = list.stream().map(i -> (i * 2)).collect(Collectors.toList());
		return doubled;
	}
}
