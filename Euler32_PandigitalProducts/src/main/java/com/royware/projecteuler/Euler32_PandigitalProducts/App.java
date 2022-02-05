package com.royware.projecteuler.Euler32_PandigitalProducts;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 We shall say that an n-digit number is pandigital if it makes use of all the digits 1 to n exactly once; for example, the 5-digit number, 15234, is 1 through 5 pandigital.

The product 7254 is unusual, as the identity, 39 × 186 = 7254, containing multiplicand, multiplier, and product is 1 through 9 pandigital.

Find the sum of all products whose multiplicand/multiplier/product identity can be written as a 1 through 9 pandigital.

HINT: Some products can be obtained in more than one way so be sure to only include it once in your sum.
 */
public class App {
	private static int LOWPAN = 1;
	private static int HIGHPAN = 9;
    
	public static void main( String[] args ) {
        long start = System.currentTimeMillis();
		Set<Integer> pandigitals = new HashSet<Integer>();
        Set<Integer> fac1Digits = new HashSet<Integer>();
        Set<Integer> fac2Digits = new HashSet<Integer>();
        Set<Integer> prodDigits = new HashSet<Integer>();
        
    	//Make first factor and validate it
    	for(int f1 = 2; f1 <= 99; f1++) {
    		int numDig = digitsToSet(f1, fac1Digits);
    		if(numDig > 0 && ((fac1Digits.size() == 1 && f1 < 10) || (fac1Digits.size() == 2 && f1 >= 10))) {
	    		//Make second factor and validate it
	    		for(int f2 = 134; f2 <= 9999; f2++) {
	        		numDig = digitsToSet(f2, fac2Digits);
	        		if(numDig > 0 && ((fac2Digits.size() == 3 && f2 < 1000) || (fac2Digits.size() == 4 && f2 >= 1000))) {
	    				//Make product and validate it
	        			int prod = f1*f2;
	    				numDig = digitsToSet(prod, prodDigits);
		        		if(numDig > 0 && (prodDigits.size() == 4 && prod < 10000)) {
		        			//factors and product are valid, now check to see if the intersection of their digits is pandigital
			    			if(isPandigital(fac1Digits, fac2Digits, prodDigits)) {
			    				System.out.println(f1 + " * " + f2 + " = " + prod);
			    				pandigitals.add(prod);
			    			}
		        		}
	    			}
	        		fac2Digits.clear();
	        		prodDigits.clear();
	    		}
    		}
    		fac1Digits.clear();
        }
    	
    	//Find the sum of the pandigital products
    	long panSum = 0;
    	Iterator<Integer> iter = pandigitals.iterator();
    	while(iter.hasNext()) {
    		panSum += iter.next();
    	}
    	
    	System.out.println("The sum of all 1 to 9 pandigital products = " + panSum);
    	System.out.println("Elapsed time = " + (System.currentTimeMillis() - start) + " ms");
    }
    
	public static boolean isPandigital(Set<Integer> fac1Digits, Set<Integer> fac2Digits, Set<Integer> prodDigits) {		
		Set<Integer> panCheck = new HashSet<Integer>();
		int f1Digits = 0, f2Digits = 0, pDigits = 0;
		int checkSum = LOWPAN + HIGHPAN - 1;
		
		f1Digits = fac1Digits.size();
		f2Digits = fac2Digits.size();
		pDigits = prodDigits.size();
		boolean c1 = f1Digits == 1 && f2Digits == 4 && pDigits == 4;
		boolean c2 = f1Digits == 2 && f2Digits == 3 && pDigits == 4;
		
		//Check for no intersection of the three sets
		Set<Integer> intersect = new HashSet<Integer>(fac1Digits);
		intersect.retainAll(fac2Digits);  //gives intersection of factor 1 digits and factor 2 digits
		intersect.retainAll(prodDigits);  //gives intersection of factor 1 digits, factor 2 digits, and the product digits
		if(intersect.size() == 0) {  //then all digits are unique
			panCheck.addAll(fac1Digits);
			panCheck.addAll(fac2Digits);
			panCheck.addAll(prodDigits);
			
			return ((panCheck.size() == checkSum) && (c1 || c2));
		}
		else {
			return false;
		}
	}
	
	public static int digitsToSet(int val, Set<Integer> set) {
		int numDigits = 0;
		int digit = 0;
		while(val > 0) {
			digit = val % 10;
			if(digit >= LOWPAN && digit <= HIGHPAN) {
				set.add(digit);
				numDigits++;
			}
			else {
				return 0;
			}
			val /= 10;
		}
		
		return numDigits;
	}
}
