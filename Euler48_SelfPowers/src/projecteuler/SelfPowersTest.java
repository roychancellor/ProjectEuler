package projecteuler;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

public class SelfPowersTest {

	@Test
	public void testMakeSeriesSum() {
		assertEquals(new BigDecimal(10405071317L), SelfPowers.makeSeriesSum(1, 10));
	}

	@Test
	public void testGetLastNDigits() {
		StringBuilder test = new StringBuilder("12345678901234567890");
		
		assertEquals(10, SelfPowers.getLastNDigits(10, test).length());
		assertEquals("1234567890", SelfPowers.getLastNDigits(10, test));
		assertNull(SelfPowers.getLastNDigits(-1, test));
		assertNull(SelfPowers.getLastNDigits(test.length() + 1, test));
	}
}
