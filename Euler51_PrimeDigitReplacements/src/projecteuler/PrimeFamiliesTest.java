package projecteuler;

import static org.junit.Assert.*;

import org.junit.Test;

public class PrimeFamiliesTest {

	@Test
	public void testSizeOfPrimePattern() {
//		fail("Not yet implemented");
	}

	@Test
	public void testSizeOfPrimePatternReplaceOne() {
		assertEquals(6, PrimeFamilies.sizeOfPrimePatternReplaceOne(13, 7));
		assertEquals(13, PrimeFamilies.sizeOfPrimePatternReplaceOne(13, 6));
	}

	@Test
	public void testSizeOfPrimePatternReplaceTwo() {
		assertEquals(7, PrimeFamilies.sizeOfPrimePatternReplaceTwo(56123, 8));
		assertEquals(56003, PrimeFamilies.sizeOfPrimePatternReplaceTwo(56123, 7));
	}

//	@Test
//	public void testSizeOfPrimePatternReplaceN() {
//		assertEquals(7, PrimeFamilies.sizeOfPrimePatternReplaceN(56123, 2, 8));
//		assertEquals(56003, PrimeFamilies.sizeOfPrimePatternReplaceN(56123, 2, 7));
//	}
	
	@Test
	public void testGetIndexesToReplace() {
		int[][] assert52 = {{0,1},{0,2},{0,3},{1,2},{1,3},{2,3}};
		assertArrayEquals(assert52, PrimeFamilies.getIndexesToReplace(5, 2));
	}

}
