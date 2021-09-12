/**
 * 
 */
package com.ss.jb5.one;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

		List<Integer> nums = Arrays.asList(3, 44, 99, 2, 39, 0, 1, 238);

		String str = getString(nums);

		System.out.println(str);

	}
	
	public static String getString(List<Integer> list) {
		
		List<String> strList = new LinkedList<>();

		// convert to strings and add o or e for odd or even
		for (Integer i : list) {
			if ((i % 2) == 0) {
				strList.add("e" + Integer.toString(i));
			}
			else {
				strList.add("o" + Integer.toString(i));
			}
		}
		
		// add commas between strings and concatonate to str
		String str = strList.stream().collect(Collectors.joining(","));

		return str;
	}
}
