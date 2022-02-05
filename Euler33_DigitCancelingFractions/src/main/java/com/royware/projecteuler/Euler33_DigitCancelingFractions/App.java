package com.royware.projecteuler.Euler33_DigitCancelingFractions;

/**
The fraction 49/98 is a curious fraction, as an inexperienced mathematician in attempting to simplify it may incorrectly believe that 49/98 = 4/8,
which is correct, is obtained by canceling the 9s.

We shall consider fractions like, 30/50 = 3/5, to be trivial examples.

There are exactly four non-trivial examples of this type of fraction, less than one in value, and containing two digits in the numerator and denominator.

If the product of these four fractions is given in its lowest common terms, find the value of the denominator.
 */
public class App {
    public static void main( String[] args ) {
    	int[] canceledFrac = new int[2];
    	int prodNumer = 1;
    	int prodDenom = 1;
    	
    	for(int i = 11; i <= 98; i++) {
    		for(int j = i + 1; j <= 99; j++) {
    			//Check for an equal, non-zero digits between numerator and denominator,
    			//then compare the fractional value as-is and as a new fraction formed
    			//by "canceling" the equal digits in the numerator and denominator
    			//If the values are equal (within a very small tolerance), store the denominator
    			canceledFrac = getCanceledFrac(i, j);
    			if(canceledFrac[0] > 0 && canceledFrac[1] > 0) {
    				if(equalFractions(canceledFrac[0], canceledFrac[1], i, j)) {
    					System.out.println("Found " + i + " / " + j);
    					prodNumer *= i;
    					prodDenom *= j;
    				}
    			}
    		}
    	}
    	
    	System.out.println("The final fraction is " + prodNumer + " / " + prodDenom + ", decimal = " + ((double)prodNumer / prodDenom));
    }
    
    public static int[] getCanceledFrac(int num, int den) {
    	int[] numerDigits = new int[2];
    	int[] denomDigits = new int[2];
    	int[] newNumDen = {0, 0};
    	
    	numerDigits = getDigits(num);
    	denomDigits = getDigits(den);
    	
    	boolean foundMatch = false;
    	for(int i = 0; i < 2; i++) {
    		for(int j = 0; j < 2; j++) {
    	    	if(!(numerDigits[i] == 0 && denomDigits[j] == 0) && !foundMatch) {
    		    	if(numerDigits[i] == denomDigits[j]) {
    		    		newNumDen[0] = numerDigits[i == 0 ? 1 : 0];
    		    		newNumDen[1] = denomDigits[j == 0 ? 1 : 0];
    		    		foundMatch = true;
    		    	}
    	    	}
    		}
    	}
    	
    	return newNumDen;
    }
    
    public static int[] getDigits(int num) {
    	int[] digits = new int[2];
    	
    	int i = 0;
    	while(num > 0) {
    		digits[i] = num % 10;
    		num /= 10;
    		i++;
    	}
    	
    	return digits;
    }
    
    public static boolean equalFractions(double n1, double d1, double n2, double d2) {
    	if(Math.abs((n1/d1) - (n2/d2)) <= 0.000000001) {
    		return true;
    	}
    	return false;
    }
}
