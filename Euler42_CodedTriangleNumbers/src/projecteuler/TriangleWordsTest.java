package projecteuler;

import static org.junit.Assert.*;

import org.junit.Test;

public class TriangleWordsTest {

	@Test
	public void testWordToValue() {
		assertEquals(55, TriangleWords.wordToValue("SKY"));
		assertEquals(1, TriangleWords.wordToValue("A"));
		assertEquals(26, TriangleWords.wordToValue("Z"));
		assertEquals(3, TriangleWords.wordToValue("AAA"));
		assertEquals(78, TriangleWords.wordToValue("ZZZ"));
	}

	@Test
	public void testIsTriangleNumber() {
		assertTrue(TriangleWords.isTriangleNumber(1));
		assertTrue(TriangleWords.isTriangleNumber(3));
		assertTrue(TriangleWords.isTriangleNumber(6));
		assertTrue(TriangleWords.isTriangleNumber(10));
		assertTrue(TriangleWords.isTriangleNumber(15));
		assertTrue(TriangleWords.isTriangleNumber(21));
		assertTrue(TriangleWords.isTriangleNumber(28));
		assertTrue(TriangleWords.isTriangleNumber(36));
		assertTrue(TriangleWords.isTriangleNumber(45));
		assertTrue(TriangleWords.isTriangleNumber(55));
	}

	@Test
	public void testIsInteger() {
		assertTrue(TriangleWords.isInteger(10));
		assertFalse(TriangleWords.isInteger(10.0001));
		assertFalse(TriangleWords.isInteger(9.9999));
	}
}
