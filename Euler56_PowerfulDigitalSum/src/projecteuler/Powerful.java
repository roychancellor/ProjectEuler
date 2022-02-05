/**
 * A googol (10^100) is a massive number: one followed by one-hundred zeros; 100^100 is almost unimaginably large:
 * one followed by two-hundred zeros. Despite their size, the sum of the digits in each number is only 1.

Considering natural numbers of the form, a^b, where a, b < 100, what is the maximum digital sum?
 */
package projecteuler;

import java.math.BigDecimal;

public class Powerful {

	public static void main(String[] args) {
		BigDecimal power = new BigDecimal(1);
		int maxDigitalSum = 0;
		int sumDigits = 0;
		for(int a = 2; a < 100; a++) {
			BigDecimal base = new BigDecimal(a);
			for(int b = 99; b >= 0; b--) {
				power = base.pow(b);
				sumDigits = sumOfDigits(power);
				if(sumDigits > maxDigitalSum) {
					maxDigitalSum = sumDigits;
				}
			}
		}
		
		System.out.println("The maximum digital sum is " + maxDigitalSum);
	}
	
	public static int sumOfDigits(BigDecimal bd) {
		int sum = 0;
		
		if(bd != null) {
			while(bd.compareTo(BigDecimal.ZERO) > 0) {
				sum += bd.remainder(BigDecimal.TEN).intValue();
				bd = bd.divideToIntegralValue(BigDecimal.TEN);
			}
			return sum;
		}
		return 0;
	}

}
