package com.royware.projecteuler.Euler28_NumberSpiralDiagonals;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestSpiralDiagonals {

	@Test
	public void testsumFourCornersOfRing() {
		SpiralDiagonals sd = new SpiralDiagonals();
		assertEquals(24, sd.sumFourCornersOfRing(3));
		assertEquals(76, sd.sumFourCornersOfRing(5));
		assertEquals(160, sd.sumFourCornersOfRing(7));
	}

}
