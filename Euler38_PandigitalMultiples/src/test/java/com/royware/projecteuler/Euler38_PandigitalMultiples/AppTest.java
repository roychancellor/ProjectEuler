package com.royware.projecteuler.Euler38_PandigitalMultiples;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class AppTest {

	@Test
	public void testIsPandigital() {
		Integer[] arr1 = {9,8,7,6,5,4,3,2,1};
		Set<Integer> digits = new HashSet<Integer>(Arrays.asList(arr1));
		assertTrue(App.isPandigital(digits));
		Integer[] arr2 = {9,8,7,6,5,4,3,2};
		Set<Integer> digits2 = new HashSet<Integer>(Arrays.asList(arr2));
		assertFalse(App.isPandigital(digits2));
	}

	@Test
	public void testdigitsToNumber() {
		Integer[] digList = {9,8,7,6,5};
		List<Integer> digits = new ArrayList<Integer>(Arrays.asList(digList));
		assertEquals(98765, App.digitsToNumber(digits));
	}
	
	@Test
	public void testConcatNewDigits() {
		List<Integer> digits = new ArrayList<Integer>();
		App.concatNewDigits(digits, 12345);
		assertEquals(12345, App.digitsToNumber(digits));
		App.concatNewDigits(digits, 6789);
		assertEquals(123456789, App.digitsToNumber(digits));
	}
}
