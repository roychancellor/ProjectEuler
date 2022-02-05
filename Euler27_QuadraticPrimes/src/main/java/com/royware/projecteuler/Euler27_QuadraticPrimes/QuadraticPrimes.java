package com.royware.projecteuler.Euler27_QuadraticPrimes;

public class QuadraticPrimes {
	
	public long computeQuadratic(int a, int b, int n) {
		return n * (n + a) + b;
	}
	
	public boolean isPrime(long testVal) {
		if(testVal < 0) {
			testVal = Math.abs(testVal);
		}
        long upperTestLimit = (long)Math.sqrt(testVal);
        //because each time a factor < sqrt(testVal) is found, a factor > sqrt(testVal) is also found

        if(testVal % 2 != 0) {  //only test ODD values for primeness
	        if(testVal >= 3) {  //only look for factors of test values >= 3
	            for(long i = 3; i <= upperTestLimit; i += 2 )  //i += 2:  don't check even numbers for being a factor
	                if(testVal % i == 0)
	                    return false;  //a factor was found, so NOT prime
	        }
	        else if(testVal == 1)  //1 is not prime
	            return false;
	    }
        else { //test value is EVEN
	        if(testVal == 2)  //2 is prime
	            return true;
	        else  //evens are not prime
	        	return false;
        }
        return true;  //NO factors up to the square root of the test value were found, so test value is prime
        //otherwise the return would have been from the if block
	}
	
	public int numConsecPrimes(int a, int b) {
		int n = 0;
		while(isPrime(computeQuadratic(a, b, n))) {
			n++;
		}
		return n;
	}
}
