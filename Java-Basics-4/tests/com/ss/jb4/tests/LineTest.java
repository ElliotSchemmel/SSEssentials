/**
 * 
 */
package com.ss.jb4.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.ss.jb4.four.Line;

/**
 * @author Elliot
 *
 */
public class LineTest {
	
	Line testA = new Line(0, 0, 1, 1);
	Line testB = new Line(2, 2, 4, 4);
	Line testC = new Line(1, 2, 4, 8);
	
	@Test
	public void testGetSlope() {
		assertEquals(1.0, testA.getSlope(), .0001);
		assertNotEquals(4.0, testA.getSlope(), .0001);
	}

	@Test
	public void testGetDistance() {
		assertEquals(Math.sqrt(2.0), testA.getDistance(), .0001);
		assertNotEquals(Math.sqrt(8.0), testA.getDistance(), .0001);
	}

	@Test
	public void testParallelTo() {
		assertTrue(testA.parallelTo(testB));
		assertFalse(testA.parallelTo(testC));
	}
}
