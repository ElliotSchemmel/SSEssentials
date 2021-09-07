/**
 * 
 */
package com.ss.jb2.three;

/**
 * @author Elliot
 *
 */
public class Assignment3 {

	/**
	 * @param args
	 * Create a Shape Interface with the methods "calculateArea" and "display"
	 * Create a Rectangle, Circle, and Triangle class to implement that interface
	 */
	public static void main(String[] args) {
		
		// calculate the area and display the area of a rectangle, circle, and triangle
		
		Rectangle r = new Rectangle(8.38, 29.2);
		
		r.calculateArea();
		r.display();

		Circle c = new Circle(13.7);
		
		c.calculateArea();
		c.display();

		Triangle t = new Triangle(5.98, 9.3);
		
		t.calculateArea();
		t.display();
	}

}
