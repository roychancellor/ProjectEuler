package com.royware.projecteuler.Euler35_CircularPrimes;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TestMethods {

	@Test
	public void testGetDigits() {
		List<Integer> digits = App.getDigits(135);
		assertEquals(new Integer(1), digits.get(0));
		assertEquals(new Integer(3), digits.get(1));
		assertEquals(new Integer(5), digits.get(2));
		digits.clear();
		digits = App.getDigits(123);
		assertNull(digits);
	}

	@Test
	public void testGetRotation() {
		List<Integer> digits = new ArrayList<Integer>();
		digits.add(1);
		digits.add(9);
		digits.add(7);
		assertEquals(971, App.getRotation(digits, 1));
		assertEquals(719, App.getRotation(digits, 2));
		digits.add(5);
		assertEquals(9751, App.getRotation(digits, 1));
		assertEquals(7519, App.getRotation(digits, 2));
		assertEquals(5197, App.getRotation(digits, 3));
	}
	
	@Test
	public void testIsCircularPrime() {
		assertTrue(App.isCircularPrime(197));
		assertFalse(App.isCircularPrime(751));
	}
}
