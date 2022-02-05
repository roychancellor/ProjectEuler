/**
 * It is not until n=23, that a value exceeds one-million: (23C10)=1144066
 * How many, not necessarily distinct, values of nCr for 1 ≤ n ≤ 100 are greater than one-million?
 */
package projecteuler;

import java.math.BigDecimal;

import royutils.RoyUtils;

public class Combos {
	public static void main(String[] args) {
		int count = 0;
		final BigDecimal ONE_MILLION = new BigDecimal(1000000);
		BigDecimal combos = new BigDecimal(1);
		for(int n = 23; n <= 100; n++) {
			for(int r = 1; r <= n; r++) {
				combos = RoyUtils.numCombos(n, r);
				if(combos.compareTo(ONE_MILLION) > 0) {
//					System.out.println("(n,r): (" + n + "," + r + "): " + combos.toString());
					count++;
				}
			}
		}
		System.out.println("The count of combos > 1,000,000 is " + count);
	}

}
