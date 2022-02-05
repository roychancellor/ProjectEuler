/**
 * The prime 41, can be written as the sum of six consecutive primes:

41 = 2 + 3 + 5 + 7 + 11 + 13
This is the longest sum of consecutive primes that adds to a prime below one-hundred.

The longest sum of consecutive primes below one-thousand that adds to a prime, contains 21 terms, and is equal to 953.

Which prime, below one-million, can be written as the sum of the most consecutive primes?
 */

package projecteuler;

import java.util.ArrayList;
import java.util.List;

import royutils.RoyUtils;

public class ConsecPrimes {

	public static void main(String[] args) {
		int n = 3;
		long sumOfPrimes = 2;
		boolean keepAdding = true;
		List<Long> sums = new ArrayList<Long>();
		sums.add(2L);
//		System.out.println("n: " + (n - 1) + ", sumOfPrimes: " + sumOfPrimes);
		//Make and store all consecutive sums of primes
		while(keepAdding) {
			if(RoyUtils.isPrime(n)) {
				sumOfPrimes += n;
				if(sumOfPrimes < 1000000) {
					sums.add(sumOfPrimes);
//					System.out.println("n: " + n + ", sumOfPrimes: " + sumOfPrimes);
				} else {
					keepAdding = false;
				}
			}
			n += 2;
		}
		
		//Start with the largest sum of consecutive primes. If it is already prime, print it.
		//If not, begin testing the largest sum reduced by the smallest sum, next smallest, and so on until a prime is found.
		//This will be the prime made with the most consecutive primes
		int terms = sums.size();
		long sum = sums.get(terms - 1);
//		System.out.println("Starting sum: " + sum);
		int i = 0;
		while(i <= terms - 2 && !RoyUtils.isPrime(sum - sums.get(i))) {
			System.out.println("i: " + i + ", sum: " + (sum - sums.get(i)));
			i++;
		}
		System.out.println("i: " + i + ", sum: " + (sum - sums.get(i)));
		System.out.println("The prime below one-million with the most consecutive prime factors is " + (sum - sums.get(i)) + " with " + (terms - i) + " consecutive primes.");
	}

}
