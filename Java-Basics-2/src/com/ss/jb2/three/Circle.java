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

	@Override
	public Double calculateArea(Double x, Double y) {
		return null;
	}

	@Override
	public Double calculateArea(Double r) {
		area = r * r * Math.PI;
		return area;
	}

	@Override
	public void display() {
		System.out.println("The area of the circle is " + area);
		
	}
}
