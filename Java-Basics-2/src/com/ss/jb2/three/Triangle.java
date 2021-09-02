/**
 * 
 */
package com.ss.jb2.three;

/**
 * @author Elliot
 *
 */
public class Triangle implements Shape {
		
	private Double area;
	private Double b, h;

	public Triangle(Double b, Double h) {
		super();
		this.b = b;
		this.h = h;
	}
	
	@Override
	public void calculateArea() {
		area =  (b * h) / 2;
	}

	@Override
	public void display() {
		System.out.println("The area of the triangle is " + area);
		
	}

}
