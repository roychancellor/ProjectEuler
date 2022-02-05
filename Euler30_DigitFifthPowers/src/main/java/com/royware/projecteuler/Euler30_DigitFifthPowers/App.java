package com.royware.projecteuler.Euler30_DigitFifthPowers;

import java.util.ArrayList;
import java.util.List;

/**
 Surprisingly there are only three numbers that can be written as the sum of fourth powers of their digits:

1634 = 1^4 + 6^4 + 3^4 + 4^4
8208 = 8^4 + 2^4 + 0^4 + 8^4
9474 = 9^4 + 4^4 + 7^4 + 4^4
As 1 = 1^4 is not a sum it is not included.

The sum of these numbers is 1634 + 8208 + 9474 = 19316.

Find the sum of all the numbers that can be written as the sum of fifth powers of their digits.
 */
public class App {
    public static void main( String[] args ) {
    	List<Integer> powDigits = new ArrayList<Integer>();
    	//Generate test numbers and compute the sum of fifth powers of their digits
    	for(int n = 2; n <= 30000000; n++) {
    		if(sumPowers(n, 5) == n) {
    			System.out.println("Found one! It is " + n);
    			powDigits.add(n);
    		}
    	}
    	
    	long sum = 0;
    	for(int i = 0; i < powDigits.size(); i++) {
    		sum += powDigits.get(i);
    	}
    	
    	System.out.println("The sum of the numbers that can be written as sum of fifth powers of digits is " + sum);
    }
    
    public static long sumPowers(int n, int exponent) {
    	long sumPow = 0;
    	while(n > 0) {
    		sumPow += Math.pow(n % 10, exponent);
    		n /= 10;
    	}
    	
    	return sumPow;
    }
}
