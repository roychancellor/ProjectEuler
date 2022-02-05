package projecteuler;

import static org.junit.Assert.*;

import org.junit.Test;

public class PrimePermutationsTest {

	@Test
	public void testIsPermutation() {
		assertTrue(PrimePermutations.isPermutation(1487, 4817));
		assertTrue(PrimePermutations.isPermutation(1487, 8147));
		assertTrue(PrimePermutations.isPermutation(4817, 8147));
		assertFalse(PrimePermutations.isPermutation(1234, 123));
		assertFalse(PrimePermutations.isPermutation(1234, 2345));
	}

	@Test
	public void testDigitsToList() {
//		fail("Not yet implemented");
	}

}
