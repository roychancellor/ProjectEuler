package com.royware.projecteuler.Euler33_DigitCancelingFractions;

import static org.junit.Assert.*;

import org.junit.Test;

public class Tests {

	@Test
	public void test() {
		assertTrue(App.equalFractions(49,98,4,8));
		assertFalse(App.equalFractions(49,98,49,99));
		assertEquals(4, App.getCanceledFrac(49, 98)[0]);
		assertEquals(8, App.getCanceledFrac(49, 98)[1]);
	}

}
