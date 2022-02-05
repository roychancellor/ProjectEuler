package projecteuler;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class PandigitalDivisibilityTest {

	public static Integer[] num = {1,4,0,6,3,5,7,2,8,9};
	public static List<Integer> digits = new ArrayList<Integer>(Arrays.asList(num));

	@Test
	public void testIsSubStringDivisible() {
		assertTrue(PandigitalDivisibility.isSubStringDivisible(digits));
	}

	@Test
	public void testThreeDigitsToNumber() {
		assertEquals(406, PandigitalDivisibility.threeDigitsToNumber(digits, 1));
		assertEquals(63, PandigitalDivisibility.threeDigitsToNumber(digits, 2));
		assertEquals(635, PandigitalDivisibility.threeDigitsToNumber(digits, 3));
		assertEquals(357, PandigitalDivisibility.threeDigitsToNumber(digits, 4));
		assertEquals(572, PandigitalDivisibility.threeDigitsToNumber(digits, 5));
		assertEquals(728, PandigitalDivisibility.threeDigitsToNumber(digits, 6));
		assertEquals(289, PandigitalDivisibility.threeDigitsToNumber(digits, 7));
	}

	@Test
	public void testTenDigitsToNumber() {
		assertEquals(1406357289L, PandigitalDivisibility.tenDigitsToNumber(digits));
	}
}
