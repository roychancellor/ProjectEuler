/**
 * The arithmetic sequence, 1487, 4817, 8147, in which each of the terms increases by 3330, is unusual in two ways: (i) each of the three terms are prime,
 * and, (ii) each of the 4-digit numbers are permutations of one another.

There are no arithmetic sequences made up of three 1-, 2-, or 3-digit primes, exhibiting this property, but there is one other 4-digit increasing sequence.

What 12-digit number do you form by concatenating the three terms in this sequence?
 */

package projecteuler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import royutils.RoyUtils;

public class PrimePermutations {

	public static void main(String[] args) {
		for(int n = 1001; n <= 9999; n += 2) {
			if(n != 1487 && RoyUtils.isPrime(n)) {  //1487 is the example, so don't check it - we know it works
				int upLim = 9999 - n;
				for(int d = 1000; d <= upLim; d += 10) {
					int n1d = n + d;
					int n2d = n + 2 * d;
					boolean allArePrime = RoyUtils.isPrime(n1d) && RoyUtils.isPrime(n2d);
					if(allArePrime) {
						boolean allArePermutations = isPermutation(n, n1d) && isPermutation(n, n2d) && isPermutation(n1d, n2d);
						if(allArePermutations) {
							System.out.println("Found them: " + n + n1d + n2d);
							n = 10000;
							break;
						}
					}
				}
			}
		}
	}

	public static boolean isPermutation(int a, int b) {
		//Two numbers are permutations of each other if they are unequal, have the same number of digits, and have identical digits
		//Put the digits of each number into lists, sort the lists, and compare the lists for equality
		if(a != b && (int)Math.log10(a) == (int)Math.log10(b)) {
			List<Integer> aList = digitsToSortedList(a);
			List<Integer> bList = digitsToSortedList(b);
			
			for(int i = 0; i < aList.size(); i++) {
				if(aList.get(i) != bList.get(i)) {
					return false;
				}
			}
			return true;
		}
		
		return false;
	}
	
	public static List<Integer> digitsToSortedList(int n) {
		List<Integer> digits = new ArrayList<Integer>();
		
		while(n > 0) {
			digits.add(n % 10);
			n /= 10;
		}
		Collections.sort(digits);
		return digits;
	}
}
