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
		
		Rectangle r = new Rectangle();
		
		r.calculateArea(8.38, 29.2);
		r.display();

		Circle c = new Circle();
		
		c.calculateArea(13.7);
		c.display();

		Triangle t = new Triangle();
		
		t.calculateArea(5.98, 9.3);
		t.display();
	}

}
