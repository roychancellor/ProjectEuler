package com.royware.projecteuler.Euler37_TruncatablePrimes;

import java.util.ArrayList;

/**
The number 3797 has an interesting property. Being prime itself, it is possible to continuously remove digits from left to right,
and remain prime at each stage: 3797, 797, 97, and 7. Similarly we can work from right to left: 3797, 379, 37, and 3.

Find the sum of the only eleven primes that are both truncatable from left to right and right to left.

NOTE: 2, 3, 5, and 7 are not considered to be truncatable primes. */
public class App {
    public static void main( String[] args ) {
    	long start = System.currentTimeMillis();
    	int numTruncPrimes = 0;
    	int num = 11;
    	long sumTruncPrimes = 0;
    	ArrayList<Integer> subNumbers = new ArrayList<Integer>();
    	
    	while(numTruncPrimes < 11) { //problem statement says there are only 11
    		//Get and store all digits of the test number
    		//If ANY digit is is even, move to the next number (during the digit removal, even numbers will result which are never prime)
    		if(isPrime(num)) {
	    		if((subNumbers = makeRightToLeft(num, 10)) != null && isTruncatablePrime(subNumbers)) {
	    			subNumbers.clear();
	    			if((subNumbers = makeLeftToRight(num)) != null && isTruncatablePrime(subNumbers)) {
//		    			System.out.println("Found one: " + num);
		    			sumTruncPrimes += num;
		    			numTruncPrimes++;
	    			}
	    		}
    		}

    		num += 2; //only begin testing odd numbers because even numbers are never prime.
    	}
    	
    	System.out.println("The sum of the 11 truncatable primes is " + sumTruncPrimes);
    	System.out.println("Elapsed: " + (System.currentTimeMillis() - start) + " ms");
    }
    
//Receives the digits of a known-prime number and determines if it is truncatable
    public static boolean isTruncatablePrime(ArrayList<Integer> subNumbers) {
    	//Check sub-numbers until a non-prime is found, then return false
		//If none is found, then all are prime -> return true
    	for(int x : subNumbers) {
    		if(!isPrime(x)) {
    			return false;
    		}
    	}
    	
    	return true;
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

    //Converts a decimal number into its sub numbers right to left (e.g. 3797 -> 379 -> 37 -> 3)
	//returns null if any digit is even (unique to this problem)
    public static ArrayList<Integer> makeRightToLeft(int num, int base) {
    	ArrayList<Integer> subNumber = new ArrayList<Integer>();
    	
    	while((num / base) > 0) {
    		if((num % base) % 2 == 0) {
    			return null;
    		}
    		subNumber.add(num / base);
    		num /= base;
    	}
    	
    	return subNumber;
    }

    //Converts a decimal number into its sub numbers left to right (e.g. 3797 -> 797 -> 97 -> 7)
	//returns null if any digit is even (unique to this problem)
    public static ArrayList<Integer> makeLeftToRight(int num) {
    	ArrayList<Integer> subNumber = new ArrayList<Integer>();
    	
    	int mod = (int)(Math.pow(10, Math.ceil(Math.log10(num)) - 1));
    	while(mod > 1) {
//    		if((num % base) == 0) {
//    			return null;
//    		}
    		subNumber.add(num % mod);
    		mod /= 10;
    	}
    	
    	return subNumber;
    }
}
