/**
 * 
 */
package com.ss.jb3.three;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author Elliot
 *
 */
public class Assignment3 {

	/**
	 * @param args
	 * count the number of times a given character appears in a file
	 */
	public static void main(String[] args) {
		
		int sum = 0;
		
		System.out.println("Enter in a character to see how many times it appears in text.txt");
		
		// read in char
		Scanner sc = new Scanner(System.in);
		char c = sc.next().charAt(0);
		
		// file is text.txt
		File file = new File("text.txt");
		
		// count the times char c appears in file
		try (FileReader fr = new FileReader(file)) {
			int content;
			while ((content = fr.read()) != -1) {
				if ((char) content == c) {
					sum++;
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("The character " + c + " appeared " + sum + " times in text.txt");
		

	}

}
