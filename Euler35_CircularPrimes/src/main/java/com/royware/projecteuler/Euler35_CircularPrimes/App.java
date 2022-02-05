package com.royware.projecteuler.Euler35_CircularPrimes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
The number, 197, is called a circular prime because all rotations of the digits: 197, 971, and 719, are themselves prime.

There are thirteen such primes below 100: 2, 3, 5, 7, 11, 13, 17, 31, 37, 71, 73, 79, and 97.

How many circular primes are there below one million?
 */
public class App {
    public static void main( String[] args ) {
    	int stopVal = 1000000;
    	int countCircularPrimes = 0;
    	long start = System.currentTimeMillis();
    	
    	for(int i = 101; i < stopVal; i+= 2) {  //evens are never prime, so don't bother checking
    		if(isCircularPrime(i)) {
    			countCircularPrimes++;
//    			System.out.println("Found one: " + i);
    		}
    	}
    	countCircularPrimes += 13; //given there are 13 below 100
    	long elapsed = System.currentTimeMillis() - start;
    	System.out.println("The number of circular primes below 1,000,000 is " + countCircularPrimes);
    	System.out.println("Elapsed time = " + elapsed + " ms");
    }
    
    public static boolean isCircularPrime(int val) {
    	//Get all the digits of the number being tested
    	//If ANY digit is even, return false because one of the rotations would be even, hence not prime
    	List<Integer> digitsOfVal = getDigits(val);
    	
    	//The test value has now passed the digits-not-even test, so test the value for primeness
    	//If it is prime, test its rotations
    	if(digitsOfVal != null && isPrime(val)) {
//    		System.out.println("Testing rotations of " + val);
    		for(int r = 1; r < digitsOfVal.size(); r++) {
//    			System.out.println("Rotation: " + r);
    			int testVal = getRotation(digitsOfVal, r);
//    			System.out.print("testVal " + testVal + " ");
    			if(!isPrime(testVal)) {
//    				System.out.println("is NOT prime...break");
    				return false;
    			}
//    			System.out.println("IS prime...keep going");
    		}
    		return true;
    	}
    	
    	return false;
    }

	public static List<Integer> getDigits(int val) {
		List<Integer> digitsOfVal = new ArrayList<Integer>();;
    	int i = 0;
    	int digits = val;
    	while(digits > 0) {
    		digitsOfVal.add(digits % 10);
    		if(digitsOfVal.get(i) % 2 == 0 || digitsOfVal.get(i) % 5 == 0) {
    			return null;
    		}
    		digits /= 10;
    		i++;
    	}
    	
    	//Reverse the digits so the first element is the highest-order digit, etc.
    	Collections.reverse(digitsOfVal);
		return digitsOfVal;
	}
    
    public static int getRotation(List<Integer> digits, int rotationNumber) {
    	int rotatedVal = 0;
    	List<Integer> rotatedDigits = new ArrayList<Integer>();
    	
    	//The nth rotation takes the first n digits and moves them to the end
    	//where n goes from 1 to N - 1, where N is the total number of digits
//    	System.out.print("\nDigits of val: ");
//    	for(int x : digits) {
//    		System.out.print(x + " ");
//    	}
//    	System.out.println("\nBack part of rotation: ");
    	rotatedDigits.addAll(digits.subList(rotationNumber, digits.size()));
//    	for(int x : rotatedDigits) {
//    		System.out.print(x + " ");
//    	}
//    	System.out.println("\nComplete rotation: ");
    	rotatedDigits.addAll(digits.subList(0, rotationNumber));
//    	for(int x : rotatedDigits) {
//    		System.out.print(x + " ");
//    	}
    	
    	//Make a new number from the rotated digits
    	int numDigits = rotatedDigits.size();
    	for(int i = 0; i < numDigits; i++) {
    		rotatedVal += rotatedDigits.get(i) * Math.pow(10, numDigits - 1 - i);
    	}
    	
    	return rotatedVal;
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
