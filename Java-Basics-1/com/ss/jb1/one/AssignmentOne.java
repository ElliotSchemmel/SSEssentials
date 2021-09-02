/**
 * 
 */
package com.ss.jb1.one;

/**
 * @author Elliot
 *
 */
public class AssignmentOne {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		StringBuilder dots = new StringBuilder("........");
	
		// pattern 1
		System.out.println("1)");
		for (int i = 1; i <= 4; i++) {
			for (int j = 0; j < i; j++) {
				System.out.print('*');
			}
			System.out.print('\n');
		}
		dots.append('.');
		System.out.println(dots);
		
		// pattern 2
		System.out.println("2)");
		dots.append('.');
		System.out.println(dots);
		for (int i = 1; i <= 4; i++) {
			for (int j = 5; j > i; j--) {
				System.out.print('*');
			}
			System.out.print('\n');
		}
		
		// pattern 3
		System.out.println("3)");
		for (int i = 1; i <= 4; i++) {
			// print out spaces
			for (int j = 5; j >= i; j--) {
				System.out.print(' ');
			}
			// print out *'s
			for (int j = 0; j < i * 2 - 1; j++) {
				System.out.print('*');
			}
			System.out.print('\n');
		}
		dots.append('.');
		System.out.println(dots);
		
		// pattern 4
		System.out.println("4)");
		dots.append('.');
		System.out.println(dots);
		for (int i = 1; i <= 4; i++) {
			// print out spaces
			for (int j = 0; j <= i; j++) {
				System.out.print(' ');
			}
			// print out *'s
			for (int j = 0; j < 9 - (i * 2); j++) {
				System.out.print('*');
			}
			System.out.print('\n');
		}
	
	}

}
