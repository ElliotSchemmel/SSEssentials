/**
 * 
 */
package com.ss.jb4.four;

/**
 * @author Elliot
 *
 */
public class Assignment4 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Line a = new Line(0.0, 0.0, 1.0, 1.0);
		Line b = new Line(1.0, 2.0, 8.0, 7.0);

		System.out.println("The slope of line a is " + a.getSlope());
		System.out.println("The slope of line b is " + b.getSlope());

		System.out.println("The distance of line a is " + a.getDistance());
		System.out.println("The distance of line b is " + b.getDistance());
		
		if (a.parallelTo(b)) {
			System.out.println("Lines a and b are parallel"); 
		}
		else {
			System.out.println("Lines a and b are not parallel");
		}

	}

}
