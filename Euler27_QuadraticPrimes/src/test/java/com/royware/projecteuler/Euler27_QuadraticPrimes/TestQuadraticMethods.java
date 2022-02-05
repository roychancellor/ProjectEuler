package com.royware.projecteuler.Euler27_QuadraticPrimes;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestQuadraticMethods {

	@Test
	public void testNumConsecPrimes() {
		QuadraticPrimes qp = new QuadraticPrimes();
		assertEquals(40, qp.numConsecPrimes(1, 41));
		assertEquals(80, qp.numConsecPrimes(-79, 1601));
	}

	@Test
	public void testIsPrime() {
		QuadraticPrimes qp = new QuadraticPrimes();
		assertTrue(qp.isPrime(41));
		assertTrue(qp.isPrime(1601));
		assertTrue(qp.isPrime(-41));
		assertTrue(qp.isPrime(-1601));
		assertFalse(qp.isPrime(996));
		assertFalse(qp.isPrime(-996));
	}

}
