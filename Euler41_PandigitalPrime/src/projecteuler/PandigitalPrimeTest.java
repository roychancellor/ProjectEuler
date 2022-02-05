package projecteuler;

import static org.junit.Assert.*;

import org.junit.Test;

public class PandigitalPrimeTest {

	@Test
	public void testIsPandigital() {
		assertTrue(PandigitalPrime.isPandigital(PandigitalPrime.digitsToSet(21, 2), 2));
		assertTrue(PandigitalPrime.isPandigital(PandigitalPrime.digitsToSet(213, 3), 3));
		assertTrue(PandigitalPrime.isPandigital(PandigitalPrime.digitsToSet(2143, 4), 4));
		assertTrue(PandigitalPrime.isPandigital(PandigitalPrime.digitsToSet(52143, 5), 5));
		assertTrue(PandigitalPrime.isPandigital(PandigitalPrime.digitsToSet(621453, 6), 6));
		assertTrue(PandigitalPrime.isPandigital(PandigitalPrime.digitsToSet(2714536, 7), 7));
		assertTrue(PandigitalPrime.isPandigital(PandigitalPrime.digitsToSet(27184536, 8), 8));
		assertTrue(PandigitalPrime.isPandigital(PandigitalPrime.digitsToSet(927145386, 9), 9));
		assertFalse(PandigitalPrime.isPandigital(PandigitalPrime.digitsToSet(1, 1), 1));
		assertFalse(PandigitalPrime.isPandigital(PandigitalPrime.digitsToSet(124, 3), 3));
		assertFalse(PandigitalPrime.isPandigital(null, 3));
		assertFalse(PandigitalPrime.isPandigital(PandigitalPrime.digitsToSet(123, 3), 4));
	}

}
