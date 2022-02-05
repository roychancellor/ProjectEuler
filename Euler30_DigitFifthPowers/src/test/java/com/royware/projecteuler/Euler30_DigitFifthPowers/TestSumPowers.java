package com.royware.projecteuler.Euler30_DigitFifthPowers;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestSumPowers {

	@Test
	public void testSumPowers() {
		assertEquals(1634, App.sumPowers(1634, 4));
		assertEquals(8208, App.sumPowers(8208, 4));
		assertEquals(9474, App.sumPowers(9474, 4));
		assertEquals(4151, App.sumPowers(4151, 5));
		assertEquals(54748, App.sumPowers(54748, 5));
		assertEquals(92727, App.sumPowers(92727, 5));
		assertEquals(93084, App.sumPowers(93084, 5));
		assertEquals(194979, App.sumPowers(194979, 5));
		/**
		 * Found one! It is 4150
Found one! It is 4151
Found one! It is 54748
Found one! It is 92727
Found one! It is 93084
		 */
	}

}
