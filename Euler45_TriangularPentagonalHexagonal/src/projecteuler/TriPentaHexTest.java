package projecteuler;

import static org.junit.Assert.*;

import org.junit.Test;

public class TriPentaHexTest {

	@Test
	public void testIsTriangleNumber() {
		assertTrue(TriPentaHex.isTriangleNumber(15));
	}

	@Test
	public void testIsPentagonalNumber() {
		assertTrue(TriPentaHex.isPentagonalNumber(35));
	}

	@Test
	public void testIsHexagonalNumber() {
		assertTrue(TriPentaHex.isHexagonalNumber(45));
	}

	@Test
	public void testMakeTriangularNumber() {
		assertEquals(15,TriPentaHex.makeTriangularNumber(5));
	}

	@Test
	public void testMakePentagonalNumber() {
		assertEquals(35,TriPentaHex.makePentagonalNumber(5));
	}

	@Test
	public void testMakeHexagonalNumber() {
		assertEquals(45,TriPentaHex.makeHexagonalNumber(5));
	}
	
}
