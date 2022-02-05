/**
 * 	In the first one-thousand expansions, how many fractions contain a numerator with more digits than the denominator?
 * 
 * NOTES: In the expansion, we can see the following pattern in the fraction that results before adding 1:
 * 1/2, 2/5, 5/12, 12/29, 29/70, 70/169, 169/408, ...
 * That is, each new fraction is: (previous denominator) / (2 * previous denominator + previous numerator)
 * The square root of 2 approximation is, therefore: (new denominator + new numerator) / new denominator
 */

package projecteuler;

import java.math.BigDecimal;

public class SquareRoot {
	static final BigDecimal TWO = new BigDecimal(2);

	public static void main(String[] args) {
		BigDecimal prevNumer = new BigDecimal(1);
		BigDecimal prevDenom = new BigDecimal(2);
		BigDecimal newNumer = new BigDecimal(0);
		BigDecimal newDenom = new BigDecimal(0);
		BigDecimal root2ApproxNumer = new BigDecimal(0);
		BigDecimal root2ApproxDenom = new BigDecimal(0);
		int count = 0;
		
		for(int e = 2; e <= 1000; e++) {
			newNumer = prevDenom;
			newDenom = prevDenom.multiply(TWO).add(prevNumer);
			
			root2ApproxNumer = newDenom.add(newNumer);
			root2ApproxDenom = newDenom;
			
			if(root2ApproxNumer.toPlainString().length() > root2ApproxDenom.toPlainString().length()) {
				count++;
			}
			prevNumer = newNumer;
			prevDenom = newDenom;
		}
		
		System.out.println("The number of fractions where the numerator has more digits than the denominator is " + count);
	}

}
