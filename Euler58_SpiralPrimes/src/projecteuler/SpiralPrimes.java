/**
 * Starting with 1 and spiraling anticlockwise in the following way, a square spiral with side length 7 is formed.

37 36 35 34 33 32 31
38 17 16 15 14 13 30
39 18  5  4  3 12 29
40 19  6  1  2 11 28
41 20  7  8  9 10 27
42 21 22 23 24 25 26
43 44 45 46 47 48 49

It is interesting to note that the odd squares lie along the bottom right diagonal, but what is more interesting is that 8 out of the 13 numbers
lying along both diagonals are prime; that is, a ratio of 8/13 ≈ 62%.

If one complete new layer is wrapped around the spiral above, a square spiral with side length 9 will be formed.
If this process is continued, what is the side length of the square spiral for which the ratio of primes along both diagonals
first falls below 10%?
 */

 

import java.util.ArrayList;
import java.util.List;

import royutils.RoyUtils;

public class SpiralPrimes {

	public static void main(String[] args) {
		//Upper right diagonal: delta pattern: +2, +10, +18 (delta goes up by 8). n_i+1 = n_i + (2 + 8*i); i = 0, 1, 2, ... and n_0 = 1
		//The numbers in the diagonals go up by an amount that increases by 8 for each new ring:
		//Q1: 1, 3, 13, 31, ...
		//Q2: 1, 5, 17, 37, ...
		//Q3: 1, 7, 21, 43, ...
		//Q4: 1, 9, 25, 49, ...
		//For each quadrant, the number added to 1 is an even number: 2, 4, 6, 8, then 10, 12, 14, 16, etc.
		//So, the Q1 diagonal is: n = 1, D(1) = 3; n = 2; D(2) = 13 (+10); n = 3, D(3) = 31 (+18), ..., n = N, D(N) = 3 + 8*(N - 1)
		//D(1) = 3
		//D(2) = 3 + 2 + 8 = 13
		//D(3) = 13 + 2 + 2*8 = 31
		//D(4) = 31 + 2 + 3*8 = 57
		//D(n) = D(n - 1) + 2 + (n - 1)*8
		//The Q2, Q3, and Q4 diagonals are found from Q1. DQ2(n) = DQ1(n) + a(n); DQ3(n) = DQ1(n) + 2*a(n); DQ4(n) = DQ1(n) + 3*a(n)
		//where a(n) = s(n) - 1 and s(n) = 2*n + 1, so a(n) = 2*n, so DQ2(n) = DQ1(n) + 2*n; DQ3(n) = DQ1(n) + 4*n; DQ4(n) = DQ1(n) + 6*n
		//number of values along diagonals = 1 + 4*n
		
		long denom = 1;
		long numer = 0;
		long numerPrev = 0;
		long q1DiagCur = 0;
		long q1DiagPrev = 1;
		long q2DiagCur = 0;
		long q3DiagCur = 0;
		long q4DiagCur = 0;
		List<Long> diagonals = new ArrayList<>();
		for(int n = 1; n <= 50000; n++) {
			denom = 1 + 4*n;
			q1DiagCur = q1DiagPrev + 2 + 8*(n-1);
			q2DiagCur = q1DiagCur + 2*n;
			q3DiagCur = q1DiagCur + 4*n;
			q4DiagCur = q1DiagCur + 6*n;
			diagonals.add(q1DiagCur);
			diagonals.add(q2DiagCur);
			diagonals.add(q3DiagCur);
			diagonals.add(q4DiagCur);
			numer = numerPrev + numberOfPrimes(diagonals);
//			System.out.println("n = " + n + ": 10*numer: " + (10*numer) + ", denom: " + denom);
			if((10 * numer) < denom) {
				System.out.println("The side length is: " + (2*n+1));
				break;
			}
			q1DiagPrev = q1DiagCur;
			numerPrev = numer;
			diagonals.clear();
		}
	}
	
	private static long numberOfPrimes(List<Long> diagonals) {
		long count = 0;
		for(Long d : diagonals) {
			if(RoyUtils.isPrime(d)) {
				count++;
			}
		}
		return count;
	}

}
