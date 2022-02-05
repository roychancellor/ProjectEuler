/**
 * The series, 1^1 + 2^2 + 3^3 + ... + 10^10 = 10405071317.

Find the last ten digits of the series, 1^1 + 2^2 + 3^3 + ... + 1000^1000.
 */

package projecteuler;

import java.math.BigDecimal;

public class SelfPowers {

	public static void main(String[] args) {
		BigDecimal sum = makeSeriesSum(1, 1000);
		StringBuilder digits = new StringBuilder(sum.toString());
		
		System.out.println("The last 10 digits of the series is " + getLastNDigits(10, digits));
	}
	
	public static BigDecimal makeSeriesSum(int a, int b) {
		BigDecimal sum = new BigDecimal(0);
//		System.out.println("START, sum: " + sum);
		for(int n = a; n <= b; n++) {
			sum = sum.add(new BigDecimal(n).pow(n));
//			System.out.println("n: " + n + ", sum: " + sum.toString());
//			System.out.println("power: " + new BigDecimal(n).pow(n).toString());
		}

		return sum;
	}
	
	public static String getLastNDigits(int n, StringBuilder digits) {
		return (n > 0 && n < digits.length()) ? digits.substring(digits.length() - n) : null;
	}

}
