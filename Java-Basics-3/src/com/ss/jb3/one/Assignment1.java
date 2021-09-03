/**
 * 
 */
package com.ss.jb3.one;

import java.io.File;
import java.io.IOException;

/**
 * @author Elliot
 *
 */
public class Assignment1 {

	/**
	 * @param args
	 * This program gets a list of all file/directory names
	 * with subdirectories under a given directory
	 */
	public static void main(String[] args) throws IOException {

		// get path of starting directory, here using current directory
		File root = new File(System.getProperty("user.dir"));
		
		// traverse recursively through root directory
		dirTraversal(root);
		
	}
	
	public static void dirTraversal(File dirPath) {
		
		// print current directory
		System.out.println(dirPath.getName() + "/");
		
		// loop through other directories
		File[] files=dirPath.listFiles();
		for (File file: files) {
			if (file.isDirectory()) {
				dirTraversal(file);
			}
		}
		
		// print file names
		for (File file: files) {
			if (file.isFile()) {
				System.out.println(file.getName());
			}
		}
	}

}
