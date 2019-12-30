package com.royware.euler;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Euler26_LongestCycle {
	
	public static void main(String[] args) {
		BigDecimal fraction;
		BigDecimal one = BigDecimal.ONE;
		BigDecimal denom = new BigDecimal("7");
		long maxDenom = 1;
		int cycleLength = 0;
		int maxCycleLength = 0;
		int precision = 3002;
		
		long startTime = System.currentTimeMillis();
		for(int d = 7; d < 1000; d++) {
			if(isPrime(d)) {
				denom = new BigDecimal(d);
				fraction = one.divide(denom, precision, RoundingMode.HALF_UP);
//				System.out.println("1/" + denom.toString());
				
				//Find the cycle length			
				cycleLength = findCycleLength(fraction.toString().substring(12));
				
				//Check the current cycle length against the maximum cycle length
				if(cycleLength > maxCycleLength) {
					maxCycleLength = cycleLength;
					maxDenom = denom.longValue();
				}
			}
		}
		long elapsedTime = System.currentTimeMillis() - startTime;
		System.out.println("The denominator with the maximum cycle length is " + maxDenom + " and has cycle length " + maxCycleLength);
		System.out.println("Took " + (elapsedTime / 1000.0) + " s");
	}
	
	public static int findCycleLength(String digitsToRight) {
		int cycleLength = 0;
		int startIndex = 0;
		boolean cycleValid = false;
		int numTries = 0;
		while(!cycleValid && numTries < 10) {
			//Find candidate pattern (NOT guaranteed to find the actual pattern the first time due to repeating digits, e.g. 00444455555444455555...)
			cycleLength = 1;
			int indexNextStartChar = startIndex;
			for(int i = 1; i < 100; i++) {
				indexNextStartChar = digitsToRight.indexOf(digitsToRight.charAt(startIndex), indexNextStartChar + 1);
				cycleLength = indexNextStartChar - startIndex;
				if(digitsToRight.substring(startIndex, indexNextStartChar).equals(digitsToRight.substring(indexNextStartChar, indexNextStartChar + cycleLength))) {
					break;
				}
			}
			
			//3. Test the candidate cycle
			cycleValid = testCandidateCycle(digitsToRight, startIndex, cycleLength);
			
			if(!cycleValid) {
				//Eliminate leading two digits and look again
				digitsToRight = digitsToRight.substring(2);
			}
			numTries++;
		}
	
		return cycleLength;
	}
	
	public static boolean testCandidateCycle(String testStr, int startIndex, int cycleLength) {
		//The candidate cycle must repeat successively three times to pass
		String testCycle = testStr.substring(startIndex, startIndex + cycleLength);
		for(int i = 0; i < 3; i++) {
			if(!testStr.substring(startIndex + i * cycleLength, startIndex + i * cycleLength + cycleLength).equals(testCycle)) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean isPrime(int testVal) {
        int upperTestLimit = (int)Math.sqrt(testVal);
        //because each time a factor < sqrt(testVal) is found, a factor > sqrt(testVal) is also found

        if(testVal % 2 != 0 && testVal >= 3) {  //look for factors of the test value when it is NOT even and odd >= 3
            for(int i = 3; i <= upperTestLimit; i += 2 )  //i += 2:  don't check even numbers for being a factor
                if(testVal % i == 0)
                    return false;  //a factor was found, so NOT prime
        }
        else if(testVal == 1)  //1 is not prime
            return false;
        else if(testVal == 2)  //2 is prime
            return true;
        return true;  //NO factors found, so test value is prime
        //otherwise the return would have been from the if block
    }
	
}