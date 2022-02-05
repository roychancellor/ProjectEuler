package com.royware.projecteuler.Euler37_TruncatablePrimes;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class Tests {

	@Test
	public void testMakeRightToLeft() {
    	ArrayList<Integer> subNumberTest = new ArrayList<Integer>();
		subNumberTest.add(379);
		subNumberTest.add(37);
		subNumberTest.add(3);
		System.out.println("R->L: " + App.makeRightToLeft(3797, 10));
		System.out.println("L->R: " + App.makeLeftToRight(3797));
		assertTrue(App.makeRightToLeft(3797, 10).containsAll(subNumberTest));
		assertTrue(subNumberTest.containsAll(App.makeRightToLeft(3797, 10)));
		subNumberTest.clear();
		subNumberTest.add(797);
		subNumberTest.add(97);
		subNumberTest.add(7);
		assertTrue(App.makeLeftToRight(3797).containsAll(subNumberTest));
		assertTrue(subNumberTest.containsAll(App.makeLeftToRight(3797)));
	}

	@Test
	public void testisTruncatablePrime() {
    	ArrayList<Integer> subNumberTest = new ArrayList<Integer>();
		subNumberTest.add(379);
		subNumberTest.add(37);
		subNumberTest.add(3);
		assertTrue(App.isTruncatablePrime(subNumberTest));
		subNumberTest.clear();
		subNumberTest.add(797);
		subNumberTest.add(97);
		subNumberTest.add(7);
		assertTrue(App.isTruncatablePrime(subNumberTest));
	}
}
