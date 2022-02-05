package com.royware.projecteuler.Euler32_PandigitalProducts;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class TestPandigital {

	@Test
	public void testIsPandigital() {
		Set<Integer> testSet = new HashSet<Integer>();
		assertEquals(2, App.digitsToSet(39, testSet));
		assertEquals(2, testSet.size());
		assertEquals(3, App.digitsToSet(186, testSet));
		assertEquals(5, testSet.size());
//		assertTrue(App.isPandigital(39, 186));
		assertEquals(0, App.digitsToSet(402, testSet));
		assertEquals(0, App.digitsToSet(400, testSet));

		Set<Integer> fac1Digits = new HashSet<Integer>();
		fac1Digits.add(3);
		fac1Digits.add(9);
		Set<Integer> fac2Digits = new HashSet<Integer>();
		fac2Digits.add(1);
		fac2Digits.add(8);
		fac2Digits.add(6);
		Set<Integer> prodDigits = new HashSet<Integer>();
		assertEquals(4, App.digitsToSet(39*186, prodDigits));
		assertTrue(App.isPandigital(fac1Digits, fac2Digits, prodDigits));
	}
}
