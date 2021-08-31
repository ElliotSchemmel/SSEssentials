package com.ss.jb1.one;

public class Assignment1 {

	public static void main(String[] args) {
	
		System.out.println("1)");
		for (int i = 1; i <= 4; i++) {
			for (int j = 0; j < i; j++) {
				System.out.print('*');
			}
			System.out.print('\n');
		}
		System.out.println(".........");
		
		System.out.println("2)");
		System.out.println("..........");		
		for (int i = 1; i <= 4; i++) {
			for (int j = 5; j > i; j--) {
				System.out.print('*');
			}
			System.out.print('\n');
		}
		
		System.out.println("3)");
		System.out.println("     *");
		System.out.println("    ***");
		System.out.println("   *****");
		System.out.println("  *******");
		System.out.println("...........");
		
		System.out.println("4)");
		System.out.println("............");
		System.out.println("  *******");	
		System.out.println("   *****");
		System.out.println("    ***");
		System.out.println("     *");
	
	}
}
