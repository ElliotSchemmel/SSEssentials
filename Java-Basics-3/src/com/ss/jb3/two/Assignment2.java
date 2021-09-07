/**
 * 
 */
package com.ss.jb3.two;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author Elliot
 *
 */
public class Assignment2 {

	/**
	 * @param args
	 * This program appends text to an existing file
	 * file used is text.txt
	 */
	public static void main(String[] args) {
		
		String appendedString = "New appended text.";

		try {
			Files.write(
					Paths.get("text.txt"),
					appendedString.getBytes(),
					StandardOpenOption.APPEND);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
