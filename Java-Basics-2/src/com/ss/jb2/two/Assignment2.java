/**
 * 
 */
package com.ss.jb2.two;

/**
 * @author Elliot
 *
 */
public class Assignment2 {

	/**
	 * @param args
	 * Construct a 2D array and find the max number
	 * Show its position in the array
	 */
	public static void main(String[] args) {
		
		// keeps track of max number
		int max = 0;
		
		// keeps track of position of max number
		int maxI = 0;
		int maxJ = 0;
		
		int[][] arr = {{8, 3, 8, 98, 234, 9},
					   {3, 43, 345, 34, 5, 5},
					   {3, 3, 5, 34, 3, 5},
					   {9, 483, 4, 99, 35, 5},
					   {3, 4, 35, 3, 34, 5}};
		
		// loop through arr and find max number
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				if (arr[i][j] > max) {
					max = arr[i][j];
					maxI = i;
					maxJ = j;
				}
			}
		}	
			
		System.out.println("Max value " + max + 
					   	   " at location " + maxI + ',' + maxJ);	
		
		
		
		
	}
		
		
		

}


