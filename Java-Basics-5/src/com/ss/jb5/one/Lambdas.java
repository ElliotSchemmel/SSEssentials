/**
 * 
 */
package com.ss.jb5.one;

import java.util.Arrays;

/**
 * @author Elliot
 *
 */
public class Lambdas {

	/**
	 * @param args
	 * Use lambdas to sort an array of strings in different ways
	 */
	public static void main(String[] args) {
		
		// base string array
		String[] words = {"one", "extreme", "yellow", "two", "end", "red", "blue", "hello"};
		
		// sort by length shortest to longest 
		Arrays.sort(words, (a, b) -> a.length() - b.length());
		System.out.println(Arrays.toString(words) + " Sorted by increasing length");

		// sort by length longest to shortest
		Arrays.sort(words, (a, b) -> b.length() - a.length());
		System.out.println(Arrays.toString(words) + " Sorted by decreasing length");
		
		// sort alphabetically by first char
		Arrays.sort(words, (a, b) -> a.charAt(0) - b.charAt(0));
		System.out.println(Arrays.toString(words) + " Sorted alhpabetically by first character");
		
		// sort strings that contain 'e' first and everything else second
		Arrays.sort(words, (a, b) -> {
			if (a.contains("e")) return -1;
			else return 1;
		});
		System.out.println(Arrays.toString(words) + " Sorted by strings containing 'e' first");
		

		System.out.println("Redoing sorting with static helper methods");

		// sort by length shortest to longest 
		Arrays.sort(words, (a, b) -> asc(a, b));
		System.out.println(Arrays.toString(words) + " Sorted by increasing length");

		// sort by length longest to shortest
		Arrays.sort(words, (a, b) -> desc(a, b));
		System.out.println(Arrays.toString(words) + " Sorted by decreasing length");
		
		// sort alphabetically by first char
		Arrays.sort(words, (a, b) -> alph(a, b));
		System.out.println(Arrays.toString(words) + " Sorted alhpabetically by first character");
		
		// sort strings starting with 'e' first and everything else second
		Arrays.sort(words, (a, b) -> letter(a, b)); 
		System.out.println(Arrays.toString(words) + " Sorted by strings containing 'e' first"); 
	}
	
	public static int asc(String a, String b) {
		if (a.length() > b.length()) {
			return 1;
		}
		else if (a.length() < b.length()) {
			return -1;
		}
		else return 0;
	}

	public static int desc(String a, String b) {
		if (a.length() < b.length()) {
			return 1;
		}
		else if (a.length() > b.length()) {
			return -1;
		}
		else return 0;
	}

	public static int alph(String a, String b) {
		if (a.charAt(0) > b.charAt(0)) {
			return 1;
		}
		else if (a.charAt(0) < b.charAt(0)) {
			return -1;
		}
		else return 0;
	}
	
	public static int letter(String a, String b) {
		if (a.contains("e")) {
			return -1;
		}
		else return 1;
	}
	

}
