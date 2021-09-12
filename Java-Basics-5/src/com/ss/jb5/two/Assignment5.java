/**
 * 
 */
package com.ss.jb5.two;

import java.util.stream.IntStream;

/**
 * @author Elliot
 *
 */
public class Assignment5 {

	/**
	 * @param args
	 * Given an array of ints, check to see if a group can sum to a given target.
	 * If there are any adjacent identical ints then they either all must be chosen
	 * or none of them chosen as a group
	 */
	public static void main(String[] args) {
		
		// true, all digits used to sum to 14
		int[]  arr1 = {1, 2, 4, 6, 1};
		int target1 = 14;
		
		if (groupSumClump(arr1, target1)) System.out.println("True");
		else System.out.println("False");
			
		// true, 2, 2, 2, 5 used to sum to 11
		int[]  arr2 = {1, 2, 2, 2, 5, 2};
		int target2 = 11;
		
		if (groupSumClump(arr2, target2)) System.out.println("True");
		else System.out.println("False");
		
		// false, can not use any single 1 to sum to 20
		int[]  arr3 = {1, 1, 1, 12, 7};
		int target3 = 20;
		
		if (groupSumClump(arr3, target3)) System.out.println("True");
		else System.out.println("False");
		
		

	}
	
	public static boolean groupSumClump(int[] arr, int target) {
		
		int sum = 0;
		int identical = 0;
		boolean containsIdenticals = false;
		
		// check all adjacent identical ints, and return true or remove them from arr
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] == arr[i-1]) {	
				containsIdenticals = true;
				identical = arr[i];
				if (sum == 0) sum += arr[i] * 2;
				else sum += arr[i];
			}
		}
		
		final int fIdentical = identical;
		final int[] fArr = arr;
		
		// remove the identical numbers
		if (containsIdenticals){
			arr = IntStream.range(0,  fArr.length).filter(i -> fArr[i] != fIdentical).map(i -> fArr[i]).toArray();

			// check for the sum with the total of the identicals
			if (checkRemainder(0, arr, target - sum)) {
				return true;
			}
			// check for the sum without the total of the identicals
			else {
				return checkRemainder(0, arr, target);
			}
			
		}

		// else leave arr as is and check for the sum normally
		return checkRemainder(0, arr, target);
	
		
		

	}
	
	public static boolean checkRemainder(int start, int[] arr, int target) {
		
		// once start is at arr.length, return true if target has been reached 
		// and false if it has not
		if (start == arr.length) {
			if (target == 0) {
				return true;
			}
			return false;
		}
		
		// return true if sub-target can be reached in substring 
		if (checkRemainder(start + 1, arr, target - arr[start])) {
			return true;
		}
		// else return and check for full target again
		return checkRemainder(start + 1, arr, target);
	}

}
