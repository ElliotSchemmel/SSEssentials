/**
 * 
 */
package com.ss.jb5.one;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Elliot
 *
 */
public class ThreeLetters {

	/**
	 * @param args
	 * This program returns a list of all strings that 
	 * start with 'a' and have exactly 3 letters
	 */
	public static void main(String[] args) {
		
		// list of strings to be parsed
		List<String> strs = Arrays.asList("ale", "yellow", "bow", "apple",
							"and", "ant", "orange", "pineapple");
		
		// parse the strs
		List<String> parsedStrs = parseList(strs);
		
		// print the parsed list of strs
		parsedStrs.forEach((str) -> System.out.println(str));
	}
	
	public static List<String> parseList(List<String> list) {
		
		// filter the given list of strings to match the regex "a.."
		List<String> result = list.stream().filter(l -> l.matches("a..")).collect(Collectors.toList());
		return result;
		
	}

}
