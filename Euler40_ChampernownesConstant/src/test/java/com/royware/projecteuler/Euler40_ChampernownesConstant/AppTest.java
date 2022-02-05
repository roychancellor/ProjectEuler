package com.royware.projecteuler.Euler40_ChampernownesConstant;

import static org.junit.Assert.*;

import org.junit.Test;

public class AppTest {

	@Test
	public void testD() {
		StringBuilder num = new StringBuilder(".1234567891011121314151617181920");
		for(int i = 1; i <= 9; i++) {
			assertEquals(i, App.d(num, i));
		}
		assertEquals(1, App.d(num, 10));
		assertEquals(0, App.d(num, 11));
		assertEquals(1, App.d(num, 12));
		assertEquals(1, App.d(num, 13));
		assertEquals(1, App.d(num, 14));
	}

}
