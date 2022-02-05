package com.royware.projecteuler.Euler36_DoubleBasePalindromes;

import java.util.ArrayList;

/**
The decimal number, 585 = 1001001001 base 2 (binary), is palindromic in both bases.

Find the sum of all numbers, less than one million, which are palindromic in base 10 and base 2.

(Please note that the palindromic number, in either base, may not include leading zeros.)
 */
public class App {
    public static void main( String[] args ) {
    	//0, 1, 2,...,9 are palindromes by definition (but do not include 0 per instructions???)
    	
    	//Iterate through decimal numbers from 1 to 999,999 and check FIRST that the decimal number is palindromic
    	//If so, create and check the binary representation for palindromicity
    	//Powers of 2 are automatically not palindromic in binary - UNUSED YET
    	long start = System.currentTimeMillis();
    	int sumPal = 0;
    	for(int n = 1; n < 1000000; n+=2) {  //only check odds because evens will never be base 2 palindromic (will be 1...0)
    		if(isPalindrome(numberToDigits(n, 10)) && isPalindrome(numberToDigits(n, 2))) {
    			sumPal += n;
//    			System.out.println("Found one: " + n + ", " + numberToDigits(n, 2));
    		}
    	}
    	System.out.println("The sum of all numbers < 1,000,000 that are both base 10 and base 2 palindromes is " + sumPal);
    	System.out.println("Elapsed: " + (System.currentTimeMillis() - start) + " ms");
    }
    
    //Converts a decimal number into its digits in any base
    public static ArrayList<Integer> numberToDigits(int num, int base) {
    	ArrayList<Integer> digits = new ArrayList<Integer>();
    	
    	while(num > 0) {
    		digits.add(num % base);
    		num /= base;
    	}
    	
    	return digits;
    }
    
    //Checks for a palindrome of digits in an array list - can represent ANY base
    public static boolean isPalindrome(ArrayList<Integer> digits) {
        //determine if the digits form a palindrome by iterating over the
        //list and checking pairwise digits from the outside in until
        //a NON-EQUAL pair is found (NOT a palindrome); otherwise it is
        //a palindrome
        int numDigits = digits.size();
        for(int i = 0; i < numDigits / 2; i++) {  //if numDigits is odd, middle value doesn't matter anyway
            if(digits.get(i) != digits.get(numDigits - 1 - i))
                return false;  //found a pair of non-equal digits
        }
        return true;  //all pairs of digits were equal
    }    
}
