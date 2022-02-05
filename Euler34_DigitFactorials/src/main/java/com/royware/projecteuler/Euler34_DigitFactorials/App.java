package com.royware.projecteuler.Euler34_DigitFactorials;

/**
 145 is a curious number, as 1! + 4! + 5! = 1 + 24 + 120 = 145.

Find the sum of all numbers which are equal to the sum of the factorial of their digits.

Note: as 1! = 1 and 2! = 2 are not sums they are not included.
 */
public class App {
	private static int[] fact = {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880};
	
    public static void main( String[] args ) {
    	long sum = 0;
    	long start = System.currentTimeMillis();
    	
    	for(int i = 10; i < 100000; i++) {
    		if(sumOfFactorialOfDigits(i) == i) {
    			System.out.println("Found one: " + i);
    			sum += i;
    		}
    	}
    	System.out.println("The sum of all numbers equal to sum of factorials of their digits is " + sum);
    	System.out.println("Elapsed time: " + (System.currentTimeMillis() - start) + " ms");
    }
    
    public static long sumOfFactorialOfDigits(int val) {
    	long sumFactDigits = 0;
    	int digits = val;
    	
    	while(digits > 0) {
    		long fac = fact[digits % 10];
    		sumFactDigits += fac;
    		if(fac > val || sumFactDigits > val) {
    			return 0;
    		}
    		digits /= 10;
    	}
    	
    	return sumFactDigits;
    }
}
