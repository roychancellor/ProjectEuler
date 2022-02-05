/**
 * We shall say that an n-digit number is pandigital if it makes use of all the digits 1 to n exactly once. For example, 2143 is a 4-digit pandigital and is also prime.

What is the largest n-digit pandigital prime that exists?
 */
package projecteuler;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class PandigitalPrime {
	public static void main(String[] args) {
		//Turns out that the only pandigital primes have 4 or 7 digits, so only check the 7 digit numbers
		//See https://nekedome-project-euler.readthedocs.io/en/latest/solutions/41.html for an explanation
		int max = 0;
		int numDigits = 7;
		for(int val = 1234567; val <= 7654321; val += 2) {
			//numDigits = 7; //getNumDigits(val);
			if(isPandigital(digitsToSet(val, numDigits), numDigits) && isPrime(val)) {
				System.out.println("Found a pandigital prime: " + val);
				if(val > max) {
					max = val;
				}
			}
		}
		
		System.out.println("The maximum pandigital prime is " + max);
	}
	
	public static int getNumDigits(int val) {
		if(val < 10) {
			return 1;
		} else if(val < 100) {
			return 2;
		} else if(val < 1000) {
			return 3;
		} else if(val < 10000) {
			return 4;
		} else if(val < 100000) {
			return 5;
		} else if(val < 1000000) {
			return 6;
		} else if(val < 10000000) {
			return 7;
		} else if(val < 100000000) {
			return 8;
		} else if(val < 1000000000) {
			return 9;
		}
		return -1;
	}
	
	//Determines if a set of digits represents a 1-n pandigital number
	public static boolean isPandigital(Set<Integer> digits, int n) {		
		if(n >= 2 && n <= 9 && digits != null && digits.size() == n) {
			Integer[] checkArray = new Integer[n];
			for(Integer i = 1; i <= n; i++) {
				checkArray[i - 1] = i;
			}
			Set<Integer> panCheck = new HashSet<Integer>(Arrays.asList(checkArray));
			
			//Check for complete intersection of the set of digits and the panCheck set
			return (digits.containsAll(panCheck) && panCheck.containsAll(digits));
		} else {
			return false;
		}
	}
	
	//Converts a number into a set of numbers for testing if it is pandigital 1-n
	//Optimizes by stopping the process when a duplicate digit occurs, since that automatically won't work
	//(multiplying by 1 gives the same number which, if it has duplicate digits, is automatically not pandigital)
	public static Set<Integer> digitsToSet(int val, int n) {
		Set<Integer> digitSet = new HashSet<Integer>();
		int digit = 0;
		while(val > 0) {
			digit = val % 10;
			if(digit >= 1 && digit <= n && !digitSet.contains((Integer)digit)) {
				digitSet.add(digit);
			}
			else {
				return null;
			}
			val /= 10;
		}
		
		return digitSet;
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
