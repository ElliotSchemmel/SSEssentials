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
public class Assignment4 {

	/**
	 * @param args
	 * Remove all x's from a given list of strings
	 */
	public static void main(String[] args) {
		
		// List of strings befor x removal
		List<String> strs = new ArrayList<>(Arrays.asList("ax", "bb", "cs", "x", "xxejx"));
		
		// removing x's from strs
		List<String> noXs = noX(strs);
		
		// print out string with no x's
		noXs.forEach(str -> System.out.print(str + " "));

	}
	
	public static List<String> noX(List<String> list) {
		
		// create a stream and map the strings from list to it while removing the x's
		List<String> strm = list.stream().map(str -> str.replace("x", "")).collect(Collectors.toList());
		return strm;
	}

}
