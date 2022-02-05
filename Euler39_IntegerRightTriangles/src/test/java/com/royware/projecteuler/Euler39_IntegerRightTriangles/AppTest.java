package com.royware.projecteuler.Euler39_IntegerRightTriangles;

import static org.junit.Assert.*;

import org.junit.Test;

public class AppTest {

	@Test
	public void testComputePerimeterOfTriple() {
		assertEquals(12, App.computePerimeterOfTriple(2, 1, 1));
		assertEquals(30, App.computePerimeterOfTriple(3, 2, 1));
	}

}
