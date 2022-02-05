package projecteuler;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

public class PowerfulTest {

	@Test
	public void testSumOfDigits() {
		assertEquals(45, Powerful.sumOfDigits(new BigDecimal(123456789)));
		assertEquals(180, Powerful.sumOfDigits(new BigDecimal("99999999999999999999")));
	}

}
