/**
 * 
 */
package com.ss.jb2.three;

/**
 * @author Elliot
 *
 */
public class Circle implements Shape {
	
	private Double area;
	private Double r;

	public Circle(Double r) {
		super();
		this.r = r;
	}
	
	@Override
	public void calculateArea() {
		area =  r * r * Math.PI;
	}

	@Override
	public void display() {
		System.out.println("The area of the circle is " + area);
		
	}
}
