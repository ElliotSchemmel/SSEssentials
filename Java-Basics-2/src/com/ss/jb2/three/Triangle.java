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

	@Override
	public Double calculateArea(Double x, Double y) {
		
		area = (x * y) / 2;
		return area;

	}

	@Override
	public void display() {
		
		System.out.println("The area of the triangle is " + area);

	}

	@Override
	public Double calculateArea(Double r) {
		// TODO Auto-generated method stub
		return null;
	}
}
