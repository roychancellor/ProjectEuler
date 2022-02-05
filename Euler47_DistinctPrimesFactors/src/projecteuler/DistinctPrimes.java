/**
 * The first two consecutive numbers to have two distinct prime factors are:

14 = 2 × 7
15 = 3 × 5

The first three consecutive numbers to have three distinct prime factors are:

644 = 2² × 7 × 23
645 = 3 × 5 × 43
646 = 2 × 17 × 19.

Find the first four consecutive integers to have four distinct prime factors each. What is the first of these numbers?
 */

package projecteuler;

import java.util.HashSet;
import java.util.Set;

public class DistinctPrimes {

	public static void main(String[] args) {
		for(int n = 10; n <= 1000000; n++) {
			if(!(isPrime(n) || isPrime(n + 1) || isPrime(n + 2) || isPrime(n + 3))) {
				if(numberOfDistinctPrimeFactors(n) == 4 &&
						numberOfDistinctPrimeFactors(n + 1) == 4 &&
						numberOfDistinctPrimeFactors(n + 2) == 4 &&
						numberOfDistinctPrimeFactors(n + 3) == 4) {
					System.out.println("Found them: " + n + ", " + (n + 1) + ", " + (n + 2) + ", " + (n + 3));
					break;
				}
			}
		}
	}
	
    public static int numberOfDistinctPrimeFactors(long numToFactor) {
//        System.out.println("FACTORING " + numToFactor);
    	Set<Long> distinctFactors = new HashSet<Long>();
        //While the number to factor is even, store 2 in the factor set and divide the number by 2
        while(numToFactor % 2 == 0) {
        	distinctFactors.add(2L);
//    		System.out.println("numToFactor: " + numToFactor + ", factor: " + 2);
    		numToFactor /= 2L;
        }
        
        if(isPrime(numToFactor)) {
//    		System.out.println("numToFactor: " + numToFactor + ", factor: " + 2);
        	distinctFactors.add(numToFactor);
        	return distinctFactors.size();
        } else {
	        //Continue to prime-factor the number
	        //Only need to check for odd factors because it has been divided by 2 as many times as the quotient was even
	        //Only need to test for factors up to the square root of the test value
//	        long maxFac=(long)Math.ceil(Math.sqrt(numToFactor));
	        long i = 3;
	        while(numToFactor > 1) {
	        	while(numToFactor % i == 0) {
//	        		System.out.println("numToFactor: " + numToFactor + ", factor: " + i);
	        		distinctFactors.add(i);
	        		numToFactor /= i;
	        	}
	            i += 2;
	        }
        }
        return distinctFactors.size();
    }

	public static boolean isPrime(long testVal) {
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

}
