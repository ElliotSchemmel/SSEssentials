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

	@Override
	public Double calculateArea(Double x, Double y) {
		
		area = x * y;
		return area;

	}

	@Override
	public void display() {
		
		System.out.println("The area of the rectangle is " + area);

	}

	@Override
	public Double calculateArea(Double r) {
		// TODO Auto-generated method stub
		return null;
	}



}
