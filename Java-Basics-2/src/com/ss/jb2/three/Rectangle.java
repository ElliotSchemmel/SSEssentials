/**
 * 
 */
package com.ss.jb2.three;

/**
 * @author Elliot
 *
 */
public class Rectangle implements Shape{
		
	private Double area;
	private Double h, w;

	public Rectangle(Double h, Double w) {
		super();
		this.h = h;
		this.w = w;
	}
	
	@Override
	public void calculateArea() {
		area =  h * w;
	}

	@Override
	public void display() {
		System.out.println("The area of the rectangle is " + area);
		
	}
}
