package projecteuler;

import static org.junit.Assert.*;

import org.junit.Test;

public class PentagonNumbersTest {

	@Test
	public void testIsPentagonalNumber() {
		assertTrue(PentagonNumbers.isPentagonalNumber(1));
		assertTrue(PentagonNumbers.isPentagonalNumber(5));
		assertTrue(PentagonNumbers.isPentagonalNumber(12));
		assertTrue(PentagonNumbers.isPentagonalNumber(22));
		assertTrue(PentagonNumbers.isPentagonalNumber(35));
		assertTrue(PentagonNumbers.isPentagonalNumber(51));
		assertTrue(PentagonNumbers.isPentagonalNumber(70));
		assertTrue(PentagonNumbers.isPentagonalNumber(92));
		assertTrue(PentagonNumbers.isPentagonalNumber(117));
		assertTrue(PentagonNumbers.isPentagonalNumber(145));
		assertFalse(PentagonNumbers.isPentagonalNumber(146));
		assertFalse(PentagonNumbers.isPentagonalNumber(144));
		assertTrue(PentagonNumbers.isPentagonalNumber(1820));
		assertTrue(PentagonNumbers.isPentagonalNumber(2262));
		assertTrue(PentagonNumbers.isPentagonalNumber(1820 + 2262));
		assertTrue(PentagonNumbers.isPentagonalNumber(2262 - 1820));
	}

	@Test
	public void testMakePentagonalNumber() {
		assertEquals(1, PentagonNumbers.makePentagonalNumber(1));
		assertEquals(5, PentagonNumbers.makePentagonalNumber(2));
		assertEquals(12, PentagonNumbers.makePentagonalNumber(3));
		assertEquals(22, PentagonNumbers.makePentagonalNumber(4));
		assertEquals(35, PentagonNumbers.makePentagonalNumber(5));
		assertEquals(51, PentagonNumbers.makePentagonalNumber(6));
		assertEquals(70, PentagonNumbers.makePentagonalNumber(7));
		assertEquals(92, PentagonNumbers.makePentagonalNumber(8));
		assertEquals(117, PentagonNumbers.makePentagonalNumber(9));
		assertEquals(145, PentagonNumbers.makePentagonalNumber(10));
	}
}
