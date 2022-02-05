package com.royware.projecteuler.Euler31_CoinSums;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestCoinValueCalc {

	@Test
	public void test() {
		assertEquals(200, App.valueOfCoins(1, 2, 1, 0, 2, 1, 1));
		assertEquals(200, App.valueOfCoins(3, 1, 1, 0, 2, 1, 1));
		//1	2	1	0	2	1	1
	}

}
