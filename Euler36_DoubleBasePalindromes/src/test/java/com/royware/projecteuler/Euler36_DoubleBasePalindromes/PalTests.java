package com.royware.projecteuler.Euler36_DoubleBasePalindromes;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class PalTests {

	@Test
	public void testIsPalindrome() {
		ArrayList<Integer> digits = new ArrayList<Integer>();
		digits.add(5);
		digits.add(8);
		digits.add(5);
		assertTrue(App.isPalindrome(digits));
		digits.clear();
		digits.add(1);
		digits.add(0);
		digits.add(0);
		digits.add(1);
		digits.add(0);
		digits.add(0);
		digits.add(1);
		digits.add(0);
		digits.add(0);
		digits.add(1);
		assertTrue(App.isPalindrome(digits));
	}

	@Test
	public void testDecimalToBinary() {
		assertTrue(App.numberToDigits(585, 2).size() == 10);
		assertTrue(App.numberToDigits(585, 10).size() == 3);
	}
}
