/**
 * Euler discovered the remarkable quadratic formula: n2+n+41

It turns out that the formula will produce 40 primes for the consecutive integer values 0≤n≤39. However, when n=40,402+40+41=40(40+1)+41
is divisible by 41, and certainly when n=41,412+41+41,. The incredible formula n2−79n+1601 was discovered, which produces 80 primes for the consecutive values 0≤n≤79.
The product of the coefficients, −79 and 1601, is −126479. Considering quadratics of the form: n2+an+b, where |a|<1000 and |b|≤1000,
find the product of the coefficients, a and b, for the quadratic expression that produces the maximum number of primes for consecutive values of n, starting with n=0
 */
package com.royware.projecteuler.Euler27_QuadraticPrimes;

public class App 
{
    public static void main( String[] args ) {
        long startTime = System.currentTimeMillis();
    	
    	QuadraticPrimes qp = new QuadraticPrimes();
        int maxPrimes = 0;
        int aMaxPrimes = 0;
        int bMaxPrimes = 0;
        for(int a = -999; a < 1000; a++) {
        	for(int b = -997; b <= 1000; b++) {
        		if(qp.isPrime(b)) {
	        		int n = qp.numConsecPrimes(a, b);
//	    			System.out.println("a = " + a + ", b = " + b + ", n = " + n);
	        		if(n > maxPrimes) {
	        			maxPrimes = n;
	        			aMaxPrimes = a;
	        			bMaxPrimes = b;
	        		}
        		}
        	}
        }
        System.out.println("Elapsed time: " + ((System.currentTimeMillis() - startTime) / 1000.0) + " s");
        System.out.println("The maximum number of primes is n = " + maxPrimes + " with a = " + aMaxPrimes + " and b = " + bMaxPrimes);
        System.out.println("The product of coefficients is a*b = " + (aMaxPrimes * bMaxPrimes));
    }
}
